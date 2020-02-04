import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_17135_캐슬디펜스_200204 {
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };
	static Point first = new Point(0, 0); // 첫번째 궁수 가장 가까운 적 위치
	static Point second = new Point(0, 0); // 두번째 궁수 가장 가까운 적 위치
	static Point third = new Point(0, 0); // 세번째 궁수 가장 가까운 적 위치
	static int ans = 0; // 각 조합 별 횟수 체크
	static int max = 0; // 최대값
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int limitDist = Integer.parseInt(st.nextToken());
		int[][] map = new int[n + 1][m];
		int[][] check = new int[n][m]; // visited check 배열
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input
		
		for(int i = 0; i < map[0].length - 2; i++) {
			for(int j = i + 1; j < map[0].length - 1; j++) {
				for(int k = j + 1; k < map[0].length; k++) {
					ans = 0; // 횟수 초기화
					int cnt = 0;
					int[][] copy = new int[n + 1][m];
					for(int[] temp : map){
			            copy[cnt++] = temp.clone(); 
			        } // map 복사 copy 배열 생성
					
					while(true) {
						check = new int[n][m]; // visited 초기화
						first = bfs(copy, check, n, i, limitDist); // 첫번째 궁수 가장 가까운 적 위치
						check = new int[n][m]; // visited 초기화
						second = bfs(copy, check, n, j, limitDist); // 두번째 궁수 가장 가까운 적 위치
						check = new int[n][m]; // visited 초기화
						third = bfs(copy, check, n, k, limitDist); // 세번째 궁수 가장 가까운 적 위치
						
						// limitDistance 안에 없으면 넘어감, 있으면 횟수 + 1, 중복 제외
						if(first.x != -1) {
							if(copy[first.x][first.y] == 1) ans++;
							copy[first.x][first.y] = 0;
						}
						if(second.x != -1) {
							if(copy[second.x][second.y] == 1) ans++;
							copy[second.x][second.y] = 0;
						}
						if(third.x != -1) {
							if(copy[third.x][third.y] == 1) ans++;
							copy[third.x][third.y] = 0;
						}
						
						// 배열 값 밑으로 한칸씩 이동
						for(int a = n - 1; a >= 1; a--) {
							for(int b = 0; b < m; b++) {
								copy[a][b] = copy[a - 1][b];
							}
						}
						for(int b = 0; b < m; b++) {
							copy[0][b] = 0;
						}
						
						// 적이 남아있는지 확인
						if(clear(copy)) {
							break;
						}
					}
					if(ans > max) max = ans; // 최댓값인지 확인
				}
			}
		}
		
		System.out.println(max);
	}
	
	public static boolean clear(int[][] map) { // map clear 됐는지 확인
		for(int i = 0; i < map.length - 1; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static Point bfs(int[][] map, int[][] check, int x, int y, int limitDist) { // 각 궁수별로 가장 가까운 왼쪽의 적 확인, point로 return
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(x, y));
		while(!q.isEmpty()) {
			int r = q.peek().x;
			int c = q.peek().y;
			q.poll();
			if(r == map.length - 1) { // 처음 출발할때는 바로 앞의 적만 제거 가능
				q.offer(new Point(x - 1, y));
				check[x - 1][y] = 1;
				if(map[x - 1][y] == 1) { // 적이 있다면 return
					return new Point(x - 1, y);
				}
			} else {
				for(int i = 0; i < 3; i++) {
					if(r + dx[i] >= 0 && c + dy[i] < map[0].length && c + dy[i] >= 0) {
						if(check[r + dx[i]][c + dy[i]] == 0 && check[r][c] < limitDist) { // limitDist 까지만 탐색
							check[r + dx[i]][c + dy[i]] = check[r][c] + 1;
							q.offer(new Point(r + dx[i], c + dy[i]));							
							if(map[r + dx[i]][c + dy[i]] == 1) { // 적이 있다면 return
								return new Point(r + dx[i], c + dy[i]);
							}
						}
					}
				}
			}
		}
		
		return new Point(-1, -1); // 적이 없다면 -1, -1 return
	}
}