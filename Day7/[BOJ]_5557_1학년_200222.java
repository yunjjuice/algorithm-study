import java.io.*;
import java.util.*;

public class BOJ_1학년 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] num = new int[N+1];
		long[][] dp = new long[N+1][21];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}//end input.
		dp[1][num[1]] = 1; //1번의 계산으로 num[0] 이 되는 경우는 1개 (초기화).
		for(int i=2; i<N; i++) {
			for(int j=0; j<=20; j++) {
				if(dp[i-1][j] != 0) {
					if(j+num[i]<=20) dp[i][j+num[i]] += dp[i-1][j];
					if(j-num[i]>=0) dp[i][j-num[i]] += dp[i-1][j];
				}
			}
		}
		System.out.println(dp[N-1][num[N]]);
	}//end main.
}//end class.
