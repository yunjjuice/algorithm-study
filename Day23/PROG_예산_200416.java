/*
 * programmers lv3 예산
 */

public class PROG_예산_200416 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] { 120, 110, 140, 150 }, 485));
	}

	public static int solution(int[] budgets, int M) {
		int answer = 0;
		
		long totalBudget = 0;
		int maxBudget = 0;
		for (int i : budgets) {
			totalBudget += i;
			maxBudget = Math.max(i, maxBudget);
		}
		
		// 예산 총 합이 총 예산보다 적거나 같으면 끝
		if(totalBudget <= M) return maxBudget;
		
		int low = 0;
		int high = maxBudget;
		long maxTotal = Integer.MIN_VALUE;
		
		while(low <= high) {
			int mid = (low + high) / 2;
			long total = calc(budgets, mid); // 예산 상한선이 mid일 때 총합
			if(total == M) {
				return mid;
			} else if (total < M) { // 예산보다 적으면 하한선 높이기
				low = mid + 1;
				if(maxTotal < total) { // 지금까지 합계중에 제일 M에 근접하면서 크면 답을 옮겨둔다.
					maxTotal = total;
					answer = mid;
				}
			} else { // total > M ==> 예산 넘은 것. 상한선 줄이기
				high = mid-1;
			}
		}
		
		return answer;
	}

	private static long calc(int[] budgets, int mid) {
		long totalBudget = 0;
		for (int i : budgets) {
			if(i > mid) {
				i = mid;
			}
			totalBudget += i;
		}
		
		return totalBudget;
	}
}
