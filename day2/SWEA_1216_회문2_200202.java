import java.util.Scanner;

// longest palindrome
public class SWEA_1216_È¸¹®2_200202 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int TC = 10;
		for(int testcase = 1; testcase <= TC; ++testcase) {
			String[][] map = new String[100][100];
			input.nextLine();
			for(int i = 0; i < 100; i++) {
				String str = input.nextLine();
				for(int j = 0; j < 100; j++) {
					map[i][j] = str.substring(j, j + 1);
				}
			} // input
			
			int palinLength = 100; // variable of answer
			boolean check = true; // is palindrome exist ?
			while(palinLength >= 1) {
				for(int j = 0; j < 100; j++) {
					for(int i = 0; i < 100 - palinLength + 1; i++) { // check from row index 0 to (100 - palinLength + 1)
						check = true; // assume palindrome is exist
						for(int k = 0; k < palinLength / 2; k++) { // check front equals back
							if(!map[i + k][j].equals(map[i + palinLength - k - 1][j])) {
								check = false; // if not equal, check false and break
								break;
							}
						}
						if(check) break; // if palindrome is exist, answer is palinLength
					}
					if(check) break;
				}
				if(check) break;
				
				for(int i = 0; i < 100; i++) {
					for(int j = 0; j < 100 - palinLength + 1; j++) { // check from column index 0 to (100 - palinLength + 1)
						check = true; // assume palindrome is exist
						for(int k = 0; k < palinLength / 2; k++) { // check front equals back
							if(!map[i][j + k].equals(map[i][j + palinLength - k - 1])) {
								check = false; // if not equal, check false and break
								break;
							}
						}
						if(check) break; // if palindrome is exist, answer is palinLength
					}
					if(check) break;
				}
				if(check) break;
				
				palinLength--; // Look for a shorter than before
			}
			
			System.out.println("#" + testcase + " " + palinLength);
		}
	} // end of main
} // end of class
