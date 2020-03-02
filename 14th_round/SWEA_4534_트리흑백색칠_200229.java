import java.io.*;
import java.util.*;

class Node {
	int node;
	int link;
	public Node(int node, int link) {
		this.node = node;
		this.link = link;
	}
}
public class SWEA_4534_Æ®¸®Èæ¹é»öÄ¥_200229 {
	static long[][] dp;
	static List<Integer>[] list;
	static int[] visited;
	static Stack<Node> stack;
	final static long MOD = 1000000007;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; testcase++) {
			int n = Integer.parseInt(br.readLine());
			list = new ArrayList[n + 1];
			for(int i = 1; i < n + 1; i++) list[i] = new ArrayList<>();
			
			for(int i = 0; i < n - 1; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int now = Integer.parseInt(st.nextToken());
				int next = Integer.parseInt(st.nextToken());
				list[now].add(next);
				list[next].add(now);
			}

			dp = new long[n + 1][2];
			visited = new int[n + 1];
			visited[1] = 1;
			stack = new Stack<>();
			dfs(1);
			
			for(int i = 1; i < n + 1; i++) {
				dp[i][0] = dp[i][1] = 1;
			}
			
			while(!stack.isEmpty()) {
				Node node = stack.pop();
				int child = node.node;
				int parent = node.link;
				dp[parent][0] *= (dp[child][0] + dp[child][1]) % MOD;
				dp[parent][0] %= MOD;
				dp[parent][1] *= dp[child][0];
				dp[parent][1] %= MOD;
				
			}
			
			sb.append("#").append(testcase).append(" ").append((dp[1][0] + dp[1][1]) % MOD).append('\n');
		}
		System.out.print(sb);
	}
	
	public static void dfs(int x) {	
		for(int i = 0; i < list[x].size(); i++) {
			int tmp = list[x].get(i);
			if(visited[tmp] == 0) {
				visited[tmp] = 1;
				stack.push(new Node(tmp, x));
				dfs(tmp);
			}
		}
	}
}
