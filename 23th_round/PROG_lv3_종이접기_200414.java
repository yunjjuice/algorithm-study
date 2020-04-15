package algo;

import java.util.Arrays;

public class PROG_lv3_종이접기_200414 {
	public static void main(String[] args) {
		int n = 10;
		int[] ans = solution(n);
		System.out.println(Arrays.toString(ans));
	}
	public static int[] solution(int n) {
		int[] answer = new int[(1 << n) - 1];

		for (int i = 1; i <= n; i++) {
			int tmp = (1 << i) - 2;
			for (int j = tmp; j > tmp / 2; j--) {
				answer[j] = answer[tmp - j] ^ 1;
			}
		}
		return answer;
	}
}
