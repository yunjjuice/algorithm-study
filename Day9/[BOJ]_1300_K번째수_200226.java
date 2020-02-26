import java.io.*;
import java.util.*;

public class BOJ_K번째수 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int left = 1;
		int right = K; //M번째의 값은 M보다 클 수 있나? 없다.
		int answer = 0;
		while(left <= right) { //같을 떄 까지 마지막으로 해줘야함.
			int mid = (left + right)/2;
			int cnt = 0;
			for(int i=1; i<=N; i++) {
				cnt += Math.min(N, mid/i); //그리고 K가 N보다 클 경우에는 1행을 보면, 1행은 N가 까지 들어있는데 K/i 의 갯수가 그걸 넘어서는 상황이 발생함. 그거 조건 처리. -- 이해2.
			}
			if(cnt < K) left = mid+1;
			else {
				// mid 보다 작은 갯수가 K 보다 크면 일단 후보군이야.
				answer = mid;
				right = mid-1;
			}
		}
		System.out.println(answer);
	}//end main.
}//end class.
