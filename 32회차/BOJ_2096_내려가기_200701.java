import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2096_내려가기_200701 {
	final static int cLen = 3;
	static int[][] map;
	static int N;
	static int[][] min, max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][cLen];
		min = new int[N][cLen];
		max = new int[N][cLen];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < cLen; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		min[0][0] = max[0][0] = map[0][0];
		min[0][1] = max[0][1] = map[0][1];
		min[0][2] = max[0][2] = map[0][2];

		for (int i = 1; i < N; i++) {
			max[i][0] += map[i][0] + Math.max(max[i - 1][0], max[i - 1][1]);
			max[i][1] += map[i][1] + Math.max(Math.max(max[i - 1][0], max[i - 1][1]), max[i - 1][2]);
			max[i][2] += map[i][2] + Math.max(max[i - 1][1], max[i - 1][2]);
			min[i][0] += map[i][0] + Math.min(min[i - 1][0], min[i - 1][1]);
			min[i][1] += map[i][1] + Math.min(Math.min(min[i - 1][0], min[i - 1][1]), min[i - 1][2]);
			min[i][2] += map[i][2] + Math.min(min[i - 1][1], min[i - 1][2]);
		}
		
		System.out.println(Math.max(Math.max(max[N - 1][0], max[N - 1][1]), max[N - 1][2]) + " " 
		+ Math.min(Math.min(min[N - 1][0], min[N - 1][1]), min[N - 1][2]));
	}
}
