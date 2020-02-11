
/*
 * BOJ 15686 치킨 배달
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Chicken {
	int c;
	int r;

	public Chicken(int c, int r) {
		this.c = c;
		this.r = r;
	}
}

public class BOJ15686 {
	static int n, m, ans;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken()); // 남길 치킨 집 개수
		map = new int[n][n]; // 지도
		ArrayList<Chicken> list = new ArrayList<Chicken>();
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) { // 치킨집이면
					list.add(new Chicken(i, j));
					map[i][j] = 0; // 조합해서 넣어줘야 하기 때문에 빈칸으로 바꿔둔다
				}
			}
		}

		// for (int i = 0; i < list.size(); i++) {
		// System.out.println(list.get(i).c + " " + list.get(i).r);
		//
		// }
		//
		// for (int i = 0; i < map.length; i++) {
		// System.out.println(Arrays.toString(map[i]));
		// }

		ans = Integer.MAX_VALUE;
		boolean[] visited = new boolean[list.size()]; // 전체 치킨집 개수만큼의 배열
		int[] combArr = new int[m];
		// total 중에서 m개를 뽑아서 치킨거리를 계산한다.
		combination(combArr, list.size(), m, 0, 0, list); // 배열, n, r, index,
															// target
		
		System.out.println(ans);
	}

	public static void combination(int[] combArr, int n, int r, int index, int target, ArrayList<Chicken> list) {
		// r ==0 이란 것은 뽑을 원소를 다 뽑았다는 뜻.
		if (r == 0) {
			// System.out.println(Arrays.toString(combArr));
			ArrayList<Chicken> select = new ArrayList<Chicken>();
			for (int i = 0; i < index; i++) {
				// System.out.println("c:"+list.get(combArr[i]).c + " r:" +
				// list.get(combArr[i]).r);
				select.add(new Chicken(list.get(combArr[i]).c, list.get(combArr[i]).r));
			}
			distance(select);

			// 끝까지 다 검사한 경우..1개를 뽑은 상태여도 실패한 경우임 무조건 return 으로 종료
		} else if (target == n) {

			return;

		} else {
			combArr[index] = target;
			// (i) 뽑는 경우
			// 뽑으니까, r-1
			// 뽑았으니 다음 index + 1 해줘야 함
			combination(combArr, n, r - 1, index + 1, target + 1, list);

			// (ii) 안 뽑는경우
			// 안뽑으니까 그대로 r
			// 안뽑았으니, index는 그대로!
			// index를 그대로하면, 예를 들어 현재 1번 구슬을 comArr에 넣엇어도 다음 재귀에 다시 엎어쳐서 결국 1구슬을
			// 뽑지 않게 된다.
			combination(combArr, n, r, index, target + 1, list);
		}
	}

	// public static int[][] copy;
	// public static void combination(ArrayList<Chicken> list, boolean[]
	// visited, int cnt) {
	//// System.out.println("combi");
	//// for (int i = 0; i < copy.length; i++) {
	//// System.out.println(Arrays.toString(copy[i]));
	//// }
	//
	// if(cnt == m) {
	// copy = map;
	// ArrayList<Chicken> chi = new ArrayList<Chicken>();
	// // 전체 크기 중 m개 뽑아서 copy에 위치시킨다
	// // 뽑아서 전체 거리 계산을 해본다
	// for (int i = 0; i < list.size(); i++) {
	//// System.out.println("for " + list.size());
	// if(visited[i]) {
	//// System.out.println("i" + i);
	// copy[list.get(i).c][list.get(i).r] = 2; // 치킨집 등록해준다
	// chi.add(new Chicken(list.get(i).c, list.get(i).r));
	// }
	// }
	// distance(copy, chi);
	// return;
	// } else {
	// for (int i = 0; i < list.size(); i++) {
	// visited[i] = true;
	// combination(list, visited, cnt+1);
	// visited[i] = false;
	// }
	// }
	// }

	public static void distance(ArrayList<Chicken> list) {
//		System.out.println("distance method");
		// 지도에 있는 집이랑 치킨집이랑 거리 비교해서 짧은 애로 더함
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).c + " " + list.get(i).r);
//		}
		
		int sum = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 1){ // 집이 있으면 저장된 치킨집들이랑 거리 계산해본다
					int dist = Integer.MAX_VALUE;
					for (int k = 0; k < list.size(); k++) {
						int tmp = Math.abs(i-list.get(k).c) + Math.abs(j-list.get(k).r);
						if(tmp < dist)
							dist = tmp;
					}
					sum += dist;
				}
			}
		}
		if(ans > sum)
			ans = sum;
	}
}