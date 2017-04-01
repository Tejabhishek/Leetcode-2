/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int readCallOnce(char[] buf, int n) {
        char[] buffer = new char[4];
        int readTotalSize = 0;
        boolean eof = false;
        while(readTotalSize < n && !eof) {
        	int readSize = Math.min(read4(buffer), n - readTotalSize);
        	eof = readSize < 4;
        	System.arraycopy(buffer, 0, buf, readTotalSize, readSize);
        	readTotalSize += readSize;
        }
        return readTotalSize;
    }

    // Use buffer pointer (buffPtr) and buffer counter (buffCnt) to store the data received in previous calls.
    // In the while loop, if buffPtr reaches current buffCnt, it will be set as zero to be ready to read new data.

	private int buffPtr = 0;
	private int buffCnt = 0;
	private char[] buff = new char[4];
    public int readCallMultiple(char[] buf, int n) {
        int ptr = 0;
        while(ptr < n) {
        	if(buffPtr == 0) {
        		buffCnt = read4(buff); // read new characters from file
        	}
        	if(buffCnt == 0) break; // end of file
        	while(ptr < n && buffPtr < buffCnt) {
        		buf[ptr++] = buff[buffPtr++];
        	}
        	if(buffPtr >= buffCnt) buffPtr = 0;
        }
        return ptr;
    }
}