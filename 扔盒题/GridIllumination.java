import java.util.*;

public class GridIllumination{
	private Map<Integer, Integer> rowMap = new HashMap<>();
	private Map<Integer, Integer> colMap = new HashMap<>();
	private Map<Integer, Integer> leftDiagMap = new HashMap<>();
	private Map<Integer, Integer> rightDiagMap = new HashMap<>();
	private Set<List<Integer>> lampSet = new HashSet<>();

	public List<String> checkIllumination(int n, List<List<Integer>> lamps, List<List<Integer>> queries){
		List<String> res = new ArrayList<>();

		// Store the lamp coordinates into HashSet and update the lamp count in each coordinate
		for(List<Integer> lamp : lamps){
			lampSet.add(lamp);
			int r = lamp.get(0);
			int c = lamp.get(1);

			// Update the row lamp count
			if(!rowMap.containsKey(r)) rowMap.put(r,1);
			else rowMap.put(r,rowMap.get(r)+1);

			// Update the col lamp count
			if(!colMap.containsKey(c)) colMap.put(c,1);
			else colMap.put(c,colMap.get(c)+1);

			// Update the left diagnoal lamp count
			if(!leftDiagMap.containsKey(r-c)) leftDiagMap.put(r-c,1);
			else leftDiagMap.put(r-c,leftDiagMap.get(r-c)+1);

			// Update the right diagonal lamp count
			if(!rightDiagMap.containsKey(r+c)) rightDiagMap.put(r+c,1);
			else rightDiagMap.put(r+c,rightDiagMap.get(r+c)+1);
		}

		// Iterate each query and update the lamp count in each map (turn off the lamps)
		for(List<Integer> query : queries){
			int r = query.get(0);
			int c = query.get(1);

			// Iterate through the query itself and its neighbours to turn off the lamps
			for(int i = r-1; i <= r+1; i++){
				for(int j = c-1; j <= c+1; j++){
					if(i >=1 && i <= n && j >= 1 && j <= n){
						if(lampSet.contains(Arrays.asList(i,j))){
							rowMap.put(i,rowMap.get(i)-1);
							colMap.put(j,colMap.get(j)-1);
							leftDiagMap.put(i-j,leftDiagMap.get(i-j)-1);
							rightDiagMap.put(i+j,rightDiagMap.get(i+j)-1);
						}
					}
				}
			}

			// Check whether the grid cell is light or dark after turning off all affected lights
			if((rowMap.containsKey(r) && rowMap.get(r) != 0) || 
				colMap.containsKey(c) && colMap.get(c) != 0 ||
				leftDiagMap.containsKey(r-c) && leftDiagMap.get(r-c) != 0 ||
				rightDiagMap.containsKey(r+c) && rightDiagMap.get(r+c) != 0)
				res.add("LIGHT");
			else
				res.add("DARK");


			// Turn on all the lights after examine the cell
			for(int i = r-1; i <= r+1; i++){
				for(int j = c-1; j <= c+1; j++){
					if(i >=1 && i <= n && j >= 1 && j <= n){
						if(lampSet.contains(Arrays.asList(i,j))){
							rowMap.put(i,rowMap.get(i)+1);
							colMap.put(j,colMap.get(j)+1);
							leftDiagMap.put(i-j,leftDiagMap.get(i-j)+1);
							rightDiagMap.put(i+j,rightDiagMap.get(i+j)+1);
						}
					}
				}
			}
		}
		return res;
	}

	public static void main(String[] args){
		int n = 8;
		List<List<Integer>> lamps = new ArrayList<>();
		lamps.add(Arrays.asList(1,6));
		lamps.add(Arrays.asList(5,6));
		lamps.add(Arrays.asList(7,3));
		lamps.add(Arrays.asList(3,2));

		List<List<Integer>> queries = new ArrayList<>();
		queries.add(Arrays.asList(4,4));
		queries.add(Arrays.asList(6,6));
		queries.add(Arrays.asList(8,1));
		queries.add(Arrays.asList(3,2));
		queries.add(Arrays.asList(2,3));

		GridIllumination gd = new GridIllumination();
		List<String> illumination = gd.checkIllumination(n,lamps,queries);
		System.out.print(illumination.toString());
	}
}