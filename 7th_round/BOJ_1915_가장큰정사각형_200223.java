import java.io.*;
import java.util.*;

public class BOJ_1915_가장큰정사각형_200223 {
	static int max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		int[][] dp = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			dp[i][0] = map[i][0];
			if(max < dp[i][0]) max = dp[i][0];
		}
		
		for(int j = 1; j < m; j++) {
			dp[0][j] = map[0][j];
			if(max < dp[0][j]) max = dp[0][j];
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 1; j < m; j++) {
				if(map[i][j] == 0) dp[i][j] = 0;
				else dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
				if(max < dp[i][j]) max = dp[i][j];
			}
		}
		
		System.out.println(max * max);
	}
}
