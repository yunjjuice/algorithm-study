import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *  말 K개를 놓고 시작
 *  위, 아래, 왼쪽, 오른쪽 4가지 중 하나
 *  턴 한 번은 1번 말부터 K번 말까지 순서대로 이동
 *  한 말이 이동할 때 위에 올려져 있는 말도 함께 이동
 *  턴이 진행되던 중에 말이 4개 이상 쌓이는 순간 게임이 종료
 *  
 *  흰색 : 이동 or 엎기
 *  빨간색 : 뒤집어서 이동 or 엎기
 *  파란색 : 반대방향 변화 1칸가기 (파란색으로 zero padding)
 *  
 *  arraylist 2차원배열
 */
public class BOJ_17837_새로운게임2_200429 {
	// 오 : 1, 왼 : 2, 위 : 3, 아래 : 4
	static int[] dx = {0, 0,  0, -1, 1};
	static int[] dy = {0, 1, -1,  0, 0};
	
	static ArrayList<Integer>[][] horsePosition;
	static Postion[] postion;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N+2][N+2];	// 0~N+1
		horsePosition = new ArrayList[N+2][N+2];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				horsePosition[i][j] = new ArrayList<>();
			}
		}
		
		// 체스 보드 채우기
		for (int i = 0; i < board.length; i++) {
			Arrays.fill(board[i], 2);	// 파란색으로 zero padding
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 말 위치 및 쌓기위한 배열 설정
		postion = new Postion[K+1];
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			horsePosition[r][c].add(i);
			postion[i] = new Postion(r, c, dir);
		}
		
		int turn = 0;	// 결과 값을 저장할 변수
		loop : while(true) {
			if(++turn>1000) {	// 못찾는 조건!
				turn = -1;
				break;
			}
			//System.out.println(turn);
			for (int i = 1; i < postion.length; i++) {	// 1번 말 부터 K번째 말까지
				// 이동하려는 칸 위치 확인
				int newR = postion[i].r + dx[postion[i].dir];
				int newC = postion[i].c + dy[postion[i].dir];
				
				// 이동 하려는 칸 확인
				switch (board[newR][newC]) {
				case 0:	// 흰칸
					white(i, newR, newC);
					break;
				case 1:	// 빨간칸
					red(i, newR, newC);
					break;
				case 2:	// 파란칸
					// 이동해야하는 방향을 반대로 바꾸고 1칸 이동할 준비를 한다.
					switch (postion[i].dir) {
					case 1:
						postion[i].dir=2;
						break;
					case 2:
						postion[i].dir=1;
						break;
					case 3:
						postion[i].dir=4;
						break;
					case 4:
						postion[i].dir=3;
						break;
					default:
						break;
					}
					newR = postion[i].r + dx[postion[i].dir];
					newC = postion[i].c + dy[postion[i].dir];
					// 이동하려는데 파란색 칸이면 이동하지 않는다.
					if(board[newR][newC]==0) {	// 흰칸
						white(i, newR, newC);
					} else if(board[newR][newC]==1) {	// 빨간칸
						red(i, newR, newC);
					} else {	// 파란칸
						// 이동하지 않는다.
					}
					break;
				default:
					break;
				}
				//종료조건
				if(horsePosition[postion[i].r][postion[i].c].size()>=4) {
					break loop;
				}
			}	// end of for
		}	// end of while
		System.out.println(turn);
	}	// end of main

	private static void white(int i, int newR, int newC) {
		// 원래 있던 칸에서 현재 칸으로 이동
		int r = postion[i].r;
		int c = postion[i].c;
		int idx = horsePosition[r][c].indexOf(i);
		int last = horsePosition[r][c].size();
		//System.out.println(i+","+idx+","+last);
		horsePosition[newR][newC].addAll(horsePosition[r][c].subList(idx,last));
		for (int l = idx; l < last; l++) {
			int get = horsePosition[r][c].get(idx);
			// 위치도 새로운 값으로 초기화
			postion[get].r = newR;
			postion[get].c = newC;
			// 해당 값부터 업은 것까지 모두 삭제
			horsePosition[r][c].remove(idx);
		}
	}

	private static void red(int i, int newR, int newC) {
		// 바꾼 말의 순서를 저장할 배열
		ArrayList<Integer> tmp = new ArrayList<>();
		
		// 원래 있던 칸에서 현재 칸으로 이동
		int r = postion[i].r;
		int c = postion[i].c;
		int idx = horsePosition[r][c].indexOf(i);
		int last = horsePosition[r][c].size();
		//System.out.println(i+","+idx+","+last);
		for (int l = idx; l < last; l++) {
			int get = horsePosition[r][c].get(idx);
			// 위치도 새로운 값으로 초기화
			postion[get].r = newR;
			postion[get].c = newC;
			// 해당 값부터 업은 것까지 모두 복사 후 삭제
			tmp.add(0, get);
			horsePosition[r][c].remove(idx);
		}
		// 기존에 있는거 뒤에 엎어줌
		horsePosition[newR][newC].addAll(tmp);
	}
	
	static class Postion{	// 말의 위치를 저장할 객체
		int r;
		int c;
		int dir;	// 오 : 1, 왼 : 2, 위 : 3, 아래 : 4
		public Postion(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}	
}	// end of class
