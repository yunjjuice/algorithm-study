/*
 * BOJ 17837 새로운 게임 2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_17837_새로운게임2_200429 {
	static int N, K;
	static int[][] map;
	static ArrayList<Integer>[][] chess;
	static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	static int[] changedir = {1, 0, 3, 2}; // 파란색 만났을 때
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		chess = new ArrayList[N][N]; // 체크판에 말의 위치 저장
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				chess[i][j] = new ArrayList<Integer>();
			}
		}
		
		Horse[] horses = new Horse[K+1]; // 말 정보
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			horses[i] = new Horse(x, y, d);
			chess[x][y].add(i);
		}
		
//		System.out.println(Arrays.toString(horses));
		
		int turn = 1, nx, ny, d;
		ArrayList<Integer> tmp = new ArrayList<>();
		for (; turn <= 1000; turn++) {
			for (int i = 1; i <= K; i++) { // 말을 차례대로 움직여준다
				Horse now = horses[i];
				d = now.d;
				nx = now.x + dir[d][0];
				ny = now.y + dir[d][1];
				
				if(!isRange(nx, ny) || map[nx][ny]==2) { // 체스판을 벗어나는 경우와 파란색을 만날 경우
					d = changedir[now.d]; // 방향 반대로 바꿔줌
					horses[i].d = d;
					nx = now.x + dir[d][0];
					ny = now.y + dir[d][1];
				}
				/*
				흰색인 경우에는 그 칸으로 이동한다. 이동하려는 칸에 말이 이미 있는 경우에는 가장 위에 A번 말을 올려놓는다.
				A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
				예를 들어, A, B, C로 쌓여있고, 이동하려는 칸에 D, E가 있는 경우에는 A번 말이 이동한 후에는 D, E, A, B, C가 된다.
				빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다.
				A, B, C가 이동하고, 이동하려는 칸에 말이 없는 경우에는 C, B, A가 된다.
				A, D, F, G가 이동하고, 이동하려는 칸에 말이 E, C, B로 있는 경우에는 E, C, B, G, F, D, A가 된다.
				 */
				if(isRange(nx, ny) && map[nx][ny] != 2) {
					if(map[nx][ny] == 0) { // 흰색
						// 내 위에 쌓인 애들 다 옮겨야 한다
						int h = chess[now.x][now.y].remove(chess[now.x][now.y].size()-1);
						while(h != i) {
							tmp.add(h);
							h = chess[now.x][now.y].remove(chess[now.x][now.y].size()-1);
						}
						tmp.add(h);
						Collections.reverse(tmp);
						for (Integer num : tmp) {
							chess[nx][ny].add(num);
							horses[num].x = nx;
							horses[num].y = ny;
						}
						tmp.clear();
					} else if(map[nx][ny] == 1) { // 빨강색
						int h = chess[now.x][now.y].remove(chess[now.x][now.y].size()-1);
						while(h != i) {
							tmp.add(h);
							h = chess[now.x][now.y].remove(chess[now.x][now.y].size()-1);
						}
						tmp.add(h);
						for (Integer num : tmp) {
							chess[nx][ny].add(num);
							horses[num].x = nx;
							horses[num].y = ny;
						}
						tmp.clear();
					}
				}
				
				if(isRange(nx, ny) && chess[nx][ny].size() >= 4) {
					System.out.println(turn);
					return;
				}	
			}
		}
		System.out.println(-1);
	}
	
	static boolean isRange(int x, int y) {
		if(x>=0 && x<N && y>=0 && y<N) return true;
		return false;
	}
	
	static class Horse {
		int x;
		int y;
		int d;
		public Horse(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + ", d=" + d + "]";
		}
	}
}
