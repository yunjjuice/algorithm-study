/*
 * BOJ 2143 두 배열의 합
 * https://www.acmicpc.net/problem/2143
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2143_두배열의합_200712 {
	static int T, n, m;
	static long ans;
	static int[] A, B;
	static ArrayList<Integer> sumA, sumB; // 각 배열의 부분 합을 저장
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		n = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		m = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine(), " ");
		B = new int[m];
		for (int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		} // 입력 끝
		
		sumA = new ArrayList<>();
		sumB = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int sum = A[i];
			sumA.add(sum);
			for (int j = i+1; j < n; j++) {
				sum += A[j];
				sumA.add(sum);
			}
		}
		for (int i = 0; i < m; i++) {
			int sum = B[i];
			sumB.add(sum);
			for (int j = i+1; j < m; j++) {
				sum += B[j];
				sumB.add(sum);
			}
		}
		Collections.sort(sumA);
		Collections.sort(sumB);
		
		int L = 0; // A배열은 맨 왼쪽(제일 작은 값)부터 조회
		int R = sumB.size()-1; // B배열은 맨 오른쪽(제일 큰 값)부터 조회
		
		while(L<sumA.size() && R>=0) {
			int a = sumA.get(L);
			int b = sumB.get(R);
			if(a + b == T) { // 합이 T와 같으면
				long cntA=0, cntB=0; // a값과 같은 값의 개수와 b값과 같은 값의 개수를 구하여 곱한 뒤 더해준다 
				while(L < sumA.size() && sumA.get(L) == a) {
					cntA++;
					L++;
				}
				while(R >= 0 && sumB.get(R) == b) {
					cntB++;
					R--;
				}
				ans += (cntA * cntB);
			} else if(a + b > T) {
				R--;
			} else {
				L++;
			}
		}
		
		System.out.println(ans);
	}
}
