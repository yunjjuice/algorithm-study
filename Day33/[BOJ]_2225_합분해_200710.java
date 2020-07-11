package algorithm;

import java.io.*;
import java.util.StringTokenizer;


public class BOJ_2225_합분해 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] dp = new int[K+1][N+1];
		for(int i=0;i<=N;i++) {
			dp[1][i] = 1; //1개를 이용해서 그 수를 만드는 경우 즉, 자기 자신을 선택할 경우니까 1씩 가능한경우 넣어놓고 시작.
		}
		for(int i=2; i<=K; i++) {
			for(int j=0; j<=N; j++) {
				for(int k=0; k<=j ; k++) {
					dp[i][j] = (dp[i][j] + dp[i-1][j-k])%1000000000; 
					//i개로 j를 만드는경우는 j보다 작은 k 하나를 택해 i-1개로 j-k를 만드는 경우들의 합.
				}
			}
		}
		System.out.println(dp[K][N]);
	}//end main.
}//end class.
