/*
 * BOJ 10875 뱀
 * https://www.acmicpc.net/problem/10875
 */

import java.io.*;
import java.util.*;

public class BOJ_10875_뱀_200702_미완성 {
	static int L, l, N;
	static long ans;
	static int hx, hy, hd; // 뱀 머리 위치
	static ArrayList<Line> list;
	static boolean isDead;
	static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우상좌하
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		L = Integer.parseInt(br.readLine().trim());		
		l = 2 * L + 1;
		
		list = new ArrayList<>();
		// 경계선 만들기
		list.add(new Line(-(L+1), L+1, L+1, L+1)); // 상
		list.add(new Line(-(L+1), -(L+1), L+1, -(L+1))); // 하
		list.add(new Line(-(L+1), -(L+1), -(L+1), L+1)); // 좌
		list.add(new Line(L+1, -(L+1), L+1, L+1)); // 우
		list.add(new Line(0, 0, 0, 0)); // 현재 위치
		hx = 0;
		hy = 0;
		hd = 0;
		
		N = Integer.parseInt(br.readLine().trim());
		isDead = false;
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			
			if(!move(time, dir.equals("L") ? true : false))
				break;
		}
		
		if(!isDead) { // 명령어를 다 해도 안 죽었다면
			Line last = new Line(hx, hy, hx+(l*dir[hd][0]), hy+(l*dir[hd][1]));
			ans += lastpang(last);
		}
		
		System.out.println(ans);
	}
	
	static boolean move(int time, boolean d) { // true반시계, false시계
		switch (hd) {
		case 0: // 우
			list.add(new Line(hx, hy, hx+time, hy));
			break;
		case 1: // 상
			list.add(new Line(hx, hy, hx, hy+time));
			break;
		case 2: // 좌
			list.add(new Line(hx, hy, hx-time, hy));
			break;
		case 3: // 하
			list.add(new Line(hx, hy, hx, hy-time));
			break;
		}
		
		// 겹치지 않으면 시간을 더해주고, 겹친다면 거기까지 가는 시간만큼만 더해준다
		int tmp = check();
		
		if(d) hd = (hd + 1) % 4; // 반시계
		else hd = (hd + 3) % 4;

		if(tmp != -1) {
			ans += tmp;
			isDead = true;
			return false;
		}
		
		ans += time;
		hx = list.get(list.size()-1).ex;
		hy = list.get(list.size()-1).ey;
		return true;
	}
	
	static int check() {
		int size = list.size() - 1;
		Line now = list.get(size); 
		int tmp = -1;
		int sy = Math.min(now.sy, now.ey);
		int sx = Math.min(now.sx, now.ex);
		int ey = Math.max(now.sy, now.ey);
		int ex = Math.max(now.sx, now.ex);
		
		// 맨 마지막거랑 그 전에 들어가 있는 애들이 겹쳐져 있는지 확인
		for (int i = 0; i < size-1; i++) {
			Line com = list.get(i);
			int nsx = Math.min(com.sx, com.ex);
			int nex = Math.max(com.sx, com.ex);
			int nsy = Math.min(com.sy, com.ey);
			int ney = Math.max(com.sy, com.ey);
			
			if(sy == ey) { // 마지막에 넣은 게 가로일 때
				if(nsy == ney) { // 비교할 대상이 가로일 때
					if(sy != nsy) continue;
					if(nex < sx || ex < nsx) continue;
					if(sx<=nex && nex<=ex)
						tmp = Math.abs(Math.abs(ex-sx) - Math.abs(nex-sx));
					else if(sx<=nsx && nsx<=ex)
						tmp = Math.abs(Math.abs(ex-sx) - Math.abs(ex-nsx));
				} else { // 세로
					if(nsy>sy || sy>ney) continue;
					if(nsx<sx || ex<nsx) continue;
					if(hd == 0) // 오른쪽으로 진행중이었을 때
						tmp = Math.abs(Math.abs(ex-sx) - Math.abs(ex-nsx));
					else if(hd == 2) 
						tmp = Math.abs(Math.abs(ex-sx) - Math.abs(sx-nsx));
				}
			} else { // 세로일 때
				if(nsy == ney) { // 비교대상 가로
					if(ey<nsy || nsy<sy) continue;
					if(nex<sx || sx<nsx) continue;
					if(hd == 1) // 위로 진행중이었을 때 
						tmp = Math.abs(Math.abs(ey-sy) - Math.abs(ey-nsy));
					else if(hd == 3) // 아래로 진행중이었을 때
						tmp = Math.abs(Math.abs(ey-sy) - Math.abs(sy-nsy));
				} else { // 세로
					if(sx != nsx) continue;
					if(nsy > ey || sy > ney) continue;
					if(sy<=nsy && nsy<=ey)
						tmp = Math.abs(Math.abs(ey-sy) - Math.abs(nsy-ey));
					if(sy<=ney && ney<=ey)
						tmp = Math.abs(Math.abs(ey-sy) - Math.abs(sy-ney));
				}
			}
		}
		
		return tmp;
	}
	
	static int lastpang(Line last) {
		int sy = Math.min(last.sy, last.ey);
		int sx = Math.min(last.sx, last.ex);
		int ey = Math.max(last.sy, last.ey);
		int ex = Math.max(last.sx, last.ex);
		int tmp = 0;
		
		for (int i = 0; i < list.size(); i++) {
			Line com = list.get(i);
			int nsx = Math.min(com.sx, com.ex);
			int nex = Math.max(com.sx, com.ex);
			int nsy = Math.min(com.sy, com.ey);
			int ney = Math.max(com.sy, com.ey);
			
			if(sy == ey) { // 마지막에 넣은 게 가로일 때
				if(nsy == ney) { // 비교할 대상이 가로일 때
					if(sy != nsy) continue;
					if(nex < sx || ex < nsx) continue;
					if(sx<=nex && nex<=ex)
						tmp = Math.abs(Math.abs(ex-sx) - Math.abs(nex-sx));
					else if(sx<=nsx && nsx<=ex)
						tmp = Math.abs(Math.abs(ex-sx) - Math.abs(ex-nsx));
				} else { // 세로
					if(nsy>sy || sy>ney) continue;
					if(nsx<sx || ex<nsx) continue;
					if(hd == 0) // 오른쪽으로 진행중이었을 때
						tmp = Math.abs(Math.abs(ex-sx) - Math.abs(ex-nsx));
					else if(hd == 2) 
						tmp = Math.abs(Math.abs(ex-sx) - Math.abs(sx-nsx));
				}
			} else { // 세로일 때
				if(nsy == ney) { // 비교대상 가로
					if(ey<nsy || nsy<sy) continue;
					if(nex<sx || sx<nsx) continue;
					if(hd == 1) // 위로 진행중이었을 때 
						tmp = Math.abs(Math.abs(ey-sy) - Math.abs(ey-nsy));
					else if(hd == 3) // 아래로 진행중이었을 때
						tmp = Math.abs(Math.abs(ey-sy) - Math.abs(sy-nsy));
				} else { // 세로
					if(sx != nsx) continue;
					if(nsy > ey || sy > ney) continue;
					if(sy<=nsy && nsy<=ey)
						tmp = Math.abs(Math.abs(ey-sy) - Math.abs(nsy-ey));
					if(sy<=ney && ney<=ey)
						tmp = Math.abs(Math.abs(ey-sy) - Math.abs(sy-ney));
				}
			}
		}
		
		return tmp;
	}
	
	static boolean isRange(int x, int y) {
		return 0<=x && x<l && 0<=y && y<l;
	}
	
	static class Line {
		int sx, sy, ex, ey;

		public Line(int sx, int sy, int ex, int ey) {
			this.sx = sx;
			this.sy = sy;
			this.ex = ex;
			this.ey = ey;
		}
	}
}
