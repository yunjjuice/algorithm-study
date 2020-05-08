import java.util.*;
class Solution {
    static class Node{
        int x;
        int y;
        int num;
        Node left;
        Node right;
        public Node(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }//class Node.
    static int[][] answer;    
    static int idx;
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        idx = 0;
        ArrayList<Node> list = new ArrayList<Node>();
        for(int i=0; i<nodeinfo.length; i++){
            list.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1));
        }
        Collections.sort(list, new Comparator<Node>(){
             public int compare(Node n1, Node n2) {
                 if(n1.y < n2.y) return 1;
                 else if(n1.y == n2.y){
                     return n1.x > n2.x ? 1 : -1;
                 }
                 else return -1;
             }
        });
        Node root = list.get(0);
        for(int i=1; i<list.size(); i++){
            add(root, list.get(i));
        }
        pre(root);
        idx = 0;
        post(root);
        return answer;
    }//end main.
    public static void add(Node parent, Node child){
        if(child.x < parent.x){ //왼쪽 배치.
            if(parent.left == null){
                parent.left = child;
            }
            else add(parent.left , child);
        }
        else{ //오른쪽 배치.
            if(parent.right == null){
                parent.right = child;
            }
            else add(parent.right , child);
        }
    }//end add method.
    public static void pre(Node n){
        if(n != null){
            answer[0][idx++] = n.num;
            pre(n.left);
            pre(n.right);
        }
    }//end pre method.
    public static void post(Node n){
        if(n != null){
            post(n.left);
            post(n.right);
            answer[1][idx++] = n.num;
        }
    }//end post method.
}//end class.