import java.io.*;
import java.util.*;

public class BOJ_히스토그램에서가장큰직사각형 {
	
	static class Tree{
		Long[] tree_min;
		int[] tree_idx;
		int h=1;
		public Tree(int n) {
			while(h<n) h*=2;
			tree_min = new Long[h*2];
			tree_idx = new int[h*2];
			for(int i=1; i<h*2; i++) {
				tree_min[i] = Long.MAX_VALUE;
			}
		}
		void update(int idx, Long value) {
			int leaf = idx + h - 1;
			tree_min[leaf] = value;
			tree_idx[leaf] = leaf;
			leaf/=2;
			while(leaf>=1) {
				if(tree_min[leaf*2] < tree_min[leaf*2 + 1]) {
					tree_min[leaf] = tree_min[leaf*2];
					tree_idx[leaf] = tree_idx[leaf*2];
				}
				else {
					tree_min[leaf] = tree_min[leaf*2+1];
					tree_idx[leaf] = tree_idx[leaf*2+1];
				}
				leaf/=2;
			}
		}//end update.
		
		Long get_max(int left, int right) {
			int l = left + h -1;
			int r = right + h - 1;
			Long value = Long.MAX_VALUE;
			int idx = l;
			while(l<=r) {
				if(l%2==1) {
					if(value > tree_min[l]) {
						value = tree_min[l];
						idx = tree_idx[l];
					}
					l = l/2 + 1;
				}
				else {
					l/=2;
				}
				if(r%2==0) {
					if(value > tree_min[r]) {
						value = tree_min[r];
						idx = tree_idx[r];
					}
					r = r/2 - 1;
				}
				else {
					r/=2;
				}
			}//그 구간의 가장 작은거 찾고 그 idx 찾는 법.
			Long max_value = (right-left+1) * value;
			if(left < idx-(h-1)) {
				Long temp = get_max(left, (idx-(h-1)-1));
				max_value = max_value > temp ? max_value : temp;
			}
			if(idx-(h-1) < right) {
				Long temp = get_max((idx-(h-1)+1), right);
				max_value = max_value > temp ? max_value : temp;
			}
			return max_value;
		}//end get_max;
		
	}//end class.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if(N == 0) break;
			Tree t = new Tree(N);
			for(int i=1; i<=N; i++) {
				t.update(i, Long.parseLong(st.nextToken()));
			}
			sb.append(t.get_max(1,N)).append("\n");
		}
		System.out.print(sb);
	}//end main.
}//end class.
