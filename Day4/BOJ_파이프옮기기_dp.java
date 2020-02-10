import java.util.Scanner;

public class BOJ_파이프옮기기_dp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map = new int[N+1][N+1];
		int[][][] dp = new int[3][N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] = sc.nextInt();
			}
		}//draw map.
		
		dp[0][1][2] = 1; // 처음 스타트.
		dp[1][1][2] = 0; // 처음 스타트.
		dp[2][1][2] = 0; // 처음 스타트.
		
		for(int i=1; i<=N; i++) {
			for(int j=2; j<=N; j++) {
				if(j+1<= N && map[i][j+1] == 0) dp[0][i][j+1] = dp[0][i][j] + dp[2][i][j];
				if(i+1<= N && map[i+1][j] == 0) dp[1][i+1][j] = dp[1][i][j] + dp[2][i][j];
				if(i+1<=N && j+1<=N && map[i+1][j+1] == 0 && map[i+1][j] == 0 && map[i][j+1] == 0) dp[2][i+1][j+1] = dp[0][i][j] + dp[1][i][j] + dp[2][i][j];
			}
		}
		
		int sum = dp[0][N][N] + dp[1][N][N] + dp[2][N][N];
		System.out.println(sum);
		
		
	}
}
