package algo;

public class PROG_lv1_다트게임_200411 {
	public static void main(String[] args) {
		String dartResult = "1D2S#10S";
		System.out.println(solution(dartResult));
	}

	static public int solution(String dartResult) {
		int answer = 0;
		int[] score = new int[3];

		int cnt = 0;
		int index = -1;
		for (int i = 0; i < dartResult.length(); i++) {
			char ch = dartResult.charAt(i);
			if (ch == 'S') {
				int num = Integer.parseInt(dartResult.substring(index + 1, i)); // 바로 앞에있는 숫자 추출
				score[cnt] += num; // score 올려줌
				cnt++; // 다음 순서로 이동
				index = i; // 현재 인덱스 저장 -> 바로 앞에있는 숫자를 추출하기 위한 과정
			} else if (ch == 'D') {
				// 위와 동일
				int num = Integer.parseInt(dartResult.substring(index + 1, i));
				score[cnt] += num * num;
				cnt++;
				index = i;
			} else if (ch == 'T') {
				// 위와 동일
				int num = Integer.parseInt(dartResult.substring(index + 1, i));
				score[cnt] += num * num * num;
				cnt++;
				index = i;
			} else if (ch == '*') {
				score[cnt - 1] *= 2; // 현재순서 스코어 * 2
				if (cnt != 1)
					score[cnt - 2] *= 2; // 처음순서가 아니면 바로 전 순서 스코어 * 2
				index = i; // 현재 인덱스 저장 -> 바로 앞에있는 숫자를 추출하기 위한 과정
			} else if (ch == '#') {
				score[cnt - 1] *= -1; // 현재순서 스코어 * -1
				index = i; // 현재 인덱스 저장 -> 바로 앞에있는 숫자를 추출하기 위한 과정
			}
		}

		for (int i = 0; i < 3; i++)
			answer += score[i];
		return answer;
	}
}
