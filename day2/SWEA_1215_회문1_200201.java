import java.util.Scanner;
// palindrome : The same sentence as read from the front even when read backwards. ex)level
// The number of all palindromes
public class SWEA_1215_È¸¹®1_200201 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int TC = 10;
		for(int testcase = 1; testcase <= TC; testcase++) {
			int palinLength = Integer.parseInt(input.nextLine());
			int palinCount = 0; // variable of length
			
			String[][] map = new String[8][8];
			for(int i = 0; i < 8; i++) {
				String str = input.nextLine();
				for(int j = 0; j < 8; j++) {
					map[i][j] = str.substring(j, j + 1);
				}
			} // input
			
			for(int j = 0; j < 8; j++) {
				for(int i = 0; i < 8 - palinLength + 1; i++) { // check from row index 0 to row index 8 - palinLength + 1
					boolean check = true; // variable to check string is palindrome
					for(int k = 0; k < palinLength / 2; k++) { // check front equals back
						if(!map[i + k][j].equals(map[i + palinLength - 1 - k][j])) {
							check = false; // if not equal, check false and break
							break;
						}
					}
					if(check) palinCount++;	 // if check true, answer + 1
				}
			} // find at horizon
			
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8 - palinLength + 1; j++) { // check from column index 0 to column index 8 - palinLength + 1
					boolean check = true; // variable to check string is palindrome
					for(int k = 0; k < palinLength / 2; k++) { // check front equals back
						if(!map[i][j + k].equals(map[i][j + palinLength - 1 - k])) {
							check = false; // if not equal, check false and break
							break;
						}
					}
					if(check) palinCount++;	// if check true, answer + 1
				}
			} // find at vertical
			
			System.out.println("#" + testcase + " " + palinCount);
		}
	} // end of main
} // end of class
