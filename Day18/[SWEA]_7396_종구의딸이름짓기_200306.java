import java.io.*;
import java.util.*;

public class SWEA_7396_종구의딸이름짓기 {
	static char[][] map;
	static boolean[][] visited;
	static char[] answer;
	static int[][] direction = {{0,1},{1,0}};
	static int N;
	static int M;
	static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}//class Pos.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			String ans = "";
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visited = new boolean[N][M];
			answer = new char[N+M-1];
			for(int i=0; i<N; i++) {
				String temp = br.readLine();
				for(int j=0; j<M; j++) {
					map[i][j] = temp.charAt(j);
				}
			}//draw map.
			start(0,0,N,M);
			for(int i=0; i<answer.length; i++) {
				ans = ans + answer[i]; 
			}
			sb.append("#").append(test).append(" ").append(ans).append("\n");
		}//end TestCase.
		System.out.print(sb);
	}//end main.
	public static void start(int x, int y, int end_x, int end_y) {
		Queue<Pos> q = new LinkedList();
		q.add(new Pos(x,y));
		int cnt = 0;
		answer[cnt] = map[x][y];
		visited[x][y] = true;
		ArrayList<Pos> list = new ArrayList<>();
		while(cnt<N+M-2) {
			list.clear();
			int size = q.size();
			//System.out.println(q.size());
			char temp = 'z'; //비교 대상.
			for(int i=0; i<size; i++) {
				Pos p = q.poll();
				for(int k=0; k<2; k++) {
					int now_x = p.x + direction[k][0];
					int now_y = p.y + direction[k][1];
					if(now_x>=0 && now_x<N && now_y>=0 && now_y<M && !visited[now_x][now_y]) {
						visited[now_x][now_y] = true;
						if(map[now_x][now_y]<temp) {
							list.clear();
							temp = map[now_x][now_y];
							list.add(new Pos(now_x,now_y));
						}
						else if(map[now_x][now_y]==temp) list.add(new Pos(now_x,now_y));
					}
				}
			}
			for(int i=0; i<list.size(); i++) {
				q.add(list.get(i));
			}
			answer[++cnt] = map[list.get(0).x][list.get(0).y];
		}//end while.
	}//end start.
}//end class.
