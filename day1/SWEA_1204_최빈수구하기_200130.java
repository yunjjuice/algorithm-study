import java.util.Scanner;

public class SWEA_Solution_1204_최빈수구하기_D2_천용진 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int testCase = 1; testCase <= TC; testCase++) {
			int max = 0;
			int ans = 0;
			int t = sc.nextInt();
			int[] scores = new int[101];
			
			for(int i = 0; i < 1000; i++) {
				int score = sc.nextInt();
				scores[score] += 1;
			}
			
			for(int i = 0; i <= 100; i++) {
				if(max <= scores[i]) {
					max = scores[i];
					ans = i;
				}
			}
			
			System.out.println("#" + testCase + " " + ans);
		}
	}
}
