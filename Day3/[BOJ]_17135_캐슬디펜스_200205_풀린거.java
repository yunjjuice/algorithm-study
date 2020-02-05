import java.util.Scanner;

public class BOJ_캐슬디펜스 {
	
	static int row; //행
	static int col; //열
	static int D;	//사거리.
	static int[][] map; //초기 맵.
	static int[][] game_map; //카피 맵.
	static int max_kill = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		row = sc.nextInt();
		col = sc.nextInt();
		D = sc.nextInt();
		map = new int[row+1][col];
		game_map = new int[row+1][col];
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				map[i][j] = sc.nextInt();
			}
		}//맵 그리기.
		dfs(0,0);//조합 시작.
		System.out.println(max_kill);
	}
	
	public static void dfs(int index, int count) {
		if (index >= col) return; //범위 넘어가면 리턴.
		if (count == 3) {
			for (int i = 0; i <= row; i++) {
				for (int j = 0; j < col; j++) {
					game_map[i][j] = map[i][j];
				}
			}
			int temp_num = game_Start();
			if(temp_num > max_kill) max_kill = temp_num;
			return ;
		}
		map[row][index] = 5;
		dfs(index + 1, count+1);
		map[row][index] = 0;
		dfs(index + 1, count);
	}//조합을 통한 궁수 위치 맵에 그리기.
	
	public static int game_Start() {
		int sum = 0;//킬 수 계산.
		int top = -1;
		int[] archer_pos = new int[3]; //궁수의 y좌표.
		for (int i = 0; i < col; i++) {
			if (game_map[row][i] == 5) {
				archer_pos[++top] = i;	
			}
		}
		while (!end()) {
			int[][] enemy_pos = new int[3][2];//적의 좌표.
			for(int i=0; i<3; i++) {
				int dx = row;
				int dy = archer_pos[i];
				int min_distance = Integer.MAX_VALUE;
				boolean can_kill = false;
				for(int x1=0; x1<row; x1++) {
					for (int y1 = 0; y1 < col; y1++) {
						if(game_map[x1][y1] == 1) {
							int temp_D = Math.abs(dx-x1) + Math.abs(dy-y1);
							if(temp_D <= D) {
								if(min_distance > temp_D) {
									can_kill = true;
									enemy_pos[i][0] = x1;
									enemy_pos[i][1] = y1;
								}
								else if(min_distance == temp_D) {
									if (y1 < enemy_pos[i][1]) {
										can_kill = true;
										enemy_pos[i][0] = x1;
										enemy_pos[i][1] = y1;
									}
								}
							}
						}
					}
				}
				if(!can_kill) enemy_pos[i][0] = -1;
			}//각 궁수별로 가장 가까운 적 위치 반환.
			for(int i=0; i<3; i++) {
				if(enemy_pos[i][0]!= -1) {
					if(game_map[enemy_pos[i][0]][enemy_pos[i][1]] != 0) {
						sum++;
						game_map[enemy_pos[i][0]][enemy_pos[i][1]] = 0;
					}
				}
			}
			move_enemy(game_map);
		}//end while.
		return sum;
	}//end game.
	public static boolean end() {
		for(int i=0; i<row; i++) {
			for (int j = 0; j < col; j++) {
				if(game_map[i][j] ==1) return false;
			}
		}
		return true;
	}//check game set.
	public static void move_enemy(int[][] game_map) {
		for (int i = 0; i < row-1; i++) {
			for (int j = 0; j < col; j++) {
				game_map[row-(i+1)][j] = game_map[row-(i+2)][j];
			}
		}
		for (int j = 0; j < col; j++) {
			game_map[0][j] = 0;
		}
	}
}
