/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

        // loop 1. Copy all the nodes
        RandomListNode node = head;
        while(node != null) {
        	map.put(node, new RandomListNode(node.label));
        	node = node.next;
        }

        // loop 2. assign next and random pointers
        node = head;
        while(node != null) {
        	map.get(node).next = map.get(node.next);
        	map.get(node).random = map.get(node.random);
        }
        return map.get(head);
    }
}