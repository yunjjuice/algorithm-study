import java.util.LinkedList;
import java.util.Queue;

/*
모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 트럭은 1초에 1만큼 움직이며, 
다리 길이는 bridge_length이고 다리는 무게 weight까지 견딥니다.

최악의 시간 (len+1)*truck개수
큐사용
 */
public class PROG_다리를지나는트럭_200418 {
	public static void main(String[] args) {
		
		
		/*int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = {7,4,5,6};*/
		 
		int bridge_length = 100;
		int weight = 100;
		int[] truck_weights = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
		System.out.println(solution(bridge_length, weight, truck_weights));
	}
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        
        Queue<Integer> queue = new LinkedList<Integer>();
        
        int curWeight = 0;
        
        for (int i = 0; i < truck_weights.length; i++) {
        	while(curWeight+truck_weights[i] > weight) {	// 이번트럭이 못올라가면. 이전 트럭을 빼냄
        		if(queue.size()>=bridge_length) {	// 다리 위 공간 체크
        			System.out.println(queue.size()+" "+curWeight);
        			curWeight -= queue.poll();
        		} else {
        			queue.add(0);	// 공간 add
        			time++;	// 시간 증가
        		}
        	}
			queue.add(truck_weights[i]);	// 트럭 입장
			curWeight+=truck_weights[i];	// 다리위 무게합
			time++;
		}
        
        time += bridge_length;	// 마지막 트럭 도착시간.
        
        return time;
    }
	
}
