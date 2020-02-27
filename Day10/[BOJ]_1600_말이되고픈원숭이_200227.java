import java.io.*;
import java.util.*;

public class BOJ_말이되고픈원숭이 {
	
	static class Monkey{
		int x;
		int y;
		int cnt;
		public Monkey(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}//Class Monkey.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken()); //말처럼 움직일 수 있는 횟수.
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); //가로먼저.
		int N = Integer.parseInt(st.nextToken()); //세로다음.
		int[][] map = new int[N][M];
		boolean[][][] visited = new boolean[K+1][N][M]; //방문배열.
		int[][] m_direction = {{-1,0},{1,0},{0,-1},{0,1}};
		int[][] h_direction = {{-1,-2},{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2}};
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//draw map.
		Queue<Monkey> q = new LinkedList<>();
		q.add(new Monkey(0,0,K));
		visited[K][0][0] = true;
		int answer = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				Monkey m = q.poll();
				if(m.x == N-1 && m.y == M-1) {
					System.out.println(answer);
					return ;
				}
				if(m.cnt > 0) {
					for(int k=0; k<8; k++) {
						int new_x = m.x + h_direction[k][0];
						int new_y = m.y + h_direction[k][1];
						if(new_x>=0 && new_x<N && new_y>=0 && new_y<M && map[new_x][new_y] == 0 && !visited[m.cnt-1][new_x][new_y]) {
							q.add(new Monkey(new_x,new_y,m.cnt-1));
							visited[m.cnt-1][new_x][new_y] = true;
						}
					}
				}//말처럼 움직일 경우.
				for(int k=0; k<4; k++) {
					int new_x = m.x + m_direction[k][0];
					int new_y = m.y + m_direction[k][1];
					if(new_x>=0 && new_x<N && new_y>=0 && new_y<M && map[new_x][new_y] == 0 && !visited[m.cnt][new_x][new_y]) {
						q.add(new Monkey(new_x,new_y,m.cnt));
						visited[m.cnt][new_x][new_y] = true;
					}
				}//원숭이처럼 움직일경우.
			}
			answer++;
		}//end bfs.
		System.out.println(-1);
	}//end main.
}//end class.
