package algo;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_4796_의석이의우뚝선산_200306 {
	static int ans;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = sc.nextInt();
		for(int testcase = 1; testcase <= TC; testcase++) {
			int n = sc.nextInt();
			
			arr = new int[n];
			for(int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}
			
			ans = 0;
			for(int i = 1; i < n - 1; i++) {
				// 일단 우뚝 솟아 있는 산이 될 수 있는 산을 찾음
				if(arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
					findMT(i);
				}
			}

			sb.append("#").append(testcase).append(" ").append(ans).append('\n');
		}
		System.out.print(sb);
	}
	private static void findMT(int x) {
		int cnt1 = 0;
		int cnt2 = 0;
		for(int i = x; i > 0; i--) { // 왼쪽으로 작은산이 몇개 있는가
			if(arr[i] > arr[i - 1]) cnt1++;
			else break;
		}
		for(int i = x; i < arr.length - 1; i++) { // 오른쪽으로 작은산이 몇개 있는가
			if(arr[i] > arr[i + 1]) cnt2++;
			else break;
		}
		ans += cnt1 * cnt2; // 만들수 있는 구간의 개수 return
	}
}
