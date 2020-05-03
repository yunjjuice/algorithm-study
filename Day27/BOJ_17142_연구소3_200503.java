/*
 * BOJ 17142 연구소 3
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142_연구소3_200503 {
	static int N, M, blank, ans;
	static int[][] map;
	static int[] pick;
	static boolean[] used;
	static ArrayList<Pos> list;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		pick = new int[M];
		list = new ArrayList<Pos>();
		
		blank = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2)
					list.add(new Pos(i, j));
				if(map[i][j] == 0)
					blank++;
			}
		}
		used = new boolean[list.size()];
		
		ans = Integer.MAX_VALUE;
		select(0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	private static void select(int index) {
		if(index == M) {
//			System.out.println(Arrays.toString(pick));
			bfs();
			return;
		}
		
		for (int i = 0; i < list.size(); i++) {
			if(used[i]) continue;
			if(index>0 && pick[index-1]>i) continue;
			pick[index] = i;
			used[i] = true;
			select(index+1);
			used[i] = false;
		}
	}
	
	private static void bfs() {
		int[][] copy = new int[N][N];
		int tmpBlank = blank;
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(copy[i], -1);
		}
		
		Queue<Pos> q = new LinkedList<Pos>();
		for (int i = 0; i < M; i++) {
			Pos tmp = list.get(pick[i]);
			q.offer(tmp);
			copy[tmp.x][tmp.y] = 0;
		}
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];
				
				if(nx>=0 && nx<N && ny>=0 && ny<N && copy[nx][ny]==-1) {
					if(map[nx][ny] == 0) {
						copy[nx][ny] = copy[cur.x][cur.y] + 1;
						q.offer(new Pos(nx, ny));
						tmpBlank--;
					} else if(map[nx][ny] == 2 && tmpBlank>0) {
						copy[nx][ny] = copy[cur.x][cur.y] + 1;
						q.offer(new Pos(nx, ny));						
					}
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		if(tmpBlank == 0) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(copy[i][j] > max)
						max = copy[i][j];
				}
			}
		}
		
		if(tmpBlank == 0) {
			ans = Math.min(max, ans);
		}
	}



	static class Pos {
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
