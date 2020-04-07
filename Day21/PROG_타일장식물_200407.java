/*
 * programmers lv3 타일 장식물 
 */

public class PROG_타일장식물_200407 {
	public static void main(String[] args) {
		System.out.println(solution(5));
		System.out.println(solution(6));
	}

	public static long solution(int N) {
		long answer = 0;
		long[] f = new long[N+1];
		f[1] = 1;
		f[2] = 1;
		for (int i = 3; i <= N; i++) {
			f[i] = f[i-2] + f[i-1];
		}
		
		answer = f[N]*2 + (f[N]+f[N-1])*2;
		
		return answer;
	}
}
