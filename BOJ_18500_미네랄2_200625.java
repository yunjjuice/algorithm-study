/* 18500. 미네랄 2 Gold 3
https://www.acmicpc.net/problem/18500
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0},};
    static int R, C;
    static char[][] cave;
    static int[][] cluster;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cave = new char[R][C];
        for (int i = 0; i < R; i++) {
            cave[i] = br.readLine().toCharArray();
        }
        int t = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < t; i++) {
            int h = (Integer.parseInt(st.nextToken()) - R) * -1; //높이(R~1)를 0~R-1로 바꾼다
            if (i % 2 == 0)
                throwStick(h, 0, 1);
            else
                throwStick(h, C - 1, -1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(cave[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    private static void throwStick(int h, int from, int val) {
        for (int i = from; i >= 0 && i < C; i += val) {
            if (cave[h][i] != '.') { //미네랄 찾음
                cave[h][i] = '.';
                gravitation(h, i);
                return;
            }
        }
    }

    private static void gravitation(int r, int c) {
        cluster = new int[R][C];
        int cnum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int k = 0; k < 4; k++) {
                    int nr = i + dir[k][0], nc = j + dir[k][1];
                    if (boundaryCheck(nr, nc) || cave[nr][nc] != 'x' || cluster[nr][nc] != 0) continue;
                    findClusters(nr, nc, ++cnum);
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dir[i][0], nc = c + dir[i][1];
            if (boundaryCheck(nr, nc) || cave[nr][nc] != 'x' || !isNeedToDown(cluster[nr][nc])) continue;
            down(cluster[nr][nc]);
        }
    }


    private static void findClusters(int r, int c, int clusterNumber) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(r, c));
        cluster[r][c] = clusterNumber;
        while (!queue.isEmpty()) {
            int x = queue.peek().x, y = queue.poll().y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0], ny = y + dir[i][1];
                if (boundaryCheck(nx, ny) || cave[nx][ny] != 'x' || cluster[nx][ny] != 0) continue;
                cluster[nx][ny] = clusterNumber;
                queue.add(new Pos(nx, ny));
            }
        }
    }

    private static boolean isNeedToDown(int clusterNumber) {
        for (int j = 0; j < C; j++) {
            if (cluster[R - 1][j] == clusterNumber) {
                return false;
            }
        }
        for (int i = R - 2; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (cluster[i][j] == clusterNumber) {
                    if (cave[i + 1][j] == 'x' && cluster[i + 1][j] != clusterNumber)
                        return false;
                }
            }
        }
        return true;
    }

    private static void down(int targetCluster) {
        //몇 칸 내려가야 하는지 찾기
        int dist = R; //INF
        for (int j = 0; j < C; j++) {
            for (int i = 0; i < R; i++) {
                if (cluster[i][j] == targetCluster) {
                    while (i < R && cluster[i][j] == targetCluster) i++;

                    int oc = i;
                    while (oc < R && cave[oc][j] != 'x') oc++;

                    if (oc < R && cluster[oc][j] == targetCluster) continue;
                    if (dist > oc - i) dist = oc - i;
                }
            }
        }

        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (cluster[i][j] == targetCluster) {
                    cave[i + dist][j] = 'x';
                    cave[i][j] = '.';
                }
            }
        }
    }

    private static boolean boundaryCheck(int x, int y) {
        return x < 0 || y < 0 || x >= R || y >= C;
    }

    private static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}