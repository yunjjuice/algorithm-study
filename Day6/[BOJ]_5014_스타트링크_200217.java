import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_스타트링크 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());
		int up = Integer.parseInt(st.nextToken());
		int down = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[height+1];
		int[] distance = new int[height+1];
		visited[0] = true;
		int cnt = -1;
		Queue<Integer> q = new LinkedList();
		visited[start] = true;
		q.add(start);
		while(!q.isEmpty()) {
			int p = q.poll();
			if(p == goal) {
				cnt = distance[p];
				break;
			}
			if(p+up <= height && !visited[p + up]) {
				visited[p+up] = true;
				distance[p+up] = distance[p] + 1;
				q.add(p+up);
			}
			if(p-down > 0 && !visited[p - down]) {
				visited[p-down] = true;
				distance[p-down] = distance[p] + 1;
				q.add(p-down);
			}
		}
		if(cnt == -1) System.out.println("use the stairs");
		else System.out.println(cnt);
	}
}
