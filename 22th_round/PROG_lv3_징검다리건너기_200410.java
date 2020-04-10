package algo;

public class PROG_lv3_징검다리건너기_200410 {
	public static void main(String[] args) {
		int[] stones = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
		int k = 3;

		int ans = solution(stones, k);
		System.out.println(ans);
	}

	static int[] tree;

	public static int solution(int[] stones, int k) {
		int h = (int) Math.ceil(Math.log(stones.length) / Math.log(2)) + 1;
		tree = new int[1 << h]; // 세그트리만들기
		int answer = Integer.MAX_VALUE;

		init(stones, 0, 0, stones.length - 1); // max 트리 초기화

		for (int i = 0; i <= stones.length - k; i++) { // stones 배열 돌면서 구간 최대 값을 불러옴
			int tmp = max(0, i, i + k - 1, 0, stones.length - 1);
			if (answer > tmp) // 구간 최대 값 중에 가장 작은값으로 갱신
				answer = tmp;
		}
		return answer;
	}

	public static int init(int[] stones, int index, int start, int end) { // 세그먼트 트리 초기화
		if (start == end) {
			return tree[index] = stones[start];
		}

		int mid = (start + end) / 2;
		return tree[index] = Math.max(init(stones, index * 2 + 1, start, mid),
				init(stones, index * 2 + 2, mid + 1, end));
	}

	public static int max(int index, int left, int right, int start, int end) { // 구간 최대 값 구하는 함수
		if (start > right || end < left) {
			return 0;
		} else if (start >= left && end <= right) {
			return tree[index];
		} else {
			int mid = (start + end) / 2;
			return Math.max(max(index * 2 + 1, left, right, start, mid), max(index * 2 + 2, left, right, mid + 1, end));
		}
	}
}
