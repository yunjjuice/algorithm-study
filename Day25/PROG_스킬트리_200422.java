/*
 * programmers lv2 스킬트리
 */

public class PROG_스킬트리_200422 {
	public static void main(String[] args) {
		System.out.println(solution("CBD", new String[] {"BACDE", "CBADF", "AECB", "BDA"}));
	}

	public static int solution(String skill, String[] skill_trees) {
		int answer = 0;
		
		for (int i = 0; i < skill_trees.length; i++) {
			int index = 0; // 스킬 인덱스
			boolean flag = true;
			for (int j = 0; j < skill_trees[i].length(); j++) {
				String tmp = skill_trees[i].charAt(j) + "";
				if(skill.contains(tmp)) {
//					System.out.println("i : " + i + " j : " + j + " => " + tmp.charAt(0) + " " + skill.indexOf(tmp.charAt(0)));
					if(index != skill.indexOf(tmp)) { // skill의 index순서대로 값이 나와야하는데 그거랑 다를 경우 스킬트리 안 지킨 것
						flag = false;
						break;
					} else {
						index++;
					}
				}
			}
			if(flag) answer++;
		}
		
		return answer;
	}
}
