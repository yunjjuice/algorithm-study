/*
 * BOJ 1976 여행 가자
 * https://www.acmicpc.net/problem/1976
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976_여행가자_200718 {
	static int N, M;
	static int[] p, road;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		p = new int[N];
		road = new int[M];
		
		// 집합 만들기
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a == 1) {
					if(findSet(i) != findSet(j)) // 부모가 다르면 합친다
						union(i, j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			road[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		for (int i = 1; i < M; i++) {
			if(findSet(road[0]) != findSet(road[i])) { // 부모가 다르다면 여행 갈 수 없다
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
		
	}
	
	// 두 원소를 합친다
	private static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if(px != py) 
			p[py] = px;
	}

	// 부모를 찾는다
	private static int findSet(int x) {
		if(p[x] == x) return x;
		return p[x] = findSet(p[x]);
	}
}
