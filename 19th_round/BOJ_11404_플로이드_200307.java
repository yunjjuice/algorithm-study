package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11404_플로이드_200307 {
	static int[][] map;
	final static int MAX = 100000000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		map = new int[n + 1][n + 1];
		
		for(int i = 1; i < n + 1; i++) Arrays.fill(map[i], MAX);
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			if(map[v1][v2] > cost) {
				map[v1][v2] = cost;
			}
		} // input
		
		// 플로이드와샬 알고리즘
		for(int k = 1; k < n + 1; k++) {
			for(int i = 1; i < n + 1; i++) {
				for(int j = 1; j < n + 1; j++) {
					if(i != j) map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
				}
			}
		}
		
		for(int i = 1; i < n + 1; i++) {
			for(int j = 1; j < n + 1; j++) {
				if(map[i][j] == MAX) map[i][j] = 0;
				sb.append(map[i][j]).append(" ");
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}
