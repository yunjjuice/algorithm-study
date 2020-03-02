import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2814_최장경로_200229 {
	static int[][] map;
	static boolean[] visited;
	static int ans;
	static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m= Integer.parseInt(st.nextToken());
			
			map = new int[n + 1][n + 1];
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = map[y][x] = 1;
			}
			
			ans = 1;
			for(int i = 1; i <= n; i++) {
				max = 1;
				visited = new boolean[n + 1];
				visited[i] = true;
				dfs(i);	
			}
			
			sb.append("#").append(testcase).append(" ").append(ans).append('\n');
		}
		System.out.print(sb);
	}
	
	public static void dfs(int now) {
		if(max > ans) ans = max;
		for(int i = 1; i < map.length; i++) {
			if(!visited[i] && map[now][i] == 1) {
				visited[i] = true;
				max++;
				dfs(i);
				max--;
				visited[i] = false;
			}
		}
	}
}
