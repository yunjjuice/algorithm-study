/* 1952. 수영장 모의테스트
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int answer;
    static int[] months, comp = new int[13];
    static int day, month, month3, year;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            day = Integer.parseInt(st.nextToken());
            month = Integer.parseInt(st.nextToken());
            month3 = Integer.parseInt(st.nextToken());
            year = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            months = new int[13];
            for (int i = 1; i < months.length; i++) {
                months[i] = Integer.parseInt(st.nextToken());
                comp[i] = months[i] * day < month ? months[i] * day : month; //더 작은 쪽 이용하기
            } //end of input

            //solve
            answer = 9999999; //INF
            dfs(1, 0);
            if (answer > year) answer = year; //1년짜리 이용권이 가장 쌀 경우

            //output
            sb.append('#').append(testCase).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    } //end of main

    static void dfs(int thisMonth, int sum) {
        if (thisMonth > 12) { //종료조건
            if (answer > sum) answer = sum;
            return;
        }
//        if(months[thisMonth] * day < month) //1일 이용권이 한 달 이용권보다 싸면 1일 이용권, 아니면 한 달 이용권
//            dfs(thisMonth+1, sum + months[thisMonth]*day); //이번 달에 1일 이용권 사용할 경우
//        else
//            dfs(thisMonth+1, sum+month); //이번 달에 1달 이용권 사용할 경우
        dfs(thisMonth + 1, sum + comp[thisMonth]); //3달 이용권 사용하지 않을 경우
        dfs(thisMonth + 3, sum + month3); //3달 이용권 사용할 경우
    }
} //end of class