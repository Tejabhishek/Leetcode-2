public class Solution {
	public ArrayList<String> letterCombinations(String digits) {
		HashMap<Character, String> map = new HashMap<Character, String>();
		map.put('0', "");
		map.put('1', "");
		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");
		ArrayList<String> res = new ArrayList<String>();
		if(digits == null || digits.length == 0) return res;
		char[] chars = new char[digits.length()];
		helper(res, map, digits, chars, 0);
		return res;
	}
	public void helper(ArrayList<String> res, HashMap<Character, String> map, String digits, char[] chars, int index) {
		if(index == digits.length) {
			// found the answer and put it in the result set;
			res.add(new String(chars));
			return;
		}
		String letters = map.get(digits.charAt(index));
		for(int j = 0; j < letters.length(); j++){
            chars[i] = letters.charAt(j);
            appendDigits(digits, index + 1, cs, res);
        }
	}
}