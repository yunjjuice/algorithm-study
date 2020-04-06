/*
 * BOJ 17406 배열 돌리기 4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17406 {
	static int N, M, K;
	static int[][] map;
	static int[][] cmd;
	static int[] num;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cmd = new int[K][3]; // 명령 r, c, s
		num = new int[K]; // 순열용
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cmd[i][0] = Integer.parseInt(st.nextToken()) - 1;
			cmd[i][1] = Integer.parseInt(st.nextToken()) - 1;
			cmd[i][2] = Integer.parseInt(st.nextToken());
			num[i] = i;
		}
		
		// 입력 확인
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		for (int i = 0; i < K; i++) {
//			System.out.println(Arrays.toString(cmd[i]));
//		}
		
		ans = Integer.MAX_VALUE;
		// 명령 순서 정하고
		do {
//			System.out.println(Arrays.toString(num));
			// 배열 돌리고
			int[][] copy = new int[N][M];
			for (int i = 0; i < N; i++) {
				copy[i] = map[i].clone();
			}
			
			// 명령 수만큼 돌린다
			for (int i = 0; i < K; i++) {
				int r = cmd[num[i]][0];
				int c = cmd[num[i]][1];
				int s = cmd[num[i]][2];
				
				rotate(copy, r, c, s);
			}
			
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(copy[i]));
//			}
//			System.out.println();
			// 계산해서 최솟값 찾기
			calc(copy);
		} while(next_permutation());
		
		System.out.println(ans);
	}

	static boolean next_permutation() {
		int i = K - 1;
		while(i > 0 && num[i-1] >= num[i]) i--;
		if(i == 0) return false;
		
		int j = K - 1;
		while(num[i-1] >= num[j]) j--;
		
		int tmp = num[i-1];
		num[i-1] = num[j];
		num[j] = tmp;
		
		int k = K - 1;
		while(i < k) {
			tmp = num[i];
			num[i] = num[k];
			num[k] = tmp;
			i++;
			k--;
		}
		
		return true;
	}
	
	static void rotate(int[][] copy, int r, int c, int s) {
//		System.out.println("r " + r + ", c " + c + ", s " + s);
		for (int l = s; l >= 1; l--) {
			int tmp = copy[r-l][c+l];
			// 윗변
			for (int i = c+l; i > c-l; i--) {
				copy[r-l][i] = copy[r-l][i-1];
			}
			// 왼쪽변
			for (int i = r-l; i < r+l; i++) {
				copy[i][c-l] = copy[i+1][c-l];
			}
			// 아랫변
			for (int i = c-l; i < c+l; i++) {
				copy[r+l][i] = copy[r+l][i+1];
			}
			// 오른쪽변
			for (int i = r+l; i > r-l; i--) {
				copy[i][c+l] = copy[i-1][c+l];
			}
			copy[r-l+1][c+l] = tmp;
		}
	}
	
	
	static void calc(int[][] copy) {
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += copy[i][j];
			}
			ans = Math.min(sum, ans);
		}
	}
}
