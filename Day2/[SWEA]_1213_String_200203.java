import java.util.Scanner;

public class SWEXPERT_String {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int testcase=1; testcase<=10; testcase++) {
			int answer=0;
			int TC = sc.nextInt();
			String tokken = sc.next();
			int length = tokken.length();
			String str = sc.next();
			for(int i =0; i<str.length(); i++) {
				if(str.charAt(i) == tokken.charAt(0)) {
					if(i+length > str.length()) break;
					String temp_str = str.substring(i, i+ length);
					if(temp_str.equals(tokken)) answer++;
				}
			}
			System.out.println("#" + TC + " " + answer);
		}
	}
}
