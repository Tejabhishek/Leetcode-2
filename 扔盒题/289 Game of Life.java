// Easy version: Build a new board, using extra space
// time O(m*n), space O(m*n)
public class Solution{
	public void gameOfLife(int[][] board){
		if(board == null || board[0].length == 0) return;

		int m = board.length; int n = board[0].length;
		int[][] newBoard = new int[m][n];

		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				int lives = countLives(board, m, n, i, j);

				// under and over population
				if(board[i][j] == 1 && (lives < 2 || lives > 3))
					newBoard[i][j] = 0;
				// Lives on to next generation
				if(board[i][j] == 1 && lives >=2 && lives <=3)
					newBoard[i][j] = 1;
				// Reproduction
				if(board[i][j] == 0 && lives == 3)
					newBoard[i][j] = 1;
			}
		}

		// Update the board
		for(int i = 0; i < m; i++)
			for(int j = 0; j < n; j++)
				board[i][j] = newBoard[i][j];

	}

	// Helper function that counts the numer of lives in neighbour cells
	private int countLives(int[][] board, int m, int n, int i, int j){
		int lives = 0;

		for(int x = Math.max(i-1, 0); x <= Math.min(i+1,m-1); x++)
			for(int y = Math.max(j-1,0); y <= Math.min(j+1,n-1); y++)
				lives += board[x][y];

		lives -= board[i][j];
		return lives;
	}
}


//<---------------------------------------------------------------------------------------------------------------------------------------->
// In place, use two bits to store (next state, current state)
// time O(m*n), space O(m*n)
public class Solution{
	public void gameOfLife(int[][] board){
		if(board == null || board[0].length == 0) return;

		int m = board.length;
		int n = board[0].length;

		// Loop through each cell and set the next state
		// By default the 2nd bit is 0, so we only need to consider when 2nd bit is not zero
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				int lives = countLives(board, m, n, i, j);
				if(board[i][j] == 1 && (lives == 2 || lives == 3)) // (0,1) ---> (1,1)
					board[i][j] = 3;
				if(board[i][j] == 0 && lives == 3) // (0,0) ---> (1,0)
					board[i][j] = 2;
			}
		}

		// Update the board into new state
		for(int i = 0; i < m; i++)
			for(int j = 0; j < n; j++)
				board[i][j] >>= 1;

	}

	// Helper function that counts the lives in the eight neighbour cells
	private int countLives(int[][] board, int m, int n, int i, int j){
		int lives = 0;

		for(int x = Math.max(0, i-1); x <= Math.min(m-1,i+1); x++)
			for(int y = Math.max(0, j-1); y <= Math.min(n-1, j+1); y++)
				lives += board[i][j] & 1; // current state

		lives -= board[i][j] & 1;
		return lives;
	}
}

//<---------------------------------------------------------------------------------------------------------------------------------------->
//Follow up: what if the board size is very large, say 100 million * 100 million? We have to store the board data on local disk.
// Idea: we do not store the whole board. We only store the live cells in a set and other cells are dead by default.
// We read in the board data line by line and compute the new cell state using 3 lines each time. Then throw away the top line and read in a new line.
// I/O API: int[] readLine(), void writeLine(int[] array)
public class Solution{
	public void gameOfLife(){
		int[] prev = null, cur = null, next = null;
		int[] pointer = null;

		while((pointer = readLine()) != null){
			if(cur == null){
				cur = pointer;
				continue;
			}

			if(next == null) next = pointer;

			// First row
			if(prev == null){
				int[][] tmpBoard = new int[2][];
				tmpBoard[0] = cur.clone();
				tmpBoard[1] = next.clone();
				int[][] nextStateBoard = updateBoard(tmpBoard);
				writeLine(nextStateBoard[0]);
			}

			else{
				int[][] tmpBoard = new int[3][];
				tmpBoard[0] = prev.clone();
				tmpBoard[1] = cur.clone();
				tmpBoard[2] = next.clone();
				int[][] nextStateBoard = updateBoard(tmpBoard);
				writeLine(nextStateBoard[1]);
			}

			prev = cur;
			cur = next;
			next = null;
		}

		// Last row
		int[][] tmpBoard = new int[2][];
		tmpBoard[0] = prev.clone();
		tmpBoard[1] = cur.clone();
		int[][] nextStateBoard = updateBoard(tmpBoard);
		writeLine(nextStateBoard[1]);
	}

	public class Coord{
		int x;
		int y;
		public Coord(int x, int y){
			this.x = x;
			this.y = y;
		}

		public boolean equals(Object o){
			return o instanceOf Coord && ((Coord) o).x == x && ((Coord) o).y == y;
		}

		public int hashCode(){
			int hashCode = 1;
			hashCode = hashCode * 31 + x;
			hashCode = hashCode * 31 + y;

			return hashCode;
		}
	}

	// Private helper function that update the board state
	private int[][] updateBoard(int[][] board){
		if(board == null || board.length == 0) return board;

		int m = board.length; int n = board[0].length;

		Set<Coord> live = new hashSet<>();

		// Find the live cell in current state
		for(int i = 0; i < m; i++)
			for(int j = 0; j < n; j++)
				if(board[i][j] == 1) live.add(new Coord(i,j));

		lives = updateLives(lives, m, n);

		// Update board
		for(int i = 0; i < m; i++)
			for(int j = 0; j < n; j++)
				board[i][j] = live.contains(new Coord(i,j)) ? 1:0;
 	}

 	// Private helper that computes the live cells in the next state given the current state
 	private Set<Coord> updateLive(Set<Coord> lives, int m, int n){
 		Map<Coord, Integer> neighbours = new HashMap<>();

 		// Update the number of live neighbours of each cell
 		for(Coord cell : lives){
 			for(int i = Math.max(0, cell.x-1); i <= Math.min(m-1, cell.x+1)){
 				for(int j = Math.max(0, cell.y-1); j <= Math.min(n-1, cell.y+1)){
 					if(i == cell.x && j == cell.y) continue;
 					Coord c = new Coord(i,j);

 					if(neighbours.containsKey(c))
 						neighbours.put(c, neighbours.get(c)+1);
 					else
 						neighbours.put(c,1);
 				}
 			}
 		}

 		// Update live cells
 		Set<Coord> newLives = new HashSet<>();
 		for(Map.Entry<Coord, Integer> entry : neighbours.entrySet()){
 			Coord key = entry.getKey();
 			int value = entry.getValue();

 			if(lives.contains(key) && (value == 2 || value == 3)) 
 				newLives.add(key);
 			if(!lives.contains(key) && value == 3)
 				newLives.add(key);
 		}

 		return newLives;
 	}
}

