import java.io.*;
import java.util.*;

public class SWEA_7699_수지의수지맞는여행 {
	
	static int[][] map;
	static boolean[] visited;
	static int answer;
	static int N;
	static int M;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N+1][M+1];
			visited = new boolean[26];
			for(int i=1; i<=N; i++) {
				String temp = br.readLine();
				for(int j=1; j<=M; j++) {
					map[i][j] = temp.charAt(j-1)- 65;
				}
			}
			visited[map[1][1]] = true;
			answer = 1;
			dfs(1,1,1);
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}//end TestCase.
		System.out.print(sb);
	}//end main.
	public static void dfs(int dx, int dy, int cnt) {
		if(cnt > answer) answer = cnt;
		for(int k=0; k<4; k++) {
			int new_x = dx + direction[k][0];
			int new_y = dy + direction[k][1];
			
			if(new_x>0 && new_x<=N && new_y>0 && new_y<=M && !visited[map[new_x][new_y]]) {
				visited[map[new_x][new_y]] = true;
				dfs(new_x,new_y,cnt+1);
				visited[map[new_x][new_y]] = false;
			}
		}
	}//end dfs.
}//end class.
