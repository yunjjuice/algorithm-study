import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] pos;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            pos = new int[N+2][2];
            pos[0][0] = Integer.parseInt(st.nextToken());
            pos[0][1] = Integer.parseInt(st.nextToken());
            pos[N+1][0] = Integer.parseInt(st.nextToken());
            pos[N+1][1] = Integer.parseInt(st.nextToken());
            for (int i=1; i<=N; i++){
                pos[i][0] = Integer.parseInt(st.nextToken());
                pos[i][1] = Integer.parseInt(st.nextToken());
            }
            //end of input
            answer = Integer.MAX_VALUE;
            go(0, 0, 0, 0);

            //output
            sb.append('#').append(testCase).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    } //end of main

    static void go(int count, int visited, int before, int result) {
        if(result >= answer) return; //가지치기 : 이미 찾은 최소 이동 거리보다 큼 -> 탐색 필요 x
        if(count == N) { //종료 조건
            result += dist(before, N+1);
            if(answer > result)  answer = result;
            return;
        }
        for (int i=1; i<=N; i++) { // 모든 고객 집을 다 count 위치에 시도
            if( (visited & 1<<i) == 0) { // visited & 1 << i : i 고객이 기존 순열에 처리되었는지 확인
                // 0 -> 처리 안됨, 0 아님 -> 처리되었음
                // visited | (1 << i) : 기존 순열 상태에 i 고객 추가
                go(count + 1, visited | (1 << i), i,
                        result + dist(before, i));
            }
        }
    }

    static int dist(int a, int b){
        return Math.abs(pos[a][0] - pos[b][0]) + Math.abs(pos[a][1] - pos[b][1]);
    }
} //end of class