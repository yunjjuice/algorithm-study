import java.io.*;
import java.util.*;

public class SWEA_9282_초콜릿과건포도 {
	static int answer;
	static int[][] map;
	static int N;
	static int M;
	static int[][][][] dp; 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			dp = new int[N+1][M+1][N+1][M+1];
			for(int[][][] d1 : dp) {
				for(int[][] d2 : d1) {
					for(int[] d3 : d2) {
						Arrays.fill(d3, Integer.MAX_VALUE);
					}
				}
			}//메모이제이션 하는 곳.
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}//end input.
			answer = dfs(0,0,N,M);
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}//end TestCase.
		System.out.print(sb);
	}//end main.
	public static int dfs(int y, int x, int h, int w) {
		if(w == 1 && h == 1) return 0; //조각 못낼경우.
		if(dp[y][x][h][w] != Integer.MAX_VALUE) return dp[y][x][h][w];
		int sum = 0;
		for(int i=y; i<y+h; i++) {
			for(int j=x; j<x+w; j++) {
				sum+= map[i][j];
			}
		}
		for(int i=1; i<h; i++) {
			int sum1 = dfs(y,x,i,w);
			int sum2 = dfs(y+i,x,h-i,w);
			int sum3 = sum + sum1 + sum2;
			dp[y][x][h][w] = Math.min(dp[y][x][h][w], sum3);
		}
		
		for(int i=1; i<w; i++) {
			int sum1 = dfs(y,x,h,i);
			int sum2 = dfs(y,x+i,h,w-i);
			int sum3 = sum+sum1+sum2;
			dp[y][x][h][w] = Math.min(dp[y][x][h][w], sum3);
		}
		return dp[y][x][h][w];
	}//end dfs.
}//end class.
