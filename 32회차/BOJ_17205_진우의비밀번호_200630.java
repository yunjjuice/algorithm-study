import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
26^0 + 26^1 + 26^2 ... 26^N
N(1 ≤ N ≤ 10)
등비수열
Sn = 1(26^n-1)/26-1
   = (26^n-1)/25
 */
public class BOJ_17205_진우의비밀번호_200630 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String pw = br.readLine();
		
		double[] val = new double[11];
		for (int i = 0; i < val.length; i++) {
			val[i] = (Math.pow(26, i) - 1) / 25; 
		}
		
		double answer = 0;
		for (int i = 0; i < pw.length(); i++) {
			int num = pw.charAt(i) - 'a';
			answer += num*val[N--];
		}
		
		answer += pw.length();
		
		System.out.println((long)answer);
	}
}
