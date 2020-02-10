import java.util.Scanner;

public class BOJ_배열돌리기4 {
	
	static int[][] map;
	static int row;
	static int col;
	static int[][] inst_list;
	static int[] permutation;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		row = sc.nextInt();
		col = sc.nextInt();
		map = new int[row][col];
		int K  = sc.nextInt(); //명령 횟수.
		visited = new boolean[K];
		permutation = new int[K];
		inst_list = new int[K][3];
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				map[i][j] = sc.nextInt();
			}
		} //맵 그리기.
		for(int i=0; i<K; i++) {
			inst_list[i][0] = sc.nextInt();
			inst_list[i][1] = sc.nextInt();
			inst_list[i][2] = sc.nextInt();
		} //명령어 인풋 받기.
		dfs(0,K);
		System.out.println(min);
	}//end main.
	
	public static void dfs(int cnt, int K) {
		if(cnt == K) {
			int[][] new_map = new int[row+1][col+1];
			for(int i=0; i<row; i++) {
				for(int j=0; j<col; j++) {
					new_map[i+1][j+1] = map[i][j];
				}
			} //카피 맵 그리기.
			for(int i=0; i<K; i++) {
				rotate(new_map, inst_list[permutation[i]][0],inst_list[permutation[i]][1],inst_list[permutation[i]][2]);
			}
			int temp = sum(new_map);
			if(temp < min) min = temp;
			return ;
		}
		for(int i=0; i<K; i++) {
			if(!visited[i]) {
				permutation[cnt] = i;
				visited[i] = true;
				dfs(cnt+1,K);
				visited[i] = false;
			}
		}
	}//end dfs.
	
	public static void rotate(int[][] new_map, int r, int c, int s) {
		for(int l=1; l<=s ; l++) {
			int temp = new_map[r-l][c+l];
			for(int i=c+l; i>c-l; i--) {
				new_map[r-l][i] = new_map[r-l][i-1];
			}
			for(int i=r-l; i<r+l; i++) {
				new_map[i][c-l] = new_map[i+1][c-l];
			}
			for(int i=c-l; i<c+l; i++) {
				new_map[r+l][i] = new_map[r+l][i+1];
			}
			for(int i=r+l; i>r-l; i--) {
				new_map[i][c+l] = new_map[i-1][c+l];
			}
			new_map[r-l+1][c+l] = temp;
		} //로테이트.
		return;
	}//rotate.
	
	public static int sum(int[][] new_map) {
		int min = Integer.MAX_VALUE;
		for(int i=1; i<=row; i++) {
			int temp_sum = 0;
			for(int j=1; j<=col; j++) {
				temp_sum += new_map[i][j];
			}
			if(temp_sum < min) min = temp_sum;
		}
		return min;
	}//sum.
}
