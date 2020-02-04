/*
 * SWEA 1211 Ladder2 D4
 */

import java.util.Scanner;

public class SWEA1211 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int testCase = 1; testCase <=10; testCase++) {
			int tc = sc.nextInt();
			int[][] a = new int[100][100]; // 사다리
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					a[i][j] = sc.nextInt();
				}
			}
			
			int[] d = new int[100]; // 각 시작점 별로 이동 거리를 저장할 배열
			
			for (int row = 0; row < a.length; row++) {
				int[][] visit = new int[100][100];
				int startCol = 0;
				int startRow = row;
				if(a[startCol][startRow] == 1) { // 사다리가 연결되어 있으면 시작
					int curCol = startCol;
					int curRow = startRow;
					visit[startCol][startRow] = 1;
					// 좌우에 길이 있는지 탐색
					// 좌우에 길이 없을 때까지 내려간다
					while(curCol < a.length-1){
						if(curRow-1 >= 0 && curRow-1 < a.length && a[curCol][curRow-1] == 1 && visit[curCol][curRow-1]==0){ // 좌
							visit[curCol][curRow-1] = 1;
							curRow -= 1;
							d[startRow]++;
						}
						else if(curRow+1 >= 0 && curRow+1 < a.length && a[curCol][curRow+1] == 1 && visit[curCol][curRow+1]==0) { // 우
							visit[curCol][curRow+1] = 1;
							curRow += 1;
							d[startRow]++;
						}
						else if(curCol+1 >= 0 && curCol+1 < a.length && a[curCol+1][curRow] == 1 && visit[curCol+1][curRow]==0) { // 아래
							visit[curCol+1][curRow] = 1;
							curCol += 1;
							d[startRow]++;
						}
					} // end while
				} // end if
			} // end for
			
			// 가장 짧은 거리를 가진 것 찾기
			int min = Integer.MAX_VALUE;
			int ans = 0;
			for (int i = d.length-1; i >=0; i--) { // 복수 개인 경우 가장 큰 x 좌표를 반환하라고 했으니까
				if(min > d[i] && d[i]!=0) {
					min = d[i];
					ans = i;
				}
			}
			
			System.out.println("#" + testCase + " " + ans);
		} // end testCase
	} // end main
}
