/*
 * BOJ 19238 스타트 택시
 * https://www.acmicpc.net/problem/19238
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19238_스타트택시_200627 {
	static int N, M, fuel;
	static int tx, ty; // 택시 좌표
	static int[][] map;
	static boolean[][] visited;
	static Customer[] customers;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		customers = new Customer[M+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)
					map[i][j] = -1;
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		tx = Integer.parseInt(st.nextToken()) - 1;
		ty = Integer.parseInt(st.nextToken()) - 1;
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int dx = Integer.parseInt(st.nextToken()) - 1;
			int dy = Integer.parseInt(st.nextToken()) - 1;
			
			map[sx][sy] = i;
			customers[i] = new Customer(sx, sy, dx, dy);
		}
		
		for (int i = 0; i < M; i++) {
			// 손님들 중 가까운 사람을 찾는다
			find();
			if(fuel == -1) break;
		}
		
		System.out.println(fuel);
	}
	
	private static void find() {
		// 현재 택시 위치에서 승객들의 거리를 계산한다
		visited = new boolean[N][N];
		Queue<Taxi> q = new LinkedList<>();
		visited[tx][ty] = true;
		q.offer(new Taxi(tx, ty, 0, fuel));
		
		ArrayList<Info> list = new ArrayList<>();
		
		while(!q.isEmpty()) {
			Taxi now = q.poll();
			if(now.remain < 0) continue; // 남은 연료가 없다면 더이상 못 돌아다님
			// 이동 중 승객을 만났다면 저장해둔다
			if(map[now.x][now.y] > 0) { 
				list.add(new Info(map[now.x][now.y], now.x, now.y, now.dist));
			}
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dir[i][0];
				int ny = now.y + dir[i][1];
				if(isRange(nx, ny) && !visited[nx][ny] && map[nx][ny] >= 0) {
					visited[nx][ny] = true;
					q.offer(new Taxi(nx, ny, now.dist+1, now.remain-1));
				}
			}
		}
		
		
		// 돌아다니면서 연료 다 써도 승객을 못 데리러 가는 경우라면 영업 끝..
		if(list.size() == 0) {
			fuel = -1;
			return;
		}

		Collections.sort(list);
//		System.out.println(list.toString());
		Info shortest = list.get(0);
		map[shortest.x][shortest.y] = 0;
		
		// 계산된 승객들 중 가장 가까운 승객을 태우고 목적지까지 간다
		fuel -= shortest.dist;
//		System.out.println("after find fuel : " + fuel);
		tx = shortest.x;
		ty = shortest.y;
		drive(shortest.num);
	}
	
	// 목적지를 간다
	private static void drive(int num) {
		visited = new boolean[N][N];
		Queue<Taxi> q = new LinkedList<>();
		visited[tx][ty] = true;
		q.offer(new Taxi(tx, ty, 0, fuel));
		boolean flag = false; // 연료 없어서 도착지에 도달 못 할 경우
		
		while(!q.isEmpty()) {
			Taxi now = q.poll();
			if(now.remain < 0) continue; // 연료 없으면 탐색 끝
			if(now.x == customers[num].dx && now.y == customers[num].dy) { // 목적지 도착했다면
//				System.out.println("drive dist : " + now.dist);
				flag = true;
				tx = now.x;
				ty = now.y;
				fuel -= now.dist;
				fuel += now.dist * 2;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dir[i][0];
				int ny = now.y + dir[i][1];
				if(isRange(nx, ny) && !visited[nx][ny] && map[nx][ny] >= 0) {
					visited[nx][ny] = true;
					q.offer(new Taxi(nx, ny, now.dist+1, now.remain-1));
				}
			}
		}
		
		if(!flag) {
			fuel = -1;
			return;
		}
		
//		System.out.println("tx : " + tx + ", ty : " + ty + ", fuel : " + fuel);
	}

	static boolean isRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}

	static class Customer {
		int sx, sy, dx, dy;

		public Customer(int sx, int sy, int dx, int dy) {
			super();
			this.sx = sx;
			this.sy = sy;
			this.dx = dx;
			this.dy = dy;
		}
	}
	
	static class Taxi {
		int x, y;
		int dist; // 이동거리
		int remain; // 남은 연료
		
		public Taxi(int x, int y, int dist, int remain) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.remain = remain;
		}
	}
	
	static class Info implements Comparable<Info>{
		int num; // 승객 번호
		int x, y;
		int dist; // 거리

		public Info(int num, int x, int y, int dist) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Info o) {
			if(this.dist == o.dist) {
				if(this.x == o.x)
					return this.y - o.y;
				return this.x - o.x;
			}
			return this.dist - o.dist;
		}
	}
}
