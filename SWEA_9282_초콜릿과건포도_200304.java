/* 9282. 초콜릿과 건포도 D4
 https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW9j-qfacIEDFAUY
 -> 완탐 + 메모이제이션 & 부분합
 자바는 2.6초정도 나오는데 c++은 4초인 이유가 뭘까요 ?
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][][][] cache; // 메모이제이션
	static int[][] raisins;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

			raisins = new int[n][m];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					raisins[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end of input

			// initialize
			cache = new int[n + 1][m + 1][n + 1][m + 1];
			for (int i = 0; i < n; i++)
				for (int j = 1; j < m; j++)
					raisins[i][j] += raisins[i][j - 1];
			for (int i = 1; i < n; i++)
				for (int j = 0; j < m; j++)
					raisins[i][j] += raisins[i - 1][j];

			System.out.printf("#%d %d\n", testCase, dfs(0, 0, n, m));
		}
	}

	static int dfs(int sr, int sc, int er, int ec) {
		if (er - sr == 1 && ec - sc == 1) // 1*1임
			return 0;
		if (cache[sr][sc][er][ec] != 0)
			return cache[sr][sc][er][ec];

		int n = raisins[er - 1][ec - 1]; // 이번 자르기에서 지불해야 할 개수
		if (sr != 0)	n -= raisins[sr - 1][ec - 1];
		if (sc != 0)	n -= raisins[er - 1][sc - 1];
		if (sr != 0 && sc != 0)	n += raisins[sr - 1][sc - 1];

		// 모든 경우의 수만큼 잘라보기
		int ret = Integer.MAX_VALUE;
		for (int r = sr + 1; r < er; r++) { // 가로로 자르기
			int tmp = dfs(sr, sc, r, ec) + dfs(r, sc, er, ec);
			if (ret > tmp)	ret = tmp;
		}
		for (int c = sc + 1; c < ec; c++) { // 세로로 자르기
			int tmp = dfs(sr, sc, er, c) + dfs(sr, c, er, ec);
			if (ret > tmp)	ret = tmp;
		}

		cache[sr][sc][er][ec] = ret + n;
		return cache[sr][sc][er][ec];
	}
}