package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2143_두배열의합_200712 {
	static int T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < A.length; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		int m =  Integer.parseInt(br.readLine());
		int[] B = new int[m];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < B.length; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Integer> AsublistSum = sublistSum(A);
		ArrayList<Integer> BsublistSum = sublistSum(B);
		
		Collections.sort(AsublistSum);
		Collections.sort(BsublistSum);
		
		long result = 0;
		int aSize = AsublistSum.size();
		int bSize = BsublistSum.size();
		int aPointer = 0, bPointer = bSize-1;
		// 두개의 포인터가 범위 밖으로 나가면 종료
		while(aPointer < aSize && bPointer >= 0) {
			int aVal = AsublistSum.get(aPointer);
			int bVal = BsublistSum.get(bPointer);
			// 합이 T일 때
			if(aVal + bVal == T) {
				// a에서 중복된 것과 b에서 중복인 것의 개수를 찾아 곱해서 result에 더한다
				long aCount = 1;
				long bCount = 1;
				while(aPointer + 1 < aSize && 
						AsublistSum.get(aPointer + 1) == aVal) {
					aPointer++;
					aCount++;
				}
				while(bPointer - 1 >= 0 && 
						BsublistSum.get(bPointer - 1) == bVal) {
					bPointer--;
					bCount++;
				}
				result += aCount * bCount;
				aPointer++;
				bPointer--;
			}
			// 합이 T보다 작을 때
			else if(aVal + bVal < T) {
				aPointer++;
			}
			// 합이 T보다 클 때
			else if(aVal + bVal > T) {
				bPointer--;
			}
		}
		System.out.println(result);
	}

	// 각 배열별로 부분합의 리스트를 구함
	private static ArrayList<Integer> sublistSum(int[] arr) {
		ArrayList<Integer> sublistSum = new ArrayList<>();
		
		for (int i = 0; i < arr.length; i++) {
			int tmp = 0;
			int j = i;
			for (;  j < arr.length; j++) {
				tmp += arr[j];
				if(tmp > T) {
					break;
				}
				sublistSum.add(tmp);
			}
		}
		
		return sublistSum;
	}
}
