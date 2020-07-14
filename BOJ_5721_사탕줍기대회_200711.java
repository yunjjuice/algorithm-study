/* 5721. 사탕 줍기 대회 Gold 5
https://www.acmicpc.net/problem/5721
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N, M;
        int[] horizon, vertical;

        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            if (M == 0) break;
            N = Integer.parseInt(st.nextToken());

            horizon = new int[N + 1];
            vertical = new int[M + 1];

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 1; j <= N; j++) {
                    horizon[j] = Integer.parseInt(st.nextToken());
                    if (j >= 2) horizon[j] = Math.max(horizon[j - 1], horizon[j - 2] + horizon[j]);
                }

                vertical[i] = horizon[N];
                if (i >= 2) vertical[i] = Math.max(vertical[i - 1], vertical[i - 2] + vertical[i]);
            }

            sb.append(vertical[M]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
