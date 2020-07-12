package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_2143_두배열의합 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken()); // T값 받기.
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] tmp1 = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			tmp1[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] tmp2 = new int[M];
		for(int i=0; i<M; i++) {
			tmp2[i] = Integer.parseInt(st.nextToken());
		}
		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		for(int i=0; i<N; i++) {
			int sum = 0;
			for(int j=i; j<N; j++) {
				sum += tmp1[j];
				list1.add(sum);
			}
		}//첫번째 배열에 대한 부분합 구하기.(순서 있음)
		for(int i=0; i<M; i++) {
			int sum = 0;
			for(int j=i; j<M; j++) {
				sum += tmp2[j];
				list2.add(sum);
			}
		}//두번째 배열에 대한 부분합 구하기.(순서 있음)
		
		Collections.sort(list1);
		Collections.sort(list2);
		
		int idx1 = 0;
		int idx2 = list2.size()-1;
		long answer = 0L;
		
		while(idx1 < list1.size() && idx2 >= 0) {
			int num1 = list1.get(idx1);
			int num2 = list2.get(idx2);
			if(num1 + num2 == T) {
				long mul1 = 0L;
				while(true) {
					if(idx1 >= list1.size() || num1 != list1.get(idx1)) break;
					idx1++;
					mul1++;
				}
				long mul2 = 0L;
				while(true) {
					if(idx2 < 0 || num2 != list2.get(idx2)) break;
					idx2--;
					mul2++;
				}
				answer += (mul1 * mul2);
			}//정답 나온경우 싸그리 구하는 법.
			else if(num1 + num2 < T) idx1++;
			else idx2--;
		}//end while.
		System.out.println(answer);
	}//end main.
}//end class.
