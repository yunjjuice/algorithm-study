import java.util.*;

class Solution {
    static int N;
    static ArrayList<Integer> list = new ArrayList<Integer>();
    static int[] div;
    static boolean[] selected;
    static int[] weak_list;
    static boolean end;
    static int answer = -1;
    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        weak_list = weak;
        for(int i=1; i<=dist.length; i++){
            list.add(dist[dist.length-i]);
            div = new int[i];
            selected = new boolean[i];
            divide(i, 1, weak_list.length) ;
            if(end) return answer;
        }
        return answer;
    }//end main.
    public static void divide(int num, int now, int total){
        if(num==now){
            div[now-1] = total;
            for(int i=0; i<weak_list.length; i++){
                check(i,0);
                if(end) return;
            }
            return ;
        }
        for(int i=1; i<=total-(num-now); i++){
            div[now-1] = i;
            divide(num,now+1,total-i);
        }
    }//end divide.
    public static void check(int start, int cnt){
        if(cnt == div.length){
            answer = cnt;
            end = true;
            return ;
        }
        int length = 0;
        for(int i=1; i<div[cnt]; i++){
            int size = weak_list.length;
            int tmp = weak_list[(start+i)%size] - weak_list[(start+i-1)%size];
            if(tmp<0) tmp+=N;
            length += tmp;
        }
        for(int i=0; i<list.size(); i++){
            if(!selected[i] && list.get(i) >= length){
                selected[i] = true;
                check(start+div[cnt],cnt+1);
                if(end) return ;
                selected[i] = false;
            }
        }
    }
}//end solution.