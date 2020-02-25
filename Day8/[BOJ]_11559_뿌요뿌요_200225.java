import java.io.*;
import java.util.*;

public class BOJ_뿌요뿌요 {
	static char[][] map = new char[12][6];
	static boolean[][] visited;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}//end Pos.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<12; i++) {
			String temp =  br.readLine();
			for(int j=0; j<6; j++) {
				map[i][j] = temp.charAt(j);
			}
		}//draw map.
		boolean end = false;
		int answer = -1;
		Queue<Pos> q = new LinkedList();
		List<Pos> list;
		while(!end) {
			answer++;
			visited = new boolean[12][6];
			end = true;
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(map[i][j] != '.' && !visited[i][j]) {
						list = new ArrayList();
						q.add(new Pos(i,j));
						visited[i][j] = true;
						char color = map[i][j];
						while(!q.isEmpty()) {
							Pos p = q.poll();
							list.add(new Pos(p.x,p.y));
							for(int k=0; k<4; k++) {
								int new_x = p.x + direction[k][0];
								int new_y = p.y + direction[k][1];
								if(new_x>=0 && new_x<12 && new_y>=0 && new_y<6 && map[new_x][new_y] == color && !visited[new_x][new_y]) {
									visited[new_x][new_y] = true;
									q.add(new Pos(new_x,new_y));
								}//q에 넣는 조건.
							}//4방향 검사.
						}//구역 사이즈 정하기.
						if(list.size() >= 4) {
							end = false;
							for(int k=0; k<list.size(); k++) {
								map[list.get(k).x][list.get(k).y] = '.';
							}//모든 리스트에 있는 값 . 으로 초기화.
						}//지워지는 경우.
					}//문자를 만났을 경우.
				}
			}//end for.
			for(int dx=11; dx>=0; dx--) {
				for(int dy=5; dy>=0; dy--) {
					if(map[dx][dy]!='.') {
						int down = 1;
						while(true) {
							if(dx+down>=12) break;
							if(map[dx+down][dy] != '.') break;
							down++;
						}
						if(down-1 != 0) {
							map[dx+down-1][dy] = map[dx][dy];
							map[dx][dy] = '.';
						}
					}
				}
			}//뒤에서부터 중력추가.
		}//end bfs.
		System.out.println(answer);
	}//end main.
}//end class.
