/*
 * BOJ 2096 내려가기
 * https://www.acmicpc.net/problem/2096
 */

import java.util.Scanner;

public class BOJ_2096_내려가기_200701 {
	static int N;
	static int[][] map;
	static int[][] dp;
	static int min, max;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][3];
		dp = new int[N][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dp[0][0] = map[0][0];
		dp[0][1] = map[0][1];
		dp[0][2] = map[0][2];
		
		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]) + map[i][0]; // 왼쪽 -> 왼쪽,가운데
			dp[i][1] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2])) + map[i][1]; // 가운데 -> 왼쪽, 가운데, 오른쪽
			dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]) + map[i][2];// 오른쪽 -> 가운데, 오른쪽
		}
		
		max = Math.max(dp[N-1][0], Math.max(dp[N-1][1], dp[N-1][2]));

		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1]) + map[i][0]; // 왼쪽 -> 왼쪽,가운데
			dp[i][1] = Math.min(dp[i-1][0], Math.min(dp[i-1][1], dp[i-1][2])) + map[i][1]; // 가운데 -> 왼쪽, 가운데, 오른쪽
			dp[i][2] = Math.min(dp[i-1][1], dp[i-1][2]) + map[i][2];// 오른쪽 -> 가운데, 오른쪽
		}
		
		min = Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));
		
		System.out.println(max + " " + min);
	}
}
