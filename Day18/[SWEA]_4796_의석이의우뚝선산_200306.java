import java.io.*;
import java.util.*;

public class SWEA_4796_의석이의우뚝선산 {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		int TC = sc.nextInt();
		for(int test=1; test<=TC; test++) {
			int N = sc.nextInt();
			int[] num = new int[N];
			for(int i=0; i<N; i++) {
				num[i] = sc.nextInt();
			}
			int idx = 1;
			int up=0;
			int down=0;
			long answer = 0;
			while(idx<N) {
				if(num[idx] > num[idx-1]) {
					if(down!=0) {
						answer += up * down;
						up = 1;
						down = 0;
					}
					else up++;
				}
				else {
					down++;
				}
				idx++;
			}
			answer += up*down;
			System.out.println("#" + test + " " + answer);
		}//end TestCase.
	}//end main.
}//end class.
