// Difference between 290 and 291:
// In 291, the matching words are not given, so we need to use a character in the pattern to match different length of substrings 
//in the input string with backtracking. We keep trying until we go through the the input string and the pattern.

public class Solution{
	public boolean wordPatternMatch(String pattern, String str){
        Map<Character, String> hMap = new HashMap<>();

        return isMatch(str, 0, pattern, 0, hMap);
    }

    private boolean isMatch(String str, int i, String pattern, int j, Map<Character, String> hMap){
    	if(i == str.length() && j == pattern.length()) return true;
    	if(i == str.length() || j == pattern.length()) return false;

    	char c = pattern.charAt(j);

    	// If the current pattern character has already been matched to a word
    	if(hMap.containsKey(c)){
    		String s = hMap.get(c);

    		// Use this word to match the current substring
    		if(!str.startsWith(s, i)) return false;

    		// Check whether the rest of the string can be matched
    		return isMatch(str, i+s.length(), pattern, j+1, hMap);
    	}

    	// If the current pattern character, use backtracking to try on different substrings
    	else{
    		for(int k = i; k < str.length(); k++){
    			String s = str.substring(i, k+1);

    			if(hMap.containsValue(s)) continue; // We can also keep a hashset here to decrease the rumtime

    			// backtracking
    			hMap.put(c, s);
    			if(isMatch(str, k+1, pattern, j+1, hMap)) return true;
    			hMap.remove(c);
    		}

    		return false;
    	}
    }
}