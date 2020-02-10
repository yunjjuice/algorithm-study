import java.util.Scanner;

public class SWEXPERT_Flatten {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int testcase=1; testcase<=10; testcase++) {
			int[][] map = new int[100][100];
			int duration = sc.nextInt();
			for(int i=0; i<100; i++) {
				int temp_height = sc.nextInt();
				for(int j=0; j<temp_height; j++) {
					map[100-(j+1)][i] = 1;
				}
			}//입력 값 다 받아오기 끝.
			int time = 0;
			int answer = 0;
			int max_x = 0;
			int max_y = 0;
			int min_x = 0;
			int min_y = 0;
			while(time <= duration) {
				boolean flag = false;
				for(int i=0; i<100; i++) {
					if(flag) break;
					for(int j=0; j<100; j++) {
						if(map[i][j] == 1) {
							max_x = i;
							max_y = j;
							flag = true;
							break;
						}
					}
				} //최고 높은 상자 x,y 찾기.
				flag = false;
				for(int i=99; i>=0 ; i--) {
					if(flag) break;
					for(int j=99; j>=0 ; j--) {
						if(map[i][j] == 0) {
							min_x = i;
							min_y = j;
							flag = true;
							break;
						}
					}
				}
				answer = min_x - max_x + 1;
				if(answer <= 1) {
					break;
				}
				map[max_x][max_y] = 0;
				map[min_x][min_y] = 1;
				time++;
			}
			
			System.out.println("#" + testcase + " " + answer);
		}
	}
}
