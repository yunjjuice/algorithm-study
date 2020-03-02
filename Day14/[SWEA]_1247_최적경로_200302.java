import java.io.*;
import java.util.*;

public class SWEA_1247_최적경로 {
	
	static class Pos {
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}//class Pos.
	
	static int answer;
	static int N;
	static int[][] map;
	static boolean[] visited;
	static int[] select;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			Pos[] list = new Pos[N+2];
			map = new int[N+2][N+2];
			visited = new boolean[N+1];
			select = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N+2; i++) {
				list[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			for(int i=0; i<N+2; i++) {
				for(int j=i+1; j<N+2; j++) {
					int temp = Math.abs(list[i].x - list[j].x) + Math.abs(list[i].y - list[j].y);
					map[i][j] = temp;
					map[j][i] = temp;
				}
			}//각 vertex --> vertex 간 거리.
			dfs(0);
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}//end TestCase.
		System.out.print(sb);
	}//end main.
	public static void dfs(int cnt) {
		if(cnt == N) {
			int distance = map[0][select[0]] + map[select[N-1]][1];
			for(int i=1; i<N; i++) {
				distance += map[select[i-1]][select[i]];
			}
			if(answer > distance) answer = distance;
			return ;
		}
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				select[cnt] = i+1;
				dfs(cnt+1);
				visited[i] = false;
			}
		}
	}//end dfs.
}//end class.
