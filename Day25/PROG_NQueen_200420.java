/*
 * programmers lv3 N-Queen
 */

public class PROG_NQueen_200420 {
	public static void main(String[] args) {
		System.out.println(solution(4));
	}

	static int answer = 0;
	static int[] cols; // {0, 0, 0, 0} -> 인덱스가 행, 값이 열이 된다 -> 어차피 한 행에 하나씩만 두니까
	static int N;
	public static int solution(int n) {
		N = n;
		cols = new int[n];
		
		dfs(0);
		
		return answer;
	}
	public static void dfs(int level) {
		
		if(level == N) {
			answer++;
		}
		else {
			for (int i = 0; i < N; i++) {
				cols[level] = i;
				if(isPossible(level)) {
					dfs(level+1);
				}
			}
		}
	}
	
	public static boolean isPossible(int level) {
		for (int i = 0; i < level; i++) {
			if(cols[i] == cols[level]) // 이전행들과 지금 행들의 열 값이 같으면 -> 세로로 공격 가능 
				return false;
			if(Math.abs(level-i) == Math.abs(cols[level]-cols[i])) // 대각선이어도 안됨
				return false;
		}
		
		return true;
	}

}
