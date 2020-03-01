import java.io.*;
import java.util.*;

public class BOJ_3967_매직스타 {
	
	static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}//class Pos.
	
	static char[][] map = new char[5][9];
	static ArrayList<Pos> list = new ArrayList<>();
	static boolean[] visited = new boolean[12];
	static boolean end = false;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5; i++) {
			String temp = br.readLine();
			for(int j=0; j<temp.length(); j++){
				map[i][j] = temp.charAt(j);
				if(map[i][j] == '.') continue;
				if(map[i][j] == 'x') list.add(new Pos(i,j));
				else visited[map[i][j] - 65] = true;
			}
		}//end input.
		dfs(0);
	}//end main.
	public static void dfs(int cnt) {
		if(end) return ;
		if(cnt == list.size()) {
			if(calculate()) {
				for(int i=0; i<5; i++) {
					for(int j=0; j<9; j++) {
						System.out.print(map[i][j]);
					}
					System.out.println("");
				}
			}
			return ;
		}
		Pos p = list.get(cnt);
		for(int i=0; i<12; i++) {
			if(end) break;
			if(!visited[i]) {
				visited[i] = true;
				map[p.x][p.y] = (char)('A'+i);
				dfs(cnt+1);
				visited[i] = false;
				map[p.x][p.y] = 'x';
			}
		}
	}
	public static boolean calculate() {
		end = true;
		if(map[0][4] + map[1][3] + map[2][2] + map[3][1] - 256 != 26) {
			end = false;
			return end;
		}
		if(map[0][4] + map[1][5] + map[2][6] + map[3][7] - 256 != 26) {
			end = false;
			return end;
		}
		if(map[1][1] + map[1][3] + map[1][5] + map[1][7] - 256 != 26) {
			end = false;
			return end;
		}
		if(map[1][1] + map[2][2] + map[3][3] + map[4][4] - 256 != 26) {
			end = false;
			return end;
		}
		if(map[3][1] + map[3][3] + map[3][5] + map[3][7] - 256 != 26) {
			end = false;
			return end;
		}
		if(map[1][7] + map[2][6] + map[3][5] + map[4][4] - 256 != 26) {
			end = false;
			return end;
		}
		return end;
	}//end calculate.
}//end class.
