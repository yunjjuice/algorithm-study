package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17822_원판돌리기_200419 {
	static class Pos {
		int x, y;
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[][] map; // 원판 저장할 배열
	static int[][] check; // 인접하고 같은 숫자 없애줄때 bfs사용 -> 방문배열
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int flag; // 하나라도 없어지는 숫자가 있나 확인할 flag
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()); // 원판의 개수
		int m = Integer.parseInt(st.nextToken()); // 원판별 수의 개수
		int t = Integer.parseInt(st.nextToken()); // 회전 수
		
		ans = 0;
		map = new int[n][m]; // 원판 -> 2차원배열 [원판번호][원판안의 number index]
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int x, d, k;
		for(int v = 0; v < t; v++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			flag = 0; // flag 초기화
			check = new int[n][m]; // 방문배열 초기화
			move(x, d, k); // 원판 이동
			
			// 인접하고 같은 숫자 없애자
			for(int a = 0; a < n; a++) {
				for(int b = 0; b < m; b++) {
					if(map[a][b] != 0 && check[a][b] == 0) { // 원판 숫자가 0이아니고, 방문한적이 없어야함
						check[a][b] = 1; // 방문체크
						del(a, b); // 삭제하는 함수
					}
				}
			}
			
			if(flag == 0) { // 하나라도 숫자가 지워지지 않았으면
				avg(); // 평균을 구해서 + - 진행
			}
		}
		
		// 원판에 남아있는 수 확인해서 답 도출
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				ans += map[i][j];
			}
		}
		
		System.out.println(ans);
	}
	
	public static void avg() {
		int sum = 0, cnt = 0;
		// 평균 구하기
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] != 0) {
					cnt++;
					sum += map[i][j];
				}
			}
		}
		
		double avg = (double)sum / cnt;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] != 0) { // 숫자가 존재해야함
					if(avg < map[i][j]) { // 평균보다 크면 -
						map[i][j]--;
					} else if(avg > map[i][j]) { // 평균보다 작으면 +
						map[i][j]++;
					}
				}
			}
		}
	}

	public static void move(int x, int d, int k) { // 원판 회전
		for(int i = 0; i < map.length; i++) {
			if((i + 1) % x != 0) continue; // 배수가 아니면 넘어감
			int[] tmp = new int[map[i].length]; // 회전한 결과 저장할 임시 배열
			if(d == 0) { // 시계방향일때
				for(int j = 0; j < map[i].length; j++) {
					tmp[(j + k) % map[i].length] = map[i][j]; // 시계방향으로 k만큼 이동
				}
			} else { // 반시계방향일때
				for(int j = 0; j < map[i].length; j++) {
					tmp[j] = map[i][(j + k) % map[i].length]; // 반시계방향으로 k만큼 이동
				}
			}
			map[i] = tmp.clone(); // 임시배열을 원래배열에 저장
		}
	}
	
	public static void del(int x, int y) { // bfs로 인접하고 같은 숫자를 찾음
		int fg = 0; // 현재 정점에서 인접하고 같은 숫자가 있는지 확인할 flag변수
		Queue<Pos> q = new LinkedList<>(); // bfs진행을 위한 큐
		Queue<Pos> qq = new LinkedList<>(); // 인접한 모든 위치를 저장할 큐
		q.offer(new Pos(x, y));
		while(!q.isEmpty()) {
			Pos p = q.poll();
			int px = p.x;
			int py = p.y;
			for(int i = 0; i < 4; i++) {
				int nx = px + dx[i];
				int ny = (py + dy[i] + map[0].length) % map[0].length; // 같은 depth의 원판끼리는 index 0 과 index n-1이 인접해있음
				if(nx >= 0 && nx < map.length) { // 원판의 번호만 범위를 벗어나는지 확인
					if(check[nx][ny] == 0 && map[px][py] == map[nx][ny]) { // 방문한적없고 숫자가 자기자신과 같다면
						check[nx][ny] = 1; // 방문체크
						q.offer(new Pos(nx, ny)); // 큐에 삽입
						flag = 1; // 하나라도 인접한 숫자의 집합이 존재함
						fg = 1; // 현재 정점에서 인접하고 같은 숫자가 있음
						qq.offer(new Pos(nx, ny)); // 한꺼번에 지워주기위해 큐에저장
					}
				}
			}
		}
		if(fg == 1) qq.offer(new Pos(x, y)); // 하나라도 인접한게 있었다면 처음 자기자신도 큐에 저장
		
		// 큐에 저장했던 모든 정점을 0으로 갱신
		while(!qq.isEmpty()) {
			Pos p = qq.poll();
			map[p.x][p.y] = 0;
		}
	}
}
