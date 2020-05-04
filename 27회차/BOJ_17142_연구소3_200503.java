import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 바이러스 개수에서 M개를 뽑아 해당 위치에서 bfs
 * 연구소의 모든칸에 바이러스로 가득차는 시간 검사
 * 
 * 최대가지수 10C5 = 252
 */
public class BOJ_17142_연구소3_200503 {
	static class loc{	// 바이러스 위치를 저장할 loc클래스
		int r;
		int c;
		public loc(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}	// end of class
	static int[] dx = {-1,1,0,0};	// 상,하,좌,우
	static int[] dy = {0,0,-1,1};
	static int[][] lab;
	static boolean[][] check;	// 바이러스 체크용
	static boolean[] visited;	// 조합 체크용
	static ArrayList<loc> virusLoc;	// 바이러스 위치 저장 리스트
	static int N;
	static int M;
	static int minTime = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());	// 연구소 크기
		M = Integer.parseInt(st.nextToken());	// 놓을 수 있는 바이러스 수
		
		lab = new int[N+2][N+2];
		check = new boolean[N+2][N+2];	// 감염되었는지 체크
		
		virusLoc = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j <= N; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if(lab[i][j] == 1) {	// 벽
					lab[i][j] = 0;		// 시작전 바이러스 감염되어있음
					check[i][j] = true;	// 벽은 감염되었다고 침
				} else if(lab[i][j] == 2) {	// 바이러스
					lab[i][j] = -1;		// 시작 전 바이러스 감염되어있음
//					check[i][j] = true;
					virusLoc.add(new loc(i, j));	// 바이러스 위치저장
				}
			}
		}	// end of for (input)
		
		visited = new boolean[virusLoc.size()];
		combination(0, virusLoc.size(), M);	// 바이러스 개수에서 M개를 뽑음
		
		//결과값이 변한게 없으면 -1 출력
		System.out.println(minTime==Integer.MAX_VALUE?-1:minTime);
	}	// end of main
	
	static void combination(int start, int n, int r) {
	    if(r == 0) {	// 결정되면
	        bfs();	// bfs돌림
	        return;
	    } 

	    for(int i=start; i<n; i++) {
	        visited[i] = true;
	        combination(i + 1, n, r - 1);
	        visited[i] = false;
	    }
	}	// end of method (comb)
	
	static void bfs() {
		Queue<loc> queue = new LinkedList<>();
		for (int i = 0; i < visited.length; i++) {
			if(visited[i]) {
				queue.add(virusLoc.get(i));
			}
		}
		while(!queue.isEmpty()) {
			loc tmp = queue.poll();
			int r = tmp.r;
			int c = tmp.c;
			check[r][c] = true;	// 바이러스 활성화
			
			for (int i = 0; i < dx.length; i++) {
				int newR = r + dx[i];
				int newC = c + dy[i];
				if(0<newR && newR<=N && 0<newC && newC<=N
						&& !check[newR][newC]) {
//					System.out.println(newR+ "," + newC);
					check[newR][newC] = true;	// 바이러스 활성화
					if(lab[newR][newC]!=-1) {
						if(lab[r][c]==-1) {
							lab[newR][newC] = 1;
						} else {
							lab[newR][newC] = lab[r][c]+1;
						}
					} else {	// ==-1인 경우는 비활성화 바이러스
						
					}
					queue.add(new loc(newR, newC));
				}
			}
		}// end of while
		
		// lab배열 중에 가장 큰 값이 이번 바이러스 조합으로 걸린 시간임.
		int curMax = Integer.MIN_VALUE;
		boolean isClear = true;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(lab[i][j] == 0 && !check[i][j]) {
					isClear = false;
				}
				if(curMax<lab[i][j])
					curMax = lab[i][j];
				if(lab[i][j] != 0)	// check배열 초기화. 0초에 바이러스가 없던곳은 false
					check[i][j] = false;
			}
			Arrays.fill(lab[i], 0);	// 다음 조합을 위해 0초기화.
		}
		for (loc loc : virusLoc) {	// 바이러스 위치 -1로 초기화
			lab[loc.r][loc.c] = -1;
		}
		if(isClear)	// 연구실 모두가 바이러스로 전염되었으면 minTime갱신
			minTime = minTime>curMax?curMax:minTime;
//		System.out.println(curMax);
	}// end of bfs method
}	// end of class
