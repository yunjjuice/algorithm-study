package algo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class PROG_lv2_전화번호목록_200408 {
	static class Solution {
	    static HashSet<String>[] set;
	    public boolean solution(String[] phone_book) {
	        boolean answer = true;
	        
	        Arrays.sort(phone_book, new Comparator<String>() {
	            public int compare(String o1, String o2) {
	                return o1.length() - o2.length();
	            }
	        });

	        int min = phone_book[0].length();
	        int max = phone_book[phone_book.length - 1].length();
	        
	        set = new HashSet[max + 1];
	        for(int i = min; i <= max; i++) set[i] = new HashSet<>();
	        
	        int length = 0;
	        for(int i = 0; i < phone_book.length; i++) {
	            if(length != phone_book[i].length()) {
	                length = phone_book[i].length();
	                for(int j = i + 1; j < phone_book.length; j++) {
	                    if(length < phone_book[j].length()) {
	                        set[length].add(phone_book[j].substring(0, length));
	                    }
	                }
	            }
	        }
	        
	        for(int i = 0; i < phone_book.length; i++) {
	            if(set[phone_book[i].length()].contains(phone_book[i])) {
	                return false;
	            }
	        }
	        
	        
	        return answer;
	    }
	}
	public static void main(String[] args) {
		String[] phone_book = {"119", "97674223", "1195524421"};
		Solution s = new Solution();
		System.out.println(s.solution(phone_book));
	}
}
