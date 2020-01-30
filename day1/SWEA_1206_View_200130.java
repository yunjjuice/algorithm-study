import java.util.Scanner;

public class SWEA_Solution_1206_View_D3_Ãµ¿ëÁø {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		for(int i = 0; i < 10; i++) {
			int width = input.nextInt();
			int[] building = new int[width + 4];
			int ans = 0;
			
			for(int j = 2; j < width + 2; j++) {
				building[j] = input.nextInt();
			}
			
			for(int j = 2; j < width + 2; j++) {
				int min = 255;
				for(int k = j - 2; k <= j + 2; k++) {
					if(k != j) {
						if(building[j] - building[k] < min) {
							min = building[j] - building[k];
						}
					}
				}
				if(min < 0) min = 0;
				ans += min;
			}
			System.out.println("#" + (i + 1) + " " + ans);
		}
	}
}