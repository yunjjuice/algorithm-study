/*
 * BOJ 5014 스타트링크
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5014 {
	static int F; // 총 F층
	static int G; // 스타트링크 위치
	static int S; // 현재 강호의 위치
	static int U; // 버튼 한 번에 위로 갈 수 있는 층 수
	static int D; // 버튼 한 번에 아래로 갈 수 있는 층 수
	static int[] floor; // 층 별로 버튼 횟수를 적어넣으면 될 듯?
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
//		System.out.printf("%d %d %d %d %d", F, G, S, U, D);
		
//		int ans = BFS(S);
//		if(ans == 0){
//			System.out.println("use the stairs");
//		} else {
//			System.out.println(ans);
//		}
		BFS(S);
		if(floor[G] == 0 && S!=G){
			System.out.println("use the stairs");
		} else {
			System.out.println(floor[G]);
		}
	}
	
	public static void BFS(int cur) { // 현재 층수
		int[] dir = {U, -D};
		floor = new int[F+1];
		visited = new boolean[F+1];
//		Arrays.fill(floor, -1);
		
		Queue<Integer> q = new LinkedList<>();
		q.add(cur);
		visited[cur] = true;
//		floor[cur] = 0;
		
		while(!q.isEmpty()) {
			int current = q.poll();
//			System.out.println("current " + current);
			for (int i = 0; i < dir.length; i++) {
				int next = current + dir[i];
				if(next >= 1 && next <= F && !visited[next]) { // 해당 건물 내에 있다
//					System.out.println("next " + next);
					q.add(next);
					floor[next] = floor[current] + 1;
//					System.out.println("floor["+next+"] " + floor[next]);
					visited[next] = true;
				}
			}
		}
		
		
//		for (int i = 0; i < floor.length; i++) {
//			System.out.println(i+"층 : "+floor[i]);
//		}
//		if(floor[G] != 0){
//			return floor[G];
//		} else {
//			return -1;
//		}
	}
}
