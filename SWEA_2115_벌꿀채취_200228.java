/* 2115. 벌꿀채취 모의테스트
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, C;
    static int[][] map, subset;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            } //end of input

            //solve
            subset = new int[N][N - M + 1];
            makeSubset();
            sb.append('#').append(testCase).append(' ').append(getMaxBenefit()).append('\n');
        }
        System.out.print(sb);
    } //end of main

    static void makeSubset() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - M + 1; j++) {
                makeMaxSubset(i, j, 0, 0, 0);
            }
        }
    }

    // i : 행위치, j : 열위치, cnt : 고려한 원소 수, sum : 부분집합에 속한 원소의 합, powSum : 부분집합에 속한 원소의 이익
    static void makeMaxSubset(int i, int j, int cnt, int sum, int powSum) {
        if (sum > C) return; //부분집합의 합이 목표량 C를 초과하면 리턴
        if (cnt == M) {
            if (subset[i][j - M] < powSum) {
                subset[i][j - M] = powSum;
            }
            return;
        }

        makeMaxSubset(i, j + 1, cnt + 1, sum + map[i][j], powSum + map[i][j] * map[i][j]); //선택하는 경우
        makeMaxSubset(i, j + 1, cnt + 1, sum, powSum); //선택하지 않는 경우
    }

    static int getMaxBenefit() {
        int max = 0, temp = 0; //max : 조합적 선택 이후 최대 이익 값
        //1. 일꾼 A 기준 선택 -> maxMap[i][j]
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - M + 1; j++) {
                //2. 일꾼 B 선택
                // 같은 행 기준 선택
                for (int j2 = j + M; j2 <= N - M; j2++) {
                    temp = subset[i][j] + subset[i][j2]; // 두 일꾼 조합의 최대 이익
                    if (max < temp)
                        max = temp;
                }
                // 다음행부터 마지막행까지 선택
                for (int i2 = i + 1; i2 < N; i2++) {
                    for (int j2 = 0; j2 <= N - M; j2++) {
                        temp = subset[i][j] + subset[i2][j2];
                        if (max < temp)
                            max = temp;
                    }
                }

            }
        }
        return max;
    }

} //end of class