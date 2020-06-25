import java.awt.Point;
import java.io.*;
import java.util.*;

public class BOJ_18500_미네랄2 {
	static int R;
	static int C;
	static char [][] map;
	static boolean [][] visited;
	static boolean flag2;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			String tmp = br.readLine();
			for( int j=0; j<C; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}//draw map.
		st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int test=0; test<TC; test++) {
			int idx = Integer.parseInt(st.nextToken());
			start(R-idx, test%2==0);
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
	}//end main.
	
	public static void start(int idx, boolean dir) {
		boolean flag = false;
		if(dir) {
			int n = 0;
			while(n<C) {
				if(map[idx][n] == 'x') {
					map[idx][n] = '.';
					flag = true;
					break;
				}
				n++;
			}
		}//왼쪽에서 제거.
		else {
			int n = C-1;
			while(n>=0) {
				if(map[idx][n] == 'x') {
					map[idx][n] = '.';
					flag = true;
					break;
				}
				n--;
			}
		}//오른쪽에서 제거.
		if(flag) {
			flag2 = false;
			visited = new boolean[R][C];
ex:			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(map[i][j] == 'x' && !visited[i][j] && !flag2) {
						visited[i][j] = true;
						bfs(i,j);
					}
				}
			}
		}//없어진게 있으면 그룹핑 다시해야함.
	}//end start.
	
	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<Point>();
		ArrayList<Point> list = new ArrayList<Point>();
		q.add(new Point(x,y));
		list.add(new Point(x,y));
		map[x][y] = '1';
		while(!q.isEmpty()) {
			Point p = q.poll();
			int dx = p.x;
			int dy = p.y;
			for(int k=0; k<4; k++) {
				int new_x = dx + direction[k][0];
				int new_y = dy + direction[k][1];
				if(new_x>=0 && new_x<R && new_y>=0 && new_y<C && map[new_x][new_y]=='x' && !visited[new_x][new_y]) {
					visited[new_x][new_y] = true;
					map[new_x][new_y] = '1';
					q.add(new Point(new_x,new_y));
					list.add(new Point(new_x,new_y));
				}
			}
		}//end grouping.
		int time = 0;
ex2:	while(true) {
			for(int i=0; i<list.size(); i++) {
				Point p = list.get(i);
				int dx = p.x+time;
				int dy = p.y;
				if(dx==R-1 || map[dx+1][dy]== 'x')break ex2;
			}
			time++;
		}//end move.
		if(time>0) flag2 = true;
		for(int i=0; i<list.size(); i++) {
			Point p = list.get(i);
			map[p.x][p.y] = '.';
		}
		for(int i=0; i<list.size(); i++) {
			Point p = list.get(i);
			map[p.x+time][p.y] = 'x';
		}
	}//end bfs.
	
}//end class.
