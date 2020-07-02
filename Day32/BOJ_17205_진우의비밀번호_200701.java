/*
 * BOJ 17205 진우의 비밀번호
 * https://www.acmicpc.net/problem/17205
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17205_진우의비밀번호_200701 {
	static int N;
	static String pw;
	static long ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		pw = br.readLine().trim();
		
		findPW(0);
		
		System.out.println(ans);
	}
	
	// 글자 앞부분부터 순서대로 맞춰본다
	static void findPW(int idx) {
		if(idx == pw.length()) return;
		
		char ch = pw.charAt(idx);
		for (int i = 0; i < 26; i++) {
			char alpha = (char)('a' + i);
			if(ch == alpha) { // 맞는 글자를 찾으면 멈추고 다음 자리로 넘어감
				ans++;
				break;
			}
			// 맞는 글자가 없으면 그 글자부터 시작해서 N번째 자리까지 나올 수 있는 수의 합을 더해준다
			// N-idx까지인 이유 : 중간에 있는 자리의 글자일 때는 그 중간부터 끝자리까지의 경우의 수를 더해주면 됨
			for (int j = 0; j < N-idx; j++) {
				ans += Math.pow(26, j);
			}
		}
		findPW(idx+1);
	}
}
