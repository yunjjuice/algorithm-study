/*
 * BOJ 17140 이차원 배열과 연산
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ_17140_이차원배열과연산_200503 {
	static int r, c, k;
	static int[][] map;
	static int lenX, lenY;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
		
		map = new int[100][100];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		lenX = 3;
		lenY = 3;
		
		int time = 0;
		while(time <= 100) {
			if(map[r][c] == k) {
				break;
			}
			
			if(lenX >= lenY) {
				calcR();
			} else {
				calcC();
			}
			
//			System.out.println("time : " + time);
//			for (int i = 0; i < lenX; i++) {
//				for (int j = 0; j < lenY; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			time++;
		}
		
		System.out.println(time > 100 ? -1 : time);
	}
	
	private static void calcR() {
		int[] tmp;
		int maxLen = 0;
		for (int i = 0; i < lenX; i++) {
			tmp = new int[101];
			for (int j = 0; j < lenY; j++) {
				tmp[map[i][j]]++;
			}
			ArrayList<Pos> list = new ArrayList<>();
			for (int j = 1; j < tmp.length; j++) {
				if(tmp[j] > 0) {
					list.add(new Pos(j, tmp[j]));
				}
			}
			Collections.sort(list);
//			System.out.println(list.toString());
			int index = 0;
			Arrays.fill(map[i], 0);
			Iterator<Pos> it = list.iterator();
			while(it.hasNext()) {
				Pos p = it.next();
				map[i][index++] = p.num;
				map[i][index++] = p.cnt;
			}
			if(maxLen < index)
				maxLen = index;
		}
		lenY = maxLen;
	}
	
	private static void calcC() {
		int[] tmp;
		int maxLen = 0;
		for (int j = 0; j < lenY; j++) {
			tmp = new int[101];
			for (int i = 0; i < lenX; i++) {
				tmp[map[i][j]]++;
			}
			ArrayList<Pos> list = new ArrayList<>();
			for (int i = 1; i < tmp.length; i++) {
				if(tmp[i] > 0) {
					list.add(new Pos(i, tmp[i]));
				}
			}
			Collections.sort(list);
			int index = 0;
			
			Iterator<Pos> it = list.iterator();
			while(it.hasNext()) {
				Pos p = it.next();
				map[index++][j] = p.num;
				map[index++][j] = p.cnt;
			}
			if(maxLen < index)
				maxLen = index;
			while(index < 100)
				map[index++][j] = 0;
		}
		lenX = maxLen;
	}
	
	static class Pos implements Comparable<Pos>{
		int num;
		int cnt;
		public Pos(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Pos o) {
			if(this.cnt == o.cnt)
				return this.num - o.num;
			else
				return this.cnt - o.cnt;
		}

		@Override
		public String toString() {
			return "Pos [num=" + num + ", cnt=" + cnt + "]";
		}
	}
}
