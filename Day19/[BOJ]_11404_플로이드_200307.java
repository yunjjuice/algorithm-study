import java.io.*;
import java.util.*;

public class BOJ_11404_플로이드 {
	static long [][] distance;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		distance = new long[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			distance[start][end] = Math.min(distance[start][end], weight);
		}//end input.
		for(int mid=1; mid<=N; mid++) {
			for(int start=1; start<=N; start++) {
				for(int end=1; end<=N; end++) {
					distance[start][end] = Math.min(distance[start][mid] + distance[mid][end], distance[start][end]);
				}
			}
		}
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(distance[i][j]>=Integer.MAX_VALUE) distance[i][j] = 0;
				System.out.print(distance[i][j] + " ");
			}
			System.out.println("");
		}
		
		
	}//end main.
}//end class.
