/*
 * BOJ 11559 Puyo Puyo
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11559 {
	static int[] dc = {-1, 1, 0, 0};
	static int[] dr = {0, 0, -1, 1};
	static char[][] map = new char[12][6];
	static boolean[][] visited = new boolean[12][6];
	static int combo, check; // combo는 정답, check는 한 번이라도 부서졌는지?
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 12; i++) {
			String st = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = st.charAt(j);
			}
		}
		
//		for (int i = 0; i < 12; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		// 여러 그룹이 한 번에 터질 땐 콤보가 +1만 됨
		boolean flag = true;
		while(flag) { // while문 한 번이 한 턴 (뿌요가 내려오는 단위?)
			check = -1;
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if(visited[i][j] || map[i][j] == '.')
						continue;
					bfs(i, j);
				}
			}
			
			if(check == -1) { // check가 그대로라면 bfs문에 들어가지 않거나 삭제될 것이 없는 것
				flag = false;
			} else {
				combo++;
			}
			
//			// 뿌요 사라진 거 확인
//			for (int i = 0; i < 12; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
			
			// 아래로 내려준다
			for (int i = 0; i < 6; i++) {
				int index = 11;
				for (int j = 11; j >= 0; j--) {
					if(index <= j)
						continue;
					if(map[index][i] == '.' && map[j][i] != '.'){
						map[index][i] = map[j][i];
						map[j][i] = '.';
						index--;
					}
				}
			}
//			// 아래로 내려간거 확인
//			for (int i = 0; i < 12; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
			
			for (int i = 0; i < 12; i++) {
				Arrays.fill(visited[i], false);
			}
		}

		System.out.println(combo);
	}
	
	public static void bfs(int c, int r) {
		visited[c][r] = true;
		Queue<Pair> q = new LinkedList<Pair>();
		ArrayList<Pair> list = new ArrayList<Pair>();
		q.offer(new Pair(c, r));
		list.add(new Pair(c, r));
		while(!q.isEmpty()) {
			Pair p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nc = p.c + dc[i];
				int nr = p.r + dr[i];
				if(nc >= 0 && nc < 12 && nr >= 0 && nr < 6 && !visited[nc][nr] && map[p.c][p.r] == map[nc][nr]){
					q.offer(new Pair(nc, nr));
					list.add(new Pair(nc, nr));
					visited[nc][nr] = true;
				}
			}
		}
		
		if(list.size() >= 4) { // 뿌요가 4개 이상이면 없애준다
			for (int i = 0; i < list.size(); i++) {
				map[list.get(i).c][list.get(i).r] = '.';
			}
			check++;
		}
		
		list.clear();
	}
}

class Pair {
	int c;
	int r;
	Pair(int c, int r) {
		this.c = c;
		this.r = r;
	}
}