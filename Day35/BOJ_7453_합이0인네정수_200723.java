package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7453_합이0인네정수_200723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] A = new long[n];
        long[] B = new long[n];
        long[] C = new long[n];
        long[] D = new long[n];
        long[] AB = new long[n * n];
        long[] CD = new long[n * n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            A[i] = Long.parseLong(st.nextToken());
            B[i] = Long.parseLong(st.nextToken());
            C[i] = Long.parseLong(st.nextToken());
            D[i] = Long.parseLong(st.nextToken());
        }

        // A,B를 더한 값을 따로 저장하고, C,D와 저장한 값을 저장한다.
        int abIndex = 0;
        int cdIndex = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[abIndex++] = A[i] + B[j];
                CD[cdIndex++] = C[i] + D[j];
            }
        }

        Arrays.sort(AB, 0, abIndex);
        Arrays.sort(CD, 0, cdIndex);

        int abCountIndex = 0;
        int cdCountIndex = 0;
        int[] abCount = new int[n * n];
        int[] cdCount = new int[n * n];

        abCount[0] = 1;
        for (int i = 1; i < abIndex; i++) {
            if (AB[abCountIndex] == AB[i]) abCount[abCountIndex]++;
            else {
                AB[++abCountIndex] = AB[i];
                abCount[abCountIndex] = 1;
            }
        }

        cdCount[0] = 1;
        for (int i = 1; i < cdIndex; i++) {
            if (CD[cdCountIndex] == CD[i]) cdCount[cdCountIndex]++;
            else {
                CD[++cdCountIndex] = CD[i];
                cdCount[cdCountIndex] = 1;
            }
        }

        long count = 0;
        for (int i = 0, j = cdCountIndex; i <= abCountIndex; i++) {
            while (AB[i] + CD[j] > 0 && j > 0) j--;
            if (AB[i] + CD[j] == 0) count += (long) abCount[i] * cdCount[j];
        }
        System.out.println(count);
    }
}
