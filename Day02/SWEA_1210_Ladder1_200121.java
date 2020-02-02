/*
 * SWEA 1210 Ladder1 D4
 */

import java.util.Scanner;

public class SWEA1210 {

	public static void main(String[] args) {
		
		// 사다리 1 없는 곳 0 목표 2
		Scanner sc = new Scanner(System.in);
		
		for (int testCase = 0; testCase < 10; testCase++) {
			int testNum = sc.nextInt();
			int[][] arr = new int[100][100];
			int destCol = 0, destRow = 0; // 목적지 좌표
			// 입력 받기
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					arr[i][j] = sc.nextInt();
					if(arr[i][j] == 2) {
						destCol = i;
						destRow = j;
					}
				}
			}
			
			int curCol = destCol;
			int curRow = destRow;
			int result = -1;
			
			while(curCol > 0) {
				if(curRow-1 >=0 && curRow-1 < arr[curCol].length && arr[curCol][curRow-1] == 1) {
					arr[curCol][curRow] = 0;
					curRow -= 1; 	
				}
				else if(curRow+1 >=0 && curRow+1 < arr[curCol].length && arr[curCol][curRow+1] == 1) {
					arr[curCol][curRow] = 0;
					curRow += 1;
				}
				else if(curCol-1 >= 0 && curCol-1 < arr.length && arr[curCol-1][curRow] == 1) {
					curCol -= 1;
				}
			}
			
			result = curRow;
			
			System.out.println("#" + testNum + " " + result);			
		}
	}
}