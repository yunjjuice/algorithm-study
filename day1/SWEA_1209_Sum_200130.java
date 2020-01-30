import java.util.Scanner;

public class SWEA_Solution_1209_Sum_D3_Ãµ¿ëÁø {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		for(int testcase = 1; testcase <= 10; testcase++) {
			input.nextInt();
			int[][] map = new int[100][100];
			for(int i = 0; i < 100; i++) { 
				for(int j = 0; j < 100; j++) {
					map[i][j] = input.nextInt();
				}
			}
			
			int temp = 0;
			int index = 0;
			int max = 0;
			
			for(int i = 0; i < 100; i++) {
				for(int j = 0; j < 100; j++) {
					temp += map[i][j];	
				}
				if(max < temp) max = temp;
				temp = 0;
			}
			
			for(int i = 0; i < 100; i++) {
				for(int j = 0; j < 100; j++) {
					temp += map[j][i];
				}
				if(max < temp) max = temp;
				temp = 0;
			}
			
			for(int i = 0; i < 100; i++) {
				temp += map[i][i];
			}
			if(max < temp) max = temp;
			temp = 0;
			
			for(int i = 0; i < 100; i++) {
				for(int j = 0; j < 100; j++) {
					if(i + j == 99) {
						temp += map[j][i];
					}
				}
			}
			if(max < temp) max = temp;
			temp = 0;
			
			System.out.println("#" + testcase + " " + max);
		}
	}
}
