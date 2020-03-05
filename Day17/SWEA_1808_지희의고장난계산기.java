import java.io.*;
import java.util.*;

public class SWEA_1808_지희의고장난계산기 {
	static int[] number = new int[10];
	static int answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			answer = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<10; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			int N = Integer.parseInt(br.readLine());
			dfs(N,1);
			if(answer == Integer.MAX_VALUE) sb.append("#").append(test).append(" -1\n");
			else sb.append("#").append(test).append(" ").append(answer).append("\n");
		}//end TestCase.
		System.out.print(sb);
	}//end main.
	public static void dfs(int num, int cnt) {
		if(can_make(num+"")) {
			int temp = cnt + (num+"").length();
			if(temp < answer) answer = temp;
			return ;
		}
		ArrayList<Integer>list = new ArrayList<Integer>();
		for(int i=2; i<=Math.sqrt(num); i++) {
			if(num%i==0) list.add(i);
		}
		for(int i=0; i<list.size(); i++) {
			if(can_make(list.get(i)+"")) {
				dfs(num/list.get(i),cnt+(list.get(i)+"").length()+1);
			}
		}
	}//end dfs.
	public static boolean can_make(String num) {
		for(int i=0; i<num.length(); i++) {
			int temp_num = num.charAt(i)-'0';
			if(number[temp_num]==0) return false;
		}
		return true;
	}//end can_make
}//end class.
