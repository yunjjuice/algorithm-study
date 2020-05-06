/*
 * programmers lv3 외벽 점검
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class PROG_외벽점검_200505 {
	public static void main(String[] args) {
		System.out.println(solution(12, new int[]{1, 5, 6, 10}, new int[] {1, 2, 3, 4})); // 2
		System.out.println(solution(12, new int[]{1, 3, 4, 9, 10}, new int[] {3, 5, 7})); // 1
		System.out.println(solution(30, new int[]{0, 3, 11, 21}, new int[] {10, 4})); // 2
		System.out.println(solution(50, new int[]{1, 5, 10, 12, 22, 25}, new int[] {4, 3, 2, 1})); // 3
	}
	
	/**
	 * @param n 외벽의 길이
	 * @param weak 취약 지점의 위치
	 * @param dist 각 친구가 한 시간동안 이동할 수 있는 거리
	 * @return 취약 지점 점검을 위해 보내야 하는 친구수의 최소값
	 */
	public static int solution(int n, int[] weak, int[] dist) {
		int answer = 0;
		ArrayList<Integer> list = new ArrayList<>();
		Arrays.sort(dist);

		do {
//			System.out.println("dist : " + Arrays.toString(dist));
			for (int i = 0; i < weak.length; i++) {
				int[] line = new int[weak.length];
				for (int j = 0; j < weak.length; j++) {
					if((i+j) >= weak.length)
						line[j] = weak[(i+j) % weak.length] + n;
					else
						line[j] = weak[(i+j) % weak.length];
				}
//				System.out.println("line : " + Arrays.toString(line));
				int start = 0; // 외벽점검 인덱스
				int end = line.length-1;
				int cnt = 0; // 사람 수
				for (int j = 0; j < dist.length; j++) {
					// 이제 각 친구들을 선택해 나열하는 방법에 대해서 모든 시작 지점에 대해 직선으로 펼친 후 
					// 취약 지점에 배정해본 다음, 그중 가장 적은 친구들을 선택하는 방법을 찾으면 됩니다.
					int d = dist[j];
					while(line[end]-line[start]>d) {
//						System.out.println("end : " + end + ", start : " + start+ " => " + line[end] + ", " + line[start]);
						end--;
					}
					cnt++; // 몇 명의 친구가 돌고 있는지
					if(end == line.length-1 ) { // 친구들 다 돌기 전에 외벽점검 끝
						break;
					}
					start = end+1;
					end = line.length-1;
				}
//				System.out.println("cnt : " + cnt);
//				System.out.println();
				if(cnt > line.length-1) { // 친구들 다 써도 외벽 점검을 못 마쳤다면
					list.add(-1);
				} else {
					list.add(cnt);
				}
			}
		} while (nextPermutation(dist));
		
		Collections.sort(list);
//		System.out.println(list.toString());
		if(list.get(list.size()-1) == -1) { // 끝까지 -1이면 외벽 도는 방법 없는 것
			answer = -1;
		} else {
			Iterator<Integer> it = list.iterator();
			while(it.hasNext()) {
				int tmp = it.next();
				if(tmp != -1) {
					answer = tmp;
					break;
				}
			}
		}

		return answer;
	}
	
	private static boolean nextPermutation(int[] dist) {
		// step1
		int i = dist.length-1;
		while(i>0 && dist[i-1]>=dist[i]) --i;
		if(i == 0) return false;
		
		// step2
		int j = dist.length-1;
		while(dist[i-1] >= dist[j]) --j;
		
		// step3
		int temp = dist[i-1];
		dist[i-1] = dist[j];
		dist[j] = temp;
		
		// step4
		int k = dist.length-1;
		while(i < k) {
			temp = dist[i];
			dist[i] = dist[k];
			dist[k] = temp;
			++i;
			--k;
		}
		
		return true;
	}
}
