import java.io.*;
import java.util.*;

public class SWEA_1247_최적경로_200229_TSP {
    static Position[] arr;
    static int[][] dp;
    static boolean[] visited;
    static int visit;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());
        for(int testcase = 1; testcase <= TC; testcase++) {
            int n = Integer.parseInt(br.readLine());
            arr = new Position[n + 2];
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < n + 2; i++) {
                arr[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
             
            visit = 1 << (n + 2);
            visited = new boolean[n + 2];
            dp = new int[n + 2][visit];
             
            sb.append("#").append(testcase).append(" ").append(findShortPath(0, 1)).append('\n');   
        }
        System.out.print(sb);
    }
     
    public static int findShortPath(int h, int v) {
        if(v == visit - 3) {
            return Math.abs(arr[h].x - arr[1].x) + Math.abs(arr[h].y - arr[1].y);
        }
        
        if(dp[h][v] != 0) return dp[h][v];
         
        int ret = Integer.MAX_VALUE;
        for(int i = 2; i < visited.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                ret = Math.min(ret, findShortPath(i, v + (1 << i)) + Math.abs(arr[h].x - arr[i].x) + Math.abs(arr[h].y - arr[i].y));
                visited[i] = false;
            }
        }
        
        dp[h][v] = ret;
        return ret;     
    }
}