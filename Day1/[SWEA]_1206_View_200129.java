import java.util.Scanner;

public class SWEXPERT_Á¶¸Á±ÇÈ®º¸ {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int testcase=1; testcase<=10; testcase++) {
			int length = sc.nextInt();
			int[][] map = new int[256][length];
			for(int i=0; i<length; i++) {
				int height = sc.nextInt();
				for(int j=0; j<height; j++) {
					map[256-(j+1)][i] = 1;
				}
			}
			int apart = 0;
			for(int i=0; i<256; i++) {
				for(int j=2; j<length-2; j++) {
					if(map[i][j] == 1 && map[i][j-1] == 0 && map[i][j-2] == 0 && map[i][j+1] == 0 && map[i][j+2] == 0) {
						apart++;
					}
				}
			}
			System.out.println("#" +  testcase + " " + apart);
		}
	}
}
