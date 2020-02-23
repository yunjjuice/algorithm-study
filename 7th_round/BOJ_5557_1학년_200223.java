import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5557_1ÇÐ³â_200223 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n - 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n - 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int res = Integer.parseInt(st.nextToken());
		
		long[][] dp = new long[n - 1][21];
		dp[0][arr[0]] = 1;
		
		for(int i = 1; i < n - 1; i++) {
			for(int j = 0; j <= 20; j++) {
				if(j - arr[i] >= 0) {
					dp[i][j - arr[i]] += dp[i - 1][j];
				}
				
				if(j + arr[i] <= 20) {
					dp[i][j + arr[i]] += dp[i - 1][j];
				}
			}
		}
		
		System.out.println(dp[n - 2][res]);
	}
}
