package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1976_여행가자_200715 {
    static int[] p; // 각 인덱스의 부모를 저장할 배열
    static int[] rank; // 각 인덱스의 깊이를 저장할 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 도시의 수
        int M = Integer.parseInt(br.readLine()); // 여행계획에 속한 도시의 수

        // make set
        p = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            p[i] = i;
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1 ; j <= N; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 1) union(i, j);
            }
        }

        int[] plan = new int[M]; // 여행갈 도시
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < M; i++) {
            if (findSet(plan[0]) != findSet(plan[i])) {
                System.out.printf("NO");
                return;
            }
        }
        System.out.printf("YES");
    } // end of main

    static void union(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);

        if (px != py) link(px, py);
    }

    static int findSet(int x) {
        if (p[x] == x) return x;
        return p[x] = findSet(p[x]);
    }

    static void link(int px, int py) {
        if (rank[px] > rank[py]) {
            p[py] = px;
        } else {
            p[px] = py;
            if (rank[px] == rank[py]) rank[py]++;
        }
    }
} // end of class
