package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5721_사탕줍기대회_200711 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine()," ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			if(M==0 && N==0) {
				// 종료조건
				break;
			}
			
			int[][] box = new int[M+1][N+1];
			
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for (int j = 1; j <= N; j++) {
					box[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 각 행별 최대로 가져올 수 있는 사탕의 개수를 메모이제이션배열에 넣음
			int[] memo = new int[M+1];
			for (int i = 1; i <= M; i++) {
				// 한 행의 최대값을 dp배열을 통해 구함
				// dp배열은 현재 인덱스까지 주운 사탕의 최대값을 저장함
				int[] dp = new int[N+1];
				dp[1] = box[i][1];
				for (int j = 2; j <= N; j++) {
					dp[j] = Math.max(dp[j-2] + box[i][j], dp[j-1]);
				}
				memo[i] = dp[N];
			}
			
			// 전체 배열에서의 최댓값을 구하기 위한
			// 행의 인덱스에 따른 최댓값을 구함.
			int[] dp = new int[M+1];
			dp[1] = memo[1];
			for (int i = 2; i <= M; i++) {
				dp[i] = Math.max(dp[i-2] + memo[i], dp[i-1]);
			}
			sb.append(dp[M]).append("\n");
		}
		System.out.println(sb);
	}
}
