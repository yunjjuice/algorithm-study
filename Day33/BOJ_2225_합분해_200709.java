/*
 * BOJ 2225 합분해
 * https://www.acmicpc.net/problem/2225
 */

import java.util.Scanner;

public class BOJ_2225_합분해_200709 {
	static int N, K;
	static int[][] dp;
	static final int LIMIT = 1000000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		dp = new int[N+1][K+1];
		
		for (int i = 0; i <= N; i++) {
			dp[i][1] = 1;
		}
		
		for (int i = 0; i <= N; i++) {
			for (int j = 2; j <= K; j++) {
				for (int k = 0; k <= i; k++) {
					dp[i][j] += dp[i-k][j-1];
					dp[i][j] %= LIMIT;
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
