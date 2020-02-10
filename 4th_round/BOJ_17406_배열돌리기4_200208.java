package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Turn {
	int r;
	int c;
	int s;
}

public class BOJ_17406_배열돌리기4_200208 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input array
		
		Turn[] turnArray = new Turn[k];
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			Turn t = new Turn();
			t.r = Integer.parseInt(st.nextToken()) - 1;
			t.c = Integer.parseInt(st.nextToken()) - 1;
			t.s = Integer.parseInt(st.nextToken());
			turnArray[i] = t;
		} // input info
		
		int loop = factorial(k); // 회전 연산 진행 순서 모든 경우의 수
		int[] permuArr = new int[k]; // 회전 연산 진행 순서 담는 배열
		for(int i = 0; i < k; i++) permuArr[i] = i;

		int[][] useMap = new int[n][m]; // 배열 돌리기를 실행할 배열 생성
 		int minVal = Integer.MAX_VALUE; // 최소값 구하기 위한 변수
		while(true) {
			copyMap(useMap, map); // 초기 배열로 설정
			for(int i = 0; i < permuArr.length; i++) { // 회전 연산 진행 순서에 맞춰 배열 돌리기 진행
				turnArray(useMap, turnArray[permuArr[i]].r, turnArray[permuArr[i]].c, turnArray[permuArr[i]].s);
			}
			
			int value = minValue(useMap);
			if(value < minVal) minVal = value; // 가장 작은값 찾기
			
			loop--;
			if(loop == 0) break; // 회전 연산 모두 진행 되었으면 종료
			nextPermutation(permuArr); // 다음 회전 연산 진행 순서
		}
		
		System.out.println(minVal);
	}
	
	public static void turnArray(int[][] useMap, int r, int c, int s) { // 배열 돌리는 메소드 : s값을 점점 작게 해서 재귀 사용
		if(s == 0) return; // 더이상 돌릴 배열이 없으면 return
		
		// 제일 바깥쪽부터 시작
		int temp = useMap[r - s][c - s];
		for(int i = r - s; i < r + s; i++) {
			useMap[i][c - s] = useMap[i + 1][c - s];
		}
		for(int i = c - s; i < c + s; i++) {
			useMap[r + s][i] = useMap[r + s][i + 1];
		}
		for(int i = r + s; i > r - s; i--) {
			useMap[i][c + s] = useMap[i - 1][c + s];
		}
		for(int i = c + s; i > c - s; i--) {
			useMap[r - s][i] = useMap[r - s][i - 1];
			if(i == c - s + 1) {
				useMap[r - s][i] = temp;
			}
		}
		
		turnArray(useMap, r, c, s - 1); // 한칸 안쪽의 배열 돌리기
		
		return;
	}
	
	public static int minValue(int[][] map) { // 가장 작은 배열 값 구하는 메소드
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < map.length; i++) {
			int sum = 0;
			for(int j = 0; j < map[i].length; j++) {
				sum += map[i][j];
			}
			if(sum < min) min = sum;
		}
		return min;
	}
	
	public static void copyMap(int[][] useMap, int[][] map) { // 배열 복사 메소드
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				useMap[i][j] = map[i][j];
			}
		}
	}
	
	public static void nextPermutation(int[] arr) { // 다음 순열 구하는 메소드
		int i = arr.length - 1;
		while(i > 0 && arr[i] < arr[i - 1]) i--;
		
		if(i <= 0) return;
		
		int j = arr.length - 1;
		while(arr[i - 1] > arr[j]) j--;
		
		int temp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = temp;
		
		j = arr.length - 1;
		while(i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
	}
	
	public static int factorial(int n) { // 회전 연산 진행 순서 모든 경우의 수 구하는 메소드
		if(n == 1) return 1;
		else {
			return factorial(n - 1) * n;
		}
	}
}
