import java.io.*;
import java.util.*;

public class BOJ_3709_레이저빔은어디로 {
	static int N;
	static int M;
	static boolean[][] map;
	static boolean[][][] visited;
	static int[][] direction = {{1,0},{0,-1},{-1,0},{0,1}};
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());
		for(int test=1; test<=TC; test++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new boolean[N+1][N+1];
			visited = new boolean[N+1][N+1][4];
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			}
			st = new StringTokenizer(br.readLine());
			int dx = Integer.parseInt(st.nextToken());
			int dy = Integer.parseInt(st.nextToken());
			int direction=0;
			if(dx == 0) direction = 0;
			else if(dx == N+1) direction = 2;
			if(dy == 0) direction = 3;
			else if(dy == N+1) direction = 1;
			go(dx,dy,direction);
		}//end testCase.
		System.out.print(sb);
	}//end main.
	public static void go(int x, int y, int direct) {
		while(true) {
			x += direction[direct][0];
			y += direction[direct][1];
			if(x==0 || x==N+1 || y==0 || y==N+1) {
				sb.append(x).append(" ").append(y).append("\n");
				break ;
			}
			if(map[x][y]) {
				direct = (direct+1)%4;
			}
		}
	}//end go.
}//end class.
