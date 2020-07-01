import java.io.*;
import java.util.*;

public class BOJ_2096_내려가기 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] list = new int[3];
		int[] max_dp = new int[3];
		int[] min_dp = new int[3];
		int[] tmp_dp = new int[3];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				list[j] = Integer.parseInt(st.nextToken());
			}//한 줄 입력받기
			tmp_dp[0] = list[0] + Math.max(max_dp[0], max_dp[1]); 
			tmp_dp[1] = list[1] + Math.max(max_dp[0], Math.max(max_dp[1] , max_dp[2])); 
			tmp_dp[2] = list[2] + Math.max(max_dp[1], max_dp[2]); 
			for(int j=0; j<3; j++){
				max_dp[j] = tmp_dp[j];
			}
			tmp_dp[0] = list[0] + Math.min(min_dp[0], min_dp[1]); 
			tmp_dp[1] = list[1] + Math.min(min_dp[0], Math.min(min_dp[1] , min_dp[2])); 
			tmp_dp[2] = list[2] + Math.min(min_dp[1], min_dp[2]); 
			for(int j=0; j<3; j++){
				min_dp[j] = tmp_dp[j];
			}
		}//end for.
		System.out.println(Math.max(max_dp[0], Math.max(max_dp[1], max_dp[2])) + " " + Math.min(min_dp[0], Math.min(min_dp[1], min_dp[2])));
	}//end main.
}//end class.
