/*
 * SWEA 1208 Flatten D3
 */

import java.util.Arrays;
import java.util.Scanner;

public class SWEA1208 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int testCase = 1; testCase <=10; testCase++) {
			int[] a = new int[100];
			int dump = sc.nextInt();
			for (int i = 0; i < a.length; i++) {
				a[i] = sc.nextInt();
			}
			
			// 가장 높은 거랑 가장 낮은 거 찾기
			for (int count = 0; count < dump; count++) { // 덤프 수만큼만
				int highst = Integer.MIN_VALUE;
				int lowest = Integer.MAX_VALUE;
				int indexH = 0;
				int indexL = 0;
				for (int i = 0; i < a.length; i++) {
					if(highst <= a[i]) {
						highst = a[i];
						indexH = i;
						//System.out.println("highst "+highst + ", i " + i);
					}
					if(lowest > a[i]) {
						lowest = a[i];
						indexL = i;
					}
				}
				a[indexH]--;
				a[indexL]++;
			}
			
			Arrays.sort(a);
			int ans = a[99]-a[0];
			
			System.out.println("#" + testCase + " " + ans);
		}
	}
}
