package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_2248_이진수찾기 {
	static int dp[][];
	static String answer = "";
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		long I = Long.parseLong(st.nextToken());
		dp = new int[N+1][L+1];
		for(int i=0; i<=N; i++) {
			dp[i][0] = 1;
		}//각 자리마다 1이 하나도 없는경우는 1가지.
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=L; j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				//맨 앞자리에 1을 넣는경우 ==> 자릿수 하나 줄고 갯수도 하나 줄어드는 경우랑 같다.	
				//맨 앞자리에 0을 넣는경우 ==> 자릿수 하나 줄지만 갯수는 똑같은 경우랑 같다.
			}
		}
		start(N,L,I);
		System.out.println(answer);
	}//end main.
	public static void start(int n, int l, long i) {
		if(n==0)return;
		int temp = 0;
		for(int j=0; j<=l; j++) {
			temp += dp[n-1][j];
		}
		if(temp < i) { //temp가 i보다 작은경우는 앞에 0을놨을떄 나올수있는 모든 경우의 수를 다 따져서 안된거니까 앞에 1넣는다.
			answer += '1';
			start(n-1,l-1,i-temp);
		}
		else { //그 반대.
			answer += '0';
			start(n-1,l,i);
		}
	}//end start.
}//end class.
