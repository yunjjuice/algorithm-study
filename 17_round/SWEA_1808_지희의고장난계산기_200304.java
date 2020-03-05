package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1808_지희의고장난계산기_200304 {
	static int[] work = new int[10];
	static int num;
	static int cnt;
	static int ans;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int i = 0; i < 10; i++) {
				work[i] = Integer.parseInt(st.nextToken());
			}
			num = Integer.parseInt(br.readLine());
			
			// 몇번 버튼을 눌러야 num을 만들 수 있는가
			// 불가능 하다면 -1 출력
			cnt = 0;
			ans = 0;
			min = Integer.MAX_VALUE;
			int init = include(num);
			if(init != 0) { // 초기에 곱셈연산이 필요없다면, 수의 자리수 + 계산 버튼 만큼의 횟수만 필요
				ans = init + 1;
			} else {
				divisor(num);
				ans = min;
			}
			
			if(ans == Integer.MAX_VALUE) ans = -1;
			sb.append("#").append(testcase).append(" ").append(ans).append('\n');
		}
		System.out.print(sb);
	}
	
	public static int include(int x) {
		String str = x + "";
		for(int i = 0; i < str.length(); i++) {
			if(work[str.charAt(i) - '0'] == 0) return 0;
		}
		return str.length();
	}
	
	public static void divisor(int n) {
		if(n == 1) return;
		
		int len = include(n);
		if(len != 0) {
			if(min > cnt + len + 1) min = cnt + len + 1;
			return;
		}

		int sqrt = (int)Math.sqrt(n);
		for(int i = 2; i <= sqrt; i++) {
			if(n % i == 0) {
				int tmp = include(i);
				if(tmp != 0) {
					cnt += tmp + 1;
					divisor(n / i);
					cnt -= (tmp + 1);
				}
			}
		}
	}
}
