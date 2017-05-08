public class Solution{
	private static final String[] KEYS = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

	public List<String> letterCombinations(String digits){
		List<String> res = new ArrayList<>();
		if(digits == null || digits.length() == 0) return res;
		backtrack(res, digits, "", 0);
		return res;
	}

	private void backtrack(List<String> res, String digits, String tmp, int pos){
		if(pos == digits.length()){ // We have found a solution
			res.add(new String(tmp));
			return;
		}

		String letters = KEYS[digits.charAt(pos) - '0'];

		for(int i = 0; i < letters.length(); i++){
			backtrack(res, digits, tmp+letters.charAt(i), pos+1);
		}

	}
}