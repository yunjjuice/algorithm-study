import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Castle {
	int x;
	int y;
	public Castle(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class SWEA_1907_모래성쌓기_200303 {
	static int ans;
	static int[][] map;
	static List<Castle> list;
	static Queue<Castle> q;
	static int[][] visited;
	static int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
	static int[] dy = {1, -1, 0, 1, -1, 0, 1, -1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; ++testcase) {
			st = new StringTokenizer(br.readLine(), " ");
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			map = new int[h][w];
			q = new LinkedList<Castle>();
			for(int i = 0; i < h; i++) {
				String str = br.readLine();
				for(int j = 0; j < w; j++) {
					char ch = str.charAt(j);
					if(ch == '.') map[i][j] = 0;
					else {
						map[i][j] = ch - '0';
					}
					
				}
			} // input
			visited = new int[h][w]; // 주변 8구간 중 0인 부분이 자신보다 큰지 작은지 알려주는 배열
			ans = 0;
			
			// 처음 없어질 값들 큐에 세팅
			for(int i = 1; i < h - 1; i++) {
				for(int j = 1; j < w - 1; j++) {
					if(map[i][j] > 0 && map[i][j] < 9) {
						for(int k = 0; k < 8; k++) {
							if(map[i + dx[k]][j + dy[k]] == 0) {
								visited[i][j]++;
							}
						}
						// 주변 8구간 중 0이 자기자신보다 크면 q에 넣음
						if(map[i][j] <= visited[i][j]) q.offer(new Castle(i, j));
					}	
				}
			}

			while(true) {
				list = new ArrayList<>();
				int size = q.size();
				for(int i = 0; i < size; i++) {
					Castle c = q.poll();
					list.add(new Castle(c.x, c.y)); // 없앨 모래성 저장하는 list에 넣기
					for(int j = 0; j < 8; j++) {
						// 다음 바람이 불때 없앨 모래성 찾는 과정
						if(map[c.x + dx[j]][c.y + dy[j]] > visited[c.x + dx[j]][c.y + dy[j]] && map[c.x + dx[j]][c.y + dy[j]] < 9) {
							visited[c.x + dx[j]][c.y + dy[j]]++;
							// 다음에 없앨 모래성 찾으면 q에 넣음
							if(map[c.x + dx[j]][c.y + dy[j]] <= visited[c.x + dx[j]][c.y + dy[j]]) q.offer(new Castle(c.x + dx[j], c.y + dy[j]));
						}
					}
				}
				
				// 없어질 모래성 없애기
				for(int i = 0; i < list.size(); i++) {
					Castle c = list.get(i);
					map[c.x][c.y] = 0;
				}

				// 없앨 모래성이 없으면 break;
				if(list.size() == 0) break;
				ans++;
			}

			sb.append("#").append(testcase).append(" ").append(ans).append('\n');
		}
		System.out.print(sb);
		
	}
}
