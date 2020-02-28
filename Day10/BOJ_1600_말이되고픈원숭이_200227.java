/*
 * BOJ 1600 말이 되고픈 원숭이
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1600 {
	static int k, w, h;
	static int[][] map;
	static boolean[][][] visited;
	static int[] mc = {-1, 1, 0, 0};
	static int[] mr = {0, 0, -1, 1};
	static int[] hc = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] hr = {1, 2, 2, 1, -1, -2, -2, -1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		visited = new boolean[h][w][k+1];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 입력 확인
//		for (int i = 0; i < h; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Mon> q = new LinkedList<Mon>();
		q.offer(new Mon(0, 0, 0, 0));
		visited[0][0][0] = true; // (0,0)에서 말로 움직인 횟수 0인 거 방문함!
		
		while(!q.isEmpty()) {
			// 큐 한 번 빼낼 때 -> 빼낸 좌표에 있던 곳에서 갈 수 있는 모든 방향을 가보고 갈 수 있다면 큐에 들어감!
			Mon m = q.poll();
			if(m.c == h-1 && m.r == w-1) {
				return m.cnt;
			}
			
			// 원숭이처럼 이동
			for (int i = 0; i < 4; i++) {
				int nc = m.c + mc[i];
				int nr = m.r + mr[i];
				if(nc >= 0 && nc < h && nr >= 0 && nr < w && map[nc][nr] == 0 && !visited[nc][nr][m.hc]) {
					q.offer(new Mon(nc, nr, m.hc, m.cnt+1));
					visited[nc][nr][m.hc] = true;
				}
			}
			
			if(m.hc == k) 
				continue; // 더이상 말처럼 갈 수 없다
			
			// 말처럼 이동
			for (int i = 0; i < 8; i++) {
				int nc = m.c + hc[i];
				int nr = m.r + hr[i];
				if(nc >= 0 && nc < h && nr >= 0 && nr < w && map[nc][nr] == 0 && !visited[nc][nr][m.hc+1]) {
					q.offer(new Mon(nc, nr, m.hc+1, m.cnt+1));
					visited[nc][nr][m.hc+1] = true;
				}
			}
		}
		
		return -1;
	}
}

class Mon { // Monkey임
	int c;
	int r;
	int hc; // 말처럼 움직인 횟수
	int cnt; // 이동 횟수
	public Mon(int c, int r, int hc, int cnt) {
		this.c = c;
		this.r = r;
		this.hc = hc;
		this.cnt = cnt;
	}
}
