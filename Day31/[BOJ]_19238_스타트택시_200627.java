import java.awt.Point;
import java.io.*;
import java.util.*;

public class BOJ_19238_스타트택시 {
	static int N;
	static int M;
	static int fuel;
	static int[][] map;
	static int[][] list;
	static Point taxi;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		list = new int[M+1][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp==0) map[i][j] = 0;
				else map[i][j] = -1;
			}
		}//end draw map.
		st = new StringTokenizer(br.readLine());
		taxi = new Point(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = i;
			list[i][0] = Integer.parseInt(st.nextToken())-1;
			list[i][1] = Integer.parseInt(st.nextToken())-1;
		}//end input.
		
		int time = 0;
		boolean flag = false;
		while(time<M) {
			if(simulation()) {
				flag = true;
				break;
			}
			time++;
		}
		if(flag) System.out.println(-1);
		else System.out.println(fuel);
	}//end main.
	public static boolean simulation() {
		//처음 단계는 bfs를 돌면서? 조건에 맞는 사용자 찾기.
		Queue<Point> q = new LinkedList<Point>();
		boolean[][] visited = new boolean[N][N];
		visited[taxi.x][taxi.y] = true;
		q.add(taxi);
		int time = 0;
		boolean find_user = false;
		int user_x = N;
		int user_y = N;
		while(!q.isEmpty()) {
			if(time>fuel) break;
			int size = q.size();
			for(int i=0; i<size; i++) {
				Point p = q.poll();
				int dx = p.x;
				int dy = p.y;
				if(map[dx][dy] != 0) {
					find_user = true;
					if(user_x > dx) {
						user_x = dx;
						user_y = dy;
					}
					else if(user_x == dx && user_y > dy) {
						user_x = dx;
						user_y = dy;
					}
				}//찾았다리!
				if(find_user) continue;
				for(int k=0; k<4; k++) {
					int new_x = dx + direction[k][0];
					int new_y = dy + direction[k][1];
					if(new_x>=0 && new_x<N && new_y>=0 && new_y<N && !visited[new_x][new_y] && map[new_x][new_y] != -1) {
						visited[new_x][new_y] = true;
						q.add(new Point(new_x,new_y));
					}
				}
			}//q 사이즈 만큼 돌리기.
			if(find_user) break;
			time++;
		}
		if(user_x == N) return true; //현재 연료로 사용자 못찾았을 경우.
		else {
			fuel -= time; //연료 소비.
			time = 0;
			taxi.x = user_x;
			taxi.y = user_y;
			int target_x = list[map[user_x][user_y]][0];
			int target_y = list[map[user_x][user_y]][1];
			map[user_x][user_y] = 0;
			q = new LinkedList<Point>();
			visited = new boolean[N][N];
			visited[taxi.x][taxi.y] = true;
			q.add(taxi);
			boolean can_go = false;
ex:			while(!q.isEmpty()) {
				if(time>fuel) break;
				int size = q.size();
				for(int i=0; i<size; i++) {
					Point p = q.poll();
					int dx = p.x;
					int dy = p.y;
					if(dx == target_x && dy == target_y) {
						can_go = true;
						break ex;
					}
					for(int k=0; k<4; k++) {
						int new_x = dx + direction[k][0];
						int new_y = dy + direction[k][1];
						if(new_x>=0 && new_x<N && new_y>=0 && new_y<N && !visited[new_x][new_y] && map[new_x][new_y] != -1) {
							visited[new_x][new_y] = true;
							q.add(new Point(new_x,new_y));
						}
					}
				}//q 사이즈 만큼 돌리기.
				time++;
			}//다시 bfs돌린다.
			if(!can_go) return true; //승객은 태웠으나 남은 연료로 목적지 못데려갈경우.
			else {
				taxi.x = target_x;
				taxi.y = target_y;
				fuel += time;
				return false;
			}
		}
	}//end simulation.
}//end class.
