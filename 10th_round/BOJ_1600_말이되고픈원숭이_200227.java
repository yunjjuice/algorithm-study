import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Posi { // 현 위치, 남은 k 회수, 최소 거리
	int x;
	int y;
	int k;
	int cnt;
	public Posi(int x, int y, int k, int cnt) {
		this.x = x;
		this.y = y;
		this.k = k;
		this.cnt = cnt;
	}
}

public class BOJ_1600_말이되고픈원숭이_200227 {
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {-1, 1, 0, 0};
	// 말 처럼 움직일 수 있는 좌표
	static int[] horseX = {2, 1, 2, 1, -2, -1, -2, -1};
	static int[] horseY = {1, 2, -1, -2, 1, 2, -1, -2};
	static int[][] map;
	static int[][][] visited;
	static int px, py, pk, pc, nx, ny;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		map = new int[h][w];
		visited = new int[h][w][k + 1]; // 각 좌표에 따라 사용한 말의 움직임의 개수를 달리 세줌
		for(int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input
		
		bfs(k); // go
	}
	
	public static void bfs(int k) {
		Queue<Posi> q = new LinkedList<>();
		q.offer(new Posi(0, 0, k, 0));
		visited[0][0][k] = 1;
		while(!q.isEmpty()) {
			px = q.peek().x;
			py = q.peek().y;
			pk = q.peek().k; // 남은 말처럼 움직일 수 있는 횟수
			pc = q.peek().cnt; // 최단 거리
			q.poll();
			if(px == map.length - 1 && py == map[0].length - 1) { // 끝에 도착했다면 return
				System.out.println(pc);
				return;
			}
			
			// 그냥 움직이기
			for(int i = 0; i < 4; i++) {
				nx = px + dx[i];
				ny = py + dy[i];
				if(nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length) {
					if(visited[nx][ny][pk] == 0 && map[nx][ny] == 0) {
						visited[nx][ny][pk] = 1; // 말처럼 움직인 횟수 줄지 않음
						q.offer(new Posi(nx, ny, pk, pc + 1)); // 최단거리 + 1 해서 queue에 저장
					}
				}
			}

			// 말처럼 움직이기
			if(pk > 0) { // 말처럼 움직일 수 있는 횟수가 남아있어야 가능
				for(int i = 0; i < 8; i++) {
					nx = px + horseX[i];
					ny = py + horseY[i];
					if(nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length) {
						if(visited[nx][ny][pk - 1] == 0 && map[nx][ny] == 0) {
							visited[nx][ny][pk - 1] = 1; // 말처럼 움직인 횟수 하나 줄음
							q.offer(new Posi(nx, ny, pk - 1, pc + 1)); // 최단거리 + 1해서 queue에 저장
						}
					}
				}
			}
		}
		System.out.println(-1); // return 못했다면 -1 출력
	}
}
