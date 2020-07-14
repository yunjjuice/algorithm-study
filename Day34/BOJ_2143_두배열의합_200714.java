package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        int n = Integer.parseInt(br.readLine()); // A배열 원소 개수
        int[] input = new int[n]; // A배열 원소를 저장하기 위한 입력 배열
        int[] A = new int[n * n]; // A배열의 부집합 합을 담을 배열
        int aCount = 0;
        int sum = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());

            A[aCount++] = sum = input[i];
            for (int j = i - 1; j >= 0; j--) {
                A[aCount++] = sum += input[j];
            }

        }

        int m = Integer.parseInt(br.readLine()); // B배열 원소 개수
        input = new int[m]; // B배열 원소를 저장하기 위한 입력 배열
        int[] B = new int[m * m]; // B배열의 부집합 합을 담을 배열
        int bCount = 0;
        sum = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            input[i] = Integer.parseInt(st.nextToken());

            B[bCount++] = sum = input[i];
            for (int j = i - 1; j >= 0; j--) {
                B[bCount++] = sum += input[j];
            }
        }

        Arrays.sort(A, 0, aCount);
        Arrays.sort(B, 0, bCount);

        int[] aCnt = new int[n * n]; // A 배열의 원소의 개수를 담아둘 배열
        aCnt[0] = 1;
        int aIndex = 0;
        for (int i = 1; i < aCount; i++) {
            if (A[aIndex] == A[i]) aCnt[aIndex]++;
            else {
                A[++aIndex] = A[i];
                aCnt[aIndex] = 1;
            }
        }

        int[] bCnt = new int[m * m]; // B 배열의 원소의 개수를 담아둘 배열
        bCnt[0] = 1;
        int bIndex = 0;
        for (int i = 1; i < bCount; i++) {
            if (B[bIndex] == B[i]) bCnt[bIndex]++;
            else {
                B[++bIndex] = B[i];
                bCnt[bIndex] = 1;
            }
        }

        long ans = 0;

        for (int aIdx = 0, bIdx = bIndex; aIdx <= aIndex && bIndex >= 0; aIdx++) {
            int temp = A[aIdx] + B[bIdx];

            if (temp < t) continue;
            else if (temp > t) {
                while (bIdx > 0 && temp > t) temp = A[aIdx] + B[--bIdx];
            }
            if (temp == t) {
                ans += (long) aCnt[aIdx] * bCnt[bIdx];
            }
        }

        System.out.println(ans);
    } // end of main
} //  end of class
