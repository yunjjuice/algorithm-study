import java.io.*;
import java.util.*;

public class BOJ_2352_반도체설계 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] list = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}//end input.
		int[] dp = new int[N+1];
		int top = -1;
		dp[++top] = list[1];
		int max = Integer.MIN_VALUE;
		for(int i=2; i<=N; i++) {
			if(list[i] > dp[top]) dp[++top] = list[i];
			else {
				int idx = Arrays.binarySearch(dp, 0,top+1,list[i]); //라이브러리 사용. 
				dp[-(idx+1)] = list[i];
			}
		}//end for.
		System.out.println(top+1);//top은 스택용이므로 +1 해줘야 함.
		//결국 최장수열 구하는것과 같다. 이유는 음.. 인덱스가 증가함에 따라 숫자가 커지면 그 둘은 결국 안만나기떄문데.
	}//end main.
}//end class.
