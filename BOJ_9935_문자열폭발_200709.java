/* 9935. 문자열 폭발 Gold 4
https://www.acmicpc.net/problem/9935
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();

        char[] answer = new char[str.length];
        int answerIdx = 0;
        for (int i = 0; i < str.length; i++) {
            answer[answerIdx++] = str[i];
            if (str[i] == bomb[bomb.length-1]) {
                boolean flag = true;
                for (int j = bomb.length-2, k=answerIdx-2; j >= 0; j--, k--) {
                    if(k < 0 || answer[k] != bomb[j]){
                        flag = false;
                        break;
                    }
                }
                if (flag) answerIdx -= bomb.length;
            }
        }

        if (answerIdx==0) System.out.print("FRULA");
        else {
            String res = new String(answer, 0, answerIdx);
            System.out.print(res);
        }
    }
}