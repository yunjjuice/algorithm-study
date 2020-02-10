import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_게리맨더링 {
	static int[] people;
	static int[][] map;
	static int[] team;
	static int min = Integer.MAX_VALUE;
	static int length;
 	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		length = sc.nextInt();
		people = new int[length]; //구역간 사람 수.
		map = new int[length+1][length+1]; //인접구역 그리기.
		team = new int[length]; //조합만들기.
		for(int i=0; i<length; i++) {
			people[i] = sc.nextInt();
		}
		for(int i=1; i<=length; i++) {
			int col = sc.nextInt();
			for(int j=0; j<col; j++) {
				int temp = sc.nextInt();
				map[i][temp] = 1;
				map[temp][i] = 1;
			}
		}
		for(int i=1; i<=length/2; i++) {
			permutation(i,0,0);
		}
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
 	public static void permutation(int end, int cnt , int idx){
 		if(idx > length) return ;
 		if(cnt == end) {
 			int team_a = 0;
 			int team_b = 0;
 			for(int i=0; i<length; i++) {
 				if(team[i] == 0) team_a = i;
 				else team_b = i;
 			}
 			if(check(team_a) && check(team_b)) {
 				int cnt_a = 0;
 				int cnt_b = 0;
 				for(int i=0; i<length; i++) {
 					if(team[i] == 0) cnt_a += people[i];
 					else cnt_b += people[i];
 				}
 				if(min > Math.abs(cnt_a - cnt_b)) min = Math.abs(cnt_a - cnt_b);
 			}
 			return ;
 		}
 		for(int i=idx; i<length; i++) {
 			if(team[i]==0) {
 				team[i] = 1;
 				permutation(end, cnt+1,i+1);
 				team[i] = 0;
 			}
 		}
 	}//end permutation.
 	public static boolean check(int start) {
 		int team_color = team[start];
 		boolean[] visited = new boolean[length];
 		Queue<Integer> q = new LinkedList();
 		q.add(start+1);
 		while(!q.isEmpty()) {
 			int x = q.peek();
 			q.poll();
 			visited[x-1] = true; //방문 체크.
 			for(int j=1; j<=length; j++) {
 				if(map[x][j] == 1 && !visited[j-1] && team[j-1] == team_color) q.add(j);
 			}
 		}
 		for(int i=1; i<=length; i++) {
 			if(team[i-1] == team_color) {
 				if(!visited[i-1]) {
 					return false;
 				}
 			}
 		}
 		return true;
 	}
}
