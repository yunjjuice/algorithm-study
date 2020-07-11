package algorithm;

import java.io.*;
import java.util.*;

public class BOJ_9935_문자열폭발 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		char last = str2.charAt(str2.length()-1);
		int top = -1;
		char[] stack = new char[str1.length()];
		for(int i=0; i<str1.length(); i++) {
			char now = str1.charAt(i);
			if(now == last && top >= str2.length()-2) {
				boolean can_erase = true;
				for(int j=0; j<str2.length()-1; j++) {
					if(stack[top-j] != str2.charAt(str2.length()-(2+j))) {
						can_erase = false;
						break;
					}
				}
				if(can_erase) top -= str2.length()-1; //지울수 있는경우 top위치를 바꿔준다.
				else stack[++top] = now;
			}//비교하는 문자의 맨끝 문자가 같은경우 비교시작.
			else stack[++top] = now;
		}//end for.
		if(top == -1) System.out.println("FRULA");
		else {
			for(int i=0; i<=top; i++) {
				System.out.print(stack[i]);
			}
		}
	}//end main.
}//end class.
