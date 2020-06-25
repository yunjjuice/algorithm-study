import java.util.Arrays;

/**
 *	N_QUEEN
 *	백트래킹
 *
 */
public class PROG_N_QUEEN_200420 {
	public static void main(String[] args) {
		System.out.println(solution(4));
		System.out.println(solution(5));
		/*System.out.println(solution(6));
		System.out.println(solution(7));
		System.out.println(solution(8));
		System.out.println(solution(9));
		System.out.println(solution(10));
		System.out.println(solution(11));*/
	}
	static int count = 0;
	public static int solution(int n) {
		count = 0;
        int[][] map = new int[n][n];
        int rowIndex = 0;
        for (int i = 0; i < n; i++) {
			map[rowIndex][i] = 1;	// 첫 번째 행부터 백트래킹
			dfs(map,rowIndex+1,n);
			map[rowIndex][i] = 0;	// 초기화
			
		}
        
        return count;
    }
	private static void dfs(int[][] map, int rowIndex, int n) {
		if(rowIndex == n) {
			for (int i = 0; i < map.length; i++) {
				System.out.println(Arrays.toString(map[i]));
			}
			System.out.println();
			count ++;
		}
		for (int i = 0; i < n; i++) {
			if(isPutOn(map,rowIndex,i)) {	// 놓을수 있는 위치인지 판별
				// 놓을수 있다면 놓고 dfs
				map[rowIndex][i] = 1;
				 dfs(map,rowIndex+1,n);
				map[rowIndex][i] = 0; // 초기화
			} 
		}
	}
	private static boolean isPutOn(int[][] map, int r, int c) {
		for (int j = 1; r-j >= 0; j++) {
			// 위 방향
			if(map[r-j][c] == 1) return false;
			// 왼쪽 대각선 방향
			if(c-j > -1 && map[r-j][c-j] == 1) return false;
			// 오른쪽 대각선 방향
			if(c+j < map.length && map[r-j][c+j] == 1) return false;
		}
		return true;	// 퀸이 갈수있는 방향에 퀸이 없으면 true
	}
}
