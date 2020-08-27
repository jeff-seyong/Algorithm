package programmers.level2;

import java.util.Stack;

public class P2005_괄호변환 {

    static int position;

    static public String solution(String p) {
        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        if (p.isEmpty()) return "";

        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
        // 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
        boolean correct = isCorrect(p);
        String u = p.substring(0, position);
        String v = p.substring(position, p.length());

        // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
        if (correct)
            // 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
            return u + solution(v);

        // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
        // 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
        // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
        // 4-3. ')'를 다시 붙입니다.
        String answer = "(" + solution(v) + ")";

        // 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
        for (int i = 1; i < u.length() - 1; i++) {
            if (u.charAt(i)=='('){
                answer += ')';
            }
            else{
                answer += '(';
            }
        }
        // 4-5. 생성된 문자열을 반환합니다.
        return answer;
    }

    private static boolean isCorrect(String p) {
        int open = 0, close = 0;
        boolean ret = true;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                open++;
                stack.push('(');
            } else {
                close++;
                if (stack.isEmpty())
                    ret = false;
                else
                    stack.pop();
            }

            if (open == close) {
                position = i + 1;
                return ret;
            }
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(solution("(()())()"));
        System.out.println(solution(")("));
        System.out.println(solution("()))((()"));
    }
}