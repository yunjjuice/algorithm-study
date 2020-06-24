import java.io.*;
import java.util.*;

public class BOJ_11967_불켜기 {
	static int[][] direct = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] visited1 = new boolean[N][N];
		boolean[][] visited2 = new boolean[N][N];
        boolean[][] lighted = new boolean[N][N];
        List<Integer>[] list = new ArrayList[N*N];
        List<Integer> wait = new ArrayList<Integer>();
        int cnt = 1;
        for (int i = 0; i <N*N; i++) {
            list[i] = new ArrayList<Integer>();
        }//인접리스트 공간할당.
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x1 = Integer.parseInt(st.nextToken())-1;
        	int y1 = Integer.parseInt(st.nextToken())-1;
        	int x2 = Integer.parseInt(st.nextToken())-1;
        	int y2 = Integer.parseInt(st.nextToken())-1;
        	list[N*x1+y1].add(N*x2+y2);
        }//가지고 놀기 편하게 (1,1)을  (0,0)으로 바꾼다.
        Queue<Integer> q = new LinkedList<Integer>();
        visited1[0][0] = true;
        lighted[0][0] = true;
        q.add(0);
        while(!q.isEmpty()) {
        	int now = q.poll();
        	int x = now/N;
        	int y = now%N;
        	visited2[x][y] = false;
        	for(int k=0; k<4; k++) {
        		int new_x = x + direct[k][0];
        		int new_y = y + direct[k][1];
        		if(new_x>=0 && new_x<N && new_y>=0 && new_y<N && !visited1[new_x][new_y]) {
        			visited2[new_x][new_y] = true;
        		}
        	}
        	for(int i=0; i<list[now].size(); i++) {
        		int tmp = list[now].get(i);
        		int tmp_x = tmp/N;
        		int tmp_y = tmp%N;
        		if(!lighted[tmp_x][tmp_y]) {
        			lighted[tmp_x][tmp_y] = true;
        			cnt++;
        		}
        		wait.add(tmp);
        	}//대기열에 넣기.
        	for(int i=0; i<wait.size();) {
        		int next = wait.get(i);
        		int x2 = next/N;
        		int y2 = next%N;
        		if(visited2[x2][y2] && !visited1[x2][y2]) {
        			visited1[x2][y2] = true;
        			wait.remove(i);
        			q.add(next);
        		}
        		else i++;
        	}//end for.
        }
        System.out.println(cnt);
	}//end main.
}//end class.
