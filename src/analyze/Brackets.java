package analyze;

import java.util.*;

class Brackets {
    public static void main(String[] args) {
        System.out.println(check("()")); // true
        System.out.println(check("({[]})")); // true
        System.out.println(check("([very]{good})")); // true
        System.out.println(check("job")); // true
        System.out.println(check("")); // true
        System.out.println(check(null)); // false
        System.out.println(check("}indeed")); // false
        System.out.println(check("(]")); // false
        System.out.println(check("([)]")); // false
    }

    public static boolean check(String str) {
        if (str == null) return false;
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> brackets = new HashMap<>();
        brackets.put(')', '(');
        brackets.put('}', '{');
        brackets.put(']', '[');

        for (char c : str.toCharArray()) {
            if (brackets.containsValue(c)) {
                stack.push(c);
            } else if (brackets.containsKey(c)) {
                if (!stack.isEmpty() && (stack.peek() == brackets.get(c))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}