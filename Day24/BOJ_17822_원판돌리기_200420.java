import java.io.*;
import java.util.*;

public class BOJ_원판돌리기{
	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	
	static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}//좌표 class.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		for(int i=1; i<=N ; i++) { //1~N , 1~M 까지.
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//draw map.
		int answer = 0;
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			int many = Integer.parseInt(st.nextToken());
			if(direction == 0) many = M-many; //시계방향 --> 반시계방향
			for(int j=1; j<=N; j++) {
				if(j%num == 0) rotate(j, many);
			}//배수인것들 돌리기.
			visited = new boolean[N+1][M+1];
			boolean total_spread = false;
			for(int row=1; row<=N; row++) {
				for(int col=1; col<=M; col++) {
					if(!visited[row][col] && map[row][col] != 0) {
						if(bfs(row,col)) total_spread = true;
					}
				}
			}//bfs돌린다.
			if(!total_spread) {
				ArrayList<Pos> list = new ArrayList<>();
				int sum = 0;
				for(int row=1; row<=N; row++) {
					for(int col=1; col<=M; col++) {
						if(map[row][col]!=0) {
							list.add(new Pos(row,col));
							sum += map[row][col];
						}
					}
				}//다돌면서 남은 값들 총합이랑 그좌표 집어넣기.
				if(list.size()==0) {
					break;
				}
				double avg = (double)sum/list.size();
				for(int j=0; j<list.size(); j++) {
					if(map[list.get(j).x][list.get(j).y] < avg) map[list.get(j).x][list.get(j).y]++;
					else if(map[list.get(j).x][list.get(j).y] > avg) map[list.get(j).x][list.get(j).y]--;
				}//값 바꿔주기.
			}
		}//end inst.
		for(int row=1; row<=N; row++) {
			for(int col=1; col<=M; col++) {
				answer += map[row][col];
			}
		}
		System.out.println(answer);
	}//end main.
	public static void rotate(int num , int cnt) { //시계방향 --> 반시계방향
		int time = 0;
		while(time != cnt) {
			for(int i=0; i<M; i++) {
				map[num][i] = map[num][i+1];
			}
			map[num][M] = map[num][0];
			time++;
		}
	}//end rotate.
	
	public static boolean bfs(int x, int y) {
		visited[x][y] = true;
		Queue<Pos> q = new LinkedList();
		q.add(new Pos(x,y));
		boolean spread = false;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for(int i=0; i<4; i++) {
				int new_x = p.x + direction[i][0];
				int new_y = p.y + direction[i][1];
				if(new_y == M+1) new_y = 1;
				if(new_y == 0) new_y = M;
				if(new_x >=1 && new_x <=N && !visited[new_x][new_y] && map[new_x][new_y] == map[p.x][p.y]) {
					spread = true;
					visited[new_x][new_y] = true;
					q.add(new Pos(new_x,new_y));
				}
			}//4번 다돌고.
			if(spread)map[p.x][p.y] = 0;
		}
		return spread;
	}
	
}//end class.

//for(int k=1; k<=N; k++) {
//for(int j=1; j<=M; j++) {
//	System.out.print(map[k][j] + " ");
//}
//System.out.println("");
//}
