import java.io.*;
import java.util.*;

public class BOJ_줄세우기 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] num = new int[N];
		int[] dp = new int[N];
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}//end input.
		for(int i=0; i<N; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(num[j] < num[i] && dp[j]+1 > dp[i]) {
					dp[i]++; //이전것들과의 비교로 cnt++ 해주면 된다.
					if(max < dp[i]) max = dp[i];
				}
			}
		}//end dp.
		System.out.println(N - max);
		
	}//end main.
}//end class.
