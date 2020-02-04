/*
 * SWEA 1216 회문2 D3
 */

import java.util.Scanner;

public class SWEA1216 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int testCase = 1; testCase <= 10; testCase++) {
			int tc = sc.nextInt();
			char[][] ch = new char[100][100];
			for (int i = 0; i < ch.length; i++) {
				String tmp = sc.next();
				for (int j = 0; j < ch[i].length; j++) {
					ch[i][j] = tmp.charAt(j);
				}
			}
			
			int max = 0;
			for (int start = 0; start < ch.length; start++) {
				for (int end = 99; end >=start+max-1 ; end--) {
					int len = end - start + 1;
					int half = len/2;
					// 가로
					for (int i = 0; i < ch.length; i++) {
						boolean flag = true;
						for (int j = 0; j < half; j++) {
							if(ch[i][start+j] != ch[i][end-j]) {
								flag = false;
								break;
							}
						}
						if(!flag) continue;
						if(len > max) {
							max = len;
						}
					}
					// 세로
					for (int i = 0; i < ch.length; i++) {
						boolean flag = true;
						for (int j = 0; j < half; j++) {
							if(ch[start+j][i] != ch[end-j][i]) {
								flag = false;
								break;
							}
						}
						if(!flag) continue;
						if(len > max) {
							max = len;
						}
					}
				}
			}
			
			
//			// 가로
//			for (int col = 0; col < ch.length; col++) { // 행
//				for (int row = 0; row < ch.length; row++) { // 열
//					for (int end = ch.length-1; end >=0   ; end--) {
//						int len = end - row + 1; // 길이
//						int half = len/2;
//						boolean flag = true;
//						for (int i = row; i < row+half; i++) {
//							if(ch[col][i] != ch[col][end-i]) {
//								flag = false;
//								break;
//							}
//						}
//						if(flag) {
//							max = Math.max(max, len);
//						}
//					}
//				}
//			}
//			// 세로
//			for (int row = 0; row < ch.length; row++) { // 행
//				for (int col = 0; col < ch.length; col++) { // 열
//					for (int end = ch.length-1; end >=0 ; end--) {
//						int len = end - col + 1; // 길이
//						int half = len/2;
//						boolean flag = true;
//						for (int i = col; i < col+half; i++) {
//							if(ch[i][row] != ch[end-i][row]) {
//								flag = false;
//								break;
//							}
//						}
//						if(flag) {
//							max = Math.max(max, len);
//						}
//					}
//				}
//			}
			
			
//			for (int i = 0; i < ch.length; i++) {
//				for (int j = 0; j < ch[i].length; j++) {
//					boolean flagW = true;
//					for (int k = ch[i].length-1; k >=j; k--) {
//						int len = k-j+1;
//						for (int l = 0; l < len/2; l++) {
//							if(ch[j][l] != ch[j][k-l] && flagW){
//								flagW = false;
//							}
//						}
//						if(flagW){
//							max = Math.max(max, len);
//						}
//					}
//
//					boolean flagH = true;
//					for (int k = ch[i].length-1; k >=j; k--) {
//						int len = k-j+1;
//						for (int l = 0; l < len/2; l++) {
//							if(ch[l][j] != ch[k-l][j] && flagH){
//								flagH = false;
//							}
//						}
//						if(flagW){
//							max = Math.max(max, len);
//						}
//					}
//				}
//			}
			System.out.println("#" + testCase + " " + max);
		}
	}
}