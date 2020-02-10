package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int v1;
	int v2;
	int cost;
	
	Edge(int v1, int v2, int cost) {
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;
	}
	
	public int compareTo(Edge e) {
		if(this.cost > e.cost) {
			return 1;
		} else if(this.cost == e.cost) {
            return 0;
		} else return -1;
	}
}

public class BOJ_17472_다리만들기2_200210 {
	final static int[] dx = {0, 1, -1, 0};
	final static int[] dy = {1, 0, 0, -1};
	static int ans = Integer.MAX_VALUE;
	static int tmp = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input

		// 섬마다 섬의 번호를 매기는 과정
		int islandNum = 0;
		int[][] visited = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 1 && visited[i][j] == 0) {
					islandNum++;
					map[i][j] = islandNum;
					numberingIsland(map, visited, islandNum, i, j);
				}
			}
		}
		
		int[][] shortestPath = new int[islandNum + 1][islandNum + 1]; // 섬간 가장 짧은 거리를 담는 배열
		for(int i = 0; i < islandNum + 1; i++) Arrays.fill(shortestPath[i], Integer.MAX_VALUE); // max value로 초기화
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] >= 1) {
					findNextIsland(map, shortestPath, i, j, map[i][j]); // 섬간 가장 짧은 거리 구하기
				}
			}
		}
		
		// 섬이 모두 연결되어 있지 않다면 -1 출력
		int[] check = new int[islandNum + 1]; // 모든 정점 찾아가나 확인하는 배열
		dfs(shortestPath, check, 1);
		for(int i = 1; i < check.length; i++) {
			if(check[i] == 0) {
				System.out.println(-1);
				return;
			}
		}
		
		// 크루스칼 알고리즘, 최소 비용 신장 트리 구하기, union find
		int[] parent = new int[islandNum + 1];
		List<Edge> list = new ArrayList<Edge>();
		
		for(int i = 1; i < islandNum + 1; i++) {
			parent[i] = i;
		}
		
		for(int i = 1; i < islandNum + 1; i++) {
			for(int j = i; j < islandNum + 1; j++) {
				if(shortestPath[i][j] != Integer.MAX_VALUE) {
					list.add(new Edge(i, j, shortestPath[i][j])); // 리스트에 정점, 간선, 가중치 정보 저장
				}
			}
		}

		Collections.sort(list); // 가중치 오름차순으로 정렬
		
		int sum = 0;
		for(int i = 0; i < list.size(); i++) {
			Edge e = list.get(i);
			if(!find(parent, e.v1, e.v2)) { // 부모가 다르면, 싸이클이 아니므로 cost 더해주고, 부모 합치기
				sum += e.cost;
				unionParent(parent, e.v1, e.v2);
			}
		}

		ans = sum;
		if(ans == 0) ans = -1;
		System.out.println(ans);
	}
	
	public static void unionParent(int[] parent, int x, int y) { // 부모 합치기
		x = getParent(parent, x);
		y = getParent(parent, y);
		if(x > y) parent[x] = y;
		else parent[y] = x;
	}
	
	public static int getParent(int[] parent, int x) { // 부모 가져오기
		if(parent[x] == x) {
			return x;
		}
		
		return parent[x] = getParent(parent, parent[x]);
	}
	
	public static boolean find(int[] parent, int x, int y) { // 부모 같은지 확인
		x = getParent(parent, x);
		y = getParent(parent, y);
		if(x == y) return true;
		else return false;
	}
	
	public static void dfs(int[][] shortestPath, int[] check, int now) {
		check[now] = 1;
		for(int i = 1; i < shortestPath.length; i++) {
			if(shortestPath[now][i] != Integer.MAX_VALUE && check[i] == 0) {
				check[i] = 1;
				dfs(shortestPath, check, i);
			}
		}
	}
	
	public static void findNextIsland(int[][] map, int[][] shortestPath, int x, int y, int islandNum) {
		// 매개변수 값 재사용하기 위해 따로 저장
		int xx = x;
		int yy = y;
		for(int i = 0; i < 4; i++) {
			x = xx;
			y = yy;
			int count = 0; // 몇칸 떨어져있나 확인
			while(true) {
				if(x + dx[i] >= 0 && y + dy[i] >= 0 && x + dx[i] < map.length && y + dy[i] < map[0].length) {
					if(map[x + dx[i]][y + dy[i]] == 0) { // 0이면 계속 그 방향으로 go
						x += dx[i];
						y += dy[i];
						count++;
					} else break; // 다른 섬에 다다르면 break;
				} else { // 맵 밖으로 나가면 count 0
					count = 0;
					break;
				}
			}
			if(count >= 2) {
				if(shortestPath[islandNum][map[x + dx[i]][y + dy[i]]] > count) { // 섬에서 섬까지 최소 거리 구해줌
					shortestPath[islandNum][map[x + dx[i]][y + dy[i]]] = count;
					shortestPath[map[x + dx[i]][y + dy[i]]][islandNum] = count;
				}
			}
		}
	}
	
	public static void numberingIsland(int[][] map, int[][] visited, int islandNum, int x, int y) {
		for(int i = 0; i < 4; i++) {
			if(x + dx[i] >= 0 && y + dy[i] >= 0 && x + dx[i] < map.length && y + dy[i] < map[0].length) {
				if(map[x + dx[i]][y + dy[i]] > 0 && visited[x + dx[i]][y + dy[i]] == 0) {
					visited[x + dx[i]][y + dy[i]] = 1;
					map[x + dx[i]][y + dy[i]] = islandNum; // 섬에 번호매기기
					numberingIsland(map, visited, islandNum, x + dx[i], y + dy[i]);
				}
			}
		}
	}
}
