package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17825_주사위윷놀이_200419 {
	static class Pos { // map[loc][num]
		int num, loc;
		Pos(int num, int loc) {
			this.num = num;
			this.loc = loc;
		}
	}
	static int[] arr = new int[10]; // 주어진 dice의 눈을 저장할 배열
	static Pos[] dices = new Pos[4]; // 4개의 말이 몇번 위치에 있는지 저장할 배열
	static int[] order = new int[10]; // 어떤 말을 이동할지 순서대로 저장할 배열
	static int[][] map = new int[4][]; // 윷놀이 판을 2차원 배열로 전환
	static int[][] mal = new int[4][]; // 윷놀이 판에 다른 말이 있는지 없는지 판단할 배열
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i = 0; i < 4; i++) dices[i] = new Pos(0, 0);
		
		for(int i = 0; i < 10; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < 4; i++) dices[i] = new Pos(0, 0);
		// 윷놀이판 저장
		map[0] = new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40};
		map[1] = new int[]{0, 2, 4, 6, 8, 10, 13, 16, 19, 25, 30, 35, 40};
		map[2] = new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 25, 30, 35, 40};
		map[3] = new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 28, 27, 26, 25, 30, 35, 40};

		go(0); // 시작

		System.out.println(ans);
	}
	
	public static void go(int x) { // 말 선택 순서 permutation
		if(x == 10) {
			// 4개의 말 위치 초기화
			dices[0].num = 0; dices[0].loc = 0;
			dices[1].num = 0; dices[1].loc = 0;
			dices[2].num = 0; dices[2].loc = 0;
			dices[3].num = 0; dices[3].loc = 0;
			// 윷놀이 판 초기화
			mal[0] = new int[21];
			mal[1] = new int[13];
			mal[2] = new int[17];
			mal[3] = new int[23];
			maxVal(); // 최대값을 구하러 가자
			return;
		}
 		for(int i = 0; i < 4; i++) {
			order[x] = i;
			go(x + 1);
		}
	}

	public static void maxVal() {
		int sum = 0;
		for(int i = 0; i < order.length; i++) { // 말을 고른 순서대로 탐색
			int pick = order[i]; // 몇번째 말을 움직이는가
			int road = dices[pick].loc; // 말이 현재 어느 길을 달리고 있는가
			// 윷놀이 판을 벗어난 말을 선택하면 return
			if(road == 0 && dices[pick].num >= 21) return;
			if(road == 1 && dices[pick].num >= 13) return;
			if(road == 2 && dices[pick].num >= 17) return;
			if(road == 3 && dices[pick].num >= 23) return;
			mal[road][dices[pick].num] = 0; // 움직이기 전 말의 위치 초기화
			int pos = dices[pick].num + arr[i]; // 말이 옮긴 위치
			
			// 말이 교차점에 도달했을때, 말이 달리는 길을 최신화
			if(road == 0) {
				if(pos == 5) {
					road = 1;
				} else if(pos == 10) {
					road = 2;
				} else if(pos == 15) {
					road = 3;
				}
			}
			
			// 말의 위치 갱신
			dices[pick].loc = road;
			dices[pick].num += arr[i];
			
			// pos 범위 확인 -> 윷놀이 판을 벗어난다면 더할 점수가 없음
			if(road == 0 && pos >= 21) continue;
			if(road == 1 && pos >= 13) continue;
			if(road == 2 && pos >= 17) continue;
			if(road == 3 && pos >= 23) continue;
			
			// 말이 도달할 위치에 말이 존재하는지 확인 -> 있으면 return
			if(mal[road][pos] != 0) {
				return;
			}
			
			// 40점을 획득할 수 있는 칸은 모든 길로 도달할 수 있음 -> 모든 길에 말이 존재하는지 확인
			if((road == 0 && pos == 20) || (road == 1 && pos == 12) || (road == 2 && pos == 16) || (road == 3 && pos == 22)) {
				for(int j = 0; j < 4; j++)
					if(mal[j][mal[j].length - 1] == 1) return;
			}
			
			// 25, 30, 35 점을 획득할 수 있는 칸은 1번 2번 3번길이 도달할 수 있음 -> 각 길에 말이 존재하는지 확인
			if(road == 1 && pos >= 9) {
				if(mal[2][pos + 4] != 0) return;
				if(mal[3][pos + 10] != 0) return;
			}
			
			if(road == 2 && pos >= 13) {
				if(mal[1][pos - 4] != 0) return;
				if(mal[3][pos + 6] != 0) return;
			}
			
			if(road == 3 && pos >= 19) {
				if(mal[1][pos - 10] != 0) return;
				if(mal[2][pos - 6] != 0) return;
			}
			
			mal[road][pos] = 1; // 말 위치 이동
			
			sum += map[road][pos]; // 점수 갱신
		}
		
		if(ans < sum) ans = sum; // 최대점수 갱신
	}
}
