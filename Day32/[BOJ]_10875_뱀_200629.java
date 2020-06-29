import java.io.*;
import java.util.*;

public class BOJ_10875_뱀 {
	static class Line{
		long start_x; //직선의 시작 x.
		long start_y; //직선의 시작 y.
		long end_x; //직선의 끝 x.
		long end_y; //직선의 끝 y.
		boolean horizon; //가로인지 세로인지.
		public Line(long sx, long sy, long ex, long ey, boolean hr) {
			this.start_x=sx;
			this.start_y=sy;
			this.end_x=ex;
			this.end_y=ey;
			this.horizon = hr;
		}
	}//class Snake.
	static ArrayList<Line> line = new ArrayList<Line>();
	static int[][] db;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	static int N;
	static int direct = 3;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		N = Integer.parseInt(st.nextToken());
		line.add(new Line(-1,0,-1,2*N,true)); //위에 테두리.
		line.add(new Line(2*N+1,0,2*N+1,2*N,true)); //밑에 테두리.
		line.add(new Line(0,-1,2*N,-1,false)); //왼쪽 테두리.
		line.add(new Line(0,2*N+1,2*N,2*N+1,false)); //오른쪽 테두리.
		//4개의 테두리 넣고 시작.
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		db = new int[M+1][2];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			db[i][0] = Integer.parseInt(st.nextToken());
			char tmp = st.nextToken().charAt(0);
			if(tmp == 'L') db[i][1] = -1;
			else db[i][1] = 1;
		}//end input.
		db[M][0] = Integer.MAX_VALUE; //무한으로 직진.
		System.out.println(simulation(N,N));
	}//end main.
	public static long simulation(long sx, long sy) {
		long time = 0;
		int idx = 0;
		while(true) {
			long min_length = Long.MAX_VALUE;
			switch (direct) {
			case 0:{//방향이 위일경우.
				for(int i=0; i<line.size(); i++) {
					Line tmp_line = line.get(i);
					if(!tmp_line.horizon && tmp_line.start_y == sy){
						if(tmp_line.start_x < sx && tmp_line.end_y < sx) {//세로이면서 sx, sy 가 둘다 현재스타트x보다 작은경우.
							long tmp_length = (sx - tmp_line.start_x) < (sx - tmp_line.end_x) ? (sx - tmp_line.start_x) : (sx - tmp_line.end_x);
							min_length = tmp_length < min_length ? tmp_length : min_length;
						}
					}
					else if(tmp_line.horizon && tmp_line.start_x < sx  && (tmp_line.start_y - sy) * (tmp_line.end_y - sy) <= 0) {//가로이면서 x좌표가 스타트x 보다 작은경우.
						min_length = (sx - tmp_line.start_x) < min_length ? (sx - tmp_line.start_x) : min_length;
					}
				}//end for.
				break;
			}
			case 1:{//방향이 아래일경우.
				for(int i=0; i<line.size(); i++) {
					Line tmp_line = line.get(i);
					if(!tmp_line.horizon && tmp_line.start_y == sy){
						if(tmp_line.start_x > sx && tmp_line.end_x > sx) {//세로이면서 sx, sy 가 둘다 현재스타트x보다 큰경우.
							long tmp_length = (tmp_line.start_x - sx) < (tmp_line.end_x - sx) ? (tmp_line.start_x - sx) : (tmp_line.end_x - sx);
							min_length = tmp_length < min_length ? tmp_length : min_length;
						}
					}
					else if(tmp_line.horizon && tmp_line.start_x > sx && (tmp_line.start_y - sy) * (tmp_line.end_y - sy) <= 0) {
						min_length = (tmp_line.start_x-sx) < min_length ? (tmp_line.start_x-sx) : min_length;
					}
				}//end for.
				break;
			}
			case 2:{//방향이 왼쪽일경우.
				for(int i=0; i<line.size(); i++) {
					Line tmp_line = line.get(i);
					if(tmp_line.horizon && tmp_line.start_x == sx){
						if(tmp_line.start_y < sy && tmp_line.end_y < sy) {//가로이면서 x좌표 같고 스타트엔드 2점이 다 왼쪽.
							long tmp_length = (sy - tmp_line.start_y) < (sy - tmp_line.end_y) ? (sy - tmp_line.start_y) : (sy - tmp_line.end_y);
							min_length = tmp_length < min_length ? tmp_length : min_length;
						}
					}
					else if(!tmp_line.horizon && tmp_line.start_y < sy  && (tmp_line.start_x - sx) * (tmp_line.end_x - sx) <= 0) {//세로이면서 y좌표가 스타트y 보다 작은경우.
						min_length = (sy - tmp_line.start_y) < min_length ? (sy - tmp_line.start_y) : min_length;
					}
				}//end for.
				break;
			}
			case 3:{//방향이 오른쪽일경우.
				for(int i=0; i<line.size(); i++) {
					Line tmp_line = line.get(i);
					if(tmp_line.horizon && tmp_line.start_x == sx){
						if(tmp_line.start_y > sy && tmp_line.end_y > sy) {//가로이면서 x좌표 같고 스타트엔드 2점이 다 오른쪽.
							long tmp_length = (tmp_line.start_y - sy) < (tmp_line.end_y - sy) ? (tmp_line.start_y - sy) : (tmp_line.end_y - sy);
							min_length = tmp_length < min_length ? tmp_length : min_length;
						}
					}
					else if(!tmp_line.horizon && tmp_line.start_y > sy && (tmp_line.start_x - sx) * (tmp_line.end_x - sx) <= 0) {//세로이면서 y좌표가 스타트y 보다 큰경우.
						min_length = (tmp_line.start_y-sy) < min_length ? (tmp_line.start_y-sy) : min_length;
					}
				}//end for.
				break;
			}
			}//end switch.
			if(min_length > db[idx][0]) { //가능한경우.
				long ex = sx + db[idx][0] * direction[direct][0];
				long ey = sy + db[idx][0] * direction[direct][1];
				line.add(new Line(sx,sy,ex,ey,direct>=2));
				sx = ex;
				sy = ey;
				if(db[idx][1] == -1) { //왼쪽으로 꺽는경우.
					if(direct >=2) direct = 1 - (direct%2);
					else direct = 2 + (direct%2);
				}
				else { //오른쪽으로 꺽는경우.
					if(direct >=2) direct = direct%2;
					else direct = 3 - (direct%2);
				}//방향 바꿔주기 끝!.
				time += db[idx][0];
				idx++;
			}
			else {
				time += min_length;
				break;
			}
		}//end while.
		return time;
	}//end simulation.
}//end class.
