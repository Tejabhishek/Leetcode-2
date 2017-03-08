public class ZigzagIterator {
	// Two iterators, one for each list. Switching them before reading the next number instead of afterwards
	private Iterator<Integer> i, j, tmp;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i = v2.iterator();
        j = v1.iterator();
    }

    public int next() {
        if(j.hasNext()) {
        	// swap
        	tmp = j;
        	j = i;
        	i = tmp;
        }
        return i.next();
    }

    public boolean hasNext() {
        return i.hasNext() || j.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */