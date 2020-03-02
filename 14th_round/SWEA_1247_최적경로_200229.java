import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Position {
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class SWEA_1247_최적경로_200229 {
	static int[] direction;
	static Position[] arr;
	static int[][] dist;
	static boolean[] visited;
	static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; testcase++) {
			int n = Integer.parseInt(br.readLine());
			dist = new int[n + 2][n + 2];
			arr = new Position[n + 2];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < n + 2; i++) {
				arr[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			for(int i = 0; i < n + 2; i++) {
				for(int j = 0; j < n + 2; j++) {
					dist[i][j] = Math.abs(arr[i].x - arr[j].x) + Math.abs(arr[i].y - arr[j].y);
				}
			}
			
			min = Integer.MAX_VALUE;
			visited = new boolean[n + 2];
			
			findShortPath(0, 0, 0);

			sb.append("#").append(testcase).append(" ").append(min).append('\n');	
		}
		System.out.print(sb);
	}
	
	public static void findShortPath(int now, int depth, int res) {
		if(depth == visited.length - 2) {
			res += dist[now][1];
			min = min > res ? res : min;
			res -= dist[now][1];
			return;
		} else if(min < res) return;
		
		for(int i = 2; i < visited.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				res += dist[now][i];
				findShortPath(i, depth + 1, res);
				res -= dist[now][i];
				visited[i] = false;
			}
		}
	}
}
