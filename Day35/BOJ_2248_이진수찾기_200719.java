/*
 * BOJ 2248 이진수 찾기
 * https://www.acmicpc.net/problem/2248
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2248_이진수찾기_200719 {
	static int N, L;
	static long I;
	static int[][] dp;
	static String ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		I = Long.parseLong(st.nextToken());
		dp = new int[N+1][N+1]; // 개수를 담을 배열
		
		dp[0][0] = 1;
		for (int i = 1; i <= N; i++) {
			dp[i][0] = 1;
			dp[i][i] = 1;
			
			for (int j = 1; j <= i; j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
			}
		}
		
		ans = "";
		solution(N, L, I);
		
		System.out.println(ans);
	}
	
	static void solution(int n, int l, long i) { // 자리수, 1의 개수, 몇번째
		if(n == 0) return; // 모든 자리 수 끝
		if(l == 0) { // 1의 개수가 0개 남았으면 나머지는 다 0으로 채워줌
			for (int j = 0; j < n; j++) {
				ans += "0";
			}
			return;
		}
		
		int sum = 0; // 얘 순서에 따라 0이 붙을지 1이 붙을지 달라지게 된다
		for (int j = 0; j <= l; j++) {
			sum += dp[n-1][j];
		}
		
		if(sum >= i) {
			ans += "0";
			solution(n-1, l, i);
		} else {
			ans += "1";
			solution(n-1, l-1, i-sum);
		}
	}
}