/*
 * SWEA 1213 String D3
 */

import java.util.Scanner;

public class SWEA1213 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int testCase = 1; testCase <= 10; testCase++) {
			int tc = sc.nextInt();
			String find = sc.next();
			String str = sc.next();
			int ans = 0;
			int len = find.length(); // 찾는 글자의 길이
//			System.out.println("find " + find );
//			System.out.println("str " + str);
			
			for (int i = 0; i <= str.length()-len; i++) {
				if(str.charAt(i) == find.charAt(0)){ // 첫 번째 글자가 같은지 확인
					String tmp = str.substring(i, i+len);
//					System.out.println("tmp : "+tmp);
					if(tmp.equals(find)){
						ans++;
						i = i+len-1;
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
