import java.io.*;
import java.util.*;

public class BOJ_17205_진우의비밀번호 {
	static int N;
	static String password;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		password = br.readLine();
		System.out.println(calculate(0));
	}//end main.
	public static long calculate(int idx) {
		char now_char = password.charAt(idx); //현재 문자.
		long now_cnt = 0;
		for(int i=0; i<N-idx; i++) {
			now_cnt += (long)(Math.pow(26, i));
		}
		now_cnt *= (now_char-'a');
		now_cnt++; //그전단계 다하고 처음 이 알파벳 적어놓는 느낌?
		if(idx==password.length()-1) return now_cnt;
		else return now_cnt + calculate(idx+1);
	}
}//end class.
