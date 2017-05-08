// Easy version: queue + HashSet, O(1) time, O(n) space
public class PhoneDirectory{
	private Queue<Integer> queue;
	private Set<Integer> set;
	int maxNumbers;

	public PhoneDirectory(int maxNumbers){
		queue = new LinkedList<>();
		set = new HashSet<>();
		this.maxNumbers = maxNumbers;

		for(int i = 0; i < maxNumbers; i++)
			queue.offer(i);
	}

	public int get(){
		if(!queue.isEmpty()){
			set.add(queue.peek());
			return queue.poll();
		}

		else return -1;
	}

	public boolean check(int number){
		if(number < 0 || number >= maxNumbers) return false;
		return !set.contains(number);
	}

	public void release(int number){
		if(!set.contains(number)) return;
		else{
			set.remove(number);
			queue.offer(number);
		}
	}

	public static void main(String args[]){
		PhoneDirectory pd = new PhoneDirectory(3);

		assert pd.get() == 0;
		assert pd.get() == 1;
		assert pd.check(2);
		assert pd.get() == 2;
		assert !pd.check(2);
		pd.release(2);
		assert pd.check(2);

		System.out.println("All passed!");
	}
}

//<---------------------------------------------------------------------------------------------------------------------------------------------->
// Bitset: O(n) time, O(1) space
public class PhoneDirectory{
	private BitSet bitSet;
	int maxNumbers;
	int idx;

	public PhoneDirectory(int maxNumbers){
		bitSet = new BitSet(maxNumbers);
		this.maxNumbers = maxNumbers;
		idx = 0;
	}

	public int get(){
		if(idx == maxNumbers) return -1;
		int num = idx;
		bitSet.set(num);
		idx = bitSet.nextClearBit(idx);
		return num;
	}

	public boolean check(int number){
		if(number < 0 || number >= maxNumbers) return false;
		return !bitSet.get(number);
	}

	public void release(int number){
		if(number < 0 || number >= maxNumbers) return;
		if(!bitSet.get(number)) return;

		bitSet.clear(number);
		if(number < idx) idx = number;
	}
}

