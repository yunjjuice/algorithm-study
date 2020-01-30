import java.util.Arrays;
import java.util.Scanner;

public class SWEA_Solution_1208_Flatten_D3_Ãµ¿ëÁø {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		for(int testcase = 1; testcase <= 10; testcase++) {
			int dump = input.nextInt();
			int[] arr = new int[100];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = input.nextInt();
			}
			
			while(dump > 0) {
				Arrays.sort(arr);
				arr[0] += 1;
				arr[arr.length - 1] -= 1;
				dump--;
			}
			
			Arrays.sort(arr);
			
			int ans = arr[arr.length - 1] - arr[0];
			
			System.out.println("#" + testcase + " " + ans);
		}
	}
}
