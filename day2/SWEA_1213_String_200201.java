import java.util.Scanner;

public class SWEA_1213_String_200201 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int TC = 10;
		for(int testcase = 1; testcase <= TC; testcase++) {
			input.nextLine();
			String findStr = input.nextLine();
			String sentence = input.nextLine();
			
			int countMatches = 0; // variable of answer
			int fromIndex = 0; // variable of start of index
			
			while(true) {
				int index = sentence.indexOf(findStr, fromIndex); // find string in sentence, start from fromIndex
				if(index != -1) { // match
					fromIndex = index + findStr.length(); // from index = find index + findString.length
					countMatches++; // answer + 1
				} else break; // no match
			} // find string loop
			
			System.out.println("#" + testcase + " " + countMatches);
		}
	} // end of main
} // end of class
