import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BOJ_16637_괄호추가하기_200204 {
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException, NumberFormatException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(br.readLine());
		String str = br.readLine();
		String[] expression = new String[len];
		List<String> res = new LinkedList<String>();
		int[] check = new int[len / 2];
		
		for(int i = 0; i < expression.length; i++) {
			expression[i] = str.substring(i, i + 1);
		} // 배열에 수식 담음
		
		if(expression.length == 1) { // 길이가 1일때
			max = Integer.parseInt(expression[0]);
		} else if(expression.length == 3) { // 길이가 3일때
			max = Integer.parseInt(cal(expression[0], expression[1], expression[2]));
		} else { // 길이가 5이상
			subset(expression, res, check, 0); // 괄호의 경우의 수 부분집합으로 구하기
		}
		
		System.out.println(max);
	}
	
	public static void subset(String[] expression, List<String> res, int[] check, int pivot) {
		if(pivot == check.length) { // 괄호가 존재할 수 있는 부분집합 구하면 계산 시작
			addBranket(expression, res, check);
		} else {
			if(pivot > 0 && check[pivot - 1] == 1) { // 괄호는 중첩될 수 없음
				check[pivot] = 0;
				subset(expression, res, check, pivot + 1);
			} else {
				check[pivot] = 0;
				subset(expression, res, check, pivot + 1);
				check[pivot] = 1;
				subset(expression, res, check, pivot + 1);
			}
		}
	}
	
	public static void addBranket(String[] expression, List<String> res, int[] check) { // 계산할 수 있도록 list에 저장 및 계산
		res = new LinkedList<>(); // 결과 저장할 list 초기화
		for(int i = 0; i < check.length; i++) {
			if(check[i] == 1) { // 앞의 괄호는 묶여져 있지 않음, 맨 앞인지만 체크하고 계산 결과값을 추가해줌
				if(i > 0) {
					res.remove(res.size() - 1);
				}
				res.add(cal(expression[i * 2], expression[i * 2 + 1], expression[i * 2 + 2]));
		
			} else {
				if(i > 0) { // 맨 앞이 아니면 연산자와 뒤의 숫자만 추가
					res.add(expression[i * 2 + 1]);
					res.add(expression[i * 2 + 2]);
				} else { // 맨 앞이면 숫자, 연산자, 숫자 모두 추가
					res.add(expression[i * 2]);
					res.add(expression[i * 2 + 1]);
					res.add(expression[i * 2 + 2]);
				}
			}
		}
		
		int tmp = Integer.parseInt(cal(res.get(0), res.get(1), res.get(2)));
		for(int i = 2; i <= res.size() / 2; i++) { // 차례차례 계산
			tmp = Integer.parseInt(cal(Integer.toString(tmp), res.get(i * 2 - 1), res.get(i * 2)));
		}
		if(tmp > max) max = tmp; // 최대값인지 확인
	}
	
	public static String cal(String num1, String operator, String num2) { // 계산 method
		int a = Integer.parseInt(num1);
		int b = Integer.parseInt(num2);
		
		if(operator.equals("+")) {
			return Integer.toString(a + b);
		} else if (operator.equals("-")) {
			return Integer.toString(a - b);
		} else {
			return Integer.toString(a * b);
		}
	}
}
