package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2248_이진수찾기 {
	static long[][] dp;
	static String result = "";
	static int N,L;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		long I = Long.parseLong(st.nextToken());
		
		dp = new long[L+1][N+1];
		
		// 첫줄.
		Arrays.fill(dp[0], 1);
		// 둘째줄부터 개수
		for (int j = 1; j <= L; j++) {
			for (int k = 0; k < j; k++) {
				// ex) 3자리 이진수에서 4개이하 1인 이진수는 3개이하 1인 이진수의 개수와 같음.
				dp[j][k] = dp[j-1][k];
			}
			for (int k = j; k <= N; k++) {
				dp[j][k] = dp[j-1][k-1] + dp[j][k-1];
			}
		}
		
		makeBinary(N,L,I);
		
		System.out.println(result);
	}
	// 이진수 만들기
	private static void makeBinary(int n, int l, long i) {
		if(l==0) {
			if(result.length()<N) {
				//0행까지 비교해줌.
				for (int j = 0; j < n; j++) {
					result+="0";
				}
			}
			return;
		}
		if(n==0) {
			if(result.length()<N) {
				//0행까지 비교해줌.
				for (int j = 0; j < l; j++) {
					result+="0";
				}
			}
			return;
		}
		if(dp[l][n-1]<i) {
			// i가 큰경우 1을 붙여줌
			result += "1";
			// dp[l-1][n-1]이 선택되었음.
			makeBinary(n-1, l-1, i-dp[l][n-1]);
		} else {
			// i가 작은경우 0을 붙여줌
			result += "0";
			// dp[l][n-1]이 선택되었음.
			makeBinary(n-1, l, i);
		}
	}
}
