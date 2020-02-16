package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
	int now;
	int goal;
	
	public Pair(int now, int goal) {
		this.now = now;
		this.goal = goal;
	}
	
	public int compareTo(Pair p) {
		if(Math.abs(this.goal - this.now) > Math.abs(p.goal - p.now)) {
			return 1;
		} else if(Math.abs(this.goal - this.now) == Math.abs(p.goal - p.now)) {
			return 0;
		}
		return -1;
	}
}
public class 스타트링크 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int f = Integer.parseInt(st.nextToken()); // 총 층수
		int s = Integer.parseInt(st.nextToken()); // 강호 위치
		int g = Integer.parseInt(st.nextToken()); // 스타트링크 위치
		int u = Integer.parseInt(st.nextToken()); // 위로 u층씩 이동 가능
		int d = Integer.parseInt(st.nextToken()); // 아래로 d층씩 이동 가능
		
		if(s == g) {
			System.out.print(0);
			return;
		}
		// Au - Bd = g - s
		// A + B가 정답
		
		int[] elv = new int[f + 1];
		//Arrays.fill(elv, -1);
		int ans = -1;
		
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		q.offer(new Pair(s, g));
		elv[s] = 1;
		
		while(!q.isEmpty()) {
			int now = q.poll().now;
			
			if(now + u == g || now - d == g) {
				ans = elv[now];
				break;
			}
			
			if(now + u <= f && elv[now + u] == 0) {
				elv[now + u] = elv[now] + 1;
				q.offer(new Pair(now + u, g));
			} else if(now - d > 0 && elv[now - d] == 0) {
				elv[now - d] = elv[now] + 1;
				q.offer(new Pair(now - d, g));
			}
		}
		
		if(ans == -1) {
			System.out.print("use the stairs");
		} else {
			System.out.print(ans);
		}
	}
}
