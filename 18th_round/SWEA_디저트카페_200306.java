package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA_디저트카페_200306 {
	static int[][] map;
	static HashSet<Integer> hs;
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {-1, 1, 1, -1};
	static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // input
			
			max = 0;
			// 사각형을 그릴수 있는 것중에 가장 많은 종류 return, 먹을 수 없는 경우 -1
			for(int i = 0; i < n - 2; i++) {
				for(int j = 1; j < n - 1; j++) {
					hs = new HashSet<>(); // 해시셋 초기화
					simulation(i, j, 0, 0, 0, 0);
				}
			}
			if(max == 0) max = -1;
			sb.append("#").append(testcase).append(" ").append(max).append('\n');
		}
		System.out.print(sb);
	}
	
	private static void simulation(int x, int y, int a, int b, int dir, int cnt) {
		if(dir == 2 && cnt * 2 <= max) { // 가지치기 : 반환점을 찍었을때 cnt * 2가 max보다 작거나 같다면 return
			return;
		}
		if(cnt != 0 && a == 0 && b == 0) { // 다 만족하고 자기자신으로 돌아오면 max 갱신
			if(max < cnt) max = cnt;
			return;
		}
		
		if(hs.contains(map[x][y])) return; // 중복 있으면 return
		hs.add(map[x][y]); // 디저트 종류 hashset에 추가
		
		// 방향에 따라 가야할 다음 카페를 지정해줌
		// a와 b에는 반환점을 돌기 전에 dir = 0, dir = 1 만큼 간 거리를 저장
		if(dir == 0) {
			if(y > 0) {
				if (x < map.length - 2) simulation(x + dx[dir], y + dy[dir], a + 1, b, dir, cnt + 1);
				if(a != 0)simulation(x + dx[dir + 1], y + dy[dir + 1], a, b + 1, dir + 1, cnt + 1);
			}
			else {
				simulation(x + dx[dir + 1], y + dy[dir + 1], a, b + 1, dir + 1, cnt + 1);
			}
		} else if(dir == 1) {
			if(x < map.length - 1) {
				if( a < map.length - 1 - y) simulation(x + dx[dir], y + dy[dir], a, b + 1, dir, cnt + 1);
				if(a > 0)simulation(x + dx[dir + 1], y + dy[dir + 1], a - 1, b, dir + 1, cnt + 1);
			} else {
				simulation(x + dx[dir + 1], y + dy[dir + 1], a - 1, b, dir + 1, cnt + 1);
			}
		} else if(dir == 2) {
			if(a > 0) {
				simulation(x + dx[dir], y + dy[dir], a - 1, b, dir, cnt + 1);
			} else {
				simulation(x + dx[dir + 1], y + dy[dir + 1], a, b - 1, dir + 1, cnt + 1);
			}
		} else {
			simulation(x + dx[dir], y + dy[dir], a, b - 1, dir, cnt + 1);
		}
		
		hs.remove(map[x][y]); // hashset에서 디저트 종류 없애고 return
		return;
	}
}
