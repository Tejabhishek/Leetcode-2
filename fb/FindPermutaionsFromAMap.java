public class Solution {
	static Map<Character, String> letterMap = new HashMap<Character, String>();
	static {
		letterMap.put('0', "");
		letterMap.put('1', "");
        letterMap.put('2', "abc");
        letterMap.put('3', "def");
        letterMap.put('4', "ghi");
        letterMap.put('5', "jkl");
        letterMap.put('6', "mno");
        letterMap.put('7', "pqrs");
        letterMap.put('8', "tuv");
        letterMap.put('9', "wxyz");
	} 
	public List<String> LetterCombinations(String digits) {
		List<String> arr = new ArrayList<String>();
		if(digits == null || digits.length == 0) return arr;
		char[] cs = new char[digits.length()];
		helper(digits, res, 0, cs);
		return arr;
	}
	public void helper(String digits, List<String> res, int i, char[] cs) {
		if(i == digits.length()) {
			// find combination and return
			res.add(new String(cs));
			return;
		}
		String letters = letterMap.get(digits.charAt(i));
		for(int j = 0; j < letters.length(); j++) {
			cs[i] = letters.charAt(j);
			helper(digits, res, i + 1, cs);
		}
	}
}