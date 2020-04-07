package algo;

public class PROG_lv3_타일장식물_200407 {
	static class Solution {
	    public long solution(int N) {
	        long answer = 0;
	        long[] dp = new long[N + 2];
	        dp[0] = 0;
	        dp[1] = 1;
	        for(int i = 2; i <= N + 1; i++) {
	            dp[i] = dp[i - 1] + dp[i - 2];
	        }
	        answer = (dp[N] + dp[N + 1]) * 2;
	        return answer;
	    }
	}
	
	public static void main(String[] args) {
		int n = 8;
		Solution s = new Solution();
		long res = s.solution(n);
		System.out.println(res);
	}
}
