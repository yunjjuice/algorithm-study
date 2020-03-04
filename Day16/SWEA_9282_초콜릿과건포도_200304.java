/*
 * SWEA 9282 초콜릿과 건포도 D4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA9282 {
	static int n, m;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[n][m];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 입력 확인
//			for (int i = 0; i < n; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			ans = split(0, 0, n, m, Integer.MAX_VALUE);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int split(int y, int x, int h, int w, int min) {
		if(h == 1 && w == 1) return 0;
		
		int sum = 0;
		for (int i = y; i < y+h; i++) {
			for (int j = x; j < x+w; j++) {
				sum += map[i][j];
			}
		}
		
		// 가로
		for (int i = 1; i < h; i++) {
			int sum1 = split(y, x, i, w, min);
			int sum2 = split(y+i, x, h-i, w, min);
			int tmp = sum + sum1 + sum2;
			min = Math.min(min, tmp);
		}
		
		// 세로
		for (int i = 1; i < w; i++) {
			int sum1 = split(y, x, h, i, min);
			int sum2 = split(y, x+i, h, w-i, min);
			int tmp = sum + sum1 + sum2;
			min = Math.min(min, tmp);
		}
		
		return min;
	}
}
