/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
    	if (lists==null||lists.length==0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>(){
        	public int compare(ListNode o1, ListNode o2) {
        		return o1.val > o2.val ? 1 : (o1.val < o2.val ? -1 : 0);
        	}
        });
        for(ListNode node : lists) {
        	if(node != null) {
        		pq.add(node);
        	}
        }
        ListNode head = new ListNode(0), curr = head;
        while(!pq.isEmpty()) {
        	curr.next = pq.poll();
        	curr = curr.next;
        	if(curr.next != null) {
        		pq.add(curr.next);
        	}
        }
        return head.next;
    }
}