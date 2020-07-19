/*
 * BOJ 7453 합이 0인 네 정수
 * https://www.acmicpc.net/problem/7453
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7453_합이0인네정수_200719 {
	static int n;
	static long ans;
	static long[] A, B, C, D, AB, CD;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		A = new long[n];
		B = new long[n];
		C = new long[n];
		D = new long[n];
		AB = new long[n*n];
		CD = new long[n*n];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			A[i] = Long.parseLong(st.nextToken());
			B[i] = Long.parseLong(st.nextToken());
			C[i] = Long.parseLong(st.nextToken());
			D[i] = Long.parseLong(st.nextToken());
		}
		
		// A와 B의 합
		int idx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				AB[idx++] = A[i] + B[j];
			}
		}
		// C와 D의 합
		idx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				CD[idx++] = C[i] + D[j];
			}
		}
		
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		// 투포인터 사용
		int L = 0;
		int R = n*n - 1;
		long ab, cd, sum = 0;
		while(L < n*n && R >= 0) {
			ab = AB[L];
			cd = CD[R];
			sum = ab + cd;
			
			if(sum < 0) {
				L++;
			} else if(sum > 0) {
				R--;
			} else { // sum == 0
				long cntAB = 0;
				long cntCD = 0;
				while(L < n*n && ab == AB[L]) {
					L++;
					cntAB++;
				}
				while(R >= 0 && cd == CD[R]) {
					R--;
					cntCD++;
				}
				ans += cntAB * cntCD;
			}
		}
		
		System.out.println(ans);
	}
}
