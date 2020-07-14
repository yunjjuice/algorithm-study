/*
 * BOJ 17182 우주 탐사선
 * https://www.acmicpc.net/problem/17182
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17182_우주탐사선_200710 {
	static int N, K, ans;
	static int[][] map;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 플로이드-워셜
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(k==i || k==j || i==j) continue;
					if(map[i][j] > map[i][k] + map[k][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
		
		visited = new boolean[N];
		ans = Integer.MAX_VALUE;
		dfs(K, 0, 0); // 시작정점이 K라고 주어졌다
		System.out.println(ans);
	}
	
	static void dfs(int now, int cnt, int sum) { // 시작정점, 방문한 정점 개수, 합
		if(ans < sum) return;
		if(cnt == N) {
			ans = sum;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i, cnt+1, sum+map[now][i]);
				visited[i] = false;
			}
		}
	}
}
