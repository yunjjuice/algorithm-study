import java.io.*;
import java.util.*;

public class BOJ_19237_어른상어 {
	static class Ocean{
		int num;
		int time;
		boolean shark;
		public Ocean (int num, int time, boolean shark){
			this.num = num;
			this.time = time;
			this.shark = shark;
		}
	}
	static int N;
	static int M;
	static int K;
	static int[][] shark_pos;
	static Ocean[][] map;
	static int[][] direction = {{0,0},{-1,0},{1,0},{0,-1},{0,1}};
	static int[][][] shark_db;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Ocean[N][N];
		shark_pos = new int[M+1][3];
		shark_db = new int[M+1][5][4];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp !=0) {
					map[i][j] = new Ocean(tmp,K,true);
					shark_pos[tmp][0] = i;
					shark_pos[tmp][1] = j;
				}
				else map[i][j] = new Ocean(tmp,0,false);
			}
		}//일단 위치정보 저장.
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++) {
			shark_pos[i][2] = Integer.parseInt(st.nextToken());
		}//처음 방향 저장.
		for(int i=1; i<=M; i++) {
			for(int j=1; j<=4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<4; k++) {
					shark_db[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		int time = 1;
		while(time<=1000) {
			simulation();
			boolean can_end = true;
			for(int i=2; i<=M; i++) {
				if(shark_pos[i][0] != -1) {
					can_end = false;
					break;
				}//2번상어부터~ 아직 좌표내에 하나라도 있다? 시뮬돌려야한다.
			}
			if(can_end) break;
			time++;
		}
		if(time>1000) System.out.println(-1);
		else System.out.println(time);
	}//end main.
	public static void simulation() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j].num !=0 && !map[i][j].shark) {
					map[i][j].time--;
					if(map[i][j].time ==0) map[i][j].num = 0; //냄새 없는곳으로 바꿔주기.
				}
			}
		}//냄새 카운트 줄이기!
		
		for(int i=1; i<=M; i++) {
			if(shark_pos[i][0] != -1) { //죽은 상어 아니면 시뮬 돌리기.
				int now_x = shark_pos[i][0];
				int now_y = shark_pos[i][1];
				int now_direction = shark_pos[i][2];
				boolean find_empty = false;
				for(int k=0; k<4; k++) {
					int new_x = now_x + direction[shark_db[i][now_direction][k]][0];
					int new_y = now_y + direction[shark_db[i][now_direction][k]][1];
					if(new_x>=0 && new_x<N && new_y>=0 && new_y<N && map[new_x][new_y].num == 0) {
						shark_pos[i][0] = new_x;
						shark_pos[i][1] = new_y;
						shark_pos[i][2] = shark_db[i][now_direction][k];
						find_empty = true;
						break;
					}
				}//일단 빈공간 찾는 4번의 움직임.
				if(!find_empty) { //못찾았을경우 우선순위로 내 냄새 찾아가기.
					for(int k=0; k<4; k++) {
						int new_x = now_x + direction[shark_db[i][now_direction][k]][0];
						int new_y = now_y + direction[shark_db[i][now_direction][k]][1];
						if(new_x>=0 && new_x<N && new_y>=0 && new_y<N && map[new_x][new_y].num == i) {
							shark_pos[i][0] = new_x;
							shark_pos[i][1] = new_y;
							shark_pos[i][2] = shark_db[i][now_direction][k];
							break;
						}
					}
				}//end if.
				map[now_x][now_y].shark = false;
			}//end if.
		}//상어 움직이기 끝!
		for(int i=1; i<=M; i++) {
			if(shark_pos[i][0] != -1) {
				int x = shark_pos[i][0];
				int y = shark_pos[i][1];
				if(!map[x][y].shark) {
					map[x][y].num = i;
					map[x][y].time = K;
					map[x][y].shark = true;
				}
				else {
					shark_pos[i][0] = -1;
					shark_pos[i][1] = -1;
				}
			}
		}//상어 움직이기.
	}//end simulation.
}//end class.
