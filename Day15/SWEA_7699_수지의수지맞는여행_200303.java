/*
 * SWEA 7699 수지의 수지 맞는 여행 D4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA7699 {
	static int r, c; // r:행 c:열
	static char[][] map;
	static boolean[] alpha;
	static int[] dx = {-1, 1, 0, 0}; // 상하좌우
	static int[] dy = {0, 0, -1, 1};
	static int ans;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			map = new char[r][c];
			for (int i = 0; i < r; i++) {
				String s = br.readLine();
				for (int j = 0; j < c; j++) {
					map[i][j] = s.charAt(j);
				}
			}
			
//			// 입력 확인
//			for (int i = 0; i < r; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			alpha = new boolean[26]; // ABCDEFGHIJKLMNOPQRSTUVWXYZ
			ans = 0;
			alpha[map[0][0] - 'A'] = true;
			dfs(0, 0, 1);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int x, int y, int day) {
		if(ans < day)
			ans = day;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || nx >= r || ny < 0 || ny >= c)
				continue;
			if(!alpha[map[nx][ny] - 'A']) {
				alpha[map[nx][ny] - 'A'] = true;
				dfs(nx, ny, day+1);
				alpha[map[nx][ny] - 'A'] = false;
			}
		}
	}
}