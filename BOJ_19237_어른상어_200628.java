/* 19237. 어른 상어 Gold 4
https://www.acmicpc.net/problem/19237
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] direct = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //위 아래 왼쪽 오른쪽
    static Smell[][] map;
    static List<Shark> sharks;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Smell[N][N];
        sharks = new ArrayList<>(M); //capacity
        for (int i = 0; i < M; i++) {
            sharks.add(new Shark(i));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken()) - 1;
                if (n >= 0) {
                    sharks.get(n).setPos(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            sharks.get(i).dir = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    sharks.get(i).priority[j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        //solve
        int time = 0;
        while (sharks.size() > 1 && time <= 1000) {
            time++;
            disappear();
            spread();
            move();
        }

        System.out.print((time > 1000 ? -1 : time));
    }

    private static void disappear() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != null) {
                    if (--map[i][j].time == 0) {
                        map[i][j] = null;
                    }
                }
            }
        }
    }

    private static void spread() {
        for (Shark shark : sharks) {
            map[shark.x][shark.y] = new Smell(shark.no, K);
        }
    }

    private static void move() {
        boolean[][] in = new boolean[N][N];
        iterateSharks:
        for (Iterator<Shark> iter = sharks.iterator(); iter.hasNext(); ) {
            Shark s = iter.next();
            //인접한 칸 중 아무 냄새가 없는 칸의 방향으로
            for (int i = 0; i < 4; i++) {
                int nx = s.x + direct[s.priority[s.dir][i]][0];
                int ny = s.y + direct[s.priority[s.dir][i]][1];
                if (boundaryCheck(nx, ny) && map[nx][ny] == null) {
                    if (in[nx][ny]) {
                        iter.remove();
                    } else {
                        in[nx][ny] = true;
                        s.x = nx;
                        s.y = ny;
                        s.dir = s.priority[s.dir][i];
                    }
                    continue iterateSharks;
                }
            }
            //아무 냄새 없는 칸이 없으면 자신의 냄새가 있는 칸의 방향으로
            for (int i = 0; i < 4; i++) {
                int nx = s.x + direct[s.priority[s.dir][i]][0];
                int ny = s.y + direct[s.priority[s.dir][i]][1];
                if (boundaryCheck(nx, ny) && map[nx][ny].no == s.no) {
                    if (in[nx][ny]) {
                        iter.remove();
                    } else {
                        in[nx][ny] = true;
                        s.x = nx;
                        s.y = ny;
                        s.dir = s.priority[s.dir][i];
                    }
                    continue iterateSharks;
                }
            }
        }
    }

    private static boolean boundaryCheck(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    private static class Smell {
        int no, time;

        public Smell(int no, int time) {
            this.no = no;
            this.time = time;
        }
    }

    private static class Shark {
        int no, x, y, dir;
        int[][] priority = new int[4][4];

        public Shark(int no) {
            this.no = no;
        }

        void setPos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}