/*
 * BOJ 5721 사탕 줍기 대회
 * https://www.acmicpc.net/problem/5721
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_5721_사탕줍기대회_200711 {
	static int M, N;
	static int[][] map;
	static int[] dp1; // i번째 행에서 선택할 수 있는 최대값 -> 각 행마다 쓰인다
	static int[] dp2; // dp1에서 구한 각 행별 최대값을 저장 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		while(M !=0 && N != 0) {
			map = new int[M+1][N+1];
			dp1 = new int[N+1];
			dp2 = new int[M+1];
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
				
				// 각 행별로 얻을 수 있는 최대값을 저장한다
				Arrays.fill(dp1, 0);
				dp1[1] = map[i][1];
				for (int j = 2; j <= N; j++) {
					 // 현재-1까지의 합이랑, 현재-2+현재까지의 합 비교해서 큰 것 저장
					dp1[j] = Math.max(dp1[j-1], dp1[j-2]+map[i][j]); 
				}
				dp2[i] = dp1[N];
			}
						
			for (int i = 2; i <= M; i++) {
				dp2[i] = Math.max(dp2[i-1], dp2[i-2] + dp2[i]);
			}
			
			sb.append(dp2[M]).append('\n');
			
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(sb);
	}
}
