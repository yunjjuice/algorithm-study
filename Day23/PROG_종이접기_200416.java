/*
 * programmers lv3 종이접기
 */

import java.util.Arrays;

public class PROG_종이접기_200416 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(1)));
		System.out.println(Arrays.toString(solution(2)));
		System.out.println(Arrays.toString(solution(3)));
		System.out.println(Arrays.toString(solution(4)));
	}

	public static int[] solution(int n) {
		
		if(n == 1)
			return new int[] {0};
		else if(n == 2)
			return new int[] {0, 0, 1};
//		0
//		001
//		0010011 => 001 0 011
//		001001100011011 => 0010011 0 0011011 => 001 0 011 0 001 1 011
		String[] arr = new String[n+1];
		arr[1] = "0";
		arr[2] = "001";
		for (int i = 3; i <= n; i++) {
			String right = arr[i-1].substring(0, arr[i-1].length()/2) + "1" + arr[i-1].substring(arr[i-1].length()/2+1, arr[i-1].length());
			arr[i] = arr[i-1] + "0" + right;
		}
		
		int[] answer = new int[arr[n].length()];
		for (int i = 0; i < arr[n].length(); i++) {
			answer[i] = arr[n].charAt(i) - '0';
		}
		
		return answer;
	}
}
