import java.util.ArrayList;

/**
 * 점수|보너스|[옵션]
 * 점수는 0에서 10 사이의 정수
 * 보너스는 S, D, T 중 하나
 * - Single(S), Double(D), Triple(T) 영역이 존재하고 각 영역 당첨 시 점수에서 1제곱, 2제곱, 3제곱
 * 옵선은 *이나 # 중 하나이며, 없을 수도 있다
 * - 스타상(*) 당첨 시 해당 점수와 바로 전에 얻은 점수를 각 2배로 만든다. 아차상(#) 당첨 시 해당 점수는 마이너스
 * @author 김경재
 *
 */
/*
1S2D*3T
1D2S#10S
1D2S0T
1S*2T*3S
1D#2S*3S
1T2D3D#
1D2S3T*
 */
public class prog_다트게임_200411 {
	public static int solution(String dartResult) {
		int answer = 0;
		
		int len = dartResult.length();
		ArrayList<Integer> valList = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			int size = valList.size() - 1;	// 리스트 마지막 값 index
			switch (dartResult.charAt(i)) {
			// 숫자
			case '0':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				valList.add(dartResult.charAt(i)-'0');
				break;
			case '1':
				if(dartResult.charAt(i+1) == '0'){	//10
					valList.add(10);
					i++;
				} else{	//1
					valList.add(1);
				}
				break;
				
			// 보너스
			case 'S':
				
				break;
			case 'D':
				valList.set(size, (int) Math.pow(valList.get(size), 2));	//2제곱
				break;
			case 'T':
				valList.set(size, (int) Math.pow(valList.get(size), 3));	//3제곱
				break;
				
			//옵션
			case '*':
				valList.set(size, valList.get(size)*2);	// 2배
				if(valList.size() > 1){	// 바로 앞에 얻은 점수 2배
					valList.set(size-1, valList.get(size-1)*2);
				}
				break;
			case '#':
				valList.set(size, valList.get(size)*(-1));	// 마이너스 점수
				break;
				
			default:
				break;
			}	// end of switch
		}	// end of for string
		
		for (int i = 0; i < valList.size(); i++) {
			answer += valList.get(i);
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		System.out.println(solution("1S2D*3T"));
	}
	
}
