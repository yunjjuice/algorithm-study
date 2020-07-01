/* 17205. 진우의 비밀번호 Gold 5
https://www.acmicpc.net/problem/17205
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String password = br.readLine();

        long answer = 0;
        for (int i = 0; i < password.length(); i++) {
            int k = password.charAt(i) - 'a';
            answer += k + 1;
            for (int j = 1; j < N - i; j++) {
                answer += k * Math.pow(26, j);
            }
        }

        System.out.println(answer);
    }
}