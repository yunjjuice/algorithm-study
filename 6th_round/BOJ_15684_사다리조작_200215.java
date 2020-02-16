package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_15684_사다리조작_200215 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[h][n];
		List<Point> list = new ArrayList<Point>();
		int[] pick;
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;
			map[a][b + 1] = -1;
		}
		
		int[] x;
		int[] y;
		
		if(check(map)) {
			System.out.print(0);
			return;
		} else {
			horizontal(list, map); // 가로선 놓을 수 있는 곳 찾아서 list에 저장
			if(list.size() == 0) { // 더이상 놓을곳이 없으면 return
				System.out.print(-1);
				return;
			}
			pick = new int[list.size()]; // subset 고르기 위한 배열
			
			int count = 0;
			while(count < 3) {
				count++;
				makeArray(pick, count);
				
				x = new int[count];
				y = new int[count];
				int index = 0;

				do {
					index = 0;
					
					for(int i = 0; i < list.size(); i++) {
						if(pick[i] == 1) {
							x[index] = list.get(i).x;
							y[index] = list.get(i).y;
							map[x[index]][y[index]] = 1;
							map[x[index]][y[index] + 1] = -1;
							index++;
						}
					}
					
					if(check(map)) {
						System.out.print(count);
						return;
					}
					
					for(int i = 0; i < count; i++) {
						map[x[i]][y[i]] = 0;
						map[x[i]][y[i] + 1] = 0;
					}
				} while(nextPermutation(pick));
			}
			
			System.out.print(-1);
		}
 	}
	
	public static boolean nextPermutation(int[] arr) {
		int i = 0;
		int j = 0;
		for(i = arr.length - 1; i > 0; i--) {
			if(arr[i] > arr[i - 1]) {
				break;
			}
		}
		
		if(i == 0) return false;
		
		for(j = arr.length - 1; j > 0; j--) {
			if(arr[i - 1] < arr[j]) {
				int temp = arr[j];
				arr[j] = arr[i - 1];
				arr[i - 1] = temp;
				break;
			}
		}
		
		j = arr.length - 1;
		
		while(i < j) {
			int temp = arr[j];
			arr[j] = arr[i];
			arr[i] = temp;
			i++;
			j--;
		}
		
		return true;
	}
	
	public static void makeArray(int[] pick, int count) {
		for(int i = pick.length - 1; i >= 0; i--) {
			if(count > 0) {
				pick[i] = 1; // 1
				count--;
			} else {
				pick[i] = 0; //0
			}
		}
	}
	
	public static void horizontal(List<Point> list, int[][] map) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length - 1; j++) {
				if(map[i][j] == 0 && map[i][j + 1] == 0) {
					list.add(new Point(i, j));
				}
			}
		}
	}
	
	public static boolean check(int[][] map) {
		for(int start = 0; start < map[0].length; start++) {
			int i = 0;
			int j = start;
			while(i < map.length) {
				if(map[i][j] == 1) {
					i++;
					j++;
				} else if(map[i][j] == -1) {
					i++;
					j--;
				} else {
					i++;
				}
			}
			
			if(j != start) {
				return false;
			}
		}
		
		return true;
	}
}
