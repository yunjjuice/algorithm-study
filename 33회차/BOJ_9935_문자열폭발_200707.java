import java.util.Scanner;
import java.util.Stack;

public class BOJ_9935_문자열폭발_200707 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		char[] str = sc.next().toCharArray();
		char[] bomb = sc.next().toCharArray();
		
		Stack<Character> s = new Stack<>();
		
		char bombLastChar = bomb[bomb.length-1];
		for (int i = 0; i < str.length; i++) {
			// 일단 스택에 넣음
			s.add(str[i]);
			// bomb의 마지막 문자와 현재 문자가 같으면
			if(s.lastElement() == bombLastChar){
				boolean flag = true;
				// bomb의 길이만큼 비교
				for (int j = s.size()-1, k = bomb.length-1; 
						j > s.size()-1-bomb.length && k >= 0; 
						j--, k--) {
					// bomb길이가 더 큰걸 방지
					if(j<0){
						flag = false;
						break;
					}
					// 비교했을 때 다른 문자가 있으면 bomb가 아님
					if(s.get(j)!=bomb[k]){
						flag = false;
					}
				}
				// bomb가 맞다면
				if(flag){
					for (int j = 0; j < bomb.length; j++) {
						// bomb 길이만큼 pop해줌
						s.pop();
					}
				}
			}
		}
		
		// 출력
		if(s.isEmpty()){
			System.out.println("FRULA");
		}
		else{
			for (Character c : s) {
				sb.append(c);
			}
			System.out.println(sb);
		}
	}
}
