package algo;

public class PROG_lv3_NQueen_200421 {
	public static void main(String[] args) {
		for(int i = 1; i <= 12; i++) {
			System.out.println(solution(i));
		}
	}

	static class Pos { // 퀸 위치 저장할 class
		int x, y;
	}

	static int answer = 0;
    static Pos[] arr;
    
    public static int solution(int n) {
        arr = new Pos[n]; // 퀸 위치 저장할 배열
        for(int i = 0; i < n; i++) arr[i] = new Pos(); // 객체 생성
        answer = 0;
        dfs(0, n);
        return answer;
    }
    
    public static void dfs(int i, int n) {
        if(i == n) { // 기저조건 = n개의 퀸을 다 배치하였으면 return
            answer++;
            return;
        }
        
        for(int j = 0; j < n; j++) {
            if(!check(i, j)) continue; // 놓을 수 없는 곳 체크
            arr[i].x = i; arr[i].y = j; // 위치 변경
            dfs(i + 1, n); // 다음 퀸 위치 찾으러
        }
    }
    
    public static boolean check(int x, int y) { // 놓을수 있는 자린지 체크
        for(int i = 0; i < x; i++) { // 지금까지 놓여져 있는 퀸 검사
            Pos p = arr[i];
            if(x + y == p.x + p.y) return false; // 대각선 확인
            if(y == p.y) return false; // 위 확인
            if(p.x - x == p.y - y) return false; // 대각선 확인
        }
        return true;
    }
}
