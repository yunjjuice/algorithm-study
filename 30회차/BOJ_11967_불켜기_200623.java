package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11967_불켜기_200623 {
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};
    static int N,M;
    static ArrayList<Integer>[] map;
    static boolean[][] goable;
    static boolean[][] visited;
    static boolean[][] light;
    static Queue<Point> queue;
    static class Point{
        int i,j;
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        
        map=new ArrayList[(N+1)*(N+1)];
        for(int i=0;i<map.length;i++) {
            map[i]=new ArrayList<>();
        }
        
        for(int i=0;i<M;i++) {
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            
            map[x*(N+1)+y].add(a*(N+1)+b);
        }
        
        goable=new boolean[N+1][N+1];
        visited=new boolean[N+1][N+1];
        light=new boolean[N+1][N+1];
        queue=new LinkedList<>();
        bfs();
        
        int answer=0;
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(light[i][j]==true)
                    answer+=1;
            }
        }
        
        System.out.println(answer);
    }
    
    static void bfs() {
    	// (1,1)에서 시작 
        goable[1][1]=true;
        visited[1][1]=true;
        light[1][1]=true;
        queue.add(new Point(1, 1));
        
        while(!queue.isEmpty()) {
            Point tmp= queue.poll();
            
            //근처 이동가능
            for(int i=0; i<4; i++) {
                int nextI=tmp.i+dir[i][0];
                int nextJ=tmp.j+dir[i][1];
                if(nextI<1 || nextI>N || nextJ<1 ||nextJ>N)
                    continue;
                
                goable[nextI][nextJ]=true;
            }
            
            //불켜기
            for(int next : map[tmp.i*(N+1) + tmp.j]) {
            	int newI = next/(N+1);
            	int newJ = next%(N+1);
                light[newI][newJ]=true;
                // 새로 불킨 곳이 갈수있고, 불켜져있고, 방문하지 않은 곳이면 방문함.
                if(goable[newI][newJ]==true 
                		&& light[newI][newJ]==true 
                        && visited[newI][newJ]==false) {
                    visited[newI][newJ]=true;
                    queue.offer(new Point(newI,newJ));
                }
            }
            
            //근처 이동
            for(int i=0; i<4; i++) {
                int nextI=tmp.i+dir[i][0];
                int nextJ=tmp.j+dir[i][1];
                if(nextI<1 || nextI>N || nextJ<1 ||nextJ>N)
                    continue;
                // 현재위치에서 사방으로 갈수있고, 불켜져있고, 방문하지 않은 곳이면 방문함.
                if(goable[nextI][nextJ]==true 
                		&& light[nextI][nextJ]==true 
                        && visited[nextI][nextJ]==false) {
                    visited[nextI][nextJ]=true;
                    queue.offer(new Point(nextI, nextJ));
                }
            }
        }
    }

}
