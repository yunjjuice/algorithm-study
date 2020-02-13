package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class LinkNode implements Comparable<LinkNode> {
	int v;
	int w;
	
	public LinkNode() {
		
	}
	
	public LinkNode(int v, int w) {
		this.v = v;
		this.w = w;
	}
	
	public int compareTo(LinkNode o) {
		if(this.w > o.w) {
			return 1;
		}
		return -1;
	}
}

public class BOJ_1753_최단경로_200211 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(br.readLine());
		
		List<LinkNode>[] map = new ArrayList[V + 1];
		
		for(int i = 1; i <= V; i++) {
			map[i] = new ArrayList<LinkNode>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			map[u].add(new LinkNode(v, w));
		}
		
		int[] distance = new int[V + 1];
		boolean[] check = new boolean[V + 1];
		
		PriorityQueue<LinkNode> dist = new PriorityQueue<>();
		
		//연결노드 distance갱신
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[k] = 0; // 시작 노드 거리 갱신
		check[k] = true; // 시작 노드 방문 체크
        for(int i = 0; i < map[k].size(); i++) {
        	// 첫번째 노드와 연결된 노드들의 거리 갱신
        	LinkNode ln = map[k].get(i);
            dist.offer(ln); // 최소값을 구하기 위해 priorityQueue에 넣어줌
            if(distance[ln.v] > ln.w)
            	distance[ln.v] = ln.w;
        }
        
        dijkstra(V, dist, distance, check, map); // 다익스트라
        
        for(int i = 1; i <= V; i++) {
        	if(distance[i] == Integer.MAX_VALUE) {
        		bw.write("INF" + '\n');
        	} else {
        		bw.write(Integer.toString(distance[i]) + '\n');
        	}
        }
        
        bw.flush();
        bw.close();
	}
	
	public static void dijkstra(int V, PriorityQueue<LinkNode> dist, int[] distance, boolean[] check, List<LinkNode>[] map) {
		for(int i = 0; i < V; i++) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;
			
			// 최소값 찾기
			while(true) {
				if(dist.size() == 0) return;
				LinkNode ln = dist.peek();
				if(!check[ln.v]) { // 방문하지 않았으면
					min = distance[ln.v]; // 최소값
					minIndex = ln.v; // 최소값 갖는 인덱스
					dist.poll();
					break;
				} else dist.poll();
			}
			
			check[minIndex] = true; // 방문 체크
			// 연결 노드 중에 더 빠른 길이 있다면 갱신
			for(int j = 0; j < map[minIndex].size(); j++) { // 
				LinkNode ln = map[minIndex].get(j);
				if(ln.w + min < distance[ln.v]) {
					distance[ln.v] = ln.w + min;
					dist.offer(new LinkNode(ln.v, distance[ln.v])); // 큐에 삽입
				}
			}
		}
	}
}
