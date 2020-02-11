import java.util.Arrays;
import java.util.Scanner;

public class BOJ_치킨배달 {
	static int ans = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] home = new int[100][2];
	static int[][] chicken_pos = new int[13][2];
	static int chicken_cnt = 0;
	static int home_cnt = 0;
	static int N;
	static int M;
	static int[] combination;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) {
					home[home_cnt][0] = i;
					home[home_cnt][1] = j;
					home_cnt++;
				}
				else if(map[i][j] == 2) {
					chicken_pos[chicken_cnt][0] = i;
					chicken_pos[chicken_cnt][1] = j;
					chicken_cnt++;
				}
			}
		}
		combination = new int[chicken_cnt];
		dfs(0, 0);
		System.out.println(ans);
	}//end main.
	
	public static void dfs(int idx, int cnt) {
		if(cnt == M) {
			distance();
			return ;
		}
		if(idx == chicken_cnt) return ;
		
		combination[idx] = 1;
		dfs(idx+1, cnt+1);
		combination[idx] = 0;
		dfs(idx+1, cnt);
		
	}//end dfs.
	public static void distance() {
		int temp_sum = 0;
		for(int i=0; i<home_cnt; i++) {
			int temp = 0;
			int min = Integer.MAX_VALUE;
			//home[i][0] , home[i][1]
			for(int j=0; j<chicken_cnt; j++) {
				if(combination[j] == 1) {
					temp = Math.abs(home[i][0] - chicken_pos[j][0]) + Math.abs(home[i][1] - chicken_pos[j][1]);
					if(temp < min) min = temp;
				}
			}
			temp_sum += min;
		}
		if(temp_sum < ans) ans = temp_sum;
	}//end distance;
}
