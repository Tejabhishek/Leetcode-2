public class Solution {
    public int[] plusOne(int[] digits) {
        int extra = 0;
        int len = digits.length - 1;
        digits[len] += 1;
        while(len >= 0) {
        	digits[len] = digits[len + extra];
        	if(digits[len] > 10) {
        		digits[len] -= 10;
        		extra = 1;
        	} else {
        		extra = 0;
        	}
        	len--;
        }
        if(extra == 1) {
        	int[] res = new int[len + 1];
        	res[0] = 1;
            for(int i = 0; i < digits.length; i++){
                res[i + 1] = digits[i];
            }
            return res;
        }
        return digits;
    }
}