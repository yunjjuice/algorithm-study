import java.io.*;
import java.util.*;
 
public class SWEA_7988_내전경기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());
        for(int test=1; test<=TC; test++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
            Map<String, Integer> map = new HashMap<>(); //key, value를 활용.
            int val = 0; //hash map value값.
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                String str1 = st.nextToken();
                String str2 = st.nextToken();
                if(!map.containsKey(str1)) {
                    map.put(str1, val++);
                    lists.add(new ArrayList<>());
                }
                if(!map.containsKey(str2)) {
                    map.put(str2, val++);
                    lists.add(new ArrayList<>());
                }
                int val1 = map.get(str1);
                int val2 = map.get(str2);
                lists.get(val1).add(val2);
                lists.get(val2).add(val1);
            }
             
            int[] v = new int[val];
            boolean result = true;
             
            Queue<Integer> q = new LinkedList<>();
            loop:
            for(int i=0; i<val; i++) {
                if(v[i] == 0) { //팀이 안정해진경우.
                    v[i] = 1;
                }
                q.add(i);
                 
                while(!q.isEmpty()) {
                    int x = q.poll();
                    int a = v[x]; //a는 팀
                    ArrayList<Integer> list = lists.get(x);
                    for(Integer l : list) {
                        if(v[l] == a) {
                            result = false;
                            break loop;//특정 구역 break;
                        }else if(v[l] == 0) { //아직 v[l]의 팀이 안정해진경우 지금 비교하는 팀과 반대팀에 넣어야함.
                            v[l] = a == 1 ? 2 : 1;
                            q.add(l);
                        }
                    }
                }
            }
            sb.append("#").append(test).append(" ").append(result ? "Yes" : "No").append("\n");
        }//end TestCase.
        System.out.print(sb);
    }//end main.
}//end class.