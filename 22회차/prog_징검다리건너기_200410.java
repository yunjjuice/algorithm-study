/**
 * stone의 값 2억
 * stone의 개수 1~20만
 * 
 * 무조건 밟아야 하는 돌 중 값이 가장 작은 값
 * : 마지막 값부터 읽어오면서 k범위 중 가장 큰값을 선택 그 값부터 k범위 반복
 * @author 김경재
 *
 */
public class prog_징검다리_건너기 {
	public static void main(String[] args) {
		int[] stones = {2,4,5,3,2,1,4,2,5,1};
		System.out.println(solution(stones, 3));
	}
	public static int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        
        int i = -1;	// 징검다리 전 위치
        int j = 1;
        loop : while(true){
        	int max = -1;	// i~ i+k 범위중 가장 큰 값
        	int addIndex = 0;
        	for (j = 1; j <= k; j++) {
        		if(i+j>=stones.length){
        			break loop;
        		}
				if(stones[i+j]>max){
					max = stones[i+j];
					addIndex = j;
					//System.out.println(stones[i+j] + "," + max +  "," + addIndex);
				}
			}
        	i += addIndex;	// max index로 위치를 바꿔줌
        	//System.out.println(max);
        	if(max < answer)
        		answer = max;	// 무조건 밟아야하는 돌중 가장 작은값을 찾음
        }
        
        return answer;
    }
}
