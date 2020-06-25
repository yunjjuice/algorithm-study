/*
 * BOJ 11967 불켜기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11967 {
	static int N, M;
	static ArrayList<Pos>[][] map; // 어느 방의 불을 켤 수 있는지 저장
	static boolean[][] room; // 불이 켜져있는지
	static boolean[][][] visited; // 방문확인
	static int light; // 불 켜진 방의 개수
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1][N+1];
		room = new boolean[N+1][N+1];
		visited = new boolean[N+1][N+1][10000];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(map[x][y] == null) {
				map[x][y] = new ArrayList<Pos>();
			}
			map[x][y].add(new Pos(a, b));
		}
		
		// 입력 확인
//		for (int i = 0; i <= N; i++) {
//			for (int j = 0; j <= N; j++) {
//				System.out.println("i "+i +", j "+j+", "+map[i][j]);
//			}
//		}
		
		light = 1;
		visited[1][1][light] = true;
		room[1][1] = true;
		Queue<Pos> q = new LinkedList<Pos>();
		q.offer(new Pos(1, 1));
		while(!q.isEmpty()) {
			// 현재 위치에서 불을 킬 수 있는 장소들 불을 킨다
			Pos cur = q.poll();
			if(map[cur.x][cur.y] != null) { // 불 켤 수 있는 스위치가 존재한다면
				for (int i = 0; i < map[cur.x][cur.y].size(); i++) {
					Pos next = map[cur.x][cur.y].get(i);
					if(room[next.x][next.y] == false) {
						room[next.x][next.y] = true; // 불을 켜주고
						light++; // 불켜진 방의 개수를 증가시킨다
					}
				}
			}
			
			// 불이 켜져있는 곳으로 상하좌우 이동한다
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];
				if(isIn(nx, ny) && !visited[nx][ny][light] && room[nx][ny]) { // 범위 내에 있고, 방문한 적 없고, 불이 켜져있다면 이동
					visited[nx][ny][light] = true;
					q.offer(new Pos(nx, ny));
				}
			}
		}
		
		System.out.println(light);
	}
	
	public static boolean isIn(int x, int y) {
		return x>=1 && x<=N && y>=1 && y<=N;
	}
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
	}
}
