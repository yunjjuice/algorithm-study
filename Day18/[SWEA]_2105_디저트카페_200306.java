import java.io.*;
import java.util.*;

public class SWEA_2105_디저트카페 {
	static int[][] map;
	static int N;
	static int answer;
	static int[][] direction = {{1,1},{1,-1},{-1,-1},{-1,1}}; //방향.
	static int start_x;
	static int start_y;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			answer = -1;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<Integer>();
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}//draw map.
			for(int i=0; i<N-2; i++) {
				for(int j=1; j<=N-2; j++) {
					start_x = i;
					start_y = j;
					list.add(map[i][j]);
					dfs(i,j,0);
					list.clear();
				}
			}
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}//end TestCase.
		System.out.print(sb);
	}//end main.
	public static void dfs(int x, int y, int dir) {
		int new_x = x + direction[dir][0];
		int new_y = y + direction[dir][1];
		if(new_x<0 || new_x >=N || new_y<0 || new_y>=N) return ;
		if(new_x == start_x && new_y == start_y) { //싸이클 완료.
			if(answer < list.size()) answer = list.size();
			return ;
		}
		if(!list.contains(map[new_x][new_y])) {
			list.add(map[new_x][new_y]);
			dfs(new_x,new_y,dir); //그대로 진행.
			if(dir!=3) dfs(new_x,new_y,dir+1); //방향 꺽기.
			list.remove(list.size()-1);
		}
		
	}//end DFS.
}//end class.
