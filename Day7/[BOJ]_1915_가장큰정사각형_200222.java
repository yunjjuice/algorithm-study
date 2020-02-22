import java.io.*;
import java.util.*;

public class BOJ_가장큰정사각형 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N+1][M+1];
		int[][] dp = new int[N+1][M+1];
		int answer = 0; //정답 초기화.
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			String temp_Str = st.nextToken();
			for(int j=1; j<=M; j++) {
				map[i][j] = temp_Str.charAt(j-1) - '0';
			}
		}//draw map.
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] != 0) {
					dp[i][j] = min(dp[i-1][j-1] , dp[i-1][j] , dp[i][j-1]) + 1;
					if(dp[i][j] > answer) answer = dp[i][j];
				}
			}
		}//end dp.
//		for(int i=1; i<=N; i++) {
//			for(int j=1; j<=M; j++) {
//				System.out.print(dp[i][j] +"");
//			}
//			System.out.println("");
//		}
		System.out.println(answer*answer);
	}//end main.
	public static int min(int a, int b, int c) {
		int min = (a<c? a:c);
		return (min<b? min:b);
	}//end min.
}//end class.
