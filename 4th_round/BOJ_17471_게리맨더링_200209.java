package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471_게리맨더링_200209 {
	static int minSub = Integer.MAX_VALUE; // 가장 작은 인구차이를 저장할 변수
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		int[] population = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		} // input	
		
		for(int i = 0; i < n; i++) {
			map[i][i] = 1; // 선거구에 혼자만 있는것도 체크해 주기 위해서 자기 자신으로 돌아오는 간선 추가
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j = 0; j < cnt; j++) {
				map[i][Integer.parseInt(st.nextToken()) - 1] = 1;
			}
		} // input
		
		int[] check = new int[n]; // bfs 수행하기 위한 방문 체크 배열
		List<Integer> section1 = new ArrayList<>(); // 선거구 1을 담을 list
		List<Integer> section2 = new ArrayList<>(); // 선거구 2를 담을 list
		
		for(int i = 1; i < (1<<n) - 1; i++) { // 선거구를 두가지로 나누기 위해 부분집합을 구함(비트마스킹)
			// 방문 초기화, 앞에 부분집합 구해줬던 거 초기화
			check = new int[n];
			section1.clear();
			section2.clear();
			
			int sumSection1 = 0;
			int sumSection2 = 0;
			for(int j = 0; j < n; j++) { // 부분집합과 여집합으로, 선거구를 두개로 나눔
				if((i & (1<<j)) != 0) {
					section1.add(j);
					sumSection1 += population[j];
				} else {
					section2.add(j);
					sumSection2 += population[j];
				}
			}
			
			// 두번째 선거구를 제외하고 첫번째 선거구가 이어져 있는지 검사
			check = new int[n];
			for(int k = 0; k < section2.size(); k++) {
				check[section2.get(k)] = 1;
			}
			if(bfs(map, check, section1.get(0), section1)) {
				// 첫번째 선거구가 다 이어져 있다면
				// * 방문 초기화 중요 *, 첫번째 선거구를 제외
				check = new int[n];
				for(int k = 0; k < section1.size(); k++) {
					check[section1.get(k)] = 1;
				}
				// 두번째 선거구가 이어져 있는지 검사
				if(bfs(map, check, section2.get(0), section2)) {
					int sub = Math.abs(sumSection1 - sumSection2);
					if(minSub > sub) minSub = sub; // 둘다 true면 인구 수 차이 구함
				} 
			}		
		}
		if(minSub == Integer.MAX_VALUE) minSub = -1; // minSub 바뀌지 않았다면 return -1
		
		System.out.println(minSub);
	}
		
	public static boolean bfs(int[][] map, int[] check, int now, List<Integer> section) {
		int count = 0; // section을 다 방문할 수 있는지 count하는 변수
		Queue<Integer> q = new LinkedList<>();
		q.offer(now);
		while(!q.isEmpty()) {
			int front = q.poll();
			for(int i = 0; i < map.length; i++) {
				if(map[front][i] == 1 && check[i] == 0) {
					q.offer(i);
					check[i] = 1;
					if(section.indexOf(i) != -1) { // section에 존재한다면 count++
						count++;
					}
				}
			}
			if(count == section.size()) return true;
		}
		return false;
	}
}
