/* 11967. 불켜기 Gold 4
https://www.acmicpc.net/problem/11967
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static Node[][] map;
    static Queue<Node> reachableNodes;
    static List<Node> turnedOnButNotVisitedNodes;
    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new Node[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = new Node(i, j);
            }
        }

        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[x][y].lightSwitches.add(map[a][b]);
        }

        //initialize
        reachableNodes = new LinkedList<>();
        turnedOnButNotVisitedNodes = new LinkedList<>();

        map[1][1].turnedOn = true;
        map[1][1].reachable = true;
        reachableNodes.add(map[1][1]);

        while (true) {
            turnOnTheLights();
            findReachableNodes();
            if (reachableNodes.isEmpty()) break;
        }

        System.out.println(calculateAnswer());
    }

    private static void turnOnTheLights() {
        while (!reachableNodes.isEmpty()) {
            Node now = reachableNodes.poll();
            for (Node node : now.lightSwitches) {
                if (!map[node.x][node.y].turnedOn) {
                    map[node.x][node.y].turnedOn = true;
                    turnedOnButNotVisitedNodes.add(map[node.x][node.y]);
                }
            }
        }
    }

    private static void findReachableNodes() {
        for (Iterator<Node> iter = turnedOnButNotVisitedNodes.iterator(); iter.hasNext(); ) {
            Node node = iter.next();
            for (int[] d : dir) {
                int nx = node.x + d[0];
                int ny = node.y + d[1];
                if (checkBoundary(nx, ny) && map[nx][ny].reachable) {
                    map[node.x][node.y].reachable = true;
                    reachableNodes.add(map[node.x][node.y]);
                    iter.remove();
                    break;
                }
            }
        }
    }

    private static int calculateAnswer() {
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j].turnedOn) answer++;
            }
        }
        return answer;
    }

    private static boolean checkBoundary(int x, int y) {
        return x > 0 && y > 0 && x <= N && y <= N;
    }

    private static class Node {
        int x, y;
        boolean turnedOn, reachable;
        List<Node> lightSwitches;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.lightSwitches = new ArrayList<>();
        }
    }
}