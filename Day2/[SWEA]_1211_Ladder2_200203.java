import java.util.Scanner;

public class SWEXPERT_Ladder2 {
	
	static int[][] map = new int[100][102];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int testcase=0; testcase<10; testcase++) {
			int min = Integer.MAX_VALUE;
			int ans = 0;
			int TC = sc.nextInt();
			for(int row=0; row<100; row++) {
				for(int col=1; col<=100; col++) {
					map[row][col] = sc.nextInt(); 
					//System.out.print(map[row][col] + " ");
				}
				//System.out.println(" ");
			}
			for(int col=1; col<=100; col++) {
				if(map[0][col] == 1) {
					int temp_cnt = ladder(col);
					//System.out.println(temp_cnt);
					if(min >= temp_cnt) {
						min = temp_cnt;
						ans = col-1;
					}
				}
			}
			System.out.println("#" + TC + " " + ans);
			
		}//end for.
	}//end main.
	
	public static int ladder(int col) {
		int x = 1;
		int y = col;
		int cnt = 1;
		int type = 1;
		
		while(x != 100) {
			//System.out.println(x + " " + y);
			switch(type) {
			case 1:{
				if(map[x][y-1] == 1) {
					type = 2;
					y--;
				}
				else if(map[x][y+1] == 1){
					type = 3;
					y++;
				}
				else {
					x++;
				}
				cnt++;
				break;
			}
			case 2:{
				if(map[x+1][y] == 1) {
					type = 1;
					x++;
				}
				else {
					y--;
				}
				cnt++;
				break;
				
			}
			case 3:{
				if(map[x+1][y] == 1) {
					type = 1;
					x++;
				}
				else {
					y++;
				}
				cnt++;
				break;
			}
			}
		}
		return cnt;
	}
	
}//end class.
