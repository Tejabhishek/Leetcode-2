public class Solution {
    public String licenseKeyFormatting(String S, int K) {
    	for (int i = s.length() - 1; i >= 0; i--)
            if (s.charAt(i) != '-') {
                sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));
            }
        return sb.reverse().toString().toUpperCase();
    }

    // more straightforward solution
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int charLen = 0;
        for(int i = s.length() - 1; i >= 0; i--) {
            if(s.charAt(i) != '-') {
                if(charLen > 0 && charLen % k == 0) {
                    sb.append('-');
                }
                sb.append(s.charAt(i));
                charLen++;
            }
        }
        return sb.reverse().toString().toUpperCase();
    }
}