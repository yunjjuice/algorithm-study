import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_사다리조작 {
	static boolean[][] visited;
	static int row;
	static int col;
	static int[] parent;
	static boolean end = false;
	static boolean[][] list;
	static int answer = -1;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		col = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		visited = new boolean[row+1][col+1];
		list =  new boolean[row+1][col+1];
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			visited[a][b] = true;
		}
		for(int i=0; i<=3; i++) {
			dfs(0,0,0,i);
			if(end) break;
		}
		System.out.println(answer);
	}//end main.
	public static void dfs(int x, int y ,int cnt, int max) {
		if(cnt == max) {
			if(check()) {
				end = true;
				answer = cnt;
			}
			return ;
		}
		for(int i=x; i<=row; i++) {
			for(int j=1; j<col; j++) {
				if(!visited[i][j] && !visited[i][j-1] && !visited[i][j+1]) {
					visited[i][j] = true;
					dfs(i,j,cnt+1,max);
					visited[i][j] = false;
					if(end) return;
				}
			}
		}
		
		
	}//end dfs.
	public static boolean check() {
		for(int i=1; i<col; i++) {
			int start = i;
			for(int j=1; j<=row; j++) {
				if(visited[j][start]) start++;
				else if(visited[j][start-1]) start--;
			}//돌리기.
			if(start != i) return false;
		}
		return true;
	}//end check.

}
