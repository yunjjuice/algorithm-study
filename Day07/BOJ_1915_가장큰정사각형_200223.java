/*
 * BOJ 1915 가장 큰 정사각형
 */

import java.util.Scanner;

public class BOJ1915 {
	static int n, m, ans;
	static int[][] map;
	static int[][] check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		check = new int[n][m];
		for (int i = 0; i< n; i++) {
			String str = sc.next();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
				if(map[i][j]==1){
					check[i][j] = map[i][j];
					ans = 1;
				}
//				System.out.println(str.charAt(j));
			}
		}
		
//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if(map[i][j] == 1 && map[i-1][j-1] == 1 && map[i-1][j] == 1 && map[i][j-1] == 1){
						check[i][j] = Math.min(check[i-1][j-1], check[i-1][j]);
						check[i][j] = Math.min(check[i][j], check[i][j-1]);
						check[i][j] += 1;
				}
				ans = Math.max(check[i][j], ans);
			}
		}
		
		System.out.println(ans * ans);
	}
}
