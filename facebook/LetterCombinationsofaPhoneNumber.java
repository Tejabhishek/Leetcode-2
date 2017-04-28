public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<String>();
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i = 0; i < digits.length(); i++) {
        	int x = Character.getNumericValue(digits.charAt(i));
        	while(ans.peek().length() == i) {
        		String t = ans.remove();
        		for(char s : mapping[x].toCharArray()) ans.add(t + s);
        	}
        }
        return res;
    }
}