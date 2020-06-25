package test2;

import java.util.Arrays;

/*
budgets : 120, 110, 140, 150  
M : 485

이분탐색
1. 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로 배정합니다.
2. 모든 요청이 배정될 수 없는 경우에는 특정한 정수 상한액을 계산하여 그 이상인 예산요청에는 모두 상한액을 배정합니다. 
   상한액 이하의 예산요청에 대해서는 요청한 금액을 그대로 배정합니다. 

 */
public class prog_예산 {
	public static int solution(int[] budgets, int M) {
        int answer = 0;
        Arrays.sort(budgets);
        
        long sum = 0;
        for (int i = 0; i < budgets.length ; i++) {
        	sum += budgets[i];
		}
        
        if(sum <= M) {
        	return budgets[budgets.length-1];	// case 1.
        }
        
        int left = 0, right = budgets[budgets.length-1];
        
        while(left <= right) {
        	int mid = (left+right)/2;
        	
        	sum = 0;
        	for (int i = 0; i < budgets.length; i++) {
				if(budgets[i]<mid) {
					sum += budgets[i];
				} else {
					sum += mid * (budgets.length-i);
					break;
				}
			}
        	
        	// mid 값 기준으로 예산을 배분했을 때 합과 실제 예산을 비교
        	if(sum > M) {	// 합이 더 크면 기준값을 낮춰줘야함
        		right = mid -1;
        	} else {	// 합이 작으면 기준값을 올려줘야함
        		answer = mid>answer?mid:answer;	// 임시 값 저장
        		left = mid + 1;
        	}
        }
        
        return answer;
    }
	public static void main(String[] args) {
		int[] budgets = {120, 110, 140, 150};
		int M = 485;
		System.out.println(solution(budgets, M));
	}
}
