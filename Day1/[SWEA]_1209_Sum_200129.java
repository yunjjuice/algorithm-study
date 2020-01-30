import java.util.Scanner;

public class SWEXPERT_Sum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int tc=0; tc<10; tc++) {
			int testCase = sc.nextInt();
			int[] sum_row = new int[100];
			int[] sum_col = new int[100];
			int[] sum_dia = new int[2];
			
			for(int i=0; i<100; i++) {
				for (int j = 0; j < 100; j++) {
					int temp = sc.nextInt();
					if(i+j == 99) {
						sum_dia[1] += temp; 
					}
					if(i==j) {
						sum_dia[0] += temp;
					}
					sum_row[i] += temp;
					sum_col[j] += temp;
				}
			}
			int max = 0;
			for(int i=0; i<100; i++) {
				if(max < sum_row[i]) max=sum_row[i];
				if(max < sum_col[i]) max=sum_col[i];
			}
			if(max < sum_dia[0])max=sum_dia[0];
			if(max < sum_dia[1]) max=sum_dia[1];
			
			System.out.println("#" + testCase + " " + max);
		}
	}
}
