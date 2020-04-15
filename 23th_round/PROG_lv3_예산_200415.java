package algo;

import java.util.Arrays;

public class PROG_lv3_예산_200415 {
	public static void main(String[] args) {
		int[] budgets = {120, 110, 140, 150};
		int M = 485;
		int ans = solution(budgets, M);
		System.out.println(ans);
	}

	public static int solution(int[] budgets, int M) {
		int answer = 0;
		Arrays.sort(budgets);

		int left = 0;
		int right = budgets[budgets.length - 1];
		while (left <= right) {
			int mid = (left + right) / 2;
			int res = check(budgets, mid, M);
			if (res == 1) {
				left = mid + 1;
			} else if (res == -1) {
				right = mid - 1;
			} else {
				right = mid;
				break;
			}
		}
		answer = right;

		return answer;
	}

	public static int check(int[] budgets, int mid, int M) {
		int sum = 0;
		for (int i = 0; i < budgets.length; i++) {
			if (budgets[i] <= mid) {
				sum += budgets[i];
			} else {
				sum += (mid * (budgets.length - i));
				break;
			}
		}
		if (sum > M)
			return -1;
		else if (sum < M)
			return 1;
		else
			return 0;
	}
}
