import java.io.*;
import java.util.*;

public class SWEA_8382_방향전환 {
	static int[] direct = {1,-1};
	static boolean[][][] map;
	static int answer;
	
	static class Pos{
		int x;
		int y;
		int dir;
		int cnt;
		public Pos(int x, int y, int dir, int cnt) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}
	}//class Pos.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			st = new StringTokenizer(br.readLine());
			int start_x = Integer.parseInt(st.nextToken())+100;
			int start_y = Integer.parseInt(st.nextToken())+100;
			int end_x = Integer.parseInt(st.nextToken())+100;
			int end_y = Integer.parseInt(st.nextToken())+100;
			map = new boolean[201][201][2];
			int dir_x = start_x < end_x ? 1 : -1;
			int dir_y = start_y < end_y ? 1 : -1;
			answer = 0;
			while(true) {
				if(start_x == end_x || start_y == end_y)break;
				start_x += dir_x;
				start_y += dir_y;
				answer+=2;
			}
			if(start_x == end_x && start_y == end_y) {
				sb.append("#").append(test).append(" ").append(answer).append("\n");
			}
			else{
				int temp = Math.abs(start_x - end_x) > Math.abs(start_y - end_y) ? Math.abs(start_x - end_x) : Math.abs(start_y - end_y);
				answer+= (temp/2)*4 + temp%2;
				sb.append("#").append(test).append(" ").append(answer).append("\n");
			}
		}//end testCase.
		System.out.print(sb);
	}//end main.
}//end class.
