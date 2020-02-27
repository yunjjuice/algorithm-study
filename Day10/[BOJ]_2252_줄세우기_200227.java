import java.io.*;
import java.util.*;

public class BOJ_줄세우기_2 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] cnt_list =  new int[N+1];
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Integer>();	
		}//리스트 만들기.
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			cnt_list[end]++; //몇차수인지.
			list[start].add(end);
		}//서로서로 연결하기.
		Queue<Integer> q = new LinkedList();
		for(int i=1; i<=N; i++) {
			if(cnt_list[i] == 0) q.add(i);
		}
		while(!q.isEmpty()) {
			int p = q.poll();
			System.out.print(p + " ");
			for(int i=0; i<list[p].size(); i++) {
				int temp = list[p].get(i);
				if(--cnt_list[temp] == 0) {
					q.add(temp);
				}//요런느낌으로 업데이트.
			}
		}//end queue.
		System.out.println("");
	}//end main.
}//end class.
