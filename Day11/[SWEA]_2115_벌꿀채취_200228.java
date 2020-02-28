import java.io.*;
import java.util.*;

public class SWEA_벌꿀채취 {
	static int N;
	static int M;
	static int C;
	static honey[][] map;
	static boolean[][] visited;
	static int[][] start = new int[2][2];
	static int answer;
	static int[][] dp1;
	static int[][] dp2;
	
	static class honey{
		int weight;
		int price;
		public honey(int weight) {
			this.weight = weight;
			this.price = weight*weight;
		}
	}//class honey.
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int test=1; test<=TC; test++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			//end input.
			map = new honey[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = new honey(Integer.parseInt(st.nextToken()));
				}
			}//draw map.
			dfs(0,0,0);
			
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}//end testCase.
		System.out.print(sb);
	}//end main.
	public static void dfs(int dx, int dy, int cnt) {
		if(cnt == 2) {
			dp1 = new int[M][C+1];
			dp2 = new int[M][C+1];
			int temp = get_honey1(0,C) + get_honey2(0,C);
			if(temp > answer) answer = temp;
			return ;
		}
		for(int i=dy; i<=N-M; i++) {
			start[cnt][0] = dx;
			start[cnt][1] = i;
			dfs(dx,i+M,cnt+1);
		}//같은 행에서 뽑을 수 있는 경우.
		for(int i=dx+1; i<N; i++) {
			for(int j=0; j<=N-M; j++) {
				start[cnt][0] = i;
				start[cnt][1] = j;
				dfs(i,j+M,cnt+1);
			}
		}
	}//end dfs.
	public static int get_honey1(int idx, int capacity) {
		if(idx == M) return 0;
		int now = dp1[idx][capacity];
		if (map[start[0][0]][start[0][1]+idx].weight <= capacity) {
			now = get_honey1(idx+1, capacity-map[start[0][0]][start[0][1]+idx].weight) + map[start[0][0]][start[0][1]+idx].price;
		}
	    now = Math.max(now, get_honey1(idx+1, capacity)); 
	    dp1[idx][capacity] = now;
		return dp1[idx][capacity];
	}
	public static int get_honey2(int idx, int capacity) {
		if(idx == M) return 0;
		int now = dp2[idx][capacity];
		if (map[start[1][0]][start[1][1]+idx].weight <= capacity) {
			now = get_honey2(idx+1, capacity-map[start[1][0]][start[1][1]+idx].weight) + map[start[1][0]][start[1][1]+idx].price;
		}
	    now = Math.max(now, get_honey2(idx+1, capacity)); 
	    dp2[idx][capacity] = now;
		return dp2[idx][capacity];
	}
}//end class.
