package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19236_청소년상어_200622 {
	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int dir[][] = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	    int[][] map = new int[4][4]; // 물고기의 점수를 저장.
		int[][] direction = new int[4][4]; // 물고기의 방향을 저장 
		
		// input
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken())-1;
				map[i][j] = a; 
				direction[i][j] = b;
			}
		}
		
		int score=map[0][0];
		answer=0;
		map[0][0]=0; // 처음에 상어가 들어갈 자리(먹으면 0으로 바꿈)
		int depth = 0;
		go(map,direction,0,0,direction[0][0],score, depth); // 현재 상태, 현재 방향, 좌표, 상어가 갈방향, 현재점수 
		System.out.println(answer);
	}
	
	static void go(int[][] map,int[][] direction, int sI,int sJ,int sd,int score, int depth) {
		//점수 최댓값 업데이트
		answer=answer<score?score:answer;
		
		// map, direction copy
		int[][] mapCopy = new int[4][4];
		int[][] directionCopy = new int[4][4];
		for(int i=0;i<4;i++) {
			for(int j=0; j<4; j++) {
				mapCopy[i][j] = map[i][j];
				directionCopy[i][j] = direction[i][j];
			}
		}
		// 물고기 이동 
		for(int num=1;num<=16;num++) { // 물고기 순서대로 
			loop:for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(mapCopy[i][j]==num) {
						//기존방향~45도 돌리기
						int d=directionCopy[i][j];
						for(int k=0;k<8;k++) {
							int nI=i+dir[(d+k)%8][0];
							int nJ=j+dir[(d+k)%8][1];
							//범위 내에 있고, 다음 위치가 상어가 아닐 때
							if(nI>=0&&nJ>=0&&nI<4&&nJ<4
									&&!(nI==sI&&nJ==sJ)) {
								//다른 물고기가 있으면 위치 바꾸기
								if(mapCopy[nI][nJ]!=0) {
									//물고기 번호와 방향을 같이 바꾸기.
									int temp=mapCopy[i][j];
									mapCopy[i][j]=mapCopy[nI][nJ];
									mapCopy[nI][nJ]=temp;
									temp=directionCopy[i][j];
									directionCopy[i][j]=directionCopy[nI][nJ];
									directionCopy[nI][nJ]=temp;
								}else { // 다른 물고기가 없을 때 
									//물고기 이동
									mapCopy[nI][nJ] = mapCopy[i][j];
									mapCopy[i][j] = 0;
									directionCopy[nI][nJ]=directionCopy[i][j];
									directionCopy[i][j]=0;
								}
								//방향 업데이트 해주기
								directionCopy[nI][nJ]=(d+k)%8;
								break loop;
							}
						}
					}
				}
			}
		}
		//show(mapCopy, directionCopy, sI, sJ, sd, score, depth);
		// 이동 거리에 따라 재귀호출 
		for(int distance=1;distance<=3;distance++) {
			int nI=sI+distance*dir[sd][0];
			int nJ=sJ+distance*dir[sd][1];
			if(nI>=0&&nJ>=0&&nI<4&&nJ<4) {
				int val=mapCopy[nI][nJ];
				//잡아먹을 수 있을 때
				if(val!=0) {
					mapCopy[nI][nJ]=0;//잡아 먹음
					go(mapCopy, directionCopy, nI,nJ,directionCopy[nI][nJ],score+val,depth+1);
					mapCopy[nI][nJ]=val;
				}
			}
		}
	}
	
	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static char[] arrow = { '↑', '↖', '←', '↙', '↓', '↘', '→', '↗' };

	static void show(int[][] map, int[][] direction, int sx, int sy, int sdir, int res, int depth) {
	    
	    System.out.println("현재 상황");
	    for (int i = 0; i < 4; ++i) {
	        for (int j = 0; j < 4; ++j) {

	            if (i == sx && j == sy){
	            	System.out.print("0"+arrow[sdir]+"  ");
	            }
	            else
	            	System.out.print(map[i][j]+""+arrow[direction[i][j]]+"  ");
	        }
	        System.out.println();
	    }
	    System.out.println("현재 상어의 방향 : "+arrow[sdir]);
	    System.out.println("현재 먹은 양 : "+res);
	    System.out.println("현재 상어 위치 : "+sx+","+sy);
	    System.out.println(depth);
	    System.out.println();
	    return;
	}
}
