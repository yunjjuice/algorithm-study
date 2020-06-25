package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18500_미네랄2_200624 {
	static int R,C,N;
	static char[][] map1, map2;
	static boolean[][] visited;
	static boolean[][] visitedCluster;
	static int[][] dir = {{0,-1},{0,1},{-1,0},{1,0}}; // 좌,우,하,상 
	static ArrayList<Point> cluster;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map1 = new char[R+2][C+2]; // zero padding
		
		
		for (int i = 1; i <= R; i++) {
			String tmp = br.readLine();
			for (int j = 1; j <= C; j++) {
				map1[i][j] = tmp.charAt(j-1);
			}
		}
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		boolean flag = true; // 참일때는 창영, 거짓일때는 상근 
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			throw_Poll(R-height+1,flag);
		    flag = !flag;
		}
		
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				sb.append(map1[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	
	private static void throw_Poll(int height, boolean flag) {
		if(flag) {
			for (int i = 1; i <= C; i++) {  // 창영 
				if (map1[height][i] == 'x') {
					map1[height][i] = '.';
					// 클러스터 체크 
					check();
					break;
				}
			}
		} else {
			for (int i = C; i >= 1; i--) { // 상근 
				if (map1[height][i] == 'x') {
					map1[height][i] = '.';
					// 클러스터 체크 
					check();
					break;
				}
			}
		}
	}

	private static void check() {
		// 바닥에 붙어있는지 bfs
		visited = new boolean[R+2][C+2];
		visitedCluster = new boolean[R+2][C+2];
		for (int i = 1; i <= C; i++) {
			if(map1[R][i] == 'x' && !visited[R][i]) {
				// 클러스터 연결을 확인 
				Queue<Point> q = new LinkedList<>();
				Point p = new Point(R, i);
				q.offer(p);
				while(!q.isEmpty()) {
					Point tmp = q.poll();
				    visited[tmp.i][tmp.j] = true;
				    for (int j = 0; j < dir.length; j++) {
						int nI = tmp.i+dir[j][0];
						int nJ = tmp.j+dir[j][1];
						if(map1[nI][nJ] == 'x' && !visited[nI][nJ]) {
							q.offer(new Point(nI,nJ));
						}
					}
				}
			}
		}
		
		// 바닥에 붙어있는것들 찍어보기 
//		for (int i = 1; i <= R; i++) {
//			for (int j = 1; j <= C; j++) {
//				char t = visited[i][j]?'x':'.';
//				System.out.print(t);
//			}
//			System.out.println();
//		}
		
		// 떠있는 클러스터 체크, bfs 
		loop : for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				// 위부터 훑고가서 처음으로 떠있는 미네랄을 만나면 
				if(map1[i][j]=='x'&&!visited[i][j]) {
					Queue<Point> q = new LinkedList<>();
					cluster = new ArrayList<>();
					Point p = new Point(i, j);
					q.offer(p);
					cluster.add(p);
					while(!q.isEmpty()) {
						Point tmp = q.poll();
					    visitedCluster[tmp.i][tmp.j] = true;
					    for (int k = 0; k < dir.length; k++) {
							int nI = tmp.i+dir[k][0];
							int nJ = tmp.j+dir[k][1];
							if(map1[nI][nJ] == 'x' && !visitedCluster[nI][nJ]) {
								p = new Point(nI,nJ);
								q.offer(p);
								cluster.add(p);
							}
						}
					} 
					// 떠있는 클러스터를 떨어뜨림 
					down();
					// 떠있는 클러스터는 하나임 
					break loop;
				}
			}
		}
		
	}
	
	private static void down() {
//		for (Point point : cluster) {
//			System.out.println(point.i+","+point.j);
//		}
		
		// 떠있는 클러스터를 최대한 떨어뜨린다.
		boolean isDownable = true;
		while(isDownable) {
			for (Point p : cluster) {
				// 모든 미네랄이 한번의 while문에 한칸씩 떨어질수있는지 체크.
				if(visited[p.i+1][p.j]||p.i+1>R) {
					isDownable=false;
					break;
				}
			}
			if(isDownable) {
				for (Point p : cluster) {
					p.i++;
				}
			}
		}
		
//		System.out.println("변경");
//		for (Point point : cluster) {
//			System.out.println(point.i+","+point.j);
//		}
		
		// 최소거리만큼 클러스터를 내려줌.
		map2 = new char[R+2][C+2]; // 복사용 
		for (char[] arr : map2) {
			Arrays.fill(arr, '.');
		}
		for (Point p : cluster) {
			map2[p.i][p.j] = 'x';
		}
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if(visited[i][j]) {
					map2[i][j] = 'x';
				}
			}
		}
		
		map1 = map2.clone();
	}

	static class Point{
		int i,j;
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}
}
