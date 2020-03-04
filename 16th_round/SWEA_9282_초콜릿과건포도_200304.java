import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_9282_초콜릿과건포도_200304 {
	static int[][] map;
	static int[][][][] dp;
	static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; ++testcase) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			map = new int[n + 1][m + 1];
			for(int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 1; j <= m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// map의 누적합을 미리 구해줌 (부분합을 계산하기 위해)
					map[i][j] += map[i][j - 1] + map[i - 1][j] - map[i - 1][j - 1];
				}
			} // input
			
			dp = new int[n + 1][m + 1][n + 1][m + 1];
			
			sb.append("#").append(testcase).append(" ").append(go(1, 1, n, m)).append('\n');
		}
		System.out.print(sb);
	}
	
	private static int go(int r, int c, int R, int C) { // r, c 에서 R, C 까지의 구간에서 지급할 건포도의 최소값 return 
		if(R == r && C == c) { // 기저조건 : 1x1에서 0
			return 0;
		}
		
		// 메모이제이션
		if(dp[r][c][R][C] != 0) return dp[r][c][R][C];
		
		min = Integer.MAX_VALUE;
		int sum = map[R][C] - map[r - 1][C] - map[R][c - 1] + map[r - 1][c - 1];
		
		// 가로로 자르는 방법
		for(int i = 1; i <= R - r; i++) {
			// 전체 합 + 위 쪽 잘린 부분 + 아래쪽 잘린 부분
			min = Math.min(min, sum + go(r, c, r + i - 1, C) + go(r + i, c, R, C));
		}
		
		for(int j = 1; j <= C - c; j++) {
			// 전체 합 + 왼 쪽 잘린 부분 + 오른쪽 잘린 부분
			min = Math.min(min, sum + go(r, c, R, c + j - 1) + go(r, c + j, R, C));
		}
		
		dp[r][c][R][C] = min; // 메모이제이션
		return min;
	}
}
