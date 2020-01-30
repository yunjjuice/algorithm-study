/*
 * SWEA 1204 최빈수 구하기 D2
 */

import java.util.Scanner;
 
public class SWEA1204 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int student = 1000;
         
        for (int i = 0; i < T; i++) {
            int caseNum = sc.nextInt();
            int[] grade = new int[101];
            int max = 0; // 어떤 점수가 제일 많은지 저장
            int count = 0; // 개수를 저장
             
            // 점수별로 몇 명씩 있는지 숫자를 센다
            for (int j = 0; j < student; j++) {
                int score = sc.nextInt();
                grade[score]++;
            }
             
            for (int j = 0; j < grade.length; j++) {
                if(count <= grade[j]) {
                    count = grade[j];
                    max = j;
                }
            }
             
            System.out.println("#" + caseNum + " " + max);
        }
 
    }
}