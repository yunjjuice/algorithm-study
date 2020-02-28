import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 총 수익이 가장 큰 값을 출력
public class SWEA_2115_벌꿀채취_200228 {
	static int[][] map;
	static int n, m, c;
	static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken()); // 선택할 수 있는 벌통의 개수
			c = Integer.parseInt(st.nextToken()); // 채취할 수 있는 최대 꿀 양
			
			map = new int[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // input
			
			max = 0;
			select();
			
			sb.append("#").append(testcase).append(" ").append(max).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void collect(int[] first, int[] second) {
		int moneyA = 0;
		int moneyB = 0;
		for(int i = 1; i < (1 << m); i++) {
			int sum = 0;
			int money = 0;
			for(int j = 0; j < m; j++) {
				if((i & (1 << j)) != 0) {
					money += first[j] * first[j];
					sum += first[j];
				}
			}
			if(sum <= c && money > moneyA) {
				moneyA = money;
			}
		}
		
		for(int i = 1; i < (1 << m); i++) {
			int sum = 0;
			int money = 0;
			for(int j = 0; j < m; j++) {
				if((i & (1 << j)) != 0) {
					money += second[j] * second[j];
					sum += second[j];
				}
			}
			if(sum <= c && money > moneyB) {
				moneyB = money;
			}
		}
		
		if(max < (moneyA + moneyB)) max = moneyA + moneyB;
	}
	
	public static void select() {
		int[] first = new int[m];
		int[] second = new int[m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n - (m - 1); j++) {
				int k = 0;
				for(k = j; k < m + j; k++) {
					first[k - j] = map[i][k];
				}
				k--;
				for(int i2 = i; i2 < n; i2++) {
					if(i2 == i) {
						for(int j2 = k + 1; j2 < n - (m - 1); j2++) {
							for(int k2 = j2; k2 <= j2 + (m - 1); k2++) {
								second[k2 - j2] = map[i2][k2];
							}
							collect(first, second);
						}
					} else {
						for(int j2 = 0; j2 < n - (m - 1); j2++) {
							for(int k2 = j2; k2 <= j2 + (m - 1); k2++) {
								second[k2 - j2] = map[i2][k2];
							}
							collect(first, second);
						}
					}
				}
			}
		}
	}
}
