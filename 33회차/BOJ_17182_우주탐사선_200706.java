import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17182_우주탐사선_200707 {
	final static int m = Integer.MAX_VALUE;	//10000정도도 충분
	static int[][] D;
	static boolean[] visited;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());	// 시작하는 행성
		
		D = new int[N][N];
		visited = new boolean[N];
		
		// input
		for (int i = 0; i < D.length; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < D.length; j++) {
				D[i][j] = Integer.parseInt(st.nextToken());
				// 본인 행성으로 가는 시간은 무한대로 설정
				if(i == j) D[i][j] = m;
			}
		}
		
		// 플로이드 워셜
		for (int k = 0; k < D.length; k++) {	// 경유정점
			for (int i = 0; i < D.length; i++) {	// 출발정점
				if(k == i) continue;
				for (int j = 0; j < D.length; j++) { // 도착정점
					if(k==i || i==j) continue;
					
					if(D[i][k] != m && D[k][j]!=m &&
							D[i][j] > D[i][k]+D[k][j]){
						D[i][j] =  D[i][k]+D[k][j];
					}
				}
			}
		}
		
		// 각 정점에서 다른 정점까지 최소 거리
		/*for (int i = 0; i < D.length; i++) {
				System.out.println(Arrays.toString(D[i]));
		}*/
		
		// K행성 부터 시작하는 순열
		visited[K] = true;
		perm(N,0,K,0);
		
		System.out.println(result);
	}	// end of main
	
	private static void perm(int n, int r, int k, int sum) {
		//System.out.println(r + "," + k + "," + sum);
		
		if (sum > result) {
            return;
        }
        if (r == n - 1 && result > sum){
            result = sum;
        } else {
            for (int i = 0; i < n; i++) {
                if (!visited[i] && i != k) {
                	visited[i] = true;
                    perm(n, r+1, i, sum + D[k][i]);
                    visited[i] = false;
                }
            }
        }
	}
	

}	// end of class
