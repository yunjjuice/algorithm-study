import java.io.*;
import java.util.*;

public class BOJ_¿¬±¸¼Ò2_NEW {
	static int N;
	static int K;
	static int[][] map;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	static ArrayList<Pos> total_list = new ArrayList<Pos>();
	static ArrayList<Pos> select_list = new ArrayList<Pos>();
	static int answer;
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) total_list.add(new Pos(i,j));
			}
		}//end input.
		answer = Integer.MAX_VALUE;
		comb(0,0);
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}//end main.
	
	public static void comb(int idx , int cnt) {
		if(cnt == K) {
			start();
		}
		for(int i=idx; i<total_list.size(); i++) {
			select_list.add(total_list.get(i));
			comb(i+1,cnt+1);
			select_list.remove(select_list.size()-1);
		}
	}//end comb.
	
	public static void start() {
//		for(int i=0; i<select_list.size(); i++) {
//			System.out.print(select_list.get(i).x + " , " + select_list.get(i).y + " // ");
//		}
		Queue<Pos> q = new LinkedList<Pos>();
		boolean[][] visited = new boolean[N][N];
		int time = 0;
		for(int i=0; i<select_list.size(); i++) {
			q.add(select_list.get(i));
			visited[select_list.get(i).x][select_list.get(i).y] = true;
		}
		while(!q.isEmpty()) {
			if(all_visited(visited)) {
				if(time < answer) answer = time;
				return;
			}
			int size = q.size();
			for(int i=0; i<size; i++) {
				Pos p = q.poll();
				for(int k=0; k<4; k++) {
					int new_x = p.x+direction[k][0];
					int new_y = p.y+direction[k][1];
					if(new_x>=0&&new_x<N&&new_y>=0&&new_y<N&&map[new_x][new_y]!=1&&!visited[new_x][new_y]) {
						visited[new_x][new_y] = true;
						q.add(new Pos(new_x,new_y));
					}
				}
			}
			time++;
		}
	}//end start.
	public static boolean all_visited(boolean[][] visited) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 0 && !visited[i][j]) return false;
			}
		}
		return true;
	}
}//end class.
