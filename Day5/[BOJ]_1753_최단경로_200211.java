import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_최단경로 {
	static class Pair implements Comparable<Pair>{
		int end;
		int weight;
		public Pair(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		public int compareTo(Pair p1) {
			return this.weight > p1.weight ? 1 : -1; //타겟이 더작을경우 우선순위 높게잡아주기.
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		ArrayList<Pair>[] graph = new ArrayList[V+1];
        for (int i = 1; i <=V; i++) {
            graph[i] = new ArrayList<Pair>();
        }
		int start_v = sc.nextInt();
		for(int i=0; i<E; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			graph[start].add(new Pair(end,weight));
		}//end input.
		
		int distance[] = new int[V+1];
		boolean[] visited = new boolean[V+1];
		for(int i=1; i<=V; i++){
            distance[i] = Integer.MAX_VALUE;
        }//distance 배열 값 맥스로 초기화.
        distance[start_v] =0;
        visited[start_v] =true;//처음 노드 방문 체크.
        PriorityQueue<Pair> q= new PriorityQueue<>();
        
        q.add(new Pair(start_v,0));
        
        while(!q.isEmpty()) {
        	Pair now = q.poll();
        	for(Pair next : graph[now.end]) {
        		if(distance[next.end] > distance[now.end] + next.weight) {
        			distance[next.end] = distance[now.end] + next.weight;
        			q.add(new Pair(next.end, distance[next.end])); //데이터가 업데이트되면 값 바꿔주고  q에 그 값과 시작점 넣어주기.
        		}
        	}
        }//돌려돌려돌림판.
      	//여기를 큐로 작성하기.
        
        for(int i=1; i<=V; i++) {
        	if(distance[i] == Integer.MAX_VALUE) System.out.println("INF");
        	else System.out.println(distance[i]);
        }//출력.
	}//end main.
}
