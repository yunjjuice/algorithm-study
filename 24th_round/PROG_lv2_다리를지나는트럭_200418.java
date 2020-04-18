package algo;

import java.util.LinkedList;
import java.util.Queue;

public class PROG_lv2_다리를지나는트럭_200418 {
	public static void main(String[] args) {
		int ans1 = solution(2, 10, new int[]{7,4,5,6});
		int ans2 = solution(100, 100, new int[]{10});
		int ans3 = solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10});
		
		System.out.println(ans1 + " " + ans2 + " " + ans3);
		
	}

	static Queue<Integer> q;

	static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		int weight_sum = 0; // 다리위에 있는 트럭의 무게의 합을 저장할 변수
		q = new LinkedList<>();

		int i = 0; // 대기 트럭 배열을 위한 index
		while (true) {
			if (i == truck_weights.length) // 대기하고 있는 트럭이 없다면 빠져나온다
				break;

			if (q.size() == bridge_length) { // 더이상 다리위에 트럭이 올라갈 수 없다면, 맨앞에 트럭을 빼준다
				weight_sum -= q.poll();
			}

			// 다리위의 트럭의 무게의 합 + 다음 건너갈 트럭의 무게가 다리가 견딜 수 있는 무게 이하 라면
			if (weight_sum + truck_weights[i] <= weight) { 
				q.offer(truck_weights[i]);
				weight_sum += truck_weights[i]; // 다리위의 트럭의 무게에 현재 트럭의 무게를 더해준다
				answer++;
				i++;
			} else { // 무게때문에 건널수 없는 상항이면
				q.offer(0); // 임시로 0을 넣어준다 -> 앞의 트럭을 나오게하기 위해
				answer++;
			}
		}

		// 아직 다리위에 남아있는 트럭들이 모두 다리를 건널 수 있도록 다리 길이 만큼 0 을 임시로 추가해준다
		while (q.size() < bridge_length) {
			q.offer(0);
		}

		// 큐에서 0을 제외한 모든 트럭이 빠져나가면 다리건너기 종료!
		while (!q.isEmpty() || weight_sum != 0) {
			weight_sum -= q.poll();
			answer++;
		}

		return answer;
	}
}
