/* 7699. 수지의 수지 맞는 여행 D4
 * dfs
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {-1, 0, 1, 0}, dy = {0,-1,0,1};
	static int answer;
	static int R, C;
	static char[] route;
	static char[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
			//R, C: 섬의 크기
			map = new char[R][C];
			for (int i = 0; i < map.length; i++) {
				String s = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = s.charAt(j);
				}
			}
			//end of input
			
			answer = 0;
			route = new char[26]; //알파벳은 최대 26개 ~
			route[0] = map[0][0];
			dfs(0,0, 1);
			
			//output
			sb.append('#').append(testCase).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}
	
	static void dfs(int x, int y, int index) {
		if(index > answer)	answer = index;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i], ny = y+dy[i];
			if(nx<0 || ny<0|| nx>=R||ny>=C
					|| contains(map[nx][ny], index))	continue; //범위 초과 or 이미 본 명물임
			route[index] = map[nx][ny];
			dfs(nx, ny, index+1);
		}
	}
	
	static boolean contains(char c, int index) {
		for(int i=0; i<index; i++) {
			if(route[i] == c)	return true;
		}
		return false;
	}
}
