import java.io.*;
import java.util.*;

public class BOJ_이차원배열과연산_NEW {
	
	static class Pos implements Comparable<Pos>{
		int value;
		int cnt;
		public Pos(int value, int cnt) {
			this.value = value;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Pos T) {
			if(T.cnt < this.cnt) return 1;
			else if(T.cnt == this.cnt && T.value < this.value) return 1;
			else return -1;
		}
	}//Pos class.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[200][200];
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}//end input.
		int row = 3;
		int col = 3;
		int time = 0;
		while(time<=100) {
			if(map[R-1][C-1] == K) break;
			if(row>=col) {
				PriorityQueue<Pos> q = new PriorityQueue<>();
				for(int i=0; i<row; i++) {
					int[] cnt_list = new int[200];
					int length = 0;
					int top = -1;
					for(int j=0; j<col; j++) {
						cnt_list[map[i][j]]++;
						map[i][j] = 0;
					}//카운트 세기.
					for(int j=1; j<200; j++) {
						if(cnt_list[j] != 0) {
							q.offer(new Pos(j,cnt_list[j]));
							length+=2;
							if(length>100) break;
						}
					}//카운트 별 q에 넣기.
					while(!q.isEmpty()) {
						Pos p = q.poll();
						map[i][++top] = p.value;
						map[i][++top] = p.cnt;
					}//q에서 다 뽑아내기.
					if(col<length) col = length; //max_col 값 바꿔주기.
				}//각 행별로.
			}
			else {
				PriorityQueue<Pos> q = new PriorityQueue<>();
				for(int i=0; i<col; i++) {
					int[] cnt_list = new int[200];
					int length = 0;
					int top = -1;
					for(int j=0; j<row; j++) {
						cnt_list[map[j][i]]++;
						map[j][i] = 0;
					}//카운트 세기.
					for(int j=1; j<200; j++) {
						if(cnt_list[j] != 0) {
							q.offer(new Pos(j,cnt_list[j]));
							length+=2;
							if(length>100) break;
						}
					}//카운트 별 q에 넣기.
					while(!q.isEmpty()) {
						Pos p = q.poll();
						map[++top][i] = p.value;
						map[++top][i] = p.cnt;
					}//q에서 다 뽑아내기.
					if(row<length) row = length; //max_col 값 바꿔주기.
				}//각 행별로.
			}//end check_row.
			time++;
		}
		if(time>100) System.out.println(-1);
		else System.out.println(time);
	}//end main.
}//end class.
