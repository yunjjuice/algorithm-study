/* 2225. 합분해 Gold 5
https://www.acmicpc.net/problem/2225
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int MODER = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int dp[][] = new int[K + 1][N + 1];
        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= K; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= N; j++) {
                dp[i][j] = (dp[i - 1][j] % MODER + dp[i][j - 1] % MODER) % MODER;
            }
        }

        System.out.println(dp[K][N]);
    }
}