/*
 * programmers lv1 다트 게임
 */

public class PROG_다트게임_200411 {
	public static void main(String[] args) {
		System.out.println(solution("1S2D*3T"));
		System.out.println(solution("1D2S#10S"));
		System.out.println(solution("1D2S0T"));
		System.out.println(solution("1S*2T*3S"));
		System.out.println(solution("1D#2S*3S"));
		System.out.println(solution("1T2D3D#"));
		System.out.println(solution("1D2S3T*"));
	}

	public static int solution(String dartResult) {
		int answer = 0;
		int[] score = new int[3]; // 1,2,3차 점수 저장
		
		int index = 0;
		for (int i = 0; i < 3; i++) {
			while(true) {
				int num = 0;
				char bonus = '0';
				char option = '0';
				char tmp = dartResult.charAt(index++);
				if(dartResult.charAt(index) == '0') { // 10인지 아닌지
					num = 10;
					index++;
				} else {
					num = tmp - '0';
				}
				
				bonus = dartResult.charAt(index++);
				
				if(bonus == 'S') {
					score[i] = (int) Math.pow(num, 1);
				} else if(bonus == 'D') {
					score[i] = (int) Math.pow(num, 2);
				} else if(bonus == 'T') {
					score[i] = (int) Math.pow(num, 3);
				}
				
				if(index == dartResult.length())
					break;
				
				option = dartResult.charAt(index);
				if(option == '*') {
					if(i == 0) {
						score[i] *= 2;
					} else {
						score[i-1] *= 2;
						score[i] *= 2;
					}
					index++;
				} else if(option == '#') {
					score[i] = -score[i];
					index++;
				} else { // 다음이 숫자면 n회차 끝
					break;
				}
				
				break;
			}
		}
		
		for (int i : score) {
			answer += i;
		}
		
		return answer;
	}
}
