import java.io.*;
import java.util.*;

public class SWEA_8275_햄스터 {
	static int[] cage;
	static int N;
	static int X;
	static int M;
	static boolean can;
	static int[] answer_list;
	static int[] answer;
	static int[][] data_list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			can = false;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			answer_list = new int[N];
			answer = new int[N];
			data_list = new int[M][3];
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				data_list[i][0] = Integer.parseInt(st.nextToken());
				data_list[i][1] = Integer.parseInt(st.nextToken());
				data_list[i][2] = Integer.parseInt(st.nextToken());
			}
			dfs(0);
			sb.append("#").append(test).append(" ");
			if(!can) {
				sb.append(-1);
			}
			else {//답이 존재할경우.
				for(int i=0; i<N; i++) {
					sb.append(answer[i]).append(" ");
				}
			}
			sb.append("\n");
		}//end TestCase.
		System.out.print(sb);
	}//end main.
	public static void dfs(int cnt) {
		if(cnt == N) {
			boolean flag = true;
			for(int i=0; i<M; i++) {
				if(!check(i)) {
					flag = false;
					break;
				}
			}
			if(flag) {
				can = true;
				int before = 0;
				int after = 0;
				for(int i=0; i<N; i++) {
					before += answer[i];
					after += answer_list[i];
				}
				if(before < after) {
					for(int i=0; i<N; i++) {
						answer[i] = answer_list[i];
					}
				}
				//비교 여기서.
			}
			return ;
		}
		for(int i=0; i<=X; i++) {
			answer_list[cnt] = i;
			dfs(cnt+1);
		}
	}//end dfs.
	public static boolean check(int idx) {
		int sum = 0;
		for(int i=data_list[idx][0]-1; i<=data_list[idx][1]-1; i++) {
			sum+= answer_list[i];
		}
		if(sum == data_list[idx][2]) return true;
		else return false;
	}
}//end class.
