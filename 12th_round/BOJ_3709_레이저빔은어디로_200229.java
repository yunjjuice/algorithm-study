import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Laser {
	int x;
	int y;
	public Laser(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class BOJ_3709_레이저빔은어디로_200229 {
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1};
	static int[] dy = { 1, 0, -1, 0};
	static StringBuilder sb;
	static int limit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			map = new int[n + 2][n + 2];
			
			for(int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine());
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int startDir = 0;
			
			if(startX == 0) startDir = 1;
			else if(startX == n + 1) startDir = 3;
			else if(startY == 0) startDir = 0;
			else startDir = 2;
			
			limit = 0;
			go(startX, startY, startDir);
			if(limit > 2500) sb.append(0).append(" ").append(0).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void go(int x, int y, int dir) {
		limit++;
		if(limit > 2500) {
			return;
		}
		if(map[x][y] == 1) dir = (dir + 1) % 4;
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if(nx == 0 || ny == 0 || nx == map.length - 1 || ny == map.length - 1) {
			sb.append(nx).append(" ").append(ny).append('\n');
			return;
		}
		go(nx, ny, dir);
		
		
	}
}
