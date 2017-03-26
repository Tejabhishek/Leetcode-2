public class Solution {
    public int numDecodings(String s) {
    	if(s == null || s.length() == 0) {
            return 0;
        }
    	int n = s.length();
        int[] ans = new int[n + 1];
        ans[0] = 1; // empty string
        ans[1] = s.charAt(0) != '0' ? 1: 0;
        for(int i = 2; i <= n; i++) {
        	int oneDigit = Integer.valueOf(s.substring(i - 1, i));
        	char twoDigits = Integer.valueOf(s.substring(i - 2, i));
        	if(oneDigit != 0 ) {
        		ans[i] += ans[i - 1];
        	}
        	if(twoDigits >= 10 && twoDigits <= 26) {
        		ans[i] += ans[i - 2];
        	}
        }
        return ans[n];
    }
}