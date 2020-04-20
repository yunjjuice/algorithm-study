import java.io.*;
import java.util.*;

public class BOJ_주사위윷놀이 {
	static int K = 10;
	static int[] select = new int[K];
	static int[] number = new int[K];
	static int[][] map = {{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,0,0,0,0,0,0,0,0,0},
						  {10,13,16,19,25},
						  {20,22,24,25,30,35,40},
						  {30,28,27,26,25}};
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}//end input.
		dfs(0);
		System.out.println(max);
	}//end main.
	public static void dfs(int cnt) { 
		if(cnt == K){
			int[][] player = new int[4][2]; //플레이어의 간 거리 , 플레이어의 도로 종류.
			boolean[][] visited = new boolean[4][41];
			int sum = 0;
			boolean can_end = true;
			for(int i=0; i<K; i++) {
				int now_road = player[select[i]][0]; //플레이어 길 종류.
				int now_idx = player[select[i]][1]; //플레이어 현재 온 거리.
				if(now_road == 0 && now_idx > 20) continue;
				if(now_road == 2 && now_idx >8) continue;
				visited[now_road][now_idx] = false;
				int next_idx = now_idx + number[i];
				switch(now_road) {
				case 0:{
					if(next_idx == 5) {
						now_road=1;
						next_idx=0;
					}
					else if(next_idx == 10) {
						now_road=2;
						next_idx=0;
					}
					else if(next_idx == 15) {
						now_road=3;
						next_idx=0;
					}
					break;
				}
				case 1:{
					if(next_idx >=4) {
						now_road=2;
						next_idx-=1;
					}
					break;
				}
				case 3:{
					if(next_idx >=4) {
						now_road=2;
						next_idx-=1;
					}
					break;
				}
				}//end switch.
				if(now_road == 2 && next_idx >=6) {
					now_road = 0;
					next_idx += 14;
				}
				if(map[now_road][next_idx] !=0 && visited[now_road][next_idx]) {
					can_end = false;
					break;
				}
				visited[now_road][next_idx] = true;
				sum+= map[now_road][next_idx];
				player[select[i]][0] = now_road;
				player[select[i]][1] = next_idx;
			}//end for.
			if(can_end && sum > max) max = sum;
			return ;
		}
		for(int i=0; i<4; i++) {
			select[cnt] = i;
			dfs(cnt+1);
		}
	}//end dfs.
}//end class.
