import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
d1, d2 >= 1
1<=x<=N-2
2<=y<=N-1
완탐
경계선 그리고 구역 1,2,3,4,5 인구수 구함
1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
 */
/*
6
1 2 3 4 1 6
7 8 9 1 4 2
2 3 4 1 1 3
6 6 6 6 9 4
9 1 9 1 9 5
1 1 1 1 9 9
 */
public class BOJ_17779_개리맨더링2_200428 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N+1][N+1];
		boolean[][] check = new boolean[N+1][N+1];
		
		for (int i = 1; i < map.length; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}	// input
		int[] district = new int[6];	// 5구역 인구수 저장할 배열
		int min = Integer.MAX_VALUE;	// 결과값을 저장할 최솟값 변수
		// x, y에 대해서 완전탐색
		// d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N
		for (int x = 1; x < N-1; x++) {	// 1<=x<=N-2
			for (int y = 2; y < N; y++) {	// 2<=y<=N-1
				for (int d1 = 1; d1 <= y-1; d1++) {
					for (int d2 = 1; d2 <= N-y && d1+d2 <=N-x; d2++) {
						// 5번 선거구 경계선 확인
						for (int i = 0; i <= d1; i++) {
							//우상단 경계선
							check[x+i][y-i] = true;
							check[x+d2+i][y+d2-i] = true;
							district[5] += map[x+i][y-i]+map[x+d2+i][y+d2-i];
						}
						for (int i = 1; i < d2 ; i++) {
							// 우하단 경계선
							check[x+i][y+i] = true;
							check[x+i+d1][y+i-d1] = true;
							district[5] += map[x+i][y+i]+map[x+i+d1][y+i-d1];
						}
						// 5선거구 내부 검색
						/*for (int i = x+1; i < x+d1+d2; i++) {
							boolean flag = false;
							for (int j = 1; j <= N; j++) {
								if(check[i][j]) {
									flag = !flag;
								}
								if(flag) {
									check[i][j] = true;
									district[5] += map[i][j];
								}
							}
						}*/
						for (int i = 0; i < d1; i++) {
							// y위치 포함 상단 구역
							int j = 0;
							while(!check[x+1+i+j][y-i]) {
								check[x+1+i+j][y-i] = true;
								district[5] += map[x+1+i+j][y-i];
								j++;
							}
						}
						for (int i = 1; i < d2; i++) {
							// y위치 하단 구역
							int j = 0;
							while(!check[x+1+i+j][y+i]) {
								check[x+1+i+j][y+i] = true;
								district[5] += map[x+1+i+j][y+i];
								j++;
							}
						}

						
						// 구역 별로 인구수 구함
						for (int r = 1; r <= N; r++) {
							for (int c = 1; c <= N; c++) {
								
								if(check[r][c]) continue; // 5번구역
								
								if (r < x + d1 && c <= y) {
									//1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
									district[1] += map[r][c];
								} else if(r<=x+d2 && y<c){
									//2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
									district[2] += map[r][c];
								} else if(x+d1 <= r&&c < y-d1+d2) {
									//3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
									district[3] += map[r][c];
								} else if(x+d2 < r&&y-d1+d2 <= c) {
									//4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
									district[4] += map[r][c];
								}
							}
						}
						
						int maxPop = Integer.MIN_VALUE;
						int minPop = Integer.MAX_VALUE;
						for (int i = 1; i < district.length; i++) {
							maxPop = maxPop<district[i]?district[i]:maxPop;
							minPop = minPop>district[i]?district[i]:minPop;
						}
						// min값 확인
						if(min>maxPop-minPop) {
							min = maxPop-minPop;
						}
						/*System.out.println("x:"+x+",y:"+y+",d1:"+d1+",d2:"+d2);
						System.out.println(maxPop-minPop);
						System.out.println(Arrays.toString(district));*/
						// 체크배열 초기화
						check = new boolean[N+1][N+1];
						// 5구역 인구수 초기화
						district = new int[6];
					}	// for d2
				}	// for d1
				
			}	// for y
		} // for x
		System.out.println(min);
	}	// main
}	// class
