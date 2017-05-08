// Idea 1: Using queue
// Problem: when there are many hits arrving at the same timestamp, queue would take up large amount of memory、
public class HitCounter{
	public static final int TIME_WINDOW = 300;
	private Queue<Integer> q;

	public HitCounter(){
		q = new LinkedList<>();
	}

	public void hit(int timestamp){
		q.offer(timestamp);
	}

	public int getHits(int timestamp){
		while(!q.isEmpty() && timestamp - q.peek() >= TIME_WINDOW)
			q.poll();

		return q.size();
	}
}

// Optimize 1: HashMap
// Use HashMap to record <timestamp, count>, so we do not need to poll to queue every time there is a hit
public class HitCounter{
	public static final int TIME_WINDOW = 300;

	// Store the number of hits each timestamp
	private Map<Integer, Integer> hMap;

	// Store the timestamp of hits in the last 300 secs
	private Queue<Integer> q;

	public HitCounter(){
		hMap = new HashMap<>();
		q = new LinkedList<>();
	}

	public void hit(int timestamp){
		if(hMap.containsKey(timestamp)){
			hMap.put(timestamp, hMap.get(timestamp)+1);
		}
		else{
			hMap.put(timestamp, 1);
			q.offer(timestamp);
		}
	}

	public int getHits(int timestamp){
		while(!q.isEmpty() && timestamp - q.peek() >= 300){
			int time = q.poll();
			hMap.remove(time);
		}

		int hitCounter = 0;
		for(int time : q)
			hitCounter += hMap.get(time);

		return hitCounter;
	}
}

// Optimize 2: Array
// If there are a lot of hits arriving at the same time, use array
public class HitCounter{
	public static final int TIME_WINDOW = 300;
	private int[] times;
	private int[] hits;

	public HitCounter(){
		times = new int[TIME_WINDOW];
		hits = new int[TIME_WINDOW];
	}

	public void hit(int timestamp){
		int idx = timestamp % TIME_WINDOW;
		if(times[idx] == timestamp)	hits[idx]++;
		else{
			times[idx] = timestamp;
			hits[idx] = 1;
		}
	}

	public int getHits(int timestamp){
		int hitCounter = 0;

		for(int i = 0; i < TIME_WINDOW; i++){
			if(timestamp - times[i] < TIME_WINDOW)
				hitCounter += hits[i];
		}

		return hitCounter;
	}

	public static void main(String[] args){
		HitCounter hc = new HitCounter();
		hc.hit(1);
		hc.hit(2);
		hc.hit(3);
		hc.hit(4);
		hc.hit(300);
		int res1 = hc.getHits(300);
		assert res1 == 4;
		int res2 = hc.getHits(301);
		assert res2 == 3;
}


// System time stamp: System.currentTimeMillis()