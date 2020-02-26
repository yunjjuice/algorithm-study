import java.io.*;
import java.util.*;

public class BOJ_°¡¸£Ä§ {
	static boolean[][] list;
	static boolean[] visited = new boolean[26];
	static int N;
	static int K;
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		list = new boolean[N][26];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < temp.length(); j++) {
				if (temp.charAt(j) == 'a' || temp.charAt(j) == 'c' || temp.charAt(j) == 'i' || temp.charAt(j) == 'n'
						|| temp.charAt(j) == 't')
					continue;
				list[i][temp.charAt(j) - '0' - 49] = true;
			}
		}
		if(K < 5) {
			System.out.println(max);
		}
		else{
			dfs(0,0);
			System.out.println(max);
		}
	}// end main.
	public static void dfs(int idx, int cnt) {
		if(cnt == K-5) {
			int temp = calculate();
			if(max < temp) max = temp;
			return ;
		}
		if(idx>25) return ;
		for(int i=idx; i<26; i++) {
			if(i==0 || i==2 || i==8 || i==13 || i==19) continue;
			visited[i] = true;
			dfs(i+1, cnt+1);
			visited[i] = false;
		}
	}//end combination.
	public static int calculate() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			boolean can = true;
			for(int j=0; j<26; j++) {
				if(list[i][j] && !visited[j]) {
					can = false;
					break;
				}
			}
			if(can) cnt++;
		}
		return cnt;
	}//end calculate.
}// end class.
