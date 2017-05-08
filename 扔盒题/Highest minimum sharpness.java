// Given a 2-d array of "sharpness" values. Find a path from the leftmost column to the rightmost column which has the highest minimum sharpness.
// Output the highest minimum sharpness. Each move can only move to the top right, right or bottom right grid.
// Example: 3*3 matrix
// 5 7 2
// 7 5 8
// 9 1 5
// The path with highest minimum sharpness is 7-->7-->8, because 7 is the highest minimum value in all the paths.
// Idea: Use DP dp[r][c] = min(max(dp[r-1][c-1], dp[r][c-1], dp[r+1][c-1]), grid[r][c])

import java.util.*;

public class Solution{
	public int highestMinVal(int[][] grid){
		if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;

		int m = grid.length, n = grid[0].length;
		int[][] dp = new int[m][n]; // dp stores the highest min value of the path at current position

		for(int r = 0; r < m; r++)
			// fill the col 0 of dp
			dp[r][0] = grid[r][0];

		// Iterate from col 1, fill dp
		for(int c = 1; c < n; c++){
			for(int r = 0; r < m; r++){
				int max = 0;
				for(int i = Math.max(0, r-1); i <= Math.min(m-1, r+1); i++){
					max = Math.max(max, dp[i][c-1]);
				}

				// update the highest min value on current position
				dp[r][c] = Math.min(max, grid[r][c]);
			}
		}

		// Find the highest min value of every path
		int res = 0;
		for(int r = 0; r < m; r++)
			res = Math.max(res, dp[r][n-1]);

		return res;
	}

	public static void main(String[] args){
		Solution so = new Solution();

		int[][] grid = new int[][]{
			{5,7,2},
			{7,5,8},
			{9,1,5}
		};

		assert so.highestMinVal(grid) == 7;
		System.out.println("Success!");
	}
}

// Follow up: 
//如果图片是1million*1million的怎么办，整张图片读不进内存，我答说dp结构可以改成一维的，然后每次只读一列。小哥说每次读一列的第一个字符非常耗时，因为要不断的跳读指针，
//然后我说可以对这个矩阵转置写到另外一个文件里，然后每次想做这个函数就对这个新文件操作就好了，这样就能按行读。小哥就问我怎么转置再存到另外一个文件里，我说根据内存大小可以多读几列。
//然后小哥就说还可以再优化，他说这有一个balance，读行输出列，写文件就很耗时，读列输出行，读文件就很耗时（主要问题是 写指针或读指针跳转到下一行 所带来的时间消耗），
//结果是每次根据内存大小读一个接近正方形的矩形，将他们写到新文件，再读下一块矩形。这样的话，读写指针跳转次数就最小了。









