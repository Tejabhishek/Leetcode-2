public class Solution {
    public String reverseString(String s) {
        if(s == null || s.length() == 0) return s;
        char[] chars = s.toCharArray();
        int length = s.length();
        int last = length - 1;
        for(int i = 0; i < length / 2; i++) {
        	char c = chars[i];
        	chars[i] = chars[last - i];
        	chars[last - i] = c;
        }
        return new String(chars);
    }
}