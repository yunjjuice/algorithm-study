/*
 * String 길이는 26이하
 */
public class PROG_스킬트리_200422 {
	public static void main(String[] args) {
		String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
		System.out.println(solution("CBD", skill_trees ));
	}
	public static int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for (int i = 0; i < skill_trees.length; i++) {
			if(isCorrect(skill,skill_trees[i]))
				answer++;
		}
        
        return answer;
    }
	private static boolean isCorrect(String skill, String skill_tree) {
		for (int i = 0, flag = 0; i < skill_tree.length(); i++) {
			// 스킬 순서에 없으면 -1 있으면 위치 리턴
			// flag는 스킬순서
			int tmp = skill.indexOf(skill_tree.charAt(i));
			
			if(tmp == -1) continue;
			else if(tmp == flag) flag++;
			else return false;
		}
		return true;
	}
}
