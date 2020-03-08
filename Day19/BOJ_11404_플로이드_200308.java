/*
 * BOJ 11404 플로이드
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11404 {
	static int n, m;
	static StringBuilder sb = new StringBuilder();
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			if(map[a][b] == 0)
				map[a][b] = c;
			if(map[a][b] != 0 && map[a][b] > c) {
				map[a][b] = c;
			}
		}
		
		// 입력 확인
//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		floyd();
		
		// 출력 n개의 정점에서 각 정점에 갈 수 있는 비용 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void floyd() {
		// k 거쳐가는 노드
		for (int k = 0; k < n; k++) {
			// i 출발 노드
			for (int i = 0; i < n; i++) {
				// j 도착 노드
				for (int j = 0; j < n; j++) {
					if(i == j) {
						map[i][j] = 0;
						continue;
					}
					if(map[i][k] == 0 || map[k][j] == 0) continue;
					if(map[i][j] == 0) map[i][j] = map[i][k] + map[k][j];
					map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
				}
			}
		}
	}
}
