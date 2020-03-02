import java.io.*;
import java.util.*;

public class SWEA_2814_최장경로 {
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static int max;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			max = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			list = new ArrayList[N+1];
			visited = new boolean[N+1];
			for(int i=1; i<=N; i++) {
				list[i] = new ArrayList<Integer>();
			}
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				list[start].add(end);
				list[end].add(start);
			}
			for(int i=1; i<=N; i++) {
				visited[i] = true;
				dfs(i,1);
				visited[i] = false;
			}
			sb.append("#").append(test).append(" " ).append(max).append("\n");
		}//end TestCase.
		System.out.print(sb);
	}//end main.
	public static void dfs(int idx, int cnt) {
		for(int i=0; i<list[idx].size(); i++) {
			if(!visited[list[idx].get(i)]){
				visited[list[idx].get(i)] = true;
				dfs(list[idx].get(i),cnt+1);
				visited[list[idx].get(i)] = false;
			}
		}
		if(cnt > max) max = cnt;
	}
}//end class.
