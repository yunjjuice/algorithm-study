/* 19236. 청소년 상어 Gold 3
https://www.acmicpc.net/problem/19236
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] dir = {{}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] number = new int[6][6]; //물고기 번호, 상어는 -2
        for (int i = 0; i < 6; i++) {
            Arrays.fill(number[i], -1);
        }
        int[][] direction = new int[6][6]; //방향
        for (int i = 1; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 5; j++) {
                number[i][j] = Integer.parseInt(st.nextToken());
                direction[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        new Container(number, direction, 0, new Pos(1, 1)).moveShark();
        System.out.println(answer);
    }

    private static int[][] copyArray(int[][] arr) {
        int[][] newArr = new int[6][6];
        for (int i = 0; i < 6; i++) {
            System.arraycopy(arr[i], 0, newArr[i], 0, 6);
        }
        return newArr;
    }

    private static class Container {
        int[][] number, direction;
        int ate;
        Pos shark;

        public Container(int[][] number, int[][] course, int ate, Pos shark) {
            this.number = number;
            this.direction = course;
            this.ate = ate;
            this.shark = shark;
        }

        public Container(Container c) {
            this.number = copyArray(c.number);
            this.direction = copyArray(c.direction);
            this.ate = c.ate;
            this.shark = new Pos(c.shark);
        }

        public void moveShark() {
            ate += number[shark.x][shark.y];
            if (answer < ate) answer = ate;

            number[shark.x][shark.y] = -2;
            moveFishes();

            number[shark.x][shark.y] = 0;
            int d = direction[shark.x][shark.y];
            while (true) {
                shark.x += dir[d][0];
                shark.y += dir[d][1];
                if (number[shark.x][shark.y] == 0) continue; //빈 칸이면 다음 탐색
                if (number[shark.x][shark.y] < 0) break; //경계 -> 집으로
                new Container(this).moveShark();
            }
        }

        private void moveFishes() {
            int min = 1;
            Pos fish;
            while ((fish = findNthFish(min)) != null) {
                min = number[fish.x][fish.y] + 1;
                moveFish(fish);
            }
        }

        private Pos findNthFish(int min) {
            int n = 20; //INF
            Pos p = null;
            for (int i = 1; i < 5; i++) {
                for (int j = 1; j < 5; j++) {
                    if (number[i][j] >= min && number[i][j] < n) {
                        n = number[i][j];
                        p = new Pos(i, j);
                    }
                }
            }
            return p;
        }

        private void moveFish(Pos fish) {
            int nd = direction[fish.x][fish.y];
            for (int i = 1; i <= 8; i++) {
                int nx = fish.x + dir[nd][0], ny = fish.y + dir[nd][1];
                if (number[nx][ny] >= 0) {
                    int tmp = number[fish.x][fish.y];
                    number[fish.x][fish.y] = number[nx][ny];
                    number[nx][ny] = tmp;

                    direction[fish.x][fish.y] = direction[nx][ny];
                    direction[nx][ny] = nd;
                    return;
                }
                nd = nd + 1 > 8 ? 1 : nd + 1;
            }
        }
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(Pos p) {
            this.x = p.x;
            this.y = p.y;
        }
    }
}