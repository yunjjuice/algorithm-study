/*
 * SWEA 1215 회문1 D3
 */

import java.util.Scanner;

public class SWEA1215 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int testCase = 1; testCase <= 10; testCase++) {
			int len = sc.nextInt();
			char[][] ch = new char[8][8];
			for (int i = 0; i < 8; i++) {
				String tmp = sc.next();
				for (int j = 0; j < ch.length; j++) {
					ch[i][j] = tmp.charAt(j);
				}
			}
			
			int count = 0;
			// 가로
			for (int i = 0; i < ch.length; i++) { // 한 열마다
				for (int j = 0; j < ch.length-len+1; j++) {
					boolean flag = true;
					for (int k = 0; k < len/2; k++) {
						if(ch[i][j+k] != ch[i][j+len-k-1]){
							flag = false;
							break;
						}
					}
					if(flag){
						count++;
					}
				}
			}
			
			// 세로
			for (int i = 0; i < ch.length; i++) { // 한 행마다
				for (int j = 0; j < ch.length-len+1; j++) {
					boolean flag = true;
					for (int k = 0; k < len/2; k++) {
						if(ch[j+k][i] != ch[j+len-k-1][i]){
							flag = false;
							break;
						}
					}
					if(flag){
						count++;
					}
				}
			}
			
			System.out.println("#" + testCase + " " + count);
		}
	}
}
