package algo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PROG_lv3_추석트래픽_200423 {
	public static void main(String[] args) {
		String[] lines = { "2016-09-15 00:00:00.000 2.3s", "2016-09-15 23:59:59.999 0.1s" };
		String[] lines2 = { "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s" };
		System.out.println(solution(lines2));
	}

	static class Log implements Comparable<Log> {
		double start, inter, end;

		Log(double start, double inter, double end) {
			this.start = start;
			this.inter = inter;
			this.end = end;
		}

		public int compareTo(Log o) {
			if (this.start < o.start)
				return -1;
			else if (this.start == o.start) {
				if (this.end < o.end)
					return -1;
				else
					return 1;
			} else
				return 1;
		}
	}

	static Queue<Log> q = new LinkedList<>();
	static Log[] logs;

	public static int solution(String[] lines) {
		int answer = 1;
		logs = new Log[lines.length];
		for (int i = 0; i < lines.length; i++) {
			String[] arr = lines[i].split(" ");
			double inter = Double.parseDouble(arr[2].replace("s", ""));
			double end = Double.parseDouble(arr[1].substring(0, 2)) * 3600
					+ Double.parseDouble(arr[1].substring(3, 5)) * 60 + Double.parseDouble(arr[1].substring(6));
			double start = end - inter + 0.001;

			logs[i] = new Log(start, inter, end);
		}

		for (int i = 0; i < logs.length; i++) {
			int size = q.size();
			for (int j = 0; j < size; j++) {
				Log lg = q.poll();
				if (logs[i].start - 1 < lg.end) {
					q.offer(lg);
				}
			}

			q.offer(logs[i]);
			if (answer < q.size())
				answer = q.size();
		}

		Arrays.sort(logs);

		q.clear();
		for (int i = 0; i < logs.length; i++) {
			int size = q.size();
			for (int j = 0; j < size; j++) {
				Log lg = q.poll();
				if (logs[i].start - 1 < lg.end) {
					q.offer(lg);
				}
			}

			q.offer(logs[i]);
			if (answer < q.size())
				answer = q.size();
		}

		return answer;
	}
}
