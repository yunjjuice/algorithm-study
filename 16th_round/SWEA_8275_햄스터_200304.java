import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_8275_햄스터_200304 {
	static class Section {
		int l, r, s;
		Section(int l, int r, int s) {
			this.l = l;
			this.r = r;
			this.s = s;
		}
	}
	static int[] arr;
	static int[] check;
	static int[] visited;
	static int[] res;
	static Section[] section;
	static int sum;
	static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; ++testcase) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken()); // 우리 개수
			int x = Integer.parseInt(st.nextToken()); // 각 우리에 들어갈 수 있는 최대 햄스터 수
			int m = Integer.parseInt(st.nextToken());
			
			arr =  new int[n + 1];
			check = new int[n + 1];
			res = new int[n + 1];
			visited = new int[n + 1];
			section = new Section[m];
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				// l부터 r 까지 s마리
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				section[i] = new Section(l, r, s);
			}
			
			// 방법 여러가지면 총 햄스터 가장 많은 것 출력
			// 위의 방법도 여러가지면 사전순 가장 빠른 것 출력
			// 만족하는 방법이 없으면 -1 출력
			sum = 0;
			max = -1;
			simul(section[0].l, section[0].r, section[0].s, x, 0);
			
			for(int i = 1; i <= n; i++) { // 방문 안한 곳은 최대값으로 채우기
				if(visited[i] == 0) res[i] = x;
			}
			
			sb.append("#").append(testcase).append(" ");
			if(max == -1) sb.append(-1);
			else {
				for(int i = 1; i <= n; i++) sb.append(res[i]).append(" ");
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	
	public static void simul(int l, int r, int s, int x, int count) {
		if(l > r && count < section.length - 1) { // 구간합 다 구했으면 다음 구간합으로 넘어가기
			if(s == 0) {
				Section sec = section[count + 1];
				simul(sec.l, sec.r, sec.s, x, count + 1);
			}
			return;
		}
		
		if(l > r && count == section.length - 1) { // 모든 구간합을 다 충족하고, 최대값을 가지는 결과를 res배열에 저장하기
			if(s == 0) {
				if(max < sum) {
					max = sum;
					res = arr.clone();
				}
			}
			return;
		}

		if(check[l] == 1) { // 이미 수를 지정한 구간은 pass하고 다음으로 넘어감
			if(s >= arr[l]) {
				simul(l + 1, r, s - arr[l], x, count);
			}
		}
		else {
			int tmp = x;
			if(s < x) tmp = s; // x보다 s가 작을경우 예외처리
			int tmp2 = s - (r - l) * x;
			if(tmp2 < 0) tmp2 = 0; // 들어가지 못할 값을 처리하는 예외처리
			for(int i = tmp2; i <= tmp; i++) {
				arr[l] = i;
				visited[l] = 1;
				check[l] = 1;
				sum += i;
				simul(l + 1, r, s - i, x, count);
				check[l] = 0;
				sum -= i;
			}
		}
	}
}
