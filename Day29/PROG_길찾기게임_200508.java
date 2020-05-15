/*
 * programmers lv3 길 찾기 게임
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class PROG_길찾기게임_200508 {
	public static void main(String[] args) {
		int[][] answer = solution(new int[][] {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}});
		for (int i = 0; i < answer.length; i++) {
			System.out.println(Arrays.toString(answer[i]));
		}
	}
	
	public static int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][nodeinfo.length]; // 전위 후위 두 가지 방법
		
		ArrayList<Node> list = new ArrayList<>();
		for (int i = 0; i < nodeinfo.length; i++) {
			list.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
		}
		
		Collections.sort(list);
//		System.out.println(list.toString());
		
		Node root = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			addNode(root, list.get(i));
		}
		
		ArrayList<Integer> tmp = new ArrayList<>(); 
		preorder(root, tmp);
		for (int i = 0; i < answer[0].length; i++) {
			answer[0][i] = tmp.get(i);
		}
		
		tmp.clear();
		
		postorder(root, tmp);
		for (int i = 0; i < answer[1].length; i++) {
			answer[1][i] = tmp.get(i);
		}
		
		return answer;
	}
	
	private static void postorder(Node node, ArrayList<Integer> tmp) {
		if(node == null)
			return;
		
		postorder(node.left, tmp);
		postorder(node.right, tmp);
		tmp.add(node.idx);
	}

	private static void preorder(Node node, ArrayList<Integer> tmp) {
		if(node == null)
			return;
		
		tmp.add(node.idx);
		preorder(node.left, tmp);
		preorder(node.right, tmp);
		
	}

	private static void addNode(Node parent, Node child) {
		if(child.x < parent.x) {
			if(parent.left == null)
				parent.left = child;
			else 
				addNode(parent.left, child);
		} else {
			if(parent.right == null)
				parent.right = child;
			else
				addNode(parent.right, child);
		}
		
	}

	static class Node implements Comparable<Node>{
		int idx, x, y;
		Node left = null;
		Node right = null;
		public Node(int idx, int x, int y) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", x=" + x + ", y=" + y + "]";
		}

		@Override
		public int compareTo(Node o) {
			if(this.y == o.y)
				return Integer.compare(this.x, o.x);
			else
				return -Integer.compare(this.y, o.y);
		}
	}
}
