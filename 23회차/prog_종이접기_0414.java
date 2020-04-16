package test2;

import java.util.Arrays;

/*
1, 0
2, 0 0 1
3, 0 0 1 0 0 1 1
4, 0 0 1 0 0 1 1 0 0 0 1 1 0 1 1

n차 배열의 크기
a(n) = 2*a(n-1)+1
a(1) = 1

가운데 0을 사이로 거울모양으로 0,1이 맞물리게
최대 20번 접기 가능
 */
public class prog_종이접기 {
	public static int[] result;
	public static int[] a = new int[21];
	public static int[] solution(int n) {
		int[] answer = {};
		arrSize(20);
		result = new int[a[20]];	// = new int[1048575];
		result[0] = 0;	// 처음 접었을 때
		
		fold(n);
		
		answer = Arrays.copyOf(result, a[n]);
		return answer;
	}
	// n차 접었을 때 값 넣기
	public static void fold(int n) {
		if(n == 1)
			return;
		fold(n-1);
		result[a[n-1]] = 0;	// 이전 배열 크기 이후부터 값 넣겠음, 0은 대칭 기준점
		for (int i = a[n-1]+1, j = a[n-1]-1; i < a[n]; i++, j--) {
			result[i] = result[j]==0?1:0;
		}
	}
	// n차 배열의 크기
	public static int arrSize(int n) {
		if(n == 1) {
			return a[n] = 1;
		}
		a[n] = 2*arrSize(n-1)+1;
		return a[n];
	}
	
	public static void main(String[] args) {
		solution(4);
	}
}
