import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class XY {
	int x;
	int y;
	public XY(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class SWEA_7699_수지의수지맞는여행_200303 {
	static char[][] map;
	static HashSet<Character> hs;
	static int[] dx = {1, 0, 0, -1};
	static int[] dy = {0, -1, 1, 0};
	static int[][] visited;
	static int ans;
	static int cnt;
	static int flag;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; ++testcase) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map = new char[r][c];
			for(int i = 0; i < r; i++) {
				String str = br.readLine();
				for(int j = 0; j < c; j++) {
					map[i][j] = str.charAt(j);
				}
			} // input

			ans = 0;
			cnt = 1;
			visited = new int[r][c];
			visited[0][0] = 1;
			
			hs = new HashSet<>();
			hs.add(map[0][0]);
			dfs(0, 0);
			
			sb.append("#").append(testcase).append(" ").append(ans).append('\n');
		}
		System.out.print(sb);
	}
	
	public static void dfs(int px, int py) {
		if(cnt > ans) ans = cnt;
		if(ans == 26) return;
		for(int i = 0; i < 4; i++) {
			int nx = px + dx[i];
			int ny = py + dy[i];
			if(nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length) {
				if(visited[nx][ny] == 0 && !hs.contains(map[nx][ny])) {
					visited[nx][ny] = 1;
					cnt++;
					hs.add(map[nx][ny]);
					dfs(nx, ny);
					cnt--;
					visited[nx][ny] = 0;
					hs.remove(map[nx][ny]);
				}
			}
		}
	}
}
