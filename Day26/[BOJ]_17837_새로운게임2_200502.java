import java.io.*;
import java.util.*;

public class BOJ_새로운게임 {//종료 조건 : 4개이상 쌓이면 종료.
	static class Pos{
		int x;
		int y;
		int d;
		int top;
		int idx;
		public Pos(int x, int y, int d, int idx) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.idx = idx;
		}
	}
	static int N;
	static int K;
	static ArrayList<Pos>[][] Pos_map;
	static Pos[] list;
	static int[][] map;
	static int[][] direction = {{0,0},{0,1},{0,-1},{-1,0},{1,0}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Pos_map = new ArrayList[N+1][N+1];
		map = new int[N+1][N+1];
		list = new Pos[K];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//맵 그리기.
		for(int i=1;i<=N;i++) {
			for(int j=1; j<=N; j++) {
				Pos_map[i][j] = new ArrayList<>();
			}
		}//2차원 Pos배열 만들어주기.
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int dx = Integer.parseInt(st.nextToken());
			int dy = Integer.parseInt(st.nextToken());
			int direct = Integer.parseInt(st.nextToken());
			list[i] = new Pos(dx,dy,direct,i);
			if(Pos_map[dx][dy] == null) {
				list[i].top = 0;
				Pos_map[dx][dy].add(list[i]);
			}
			else {
				list[i].top = Pos_map[dx][dy].size();
				Pos_map[dx][dy].add(list[i]);
			}//처음 말 놓기.
		}
		int time=0;
		boolean end = false;
		while(time!=1000) {
			if(end) break;
			for(int i=0; i<K; i++) {
				int turn_cnt = 0;
				while(turn_cnt!= 2) {
					int dx = list[i].x;
					int dy = list[i].y;
					int dtop = list[i].top;
					int new_x = dx + direction[list[i].d][0];
					int new_y = dy + direction[list[i].d][1];
					if(new_x>0 && new_x <=N && new_y>0 && new_y<=N) {
						if(map[new_x][new_y] == 0) {
							for(int j=dtop; j<Pos_map[dx][dy].size(); j++) {
								move(Pos_map[dx][dy].get(j) , new_x , new_y);
							}
							for(int j=Pos_map[dx][dy].size()-1; j>=dtop; j--) {
								Pos_map[dx][dy].remove(j);
							}
							break;
						}
						else if(map[new_x][new_y] == 1) {
							for(int j=Pos_map[dx][dy].size()-1; j>=dtop; j--) {
								move(Pos_map[dx][dy].get(j) , new_x , new_y);
								Pos_map[dx][dy].remove(j);
							}//옮기고 삭제.
							break;
						}
						else {
							if(turn_cnt == 0) {
								if(list[i].d % 2 == 1) list[i].d +=1;
								else list[i].d -= 1;
							}
							turn_cnt++;
						}//흰,빨,파
					}
					else {//인덱스 밖
						if(list[i].d % 2 == 1) list[i].d +=1;
						else list[i].d -= 1;
						turn_cnt++;
					}//맵 밖이라서 방향바꿔주기.
				}//end while.
				
				if(Pos_map[list[i].x][list[i].y].size() >=4) {
					end = true;
					break;
				}
			}//선수별 움직이기.
			time++;
		}//end while.
		if(time==1000) System.out.println(-1);
		else System.out.println(time);
	}//end main.
	
	public static void move(Pos p, int dx ,int dy) { //업데이트 해주기.
		list[p.idx].x = dx;
		list[p.idx].y = dy;
		if(Pos_map[dx][dy] == null) {
			list[p.idx].top = 0;
			Pos_map[dx][dy].add(list[p.idx]);
		}
		else {
			list[p.idx].top = Pos_map[dx][dy].size();
			Pos_map[dx][dy].add(list[p.idx]);
		}
	}//end move.
	
}//end class.
