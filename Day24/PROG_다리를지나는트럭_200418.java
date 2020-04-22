/*
 * programmers lv2 다리를 지나는 트럭
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class PROG_다리를지나는트럭_200418 {
	public static void main(String[] args) {
		System.out.println(solution(2, 10, new int[] {7, 4, 5, 6}));
		System.out.println(solution(100, 100, new int[] {100}));
		System.out.println(solution(100, 100, new int[] {10,10,10,10,10,10,10,10,10,10}));
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		
		int index = 0;
		int curWeight = 0; // 다리 위에 있는 트럭 무게 계산
		Queue<Truck> q = new LinkedList<Truck>(); // 다리를 건너고 있는 트럭들 담을 큐
		int time = 0;
		
		while(index < truck_weights.length) {
			time++;
			// 움직인다
			if(q.size() > 0) {
				Iterator<Truck> it = q.iterator();
				while(it.hasNext()) {
					it.next().length++;
				}
			}

			// 도착한 트럭 빼준다
			if(q.size() > 0) {
				Truck tmp = q.peek(); // 맨 앞에 있는 트럭의 위치만 보면 된다
				if(tmp.length == bridge_length+1) {
					curWeight -= tmp.weight;
					q.poll();
				}
			}
			
			// 트럭이 더 올라온다
			if(curWeight + truck_weights[index] <= weight) { // 현재 무게가 다리가 버틸 수 있는 무게보다 적으면 트럭 더 올림
				q.offer(new Truck(1, truck_weights[index]));
				curWeight += truck_weights[index];
				index++;
			}
			
		}
		
		answer = time + bridge_length;
		return answer;
	}
	
	static class Truck {
		int length; // 트럭이 얼마나 이동했는지
		int weight; // 트럭의 무게
		public Truck (int time, int weight) {
			this.length = time;
			this.weight = weight;
		}
	}
}
