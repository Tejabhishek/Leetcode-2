public class Solution {
    public boolean isOneEditDistance(String s, String t) {
    	// try compare from the beginning, once there is a difference, 
    	// we try to replace s(i) with t(j) or insert t(j) to s(i) and see if the rest are the same
    	if(s == null || t == null) return false;
    	int m = s.length(), n = t.length();
        if(m > n) return isOneEditDistance(t, s);
        int i = 0, j = 0;
        while(i < m && j < n) {
        	if(s.charAt(i) != t.charAt(j)) {
        		return s.substring(i + 1).equals(t.substring(j + 1)) || s.substring(i).equals(t.substring(j + 1));
        	}
        	i++; j++;
        }
        return n - j == 1;
    }
}