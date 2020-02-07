import java.util.Scanner;

/*
 * BOJ 16637 괄호 추가하기
 */
public class BOJ16637 {
	
	static int N, ans;
	static int[] num;
	static char[] op;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 수식 길이
		String str = sc.next();
//		System.out.println("str " + str);
		
		num = new int[N/2+1];
		int numCnt = 0;
		op = new char[N/2];
		int opCnt = 0;
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*') { // 숫자면
				op[opCnt++] = str.charAt(i);
			} else {
				num[numCnt++] = str.charAt(i) - '0';
			}
		}
		
//		System.out.println(Arrays.toString(op));
//		System.out.println(Arrays.toString(num));

		ans = Integer.MIN_VALUE;
		dfs(0, num[0]);
		System.out.println(ans);
	} // end of main
	
	public static void dfs(int index, int res) {
		if(index >= N/2) { // 연산자 인덱스 다 보면 끝
			if(res > ans)
				ans = res;
//			System.out.println("index : " + index);
//			System.out.println("res : " + res);
			return;
		}
//		System.out.println("index : " + index);
		
		// 앞에거 먼저
		int tmp = calc(res, op[index], num[index+1]);
		dfs(index+1, tmp);
//		System.out.println("index : " + index);
//		System.out.println("tmp : " + tmp);
		
		// 뒤에 있는거 먼저
		if((index+1) < (N/2)) {
			tmp = calc(res, op[index], calc(num[index+1], op[index+1], num[index+2]));
			dfs(index+2, tmp);
//			System.out.println("index : " + index);
//			System.out.println("tmp : " + tmp);
		}
	}
	
	public static int calc(int a, char op, int b) {
		switch (op) {
		case '+':
			return a+b;
		case '-':
			return a-b;
		case '*':
			return a*b;
		default:
			return 0;
		}
	}
} // end of class
