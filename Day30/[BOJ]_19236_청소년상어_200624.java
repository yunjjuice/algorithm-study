import java.io.*;
import java.util.*;

public class BOJ_19236_청소년상어 {
	static int max_weight = 0;
	static int[][] direct = {{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] map = new int[4][4];
		int[][] dir = new int[4][4];
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				int number = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				map[i][j] = number;
				dir[i][j] = direction;
			}
		}//end input;
		dfs(0,0,0,map,dir); //파라미터: 상어가 갈 x좌표 / y좌표 / 먹은 총합 / 현재 맵 상태  / 현재 순서별 상어 좌표.
		System.out.println(max_weight);
	}//end main.
	public static void dfs(int x, int y, int weight, int[][] map , int[][] dir) {
		int now_weight = weight + map[x][y];
		if(now_weight > max_weight) max_weight = now_weight;
		int[][] map2 = new int[4][4];
		int[][] dir2 = new int[4][4];
		for(int i=0;i<4;i++) {
			for(int j=0; j<4; j++) {
				map2[i][j] = map[i][j];
				dir2[i][j] = dir[i][j];
			}
		}//복사하기.
		map2[x][y] = -1; //상어임을 알려줌.
		for(int num=1;num<=16;num++) {
			ex: for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					if(map2[i][j]==num) { //이제 돌리기 시작.
						for(int k=0; k<8; k++) {
							int new_dir = (dir2[i][j] + k) % 8;
							int new_x = i + direct[new_dir][0];
							int new_y = j + direct[new_dir][1];
							if(new_x>=0 && new_x<4 && new_y>=0 && new_y<4 && map2[new_x][new_y] != -1){
								int tmp_num = map2[new_x][new_y];
								int tmp_dir = dir2[new_x][new_y];
								map2[new_x][new_y] = num;
								dir2[new_x][new_y] = new_dir;
								map2[i][j] = tmp_num;
								dir2[i][j] = tmp_dir;
								break ex;
							}
						}
					}//end if.
				}//end j.
			}//end i.
		}//end num.
		
		for(int k=1;k<=3;k++) {
			int new_x= x + k*direct[dir2[x][y]][0];
			int new_y= y + k*direct[dir2[x][y]][1];
			if(new_x>=0 && new_x<4 && new_y>=0 && new_y<4 && map2[new_x][new_y] != 0) {
				map2[x][y] = 0;
				dfs(new_x,new_y,now_weight,map2,dir2);
			}
		}
	}//end dfs.
}//end class.
