import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2225_합분해_200708 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N,K <= 200
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		// K = 0일때는 고려하지 않음.
		long[][] dp = new long[K+1][N+1];
		Arrays.fill(dp[1], 1);
		
		for (int i = 2; i < dp.length; i++) {
			// k가 몇개이든 0이 나오려면 0으로만 구성되어야함.
			dp[i][0] = 1;
			for (int j = 1; j < dp[i].length; j++) {
				dp[i][j] = dp[i][j-1] + dp[i-1][j];
				dp[i][j] %= 1000000000;
			}
		}
		
		System.out.println(dp[K][N]%1000000000);
	}
}
