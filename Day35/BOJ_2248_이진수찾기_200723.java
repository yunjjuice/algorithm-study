package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2248_이진수찾기_200723 {
    static long[][] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        long I = Long.parseLong(st.nextToken());
        dp = new long [N + 1][N + 1];

        dp[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        makeBinary(N, L, I);
        System.out.println(sb.toString());
    } // end of main

    static void makeBinary(int N, int L, long I) {
        if (N == 0) return;
        if (L == 0) {
            for (int i = 0; i < N; i++) {
                sb.append("0");
            }
            return;
        }

        long skip = 0;
        for (int i = 0; i <= L; i++) {
            skip += dp[N - 1][i];
        }

        if (skip >= I) {
            sb.append("0");
            makeBinary(N - 1, L, I);
        } else {
            sb.append("1");
            makeBinary(N - 1, L - 1, I - skip);
        }
    }
} // end of class