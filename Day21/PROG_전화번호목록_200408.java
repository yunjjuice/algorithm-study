/*
 * programmers lv2 전화번호 목록 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class PROG_전화번호목록_200408 {
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"119", "97674223", "1195524421"}));
		System.out.println(solution(new String[] {"123","456","789"}));
		System.out.println(solution(new String[] {"12","123","1235","567","88"}));
	}

	public static boolean solution(String[] phone_book) {
		boolean answer = true;
		
		ArrayList<String> list = new ArrayList<>(Arrays.asList(phone_book));
//		System.out.println(list.toString());
		
		Collections.sort(list, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				String s1 = (String)o1;
				String s2 = (String)o2;
				return s1.length() - s2.length();
			}
		});
		
//		System.out.println(list.toString());
		
		for (int i = 0; i < list.size(); i++) {
			for (int j = i+1; j < list.size(); j++) {
				if(list.get(j).startsWith(list.get(i))) {
					answer = false;
					return answer;
				}
			}
		}
		
		return answer;
	}
}
