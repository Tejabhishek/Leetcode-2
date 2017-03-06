public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        // two pointers, i and j, i is the beginning of the window and j is the end.
        // num is the number of distinct characters.
        int i = 0, num = 0, res = 0;
        for(int j = 0; j < s.length(); j++) {
        	if(count[s.charAt(j)]++ == 0) { 
        		// met a distinct character
        		num++;
        	}
        	while(num > k) {
        		// sliding window
        		count[s.charAt(i)]--;
        		if(count[s.charAt(i)] == 0) {
        			num--;
        		}
        		i++;
        	}
        	res = Math.max(res, j - i + 1);
        }
        return res;
    }
}