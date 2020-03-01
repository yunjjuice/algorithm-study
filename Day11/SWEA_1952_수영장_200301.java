/*
 * SWEA 1952 수영장
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952 {
	static int[] price; // 이용권 가격 1일 1달 3달 1년
	static int[] month; // 각 달의 최소 금액(일 단위랑 1달권만 비교)
	static int[][] table;
	static boolean[] fin; // 계산 끝난 달은 더이상 계산하지 못하도록
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= TC; t++) {
			price = new int[4];
			month = new int[13];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < price.length; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= 12; i++) {
				int day = Integer.parseInt(st.nextToken());
				month[i] = Math.min(price[1], day*price[0]);
			}
			
//			// 입력 확인
//			System.out.println(Arrays.toString(price));
//			System.out.println(Arrays.toString(month));
						
			ans = Integer.MAX_VALUE;
			calc(0, 0);
			if(ans > price[3])
				ans = price[3];
			
			System.out.println("#" + t + " " + ans);
		}
	}
	
	static void calc(int index, int sum) {
//		System.out.println("index : " + index + " sum : "+ sum);
		if(index >= 12) {
			ans = Math.min(sum, ans);
			return;
		}
		
		calc(index+1, sum+month[index+1]);
		calc(index+3, sum+price[2]);
	}
}

