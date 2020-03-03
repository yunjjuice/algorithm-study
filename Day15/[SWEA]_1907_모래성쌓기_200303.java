import java.io.*;
import java.util.*;

public class SWEA_1907_모래성쌓기 {
	static class Pos {
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}//class Pos.
	static int[][] direction = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			char[][]map = new char[N][M];
			int[][] no_sand = new int[N][M];
			boolean[][] visited = new boolean[N][M];
			Queue<Pos> q1 = new LinkedList<Pos>();
			Queue<Pos> q2 = new LinkedList<Pos>();
			for(int i=0; i<N; i++) {
				String temp = br.readLine();
				for(int j=0; j<M; j++) {
					map[i][j] = temp.charAt(j);
					if(map[i][j]>=49 && map[i][j]<=57) q1.add(new Pos(i,j));
				}
			}//end input.
			
			while(!q1.isEmpty()) {
				Pos p = q1.poll();
				int start = 0;
				for(int k=0; k<8; k++) {
					if(map[p.x+direction[k][0]][p.y+direction[k][1]] == '.') start++;
				}
				no_sand[p.x][p.y]= start; 
				if(no_sand[p.x][p.y]>=map[p.x][p.y]-48) {
					q2.add(new Pos(p.x,p.y));
					visited[p.x][p.y]= true; 
				}
			}//우선순위큐에서 가중치 값 가장 큰것들 부터 해서 fix 과정 밟는 것.
			
			while(!q2.isEmpty()) {
				int size = q2.size();
				for(int i=0; i<size; i++) {
					Pos p = q2.poll();
					for(int k=0; k<8; k++) {
						int new_x = p.x+direction[k][0];
						int new_y = p.y+direction[k][1];
						if(map[new_x][new_y] != '.' && !visited[new_x][new_y]) {
							no_sand[new_x][new_y]++;
							if(no_sand[new_x][new_y]>=map[new_x][new_y]-48) {
								q2.add(new Pos(new_x,new_y));
								visited[new_x][new_y] = true;
							}
						}
					}
				}
				answer++;
			}//end bfs.
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}//end TestCase.
		System.out.print(sb);
	}//end main.
}//end class.
