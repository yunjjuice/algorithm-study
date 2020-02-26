import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1300_K번째수_200226 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int left = 1;
		int right = k; // min(10^9, n^2), right를 k로 두는 이유 : k번째수는 아무리 커봤자 k보다는 같거나 작다
		while(left <= right) {
			int mid = (left + right) / 2;
			int count = 0;
			
			for(int i = 1; i <= n; i++) {
				count += Math.min(n, mid / i); // mid보다 작은 수의 개수를 구하는 과정
			}
			
			if(count < k) left = mid + 1;
			else right = mid - 1;
		}
		
		System.out.println(left);
	}
}
