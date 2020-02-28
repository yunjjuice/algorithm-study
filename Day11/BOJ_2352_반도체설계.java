/*
 * BOJ 2352 반도체 설계
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2352 {
	static int n, ans;
	static int[] a, d;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		a = new int[n+1];
		d = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.toString(a));
		
		for (int i = 1; i <= n; i++) {
			d[i] = 1;
			for (int j = 1; j < i; j++) { // i가 항상 j보다 크다
				if(a[j] < a[i] && d[i] < d[j]+1){
					d[i] = d[j] + 1;
//					System.out.println("i " + i +" j " + j + " d[i] "+d[i]);
				}
			}
		}
		
//		System.out.println(Arrays.toString(d));
		
		ans = d[1];
		for (int i = 1; i <= n; i++) {
			if(ans < d[i])
				ans = d[i];
		}
		
		System.out.println(ans);	
	}
}
