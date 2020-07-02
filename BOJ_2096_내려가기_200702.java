/* 2096. 내려가기 Gold 4
https://www.acmicpc.net/problem/2096
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] rowMin = new int[3], rowMax = new int[3];
    static int[][] idx = {{0, 1}, {0, 1, 2}, {1, 2}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[] tmpMin = new int[3], tmpMax = new int[3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                tmpMin[j] = tmpMax[j] = Integer.parseInt(st.nextToken());
                tmpMin[j] += min(j);
                tmpMax[j] += max(j);
            }
            rowMin = Arrays.copyOf(tmpMin, 3);
            rowMax = Arrays.copyOf(tmpMax, 3);
        }
        System.out.println(max(1) + " " + min(1));
    }

    static int min(int n) {
        int min = Integer.MAX_VALUE;
        for (int i : idx[n]) {
            if (min > rowMin[i]) min = rowMin[i];
        }
        return min;
    }

    static int max(int n) {
        int max = 0;
        for (int i : idx[n]) {
            if (max < rowMax[i]) max = rowMax[i];
        }
        return max;
    }
}