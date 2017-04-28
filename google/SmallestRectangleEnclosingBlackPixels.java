public class Solution {
    /**
     * @param image a binary matrix with '0' and '1'
     * @param x, y the location of one of the black pixels
     * @return an integer
     */
    public int minArea(char[][] image, int y, int x) {
        if(image == null || image.length == 0 || image[0].length == 0) return 0;
        int m = image.length, n = image[0].length;
        int left = findLeft(image, 0, x);
        int right = findRight(image, x, n - 1);
        int top = findTop(image, 0, y);
        int bottom = findBottom(image, y, m - 1);
        return (right - left + 1) * (bottom - top + 1);
    }
    private int findLeft(char[][] image, int start, int end) {
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(isEmptyColumn(image, mid)) start = mid;
            else end = mid;
        }
        if(isEmptyColumn(image, start)) return end;
        return start;
    }

    private int findRight(char[][] image, int start, int end) {
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(isEmptyColumn(image, mid)) end = mid;
            else start = mid;
        }
        if(isEmptyColumn(image, end)) return start;
        return end;
    }

    private int findTop(char[][] image, int start, int end) {
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(isEmptyRow(image, mid)) start = mid;
            else end = mid;
        }
        if(isEmptyRow(image, start)) return end;
        return start;
    }

    private int findBottom(char[][] image, int start, int end) {
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(isEmptyRow(image, mid)) end = mid;
            else start = mid;
        }
        if(isEmptyRow(image, end)) return start;
        return end;
    }
    
    private boolean isEmptyColumn(char[][] image, int index) {
        for(int i = 0; i < image.length; i++) {
            if(image[i][index] == '1') return false;
        }
        return true;
    }

    private boolean isEmptyRow(char[][] image, int index) {
        for(int i = 0; i < image[0].length; i++) {
            if(image[index][i] == '1') return false;
        }
        return true;
    }
}