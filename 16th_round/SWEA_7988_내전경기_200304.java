import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7988_내전경기_200304 {
	static HashMap<String, Integer> hm;
	static int[][] graph;
	static int[] check;
	static boolean ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; ++testcase) {
			int k = Integer.parseInt(br.readLine());
			int value = 0;
			graph = new int[2 * k][2 * k];
			hm = new HashMap<>();
			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String str1 = st.nextToken();
				String str2 = st.nextToken();
				// 중복확인해서 value값과 함께 hashmap에 저장
				if(!hm.containsKey(str1)) {
					hm.put(str1, value);
					value++;
				}
				if(!hm.containsKey(str2)) {
					hm.put(str2, value);
					value++;
				}
				// 서로 같은팀이 되면 안되는 짝 저장
				graph[hm.get(str1)][hm.get(str2)] = 1;
				graph[hm.get(str2)][hm.get(str1)] = 1;
			} // input
			
			check = new int[hm.size()];
			ans = true;

			for(int i = 0; i < hm.size(); i++) {
				if(check[i] == 0) { // 팀이 정해지지 않았다면 팀 정하기
					check[i] = 1;
					makeGroup(0);
				}
				if(!ans) {
					break;
				}
			}
			
			sb.append("#").append(testcase).append(" ");
			if(ans) sb.append("Yes");
			else sb.append("No");
			sb.append('\n');
		}
		System.out.print(sb);
	}
	
	public static void makeGroup(int x) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(x);
		
		while(!q.isEmpty()) {
			int nx = q.poll();
			for(int i = 0; i < hm.size(); i++) {
				if(graph[nx][i] == 1) { // 같은팀이면 안되는 사람 발견 !
					if(check[i] != 0 && check[nx] == check[i]) { // 팀이 정해진 아이인데 나랑 같은팀이라면 더이상 짝을 짓지 못한다는 것
						ans = false;
						return;
					}
					
					if (check[i] == 0) { // 팀이 정해진 아이가 아니라면 나와 다른 팀으로 배정
						check[i] = check[nx] == 1 ? 2 : 1;
						q.offer(i);
					} 
				}
			}
		}
	}
}
