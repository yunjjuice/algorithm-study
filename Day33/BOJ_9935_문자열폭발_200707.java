/*
 * BOJ 9935 문자열 폭발
 * https://www.acmicpc.net/problem/9935
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9935_문자열폭발_200707 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); 
		
		String str = br.readLine().trim();
		String bomb = br.readLine().trim();
		int length = bomb.length();
		int index = 0;
		char[] array = new char[str.length()];
		boolean flag = false;
		
		for (int i = 0; i < str.length(); i++) {
			array[index++] = str.charAt(i);
			
			if(array[index-1] == bomb.charAt(length-1)) { // 마지막 글자랑 같으면 폭탄문자열길이만큼 문자열을 비교한다
				if(index - length < 0) continue;
				flag = true;
				for (int j = 0; j < length; j++) {
					if(array[index - j - 1] != bomb.charAt(length - j - 1)) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					index -= length;
				} 
			}
		}
		
		if(index == 0) {
			sb.append("FRULA");
		} else {
			for (int i = 0; i < index; i++) {
				sb.append(array[i]);
			}
		}
		
		System.out.println(sb);		
	}
}
