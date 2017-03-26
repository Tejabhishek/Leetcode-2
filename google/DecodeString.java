public class Solution {
    public String decodeString(String s) {
    	Stack<Integer> count = new Stack<Integer>();
        Stack<String> result = new Stack<Integer>();
        int i = 0;
        result.push("");
        while(i++ < s.length()) {
            char ch = s.charAt(i);
            if(ch >= '0' && ch <= '9') {
                // current character is a number
                int start = i;
                while(s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') i++;
                count.push(s.substring(start, i + 1));
            } else if(ch == '[') {
                result.push("");
            } else if(ch == ']') {
                String str = result.pop();
                StringBuilder sb = new StringBuilder();
                int times = count.pop();
                while(times-- > 0) sb.append(str);
                result.push(result.pop() + sb.toString());
            } else {
                // is a letter
                result.push(result.pop() + ch);
            }
        }
        return result.pop();
    }
}