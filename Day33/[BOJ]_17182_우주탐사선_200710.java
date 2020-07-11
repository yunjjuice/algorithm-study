package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_17182_우주탐사선 {
	static int answer = Integer.MAX_VALUE;
	static boolean[] visited;
	static int N;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N];
		int start = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//end input.
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}//플로이드워샬(맞나?)
		
		visited[start] = true;
		dfs(start,1,0); //현재 스타트지점에서 dfs돌리고 , 카운트는 스타트지점 하나 들어가있는 상태니까 1 , 거리는 아직 어디가지않은상태니까 0.
		System.out.println(answer);
	}//end main.
	public static void dfs(int now, int count, int dist) {
		if(answer < dist) return; //지금 정해져온 답보다 큰경우는 가지치기.
		if(count == N) {
			answer = Math.min(answer, dist);
			return;
		}
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i,count+1,dist+map[now][i]);
				visited[i] = false;
			}
		}//end for.
	}//end dfs.
}//end class.
