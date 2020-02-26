import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Pos {
	int x;
	int y;
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_11559_PuyoPuyo_200226 {
	static char[][] map = new char[12][6];
	static int[][] visited = new int[12][6];
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static int cnt;
	static List<Pos> list;
	static int flag;
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		} // input
		
		while(true) {
			flag = 0;
			for(int i = 0; i < 12; i++) {
				for(int j = 0; j < 6; j++) {
					if(map[i][j] != '.') { // 같은 색깔 몇개 모여있는지 찾아보자
						cnt = 0; // 몇개가 같이 모여있나 확인하기 위한 variable
						list = new ArrayList<>(); // 삭제할 뿌요가 들어갈 리스트 초기화
						visited = new int[12][6]; // 방문 배열 초기화
						dfs(i, j);
						if(cnt >= 4) { // 4개 이상 모여있으면 삭제 시작
							flag = 1; // 이번 턴은 삭제할 것이 존재하니 다음 루프를 돌기위해 flag on
							for(int k = 0; k < list.size(); k++) {
								Pos p = list.get(k);
								map[p.x][p.y] = '.'; // 일단 삭제만
							}
						}
					}
				}
			}
			
			if(flag == 0) break; // 삭제한 뿌요가 없으면 종료
			ans++; // 삭제한 뿌요가 있으면 + 1 연쇄
			down(); // 뿌요들을 빈공간없이 내려준다
		}
		
		System.out.println(ans);
	}
	
	public static void down() {
		for(int j = 0; j < 6; j++) {
			for(int i = 10; i >= 0; i--) { // 밑에서 부터 확인
				if(map[i][j] != '.') {
					int count = 0;
					for(int k = i + 1; k < 12; k++) {
						if(map[k][j] == '.') count++; // 밑에 빈 공간이 얼마나 있나 확인후
					}
					if(count != 0) { // 빈 공간만큼 내려줌
						map[i + count][j] = map[i][j];
						map[i][j] = '.';
					}
				}
			}
		}
	}
	
	public static void dfs(int x, int y) {
		visited[x][y] = 1; // 방문체크
		list.add(new Pos(x, y)); // 삭제할 뿌요 리스트에 넣기
		cnt++; // 몇개가 모여있는지 세기
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0 && ny >= 0 && nx < 12 && ny < 6) {
				if(map[nx][ny] == map[x][y] && visited[nx][ny] == 0) {
					dfs(nx, ny);
				}
			}
		}
	}
}
