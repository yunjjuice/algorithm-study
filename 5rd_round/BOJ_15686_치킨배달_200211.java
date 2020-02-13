package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class House { // 집과, 치킨집의 위치정보를 담을 class
	int x;
	int y;
	
	public House(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_15686_치킨배달_200211 {
	final static int[] dx = {0, 0, 1, -1};
	final static int[] dy = {1, -1, 0, 0};
	static int ans = 0;
	static int min = Integer.MAX_VALUE;
	static int flag = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][n];
		int[][] visited = new int[n][n]; // bfs 위한 visited 배열
		List<House> house = new LinkedList<>(); // 집 위치 담을 list
		List<House> chicken = new LinkedList<>(); // 치킨집 위치 담을 list
		int numChicken = 0; // 치킨집 개수 변수
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					house.add(new House(i, j));
				} else if(map[i][j] == 2) {
					chicken.add(new House(i, j));
					numChicken++;
				}
			}
		} // input
		
		// 치킨집 m개 선택하는 조합 2차원 배열에 담기
		int[][] subsetCheck = new int[combination(numChicken, m)][numChicken];
		int[] temp = new int[numChicken];
		
		subset(subsetCheck, temp, m, temp.length - m, 0); // 모든 조합이 담김
		
		int[][] subMap = new int[n][n];
		for(int i = 0; i < subsetCheck.length; i++) {
			subMap = subsetMap(map, subsetCheck[i], chicken, house); // 치킨집 조합별로 subMap 생성
			int sum = 0;
			for(int j = 0; j < house.size(); j++) { // 각 집에서 출발할때 가장 가까운 치킨집 구하기
				initVisit(visited); // visited 초기화
				sum += bfs(subMap, visited, house.get(j)); // 가장 가까운 치킨집 거리 합해줌
			}
			if(min > sum) min = sum; // 최소값 구하기
		}
		
		ans = min;
		System.out.println(ans);
	}
	
	public static void subset(int[][] subsetCheck, int[] temp, int m, int k, int pivot) {
		// 치킨집 고르는 모든 조합 2차원 배열에 담기
		if(pivot == subsetCheck[0].length) {
			subsetCheck[flag] = temp.clone();
			flag++;
			return;
		}
		
		if(k > 0) {
			temp[pivot] = 0;
			subset(subsetCheck, temp, m, k - 1, pivot + 1);
		}	
		
		if(m > 0) {
			temp[pivot] = 1;
			subset(subsetCheck, temp, m - 1, k, pivot + 1);
		}
	}
	
	public static int[][] subsetMap(int[][] map, int[] subset, List<House> chicken, List<House> house) {
		// 치킨집 조합별로 submap 생성
		int[][] subMap = new int[map.length][map[0].length];
		for(int i = 0; i < subset.length; i++) {
			if(subset[i] == 1) {
				House h = chicken.get(i);
				subMap[h.x][h.y] = 2;
			}
		}
		
		for(int i = 0; i < house.size(); i++) {
			House h = house.get(i);
			subMap[h.x][h.y] = 1;
		}
		
		return subMap;
	}
	
	public static long factorial(long n) {
		if(n <= 1) return 1;
		else return factorial(n - 1) * n;
	}
	
	public static int combination(int n, int k) {
		// 조합의 개수 구하기
		long res = factorial((long)n) / (factorial((long)k) * factorial((long)(n - k)));
		return (int)res;
	}
	
	public static void initVisit(int[][] visited) {
		// 방문 배열 초기화 함수
		for(int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], -1);
		}
	}
 	
	public static int bfs(int[][] map, int[][] visited, House p) {
		Queue<House> q = new LinkedList<>();
		q.offer(new House(p.x, p.y));
		visited[p.x][p.y] = 0;
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			q.poll();
			for(int i = 0; i < 4; i++) {
				if(x + dx[i] >= 0 && y + dy[i] >= 0 && x + dx[i] < map.length && y + dy[i] < map[0].length) {
					if(visited[x + dx[i]][y + dy[i]] == -1) {
						q.offer(new House(x + dx[i], y + dy[i]));
						visited[x + dx[i]][y + dy[i]] = visited[x][y] + 1;
						if(map[x + dx[i]][y + dy[i]] == 2) {
							return visited[x + dx[i]][y + dy[i]];
						}
					}
				}
			}
		}
		return -1;
	}
}
