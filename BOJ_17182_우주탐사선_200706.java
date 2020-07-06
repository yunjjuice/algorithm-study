/* 17182. 우주 탐사선 Gold 3
https://www.acmicpc.net/problem/17182
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] dist;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //floyd-warshall로 각 노드간 최소 거리 찾기 (이미 방문한 행성 중복 방문하는 경우 계산)
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        explore(0, 1, start, 1 << start);

        System.out.println(answer);
    }

    static void explore(int time, int count, int now, int visited) {
        if (time >= answer) return;
        if (count == N) {
            answer = time; //time always smaller than answer
            return;
        }
        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) == 0) { //아직 방문하지 않음
                explore(time + dist[now][i], count+1, i, visited | (1 << i));
            }
        }
    }
}