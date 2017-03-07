public class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
    	// {"a", "bcd", "e"} to "a bcd e "; which is a whole sentence length plus a space length.
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for(int i = 0; i < rows; i++) {
        	start += cols;
        	if(s.charAt(start % l) == ' ') {
        		// fit in one row without adding any spaces
        		start++; // start a new line
        	} else {
        		// remove the extra characters from next word
        		while(start > 0 && s.charAt((start - l) % l) != ' ') {
        			start--;
        		}
        	}
        }
        return start / s.length();
    }
    // O (m + n), m: length of sentence by char, n: rows
    public int wordsTypingwithMap(String[] sentence, int rows, int cols) {
    	String s = String.join(" ", sentence) + " ";
    	int len = s.length(), count = 0;
    	int[] map = new int[len];
    	for(int i = 1; i < len; ++i) {
    		map[i] = s.charAt(i) == ' ' ? 1 : map[i - 1] - 1;
    	}
    	for(int i = 0; i < rows; ++i) {
    		count += cols;
    		count += map[count % len];
    	}
    	return count / len;
    }
}