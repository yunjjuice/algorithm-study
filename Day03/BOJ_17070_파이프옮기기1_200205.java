import java.util.Scanner;

/*
 * BOJ 17070 파이프 옮기기 1
 */

public class BOJ17070 {
	
	static int N;
	static int[][] map;
	static int count;
	static int[] dy = {0, 1, 1}; // 행 . 오른쪽, 대각선, 아래쪽
	static int[] dx = {1, 1, 0}; // 열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 집의 크기
		map = new int[N+1][N+1]; // 인덱스 1부터 시작
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map[i].length; j++) {
				map[i][j] = sc.nextInt(); // 0이면 빈 공간, 1이면 벽
			}
		}
		
		// 처음 파이프의 위치
		int[] p1 = {1, 1}; // y, x
		int[] p2 = {1, 2};
		
		count = 0;
		
		solution(p2[0], p2[1], 0);
		
		System.out.println(count);
		
		// 앞에 있는 점만 계산하면 되는 거 아닌가? 뒤에 부분은 원래 앞에 있던 좌표로 가게 되어 있으니까
		
		// 가로로 있으면 오른쪽, 대각선으로만 이동
		// 세로로 있으면 아래쪽, 대각선으로만 이동
		// 대각선으로 있으면 오른쪽, 아래쪽, 대각선으로 이동 
	}
	
	public static void solution(int py, int px, int dir) { // dir : 0:가로, 1:세로, 2:대각선
		if(py==N && px==N) {
			count++;
			return;
		}
		
		int[] next = getDir(dir);
		
		for (int i = 0; i < next.length; i++) {
//			System.out.println("next : " + next[i]);
			int ny = py + dy[next[i]];
			int nx = px + dx[next[i]];
			
			// 범위 검색
			if(ny<1 || ny>N || nx<1 || nx>N || map[ny][nx]!=0) continue;
			if(next[i] == 1 && (map[(ny-1)][nx]!=0 || map[ny][nx-1]!=0)) { // 대각선일 땐 세 칸 탐색
				continue;
			}
			
			solution(ny, nx, next[i]);
		}
		
	}
	
	public static int[] getDir(int type) {
		if(type==0) { // type : 0:가로, 1:대각선, 2:세로
			int[] tmp = {0, 1};
			return tmp;
		} else if(type==1) {
			int[] tmp = {0, 1, 2};
			return tmp;
		} else if(type==2) {
			int[] tmp = {1, 2};
			return tmp;
		}
		return null;
	}
}
