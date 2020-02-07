/*
 * BOJ 17135 캐슬 디펜스
 */

import java.util.Arrays;
import java.util.Scanner;

public class BOJ17135 {
	static int N, M, D;
	static int[][] map;
	static boolean[] used; // 궁수 위치 놓을 때 쓸 것
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 행
		M = sc.nextInt(); // 열
		D = sc.nextInt(); // 궁수의 공격 거리 제한
		map = new int[N+1][M];
		for (int i = 0; i < map.length-1; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = sc.nextInt(); // 0은 빈 칸, 1은 적
			}
		}
		used = new boolean[M]; 
//		for (int i = 0; i < map[N].length; i++) {
//			map[N][i] = 2; // 성
//		}
		
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		// 궁수 3명을 배치한다
		pick(0, 0);
		// 3명 배치 완료되면 게임 시작
		// 1턴 - 궁수가 공격. 공격받은 적은 사라짐
		// 		적이 아래로 한 칸 이동. 성에 오거나 격자판 벗어나면 그 적은 사라짐
		// 모든 적이 제외되면 게임 끝
		// 궁수의 공격으로 제거할 수 있는 적의 최대 수 출력
	}
	
	public static void pick(int idx, int cnt) { // idx : 배치할 인덱스, cnt : 배치한 개수-> 궁수는 총 세 명
		if(cnt == 3) { // 궁수는 세 명이니까
			for (int i = 0; i < M; i++) {
				if(used[i]) {
					System.out.print(i); // 세 명이 놓여질 수 있는 위치
					// 출력말고 이 위치에 있는 애들로 게임 시작
				}
			}
			System.out.println();
		}
		for (int i = idx; i < M; i++) {
			if(!used[i]) {
				used[i] = true;
				pick(i, cnt+1);
				used[i] = false;
			}
		}
	}
}
