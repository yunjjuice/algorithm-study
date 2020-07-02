/* 10875. 뱀 Gold 1
https://www.acmicpc.net/problem/10875
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 200000005;
    static final int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;
    static final int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int L, N;
    static List<Line> lines = new ArrayList<>();
    static int snakeX = 0, snakeY = 0, snakeD = 3; //뱀 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        L = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        int[] seconds = new int[N];
        char[] dirs = new char[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            seconds[i] = Integer.parseInt(st.nextToken());
            dirs[i] = st.nextToken().charAt(0);
        }

        //경계선
        lines.add(new Line(-L - 1, -L - 1, -L - 1, L + 1));
        lines.add(new Line(-L - 1, -L - 1, L + 1, -L - 1));
        lines.add(new Line(L + 1, -L - 1, L + 1, L + 1));
        lines.add(new Line(-L - 1, L + 1, L + 1, L + 1));
        lines.add(new Line(0, 0, 0, 0)); //중점 (뱀 초기 위치)

        long time = 0;
        int turn = -1;
        while (++turn < N) {
            int t = moveForward(seconds[turn]);
            time += t;
            if (lines.size() != turn + 6) break;

            if (dirs[turn] == 'L') turnLeft();
            else turnRight();
        }
        if (turn == N) time += moveForward(2 * L + 1); //최대값

        System.out.print(time);
    }

    static int moveForward(int seconds) {
        int nx = snakeX, ny = snakeY; //Line 만들 때 바뀌니까 보관 해두기
        int nt = INF;
        Line now = new Line(seconds);
        for (Line line : lines) {
            if (line.isCrash(now)) { //부딪힘
                int t;
                if (snakeD == LEFT) {
                    t = ny - line.ey;
                } else if (snakeD == RIGHT) {
                    t = line.sy - ny;
                } else if (snakeD == UP) {
                    t = nx - line.ex;
                } else { //down
                    t = line.sx - nx;
                }
                if (nt > t) nt = t;
            }
        }
        if (nt != INF) return nt;
        lines.add(now);
        return seconds;
    }

    static void turnLeft() {
        snakeD = (snakeD + 1) % 4;
    }

    static void turnRight() {
        snakeD = (snakeD + 3) % 4;
    }

    static class Line {
        int sx, sy, ex, ey; //start, end -> -L, -L에 가까운 점이 시작점

        public Line(int sx, int sy, int ex, int ey) {
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
        }

        public Line(int seconds) {
            switch (snakeD) {
                case UP:
                    ex = snakeX - 1;
                    sx = snakeX = snakeX - seconds;
                    sy = ey = snakeY;
                    break;
                case LEFT:
                    ey = snakeY - 1;
                    sy = snakeY = snakeY - seconds;
                    sx = ex = snakeX;
                    break;
                case DOWN:
                    sx = snakeX + 1;
                    ex = snakeX = snakeX + seconds;
                    sy = ey = snakeY;
                    break;
                case RIGHT:
                    sy = snakeY + 1;
                    ey = snakeY = snakeY + seconds;
                    sx = ex = snakeX;
            }
        }

        boolean isCrash(Line l) {
            if (sx == ex) { //가로
                if (l.sx == l.ex) { //가로
                    if (sx != l.sx) return false;
                    if (ey < l.sy || l.ey < sy) return false;
                } else { //세로
                    if (ey < l.sy || l.sy < sy) return false;
                    if (l.ex < sx || sx < l.sx) return false;
                }
            } else {
                if (l.sx == l.ex) {
                    if (ex < l.sx || l.sx < sx) return false;
                    if (l.ey < sy || sy < l.sy) return false;
                } else {
                    if (sy != l.sy) return false;
                    if (ex < l.sx || l.ex < sx) return false;
                }
            }
            return true;
        }
    }
}