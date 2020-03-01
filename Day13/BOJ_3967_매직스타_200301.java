/*
 * BOJ 3967 매직 스타
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
//import java.util.Arrays;

public class BOJ3967 {
	static int[][][] pos = {
			{{0, 4}, {1, 3}, {2, 2}, {3, 1}},
			{{0, 4}, {1, 5}, {2, 6}, {3, 7}},
			{{1, 1}, {1, 3}, {1, 5}, {1, 7}},
			{{1, 1}, {2, 2}, {3, 3}, {4, 4}},
			{{1, 7}, {2, 6}, {3, 5}, {4, 4}},
			{{3, 1}, {3, 3}, {3, 5}, {3, 7}}
	};
	static String alpha = "ABCDEFGHIJKL"; // 사용할 알파벳들
	static boolean[] used = new boolean[12]; // 사용된 알파벳 저장
	static char[][] map = new char[5][9]; 
	static boolean flag;
	static ArrayList<Node> list = new ArrayList<Node>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			String st = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = st.charAt(j);
				if(alpha.contains(map[i][j]+"")) { // 이미 사용된 글자는 다시 사용 못하도록 하기 위해
					used[alpha.indexOf(map[i][j])] = true;
				}
				if(map[i][j] == 'x') { // 입력해야될 알파벳 개수
					list.add(new Node(i, j));
				}
			}
		}
		
		// 입력 확인
//		System.out.println(Arrays.toString(used));
//		for (int i = 0; i < 5; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		solve(0);
		System.out.println(sb);
	}
	
	static void solve(int index) {
		if(index == list.size()) {
			if(check()) {
				flag = true;
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[i].length; j++) {
						sb.append(map[i][j]);
					}
					sb.append("\n");
				}
			}
			return;
		}
		
		if(!flag) {
			for (int i = 0; i < 12; i++) {
				if(used[i])
					continue;
				map[list.get(index).c][list.get(index).r] = alpha.charAt(i);
				used[i] = true;
				solve(index+1);
				used[i] = false;
				map[list.get(index).c][list.get(index).r] = 'x';
			}
		}
	}
	
	static boolean check() {
		if((map[pos[0][0][0]][pos[0][0][1]] + map[pos[0][1][0]][pos[0][1][1]] + map[pos[0][2][0]][pos[0][2][1]] + map[pos[0][3][0]][pos[0][3][1]]) - 4 * 'A' + 4 != 26)
			return false;
		if((map[pos[1][0][0]][pos[1][0][1]] + map[pos[1][1][0]][pos[1][1][1]] + map[pos[1][2][0]][pos[1][2][1]] + map[pos[1][3][0]][pos[1][3][1]]) - 4 * 'A' + 4 != 26)
			return false;
		if((map[pos[2][0][0]][pos[2][0][1]] + map[pos[2][1][0]][pos[2][1][1]] + map[pos[2][2][0]][pos[2][2][1]] + map[pos[2][3][0]][pos[2][3][1]]) - 4 * 'A' + 4 != 26)
			return false;
		if((map[pos[3][0][0]][pos[3][0][1]] + map[pos[3][1][0]][pos[3][1][1]] + map[pos[3][2][0]][pos[3][2][1]] + map[pos[3][3][0]][pos[3][3][1]]) - 4 * 'A' + 4 != 26) 
			return false;
		if((map[pos[4][0][0]][pos[4][0][1]] + map[pos[4][1][0]][pos[4][1][1]] + map[pos[4][2][0]][pos[4][2][1]] + map[pos[4][3][0]][pos[4][3][1]]) - 4 * 'A' + 4 != 26) 
			return false;
		if((map[pos[5][0][0]][pos[5][0][1]] + map[pos[5][1][0]][pos[5][1][1]] + map[pos[5][2][0]][pos[5][2][1]] + map[pos[5][3][0]][pos[5][3][1]]) - 4 * 'A' + 4 != 26) 
			return false;
			
		return true;
	}
}

class Node {
	int c;
	int r;
	public Node(int c, int r) {
		this.c = c;
		this.r = r;
	}
}
