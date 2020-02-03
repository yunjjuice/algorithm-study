import java.util.Scanner;

public class SWEXPERT_회문1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[][] map = new char[8][8];
		for(int testcase=1; testcase<=10; testcase++) {
			int length = sc.nextInt();
			for(int i=0; i<8; i++) {
				String temp_str = sc.next();
				for(int j=0; j<8; j++) {
					map[i][j] = temp_str.charAt(j);
				}
			}//end draw map.
			int sum = 0;
			for(int i=0; i<8; i++) {
				String s_row = "";
				String s_col = "";
				for(int j=0; j<8; j++){
					s_row = s_row + map[i][j];
					s_col = s_col + map[j][i];
				}//8개 길이 스트링 합성.
				sum += check(s_row , length);
				sum += check(s_col , length);
			}//end for.
			System.out.println("#" +  testcase + " " +  sum);
		}//end testCase.
	}
	public static int check(String str , int length) {
		int cnt = 0;
		for(int i=0 ; i<=8-length; i++) {
			String temp_str = str.substring(i, i+length);
			//System.out.println(temp_str);
			boolean flag = true;
			if(length%2 == 0) { //길이 짝수처리.
				for(int iter = 0 ; iter < (length/2); iter++) {
					if(temp_str.charAt(iter) != temp_str.charAt(length-(iter+1))) {
						flag = false;
						break;
					}
				}
			}
			else { //길이 홀수처리.
				for(int iter = 1 ; iter <= (length/2); iter++) {
					if(temp_str.charAt(length/2-iter) != temp_str.charAt(length/2+iter)) {
						flag = false;
						break;
					}
				}
			}
			if(flag) cnt++;
		}
		return cnt;
	}
}
