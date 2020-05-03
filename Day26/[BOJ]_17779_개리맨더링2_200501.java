import java.io.*;
import java.util.*;

public class BOJ_개리맨더링2 {
	static int[][] map;
	static int total_sum;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		int answer = Integer.MAX_VALUE;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				total_sum += map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//draw_map.
		for(int i=1; i<=N-2; i++) {
			for(int j=2; j<=N-1; j++) {
				int d1 = 1;
				while(true) {
					if(i+d1 == N || j+d1 == N+1) break;
					d1++;
				}
				int d2 = 1;
				while(true) {
					if(i+d2 == N || j-d2 == 0) break;
					d2++;
				}
				for(int t1=1; t1<=d1-1; t1++) {
					for(int t2=1; t2<=d2-1; t2++) {
						if(t1+t2 > N-i) continue;
						else {
							int temp_answer = calculate(i,j,t1,t2);
							if(temp_answer < answer) answer = temp_answer;
						}
					}
				}
			}
		}
		System.out.println(answer);
	}//end main.
	public static int calculate(int x, int y, int d2, int d1) {
		int[] cnt = new int[5];
		int x2 = x+d2;
		int y2 = y+d2;
		int x3 = x+d1;
		int y3 = y-d1;
		boolean[][] new_map = new boolean[N+1][N+1];
		for(int i=0; i<=d1; i++) {
			new_map[x+i][y-i] = true;
		}
		for(int i=0; i<=d2; i++) {
			new_map[x+i][y+i] = true;
		}
		for(int i=0; i<=d1; i++) {
			new_map[x2+i][y2-i] = true;
		}
		for(int i=0; i<=d2; i++) {
			new_map[x3+i][y3+i] = true;
		}
		for(int i=1; i<x+d1; i++) {
			for(int j=1; j<=y; j++) {
				if(new_map[i][j]) break;
				else cnt[0] += map[i][j];
			}
		}
		for(int i=1; i<=x+d2; i++) {
			for(int j=N; j>y; j--) {
				if(new_map[i][j]) break;
				else cnt[1] += map[i][j];
			}
		}
		for(int i=x+d1; i<=N; i++) {
			for(int j=1; j<y-d1+d2; j++) {
				if(new_map[i][j]) break;
				else cnt[2] += map[i][j];
			}
		}
		for(int i=N; i>x+d2; i--) {
			for(int j=N; j>=y-d1+d2; j--) {
				if(new_map[i][j]) break;
				else cnt[3] += map[i][j];
			}
		}
		cnt[4] = total_sum - (cnt[0] + cnt[1] + cnt[2] + cnt[3]);
		int max = cnt[0];
		int min = cnt[0];
		for(int i=1;i<5; i++) {
			if(cnt[i] < min) min = cnt[i];
			if(cnt[i] > max) max = cnt[i];
		}
		
		return max-min;
	}
}//end class.
