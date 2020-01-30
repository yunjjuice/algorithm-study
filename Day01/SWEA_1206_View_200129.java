/*
 * SWEA 1206 View D3
 */

import java.util.Arrays;
import java.util.Scanner;

public class SWEA1206 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int testCase = 1; testCase <= 10; testCase++) {
			int B = sc.nextInt(); // 빌딩 개수
			int[][] map = new int[256][B]; // 빌딩 높이 저장
			for (int row = 0; row < B; row++) {
				int b = sc.nextInt();
				for (int col = 255; col > 255-b; col--) {
					map[col][row] = 1;
				}
			}
			
//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			int ans = 0;
			// 양쪽 거리 2 이상의 공간이 확보될 때 조망권이 확보된다.
			// 조망권이 확보된 세대 수 출력
			for (int row = 2; row < B-2; row++) { // 양 끝에 2개는 없음
				for (int col = 0; col < 256; col++) {
					if(map[col][row] == 1){
						if(map[col][row-2]==0 && map[col][row-1]==0 && map[col][row+1]==0 && map[col][row+2]==0)
							ans++;
					}
				}
			}

			System.out.println("#" + testCase + " " + ans);
		}
	}
}