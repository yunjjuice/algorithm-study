/*
 * programmers lv3 징검다리 건너기 
 */

import java.util.Arrays;

public class PROG_징검다리건너기_200410 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
	}
	
	public static int solution(int[] stones, int k) {
		int answer = 0;
		
		int left = 1;
		int right = 200000000;
		int mid = 0;
		
		// 이분탐색 -> 처음으로 징검다리가 연속으로 0인 개수가 k개 만큼일 때를 찾는다
		while(left <= right) {
			mid = (left + right) / 2;
			
			if(isAnswer(stones, k, mid)) { // 징검다리가 0인 개수가 k보다 더 많을 때 -> 최대값을 줄여준다
				right = mid - 1;
			} else { // 징검다리가 0인 개수가 k보다 더 적거나 같을 때 -> 최소값을 키워준다
				left = mid+1;
				answer = left;
			}
		}
		
		return answer;
	}
	
	public static boolean isAnswer(int[] stones, int k, int mid) {
		int max = 0;
		for (int i = 0; i < stones.length; i++) {
			if(stones[i]-mid <= 0)
				max++;
			else
				max = 0;
			
			if(max >= k)
				return true;
		}
		
		return false;
	}

//	public static int solution(int[] stones, int k) {
//		int answer = 0;
//		
//		boolean flag = true;
//		while(flag) {
//			// 한 명이 건너간다
//			int i = 0; // 현재 니니즈의 위치
//			int j = 0; // 징검돌이 0일 때 k보다 작은지 확인할 변수
//			while(i < stones.length) {
//				if(stones[i] == 0) { // 징검돌이 0이면 건너뜀
//					j++;
//					i++;
//				} else {
//					if(j >= k) { // 징검돌이 0인 개수가 k보다 많아지면 건널 수 없으므로 브레이크
//						flag = false;
//						answer--; // break해도 맨 아래 answer++은 수행되므로 하나 빼준다
//						break;
//					}
//					j = 0;
//					stones[i]--;
//					i++;
//				}
//			}
//			answer++;
//		}
//		
//		return answer;
//	}
}
