/**
* Time Complexity: O(N) and space complexity: O(1)
* The idea is to associate the original node with its copy node in a single linked list.
* In this way, we don't need extra spance to keep track of the new nodes.
* Algorithm:
* 1. Iterate the original list and duplicate each node. The duplicate of each node follows its original immediately
* 2. Iteraate the new list and assign the random pointer for each duplicated node.
* 3. Restore the original list and extract the duplicated notes.
*/

public RandomListNode copyRandomList(RandomListNode head) {
	RandomListNode iter = head, next;
	// First round: make copy of each node,
	// and link them together side-by-side in a single list
	while(iter != null) {
		next = iter.next;
		RandomListNode copy = new RandomListNode(iter.label);
		iter.next = copy;
		copy.next = next;
		iter = next;
	}
	// Second round: assign random pointers for the copy nodes.
	iter = head;
	while(iter != null) {
		if(iter.random != null) {
			iter.next.random = iter.random.next;
		}
		iter = iter.next.next;
	}
	// Third round: restore the original list, and extract the copy list.
	iter = head;
	RandomListNode dummy = new RandomListNode(0);
	RandomListNode copy, copyIter = dummy;
	while(iter != null) {
		next = iter.next.next;
		// extract the copy
		copy = iter.next;
		copyIter.next = copy;
		copyIter = copy;

		// restore the original list
		iter.next = next;
		iter = next;
	}
	return dummy.next;
}