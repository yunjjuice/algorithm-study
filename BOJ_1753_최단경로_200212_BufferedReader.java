import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1753. 최단경로 Gold 5 https://www.acmicpc.net/problem/1753
 * 
 * 
 */
public class BOJ_1753_최단경로_200212_BufferedReader {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// input
		int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
		short K = Short.parseShort(br.readLine());
		ArrayList<HashMap<Short, Byte>> list = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			list.add(new HashMap<>());
		}
		short u, v;
		byte w;
		for (; E > 0; E--) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Short.parseShort(st.nextToken());
			v = Short.parseShort(st.nextToken());
			w = Byte.parseByte(st.nextToken());
			if (list.get(u).get(v) == null)
				list.get(u).put(v, w); // 정점이 key, 가중치가 value
			else if (list.get(u).get(v) > w)
				list.get(u).replace(v, w); // 새로 받은 정점의 가중치가 작을 경우 갱신
		}

		// solve
		boolean[] visited = new boolean[V + 1];
		PriorityQueue<m_Pair> pq = new PriorityQueue<>();
		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[K] = 0;
		pq.add(new m_Pair(K, 0));
		// initialize end
		while (!pq.isEmpty()) {
			m_Pair p = pq.poll();
			if (visited[p.v])
				continue;
			visited[p.v] = true;
			list.get(p.v).forEach((key, value) -> {
				if (!visited[key] && dist[key] > value + dist[p.v]) {
					dist[key] = value + dist[p.v];
					pq.add(new m_Pair(key, dist[key]));
				}
			});
		}

		// output
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			sb.append((dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]) + "\n"); // 경로 없으면 INF
		}
		System.out.println(sb);
	}// end of main
} // end of class

class m_Pair implements Comparable<m_Pair> {
	short v;
	int dist;

	m_Pair(short v, int dist) {
		this.v = v;
		this.dist = dist;
	}

	@Override
	public int compareTo(m_Pair o) {
		return dist < o.dist ? -1 : 1;
	}
}