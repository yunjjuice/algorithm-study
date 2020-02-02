import java.util.Scanner;

// the index having shortest path
// biggest index
// return y index
public class SWEA_1211_Ladder2_200201 {
	final static int[] dx = { 0, 0, 1 };
	final static int[] dy = { 1, -1, 0 }; // array to check (1.right, 2.left, 3.down)
	static int min = 10000; // variable to check shortest path
	static int count = 0; // variable to count distance
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int TC = 10; // number of testCase
		for(int testcase = 1; testcase <= TC; testcase++) {
			input.nextInt(); // input # number
			int[][] map = new int[100][102];
			int ans = 0;
			min = 10000; // initialize
			for(int i = 0; i < 100; i++) {
				for(int j = 1; j < 101; j++) {
					map[i][j] = input.nextInt();
				}
			} // input
	
			for(int i = 1; i < 101; i++) {
				if(map[0][i] == 1) {
					count = 0; // initialize
					go(map, 0, i, "D"); // start
					if(count <= min) { // find shortest path
						min = count;
						ans = i - 1;
					}
				}
			} // check loop
			
			System.out.println("#" + testcase + " " + ans);
		} // end of testcase loop
	} // end of main
	
	public static void go(int[][] map, int x, int y, String dir) { // go down method checking both side
		if(x == 100) return; // finish of the map
		if(count > min) return; // count is bigger than max -> return
		
		count++;
		
		if(dir.equals("D")) { // if direction is Down check Right and Left side
			if(map[x + dx[0]][y + dy[0]] == 1) {
				go(map, x + dx[0], y + dy[0], "R");
			} else if(map[x + dx[1]][y + dy[1]] == 1) {
				go(map, x + dx[1], y + dy[1], "L");
			} else {
				go(map, x + 1, y, "D");
			}
		} else if(dir.equals("L")) { // if direction is Left check Left side
			if(map[x + dx[1]][y + dy[1]] == 1) {
				go(map, x + dx[1], y + dy[1], "L");
			} else {
				go(map, x + 1, y, "D");
			}
		} else if(dir.equals("R")) { // if direction is Right check Right side
			if(map[x + dx[0]][y + dy[0]] == 1) {
				go(map, x + dx[0], y + dy[0], "R");
			} else {
				go(map, x + 1, y, "D");
			}
		}
	}
} // end of class
