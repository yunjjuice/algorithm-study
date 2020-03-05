package algo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_5684_운동_200305 {
	static class LinkNode {
		int e, c;
		LinkNode(int e, int c) {
			this.e = e;
			this.c = c;
		}
	}
	static List<LinkNode>[] list;
	static long[] visited;
	static long min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = sc.nextInt();
		for(int testcase = 1; testcase <= TC; testcase++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			list = new ArrayList[n + 1];
			for(int i = 1; i < n + 1; i++) list[i] = new ArrayList<>();
			
			for(int i = 0; i < m; i++) {
				int s = sc.nextInt();
				int e = sc.nextInt();
				int c = sc.nextInt();
				list[s].add(new LinkNode(e, c));
			}

			min = Long.MAX_VALUE;
			
			for(int i = 1; i < n + 1; i++) {
				visited = new long[n + 1];
				findCycle(i, i, 0);
			}
			
			if(min == Long.MAX_VALUE) min = -1;
			sb.append("#").append(testcase).append(" ").append(min).append('\n');
		}
		System.out.print(sb);
	}
	
	private static void findCycle(int now, int start, long dist) {
		if(now == start && visited[now] == 1) { // 싸이클이 있다면 min 갱신
			if(dist < min) min = dist;
			return;
		}
		if(visited[now] == 1) return; // 자기자신아닌데 방문한 곳이면 return
		if(dist >= min) return; // 계산하고 있는 거리값이 현재min보다 크면 return
		
		visited[now] = 1;
		for(int i = 0; i < list[now].size(); i++) {
			LinkNode l = list[now].get(i);
			findCycle(l.e, start, dist + l.c);
		}
	}
}
