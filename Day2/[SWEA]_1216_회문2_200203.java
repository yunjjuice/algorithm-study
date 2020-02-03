import java.util.Scanner;

public class SWEXPERT_회문2 {
	static int max_length;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[][] map = new char[100][100];
		for(int testcase=1; testcase<=10; testcase++) {
			max_length = 1;
			int TC = sc.nextInt();
			for(int i=0; i<100; i++) {
				String temp_str = sc.next();
				for(int j=0; j<100; j++) {
					map[i][j] = temp_str.charAt(j);
				}
			}//end draw map.
			for(int i=0; i<100; i++) {
				String s_row = "";
				String s_col = "";
				for(int j=0; j<100; j++){
					s_row = s_row + map[i][j];
					s_col = s_col + map[j][i];
				}//8개 길이 스트링 합성.
				check(s_row);
				check(s_col);
			}//end for.
			System.out.println("#" +  TC + " " +  max_length);
		}//end testCase.
	}
	public static void check(String str) {
		for(int i=0; i<100; i++) {
			char start = str.charAt(i) ;
			for(int j=99 ; j > i; j--) {
				if(start == str.charAt(j)) {
					if(j-i+1 <= max_length) continue;
					String temp_str = str.substring(i,j+1);
//					System.out.println(temp_str);
					boolean flag = true;
					while(true) {
						temp_str = temp_str.substring(1,temp_str.length()-1);
//						System.out.println(temp_str);
//						System.out.println(temp_str.length());
						if(temp_str.length()<=1) break;
						if(temp_str.charAt(0) != temp_str.charAt(temp_str.length()-1)) {
							flag = false;
							break;
						}
					}//end while.
					if(flag) max_length = j-i+1;
				}
			}//end for.
		}//end method.
		
		return;
	}
}
