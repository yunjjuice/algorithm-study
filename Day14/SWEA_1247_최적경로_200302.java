/*
 * SWEA 1247 최적 경로 D5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1247 {
	static int n; // 고객 수
	static ArrayList<Pos> list;
	static boolean[] used;
	static int[] np;
	static int ans;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			used = new boolean[n+2];
			np = new int[n+2];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n+2; i++) { // 0은 출발 1은 집
				list.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			Pos tmp = list.get(1);
			list.remove(1);
			list.add(tmp);
			np[0] = 0;
			np[n+1] = n+1;
			
			// 입력 확인
//			for (int i = 0; i < list.size(); i++) {
//				System.out.println(i + " : " + list.get(i).x + " " + list.get(i).y);
//			}
			
			ans = Integer.MAX_VALUE;
			solve(1);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static void solve(int index) {
		if(index == n+1) {
//			System.out.println();
//			System.out.println(Arrays.toString(np));
			
			int sum = 0;
			for (int i = 0; i < n+1; i++) {
//				System.out.print(np[i] + " ");
//				System.out.println(list.get(np[i]).x + " " + list.get(np[i]).y);
				sum += (Math.abs(list.get(np[i]).x - list.get(np[i+1]).x) + Math.abs(list.get(np[i]).y - list.get(np[i+1]).y)); 
//				System.out.println("for : " + sum);
			}
			
//			System.out.println("fin : " + sum);
			if(ans > sum)
				ans = sum;
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			if(used[i])
				continue;
			np[index] = i;
			used[i] = true;
			solve(index+1);
			used[i] = false;
		}
	}
}

class Pos {
	int x;
	int y;
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}