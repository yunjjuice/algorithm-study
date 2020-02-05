import java.util.Scanner;

public class BOJ_괄호추가하기 {
	static char[] oper_list = new char[9];
	static int[] num_list = new int[10];
	static int length_num;
	static int length_oper;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);
		int temp = sc.nextInt();
		length_num = temp - temp/2;
		length_oper = temp/2;
		String str = sc.next();
		for(int i=0; i<temp; i++) {
			if(i%2 == 0) num_list[i/2] = Integer.parseInt(str.charAt(i)+ "");
			else oper_list[i/2] = str.charAt(i);
		} //연산자, 피연산자 구분해서 저장하기.
		
		dfs(0,num_list[0]);
		System.out.println(max);
	}//end main.
	
	
	//괄호 내 괄호가 안된다는 조건 하에 2가지로 분류해서 dfs를 돌릴 수 있을 것 같아 구현했다.
	public static void dfs(int idx, int sum) {
		if(idx >= length_oper) {
			if(sum > max) {
				max = sum;
			}
			//최댓값과 비교하는 식.
			return;
		}
		dfs(idx+1 , calculate(sum, num_list[idx+1], oper_list[idx]));
		//지금 연산자에서 괄호를 하냐
		if(idx+1 < length_oper) {
			int after_num = calculate(num_list[idx+1] , num_list[idx+2] , oper_list[idx+1]);
			dfs(idx+2 , calculate(sum,after_num, oper_list[idx]));
		}
		//지금 연산자에서 괄호안하고 다음 연산자에서 괄호를 하냐
		
	}//end dfs.
	
	public static int calculate(int num1, int num2, char operator) {
		int temp = 0;
		switch(operator) {
		case '+' : temp = num1 + num2; break;
		case '-' : temp = num1 - num2; break;
		case '*' : temp = num1 * num2; break;
		}
		return temp;
	} //계산하는 함수. num1이 왼쪽.
}
