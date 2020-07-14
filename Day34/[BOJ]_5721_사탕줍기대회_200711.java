package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_5721_사탕줍기대회{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N==0 || M==0) break;//종료 조건.
			int[] dp = new int[N+1];
			int[] max_row = new int[N+1];
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				int[] list = new int[M+1];
				list[1] = Integer.parseInt(st.nextToken());
				for(int j=2; j<=M; j++) {
					list[j] = Math.max(list[j-1], list[j-2] + Integer.parseInt(st.nextToken()));
				}
				max_row[i] = list[M];
			}//end for.
			dp[1] = max_row[1];
			for(int i=2; i<=N; i++) {
				dp[i] = Math.max(dp[i-1], dp[i-2] + max_row[i]);
			}
			sb.append(dp[N]).append("\n");
		}//end while.
		System.out.print(sb);//정답 출력.
	}//end main.
}//end class.
