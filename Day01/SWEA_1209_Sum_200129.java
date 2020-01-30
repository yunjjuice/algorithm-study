/*
 * SWEA 1209 Sum D3
 */

import java.util.Scanner;

public class SWEA1209 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] a = new int[100][100];
		
		for (int testCase = 1; testCase <= 10; testCase++) {
			int t = sc.nextInt();
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					a[i][j] = sc.nextInt();
				}
			}
			
			int sum = 0;
			int ans = Integer.MIN_VALUE;
			
			// 가로
			for (int i = 0; i < a.length; i++) {
				sum = 0;
				for (int j = 0; j < a[i].length; j++) {
					sum += a[i][j];
				}
				ans = Integer.max(sum, ans);
			}
			// 세로
			for (int i = 0; i < a.length; i++) {
				sum = 0;
				for (int j = 0; j < a[i].length; j++) {
					sum += a[j][i];
				}
				ans = Integer.max(sum, ans);
			}
			
			// 대각선
			sum = 0;
			for (int i = 0; i < a.length; i++) {
				sum += a[i][i];
			}
			ans = Integer.max(sum, ans);
			
			// 반대 대각선
			sum = 0;
			for (int i =a.length-1; i >=0; i--) {
				sum += a[i][i];
			}
			ans = Integer.max(sum, ans);
			
			System.out.println("#" + testCase + " " + ans);
		}
	}
}
