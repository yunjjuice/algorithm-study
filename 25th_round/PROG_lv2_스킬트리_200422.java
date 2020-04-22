package algo;

public class PROG_lv2_스킬트리_200422 {
	public static void main(String[] args) {
		String skill = "CBD";
		String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
		System.out.println(solution(skill, skill_trees));
	}

	public static int solution(String skill, String[] skill_trees) {
		int answer = 0;
		for (int i = 0; i < skill_trees.length; i++) {
			if (check(skill, skill_trees[i])) { // 스킬트리조건에 만족하는지 검사
				answer++;
			}
		}
		return answer;
	}

	public static boolean check(String skill, String skill_tree) {
		int now = -1; // 이전 스킬의 index 기억할 변수
		int flag = 0; // 중간에 빠진게 있나 확인할 변수
		for (int i = 0; i < skill.length(); i++) {
			int next = skill_tree.indexOf(skill.substring(i, i + 1)); // 다음 스킬의 index
			if (next == -1) { // 만약에 다음 스킬이 없으면 flag를 통해 빠졌다고 체크
				flag = 1;
				continue;
			}
			
			if (flag == 1) return false; // 빠진 스킬이 있을때 다음 연계 스킬이 나오면 안됨

			if (now < next) { // 다음 스킬이 더 뒤에있다면 스킬 인덱스 갱신
				now = next;
			} else { // 다음 스킬이 더 앞에있으면 안됨
				return false;
			}
		}
		
		return true;
	}
}
