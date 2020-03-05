import java.io.*;
import java.util.*;

public class SWEA_5684_운동 {
	static class Pos implements Comparable<Pos>{
		int end;
		int weight;
		public Pos(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		public int compareTo(Pos p) {
			return this.weight > p.weight ? 1 : -1;
		}
	}//class Pos.
	
	static ArrayList<Pos>[] list;
	static int[] dp;
	static int N;
	static int M;
	static int answer;
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for(int test=1; test<=TC; test++) {
			answer = Integer.MAX_VALUE;
			N = sc.nextInt();
			M = sc.nextInt();
			list = new ArrayList[N+1];
			dp = new int[N+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					dp[i] = Integer.MAX_VALUE;
					}
			}//플로이드 위한 dp리스트.
			for(int i=1; i<=N; i++) {
				list[i] = new ArrayList<Pos>();
			}//list 초기화.
			for(int i=0; i<M; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				int weight = sc.nextInt();
				list[start].add(new Pos(end,weight));
			}
			for(int i=1; i<=N; i++) {
				dijkstra(i);
			}//사이클 구하기.
			if(answer == Integer.MAX_VALUE) System.out.println("#" + test + " -1");
			else System.out.println("#" + test + " " + answer );
		}//end TestCase.
	}//end main.
	public static void dijkstra(int start) {
		int[] dijkstra = new int[N+1];
		for(int i=1; i<=N; i++) {
			dijkstra[i] = Integer.MAX_VALUE;
		}
		PriorityQueue<Pos>q = new PriorityQueue<>();
		q.offer(new Pos(start,0));
		while(!q.isEmpty()) {
			Pos p = q.poll();
			if(p.end == start && p.weight!=0) {
				if(answer > p.weight) answer = p.weight;
				break;
			}
			if(dijkstra[p.end] < p.weight) continue;
			for(int i=0; i<list[p.end].size(); i++) {
				if(dijkstra[list[p.end].get(i).end] > p.weight + list[p.end].get(i).weight) {
					dijkstra[list[p.end].get(i).end] = p.weight + list[p.end].get(i).weight;
					q.offer(new Pos(list[p.end].get(i).end, dijkstra[list[p.end].get(i).end]));
				}
			}
		}//end while.
	}//end dijkstra.
}//end class.
