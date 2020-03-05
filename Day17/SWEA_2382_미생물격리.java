import java.io.*;
import java.util.*;

public class SWEA_2382_미생물격리 {
	static class Virus{
		int x;
		int y;
		int direction;
		int size;
		int max;
		public Virus(int x, int y, int direction, int size, int max) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.size = size;
			this.max = max;
		}
	}
	static Virus[][] map;
	static int[][] dir = {{0,0},{-1,0},{1,0},{0,-1},{0,1}}; //숫자 맞춰주기위해 의미없는 값 하나 넣음.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //셀의 갯수.
			int M = Integer.parseInt(st.nextToken()); //격리 시간.
			int K = Integer.parseInt(st.nextToken()); //미생물 수.
			map = new Virus[N][N];
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int dx = Integer.parseInt(st.nextToken());
				int dy = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				map[dx][dy] = new Virus(dx,dy,direction,size,size);
			}
			int time = 0;
			Queue<Virus> q = new LinkedList<>();
			while(time <M) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(map[i][j] != null) {
							q.add(map[i][j]);
							map[i][j] = null;
						}
					}
				}//일단 다 꺼내오기.
				while(!q.isEmpty()) {
					Virus v = q.poll();
//					System.out.println(v.x + " "  + v.y + " " + v.direction + " " + v.size);
					int direction = v.direction;
					int size = v.size;
					int dx = v.x + dir[direction][0];
					int dy = v.y + dir[direction][1];
					if(dx == 0 || dx == N-1 || dy == 0 || dy == N-1) {
						direction = direction%2 == 1 ? direction+1 : direction-1;
						size = size/2;
						if(size>0) map[dx][dy] = new Virus(dx,dy,direction,size,size);
					}
					else{
						if(map[dx][dy] == null) map[dx][dy] = new Virus(dx,dy,direction,size,size);
						else {
							if(map[dx][dy].max < size) {
								map[dx][dy].direction = direction;
								map[dx][dy].max = size;
							}
							map[dx][dy].size += size;
						}
					}
				}//end queue.
				time++;
			}//시뮬레이션 스타트.
			int answer = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] != null) answer += map[i][j].size;
				}
			}
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}//end TestCase.
		System.out.println(sb);
	}//end main.
}//end class.
