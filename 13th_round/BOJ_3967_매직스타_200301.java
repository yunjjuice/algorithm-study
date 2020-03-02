import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class BOJ_3967_매직스타_200301 {
	static int[][] map;
	static int[] check;
	static int flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[5][9];
		check = new int[13];
		
		for(int i = 0; i < 5; i++) {
			String str = br.readLine();
			for(int j = 0; j < 9; j++) {
				char ch = str.charAt(j);
				if(ch == '.') map[i][j] = -1;
				else if(ch == 'x') map[i][j] = 0;
				else {
					map[i][j] = ch - 'A' + 1;
					check[map[i][j]] = 1;
				}
			}
		} // input

		flag = 0;
		fill(0,0);
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 9; j++) {
				if(map[i][j] == -1) System.out.print(".");
				else System.out.print((char)(map[i][j] + 'A' - 1));
			}
			System.out.println();
		}
	}
	
	public static boolean check() {
		if(map[0][4] + map[1][3] + map[2][2] + map[3][1] > 26) return false;
		if(map[1][1] + map[1][3] + map[1][5] + map[1][7] > 26) return false;
		if(map[0][4] + map[1][5] + map[2][6] + map[3][7] > 26) return false;
		if(map[3][1] + map[3][3] + map[3][5] + map[3][7] > 26) return false;
		if(map[1][1] + map[2][2] + map[3][3] + map[4][4] > 26) return false;
		if(map[1][7] + map[2][6] + map[3][5] + map[4][4] > 26) return false;
		return true;
	}
	
	public static void fill(int x, int y) {
		
		if(!check()) return;
		if(x == 1 && y == 7 && map[1][1] + map[1][3] + map[1][5] + map[1][7] != 26) return;
		if(x == 3 && y == 1 && map[0][4] + map[1][3] + map[2][2] + map[3][1] != 26) return;
		if(x == 3 && y == 7) {
			if(map[0][4] + map[1][5] + map[2][6] + map[3][7] != 26 || map[3][1] + map[3][3] + map[3][5] + map[3][7] != 26) return;
		}
		if(x == 4 && y == 4) {
			if(map[1][1] + map[2][2] + map[3][3] + map[4][4] != 26 || map[1][7] + map[2][6] + map[3][5] + map[4][4] != 26) return;
		}
		if(map[0][4] + map[1][3] + map[2][2] + map[3][1] == 26 && map[1][1] + map[1][3] + map[1][5] + map[1][7] == 26
				&& map[0][4] + map[1][5] + map[2][6] + map[3][7] == 26 && map[3][1] + map[3][3] + map[3][5] + map[3][7] == 26
				&& map[1][1] + map[2][2] + map[3][3] + map[4][4] == 26 && map[1][7] + map[2][6] + map[3][5] + map[4][4] == 26) {
			flag = 1;
			return;
		}
		for(int i = x; i < 5; i++) {
			for(int j = 0; j < 9; j++) {
				if(map[i][j] == 0) {
					for(int k = 1; k < 13; k++) {
						if(check[k] == 0) {
							check[k] = 1;
							map[i][j] = k;
							fill(i, j);
							if(flag == 1) return;
							map[i][j] = 0;
							check[k] = 0;
						}
					}
				}
			}
		}
	}
}
