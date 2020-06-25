import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 초기 배열 3*3
 * 행개수>=열개수 : R연산
 * 행개수<열개수 : C연산
 * 
 * 정렬 방법 : 1. (숫자, 나온횟수) 형태로 만든다.
 * 		   2. 나온 횟수 오름차순으로 먼저 정렬한다.
 * 		   3. 나온 횟수가 같을때는 숫자가 더 작은것이 앞선다.
 * 
 * 행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버린다.
 * 배열 A에 들어있는 수와 r, c, k가 주어졌을 때, A[r][c]에 들어있는 값이 k가 되기 위한 최소 시간을 구해보자.
 * 이 값이 100을 넘어가는 경우에는 -1을 출력한다.
 * 
 */
public class BOJ_17140_이차원배열과연산_200503 {
	static int[][] arr = new int[101][101];
	static int rSize = 3;
	static int cSize = 3;
	static class pair implements Comparable<pair>{
		int num;
		int frequency;
		public pair(int num, int frequency) {
			super();
			this.num = num;
			this.frequency = frequency;
		}
		@Override
		public int compareTo(pair o) {
			if(this.frequency<o.frequency) return -1;
			else if(this.frequency>o.frequency) return 1;
			else {	// 빈도가 같을때
				if(this.num<o.num) return -1;
				else return 1;
			}
		}
		@Override
		public String toString() {
			return "pair [num=" + num + ", frequency=" + frequency + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= rSize; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j <= cSize; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while(true) {
			// 종료조건
			if(arr[r][c] == k) {
				// 시간 출력
				System.out.println(time);
				break;
			}
			if(++time>100) {	// 실패
				System.out.println(-1);
				break;
			}
			if(rSize>=cSize) {
				// R연산
				int max = 0;	// 가장큰 행의 크기를 저장할 변수
				for (int i = 1; i <= rSize; i++) {
					ArrayList<pair> list = new ArrayList<>();	// pair을 저장할 리스트
					for (int j = 1; j <= cSize; j++) {
						int num = arr[i][j];
						if(num == 0) continue;	// 0은 취급 x
						boolean flag = false;	// num이 리스트에 있는지 없는지 체크
						for (pair pair : list) {
							if(pair.num == num) {
								pair.frequency++;	// num이 리스트에 있으면 빈도수+1;
								flag = true;	// num이 리스트에 있음.
								break;
							}
						}
						if(!flag) {	// num이 리스트에 없다면.
							list.add(new pair(num,1)); // 새로운 pair 추가
						}
					}
					// list 정렬
					Collections.sort(list);
//					System.out.println(list);
					// 한 행이 끝나면 덮어 써줌.
					Arrays.fill(arr[i], 0);
					int j = 1;
					for (pair pair : list) {
						arr[i][j++] = pair.num;
						arr[i][j++] = pair.frequency;
					}
					for(;j<=100;j++) {
						arr[i][j] = 0;
					}
					int tmp = list.size()*2;	// 현재 행의 크기를 저장할 변수
					if(max<tmp) max = tmp;
				}
				cSize = max;
			}else {
				// C연산
				int max = 0;	// 가장큰 행의 크기를 저장할 변수
				for (int j = 1; j <= cSize; j++) {
					ArrayList<pair> list = new ArrayList<>();	// pair을 저장할 리스트
					for (int i = 1; i <= rSize; i++) {
						int num = arr[i][j];
						if(num == 0) continue;	// 0은 취급 x
						boolean flag = false;	// num이 리스트에 있는지 없는지 체크
						for (pair pair : list) {
							if(pair.num == num) {
								pair.frequency++;	// num이 리스트에 있으면 빈도수+1;
								flag = true;	// num이 리스트에 있음.
								break;
							}
						}
						if(!flag) {	// num이 리스트에 없다면.
							list.add(new pair(num,1)); // 새로운 pair 추가
						}
					}
					// list 정렬
					Collections.sort(list);
//					System.out.println(list);
					// 한 행이 끝나면 덮어 써줌.
					int i = 1;
					for (pair pair : list) {
						arr[i++][j] = pair.num;
						arr[i++][j] = pair.frequency;
					}
					for(;i<=100;i++) {
						arr[i][j] = 0;
					}
					int tmp = list.size()*2;	// 현재 행의 크기를 저장할 변수
					if(max<tmp) max = tmp;
				}
				rSize = max;
			}	// end of r,c
			
		}	// end of while
	}	// end of main
}	// end of class
