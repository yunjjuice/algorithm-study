package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N, M;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if (M == 0 && N == 0) break;

            int[] rows = new int[M + 1]; // 각 행에 대한 최대값을 저장해 둘 배열

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int[] temps = new int[N + 1]; // 한 행을 입력받을 배열
                temps[1] = Integer.parseInt(st.nextToken());

                for (int j = 2; j <= N; j++) {
                    int input = Integer.parseInt(st.nextToken());
                    temps[j] = Math.max(temps[j - 1], temps[j - 2] + input);
                }

                rows[i] = temps[N];
            }

            int[] dp = new int[M + 1]; // rows를 이용하여 dp로 푼다.
            dp[1] = rows[1];

            for (int i = 2; i <= M; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + rows[i]);
            }

            sb.append(dp[M]).append("\n");
        }
        System.out.print(sb.toString());
    } // end of main
} // end of class
