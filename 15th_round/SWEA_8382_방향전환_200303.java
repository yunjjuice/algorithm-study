import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_8382_방향전환_200303 {
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= TC; ++testcase) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sub = abs(abs(x1 - x2) - abs(y1 - y2));
			int dist = abs(x1 - x2) + abs(y1 - y2);
			if(sub <= 1) ans = dist;
			else ans = sub - (sub % 2) + dist;

			
			sb.append("#").append(testcase).append(" ").append(ans).append('\n');
		}
		
		System.out.print(sb);
	}
	
	public static int abs(int x) {
		if(x < 0) return -x;
		return x;
	}
}
