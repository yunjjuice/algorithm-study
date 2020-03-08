import java.io.*;
import java.util.*;

public class BOJ_히스토그램에서가장큰직사각형_풀이2 {
	static int[] num;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if(N == 0) break;
			num = new int[N];
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}//end input.
			long answer = dfs(0,N-1);
			sb.append(answer).append("\n");
		}//end testcase.
		System.out.print(sb);
	}//end main.
	public static long dfs(int start, int end) {
		System.out.print(start + " , " + end);
		if(start == end) return num[start];
		int min_value = Integer.MAX_VALUE;
		int min_idx = 0;
		for(int i=start; i<=end; i++) {
			if(min_value > num[i]) {
				min_value = num[i];
				min_idx = i;
			}
		}//최소 값 찾기.
		long answer = min_value * (end-start+1);
		System.out.println("  "  + answer);
		if(start<min_idx) {
			long answer_before = dfs(start,min_idx-1); 
			answer = answer < answer_before ? answer_before : answer;
		}
		if(min_idx<end) {
			long answer_after = dfs(min_idx+1, end);
			answer = answer < answer_after ? answer_after : answer;
		}
		return answer;
	}
}//end class.
