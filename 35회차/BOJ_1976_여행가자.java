package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976_여행가자 {
	static int N,M;
	static int[] parent;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j <= N; j++) {
				// 다른 도시로 갈수 있을 때, i와 j를 union
				if(st.nextToken().equals("1")) {
					union(i,j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine()," ");
		int rootStart = find(Integer.parseInt(st.nextToken()));
		
		boolean flag = true;
		for (int i = 1; i < M; i++) {
			// 같은 부모를 가지고 있으면 연결된 길임.
			if(rootStart!=find(Integer.parseInt(st.nextToken()))) {
				flag = false;
			}
		}
		
		if(flag) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
	
	private static void union(int i, int j) {
		i = find(i);
		j = find(j);
		// i를 j에 합침
		parent[i] = j;
	}
	private static int find(int x) {
		if(x == parent[x]) {
			return x;
		}else {
			// 갱신시키면서
			return parent[x] = find(parent[x]);
		}
	}
}
