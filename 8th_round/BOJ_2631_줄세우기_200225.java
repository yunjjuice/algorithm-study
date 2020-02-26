import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// n - LIS
public class BOJ_2631_줄세우기_200225 {
	static int max = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());	
		
		int[] dp = new int[n]; // dp[i] = i번째 원소를 끝으로 하는 최장 증가 수열 길이
		
		Arrays.fill(dp, 1);
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < i; j++) {
				if(arr[i] > arr[j] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;			
				}
			}
			if(max < dp[i]) max = dp[i];
		}
		
		System.out.println(n - max);
	}
}
