import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1952_수영장_200228 {
	static int[] fee;
	static int[] usePlan;
	static int[] cheepFee;
	static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; testcase++) {
			fee = new int[4]; // 1일, 1달, 3달, 1년
			usePlan = new int[12]; // 1월 ~ 12월 사용 횟수
			cheepFee = new int[12];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < 4; i++) {
				fee[i] = Integer.parseInt(st.nextToken());
			}			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < 12; i++) {
				usePlan[i] = Integer.parseInt(st.nextToken());
			}
			
			min = 0;
			// 1일 이용권 vs 1달권이 더 싸면 1달권 선택
			for(int i = 0; i < 12; i++) {
				if(usePlan[i] * fee[0] > fee[1]) {
					cheepFee[i] = fee[1];
					min += cheepFee[i];
				}
				else {
					cheepFee[i] = usePlan[i] * fee[0];
					min += cheepFee[i];
				}
			}
			// 3달권 생각
			int sum = 0;
			dfs(0, sum);
			
			// 1년 이용권
			if(min > fee[3]) min = fee[3];
			
			
			
			
			sb.append("#").append(testcase).append(" ").append(min).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void dfs(int now, int sum) {
		if(now >= 12) {
			if(min > sum)
				min = sum;
			return;
		}
		for(int i = now; i < 12; i++) {
			//System.out.println(i);
			sum += fee[2];
			dfs(i + 3, sum);
			sum -= fee[2];
			sum += cheepFee[i];
		}
		
		if(min > sum) min = sum;
	}
}
