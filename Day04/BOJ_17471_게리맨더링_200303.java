/*
 * BOJ 17471 게리맨더링
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17471 {
	static int n;
	static int[] po;
	static boolean[][] map;
	static int ans;
	static boolean[] pick; // 조합 저장
	static int[] a;
	static int[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		po = new int[n];
		pick = new boolean[n];
		a = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			po[i] = Integer.parseInt(st.nextToken());
		}
		map = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int con = Integer.parseInt(st.nextToken()) - 1;
				map[i][con] = true;
			}
		}
		
		// 입력 확인
//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		// 1. 구역을 나눈다
		// 2. 구역끼리 연결되어있는지 확인한다
		// 3. 두 구역의 인구 수의 차이를 계산한다
		ans = Integer.MAX_VALUE;
		makeGroup();
		if(ans == Integer.MAX_VALUE)
			ans = -1;
		System.out.println(ans);
	}
	
	static void makeGroup() {
		// 한 그룹에 1개~n/2개까지 해서 조합하기
		for (int k = 1; k <= n/2; k++) {
			makeGroup(0, k);
		}
	}
	
	static void makeGroup(int cnt, int k) {
		if(cnt == k) {
			if(check()) { // 나눠진 그룹들이 연결되어있는지 확인
				int p1 = 0;
				int p2 = 0;
				for (int i = 0; i < n; i++) {
					if(pick[i])
						p1 += po[i];
					else
						p2 += po[i];
				}
				int tmp = Math.abs(p1 - p2);
				ans = Math.min(tmp, ans);
			}
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if(pick[i]) continue;
			if(cnt>0 && a[cnt-1]>i)
				continue;
			a[cnt] = i;
			pick[a[cnt]] = true;
			makeGroup(cnt+1, k);
			pick[a[cnt]] = false;
		}
	}
	
	static boolean check() {
		ArrayList<Integer> p1 = new ArrayList<>();
		ArrayList<Integer> p2 = new ArrayList<>();
		boolean[] check1 = new boolean[n]; // isConnected에서 방문체크용
		boolean[] check2 = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			if(pick[i]) {
				p1.add(i);
				check1[i] = true;
			} else {
				p2.add(i);
				check2[i] = true;
			}
		}
		
		if(isConnected(check1, p1.get(0), p1) && isConnected(check2, p2.get(0), p2))
			return true;
		
		return false;
	}
	
	static boolean isConnected(boolean[] check, int start, ArrayList<Integer> list) {
		if(list.size() == 1) return true; // 나 혼자면 연결된거 확인할 필요가 없다
		Queue<Integer> q = new LinkedList<Integer>();
		int con = 0;
		q.offer(start);
		while(!q.isEmpty()) {
			int s = q.poll();
			for (int i = 0; i < n; i++) {
				if(map[s][i] && check[i]) { // 둘이 연결되어있고, 방문한 적 없으면
					q.offer(i);
					check[i] = false;
					if(list.indexOf(i) != -1) // 리스트에 i가 연결되어있다면 + 해줌
						con++;
				}
			}
			if(con == list.size()) {
				return true;
			}
		}
		
		return false;
	}
}
