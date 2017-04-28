class Coordinate {
	int x, y;
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Solution {
	public int numIslands(boolean[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length) return 0;
		int m = grid.length; 
		int n = gird[0].length;
		int islands = 0;

		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j]) {
					markByBFS(grid, i, j);
					islands++;
				}
			}
		}

		return islands;
	}

	private void markByBFS(boolean[][] grid, int x, int y) {
		// magic numbers
		int[] directionX = {0, 1, -1, 0};
		int[] directionY = {1, 0, 0, -1};

		Queue<Coordinate> queue = new LinkedList<>();
		queue.offer(new Coordinate(x, y));
		while(!queue.isEmpty()) {
			Coordinate coor = queue.poll();
			for(int i = 0; i < 4; i++) {
				Coordinate adj = new Coordinate(
					coor.x + directionX[i];
					coor.y + directionY[i];
				);
				if(!inBound(adj, grid)) continue;
				if(grid[adj.x][adj.y]) {
					grid[adj.x][adj.y] = false;
					queue.offer(adj);
				}
			}
		}
	}

	private boolean inBound(Coordinate coor, boolean grid) {
		int m = grid.length;
		int n = grid[0].length;
		return coor.x >= 0 && coor.x < n && coor.y >= 0 && coor.y < m;
	}
}