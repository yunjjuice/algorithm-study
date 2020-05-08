import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PROG_길찾기게임_200508 {
	static int index = 0;
	public static void main(String[] args) {
		int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
		solution(nodeinfo);
	}
	public static int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];	// 0번은 pre, 1번은 post
        
        ArrayList<node> nodelist = new ArrayList<>();
        
        for (int i = 0; i < nodeinfo.length; i++) {
			nodelist.add(new node(i+1,nodeinfo[i][1],nodeinfo[i][0]));
		}
        // nodeinfo를 level을 기점으로 정렬
        Collections.sort(nodelist, new Comparator<node>() {
			@Override
			public int compare(node o1, node o2) {
				if(o1.R>o2.R)
					return -1;	// level이 높으면 더 앞
				else if(o1.R<o2.R)
					return 1;
				else {	// 같을 때
					if(o1.C<o2.C) {	// c좌표가 더 전인게 앞
						return -1;
					} else {
						 return 1;
					}
				}
			}
		});
        // 자식들을 트리에 넣는 코드
        node root = nodelist.get(0);
        for (int i = 1; i < nodelist.size(); i++) {
        	linkSub(root,nodelist.get(i));
		}
        // 트리 전위순회
        preorder(answer, root);
        // 트리 후위순회
        index=0;
        postorder(answer, root);
        
        return answer;
    }
	
	private static void postorder(int[][] answer, node n) {
		// 후위순회
		if(n == null) return;
		else {
			postorder(answer, n.left);
			postorder(answer, n.right);
			answer[1][index++] = n.data;	// answer[1]은 후위
		}
	}
	private static void preorder(int[][] answer, node n) {
		// 전위순회
		if(n == null) return;
		else {
			answer[0][index++] = n.data;	// answer[0]은 전위
			preorder(answer, n.left);
			preorder(answer, n.right);
		}
	}
	private static void linkSub(node parent, node child) {
		if(parent.C > child.C) {
			// child가 왼쪽
			if(parent.left == null) {
				// 없으면 여기 넣음
				parent.left = child;
			} else {
				// 이미 존재하면 더 타고 들어감.
				linkSub(parent.left, child);
			}
		} else {
			// child가 오른쪽
			if(parent.right == null) {
				// 없으면 여기 넣음
				parent.right = child;
			} else {
				// 이미 존재하면 더 타고 들어감.
				linkSub(parent.right, child);
			}
		}
	}	// end of method link

	static class node{
		int data;
		int R;
		int C;
		node left;
		node right;
		public node(int data, int r, int c) {
			super();
			this.data = data;
			R = r;
			C = c;
		}
	}
}
