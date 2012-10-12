/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**    
    Given a sorted linked list, delete all duplicates such that each element appear only once.
    
    For example,
    Given 1->1->2, return 1->2.
    Given 1->1->2->3->3, return 1->2->3.
    */ 
    public ListNode deleteDuplicates(ListNode head) {
        ListNode c = head;
        while(c!=null){
            while(c.next!=null && c.val == c.next.val){
                c.next = c.next.next;
            }
            c=c.next;
        }
        return head;
    }

    /**    
    Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
    
    For example,
    Given 1->2->3->3->4->4->5, return 1->2->5.
    Given 1->1->1->2->3, return 2->3.
   */ 
    public ListNode deleteDuplicates(ListNode head) {
        // dummy node makes it easier to remove the head node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        ListNode c = head;
        // flag to indicate if the current node is a duplicate
        boolean dup = false;
        while(c!=null){
            // remove all dups after current
            while(c.next!=null && c.next.val==c.val){
                dup=true;
                c.next=c.next.next;
            }
            // remove current if it contains dup
            if(dup){
                p.next=c.next;
                dup =false;
            }else{
                p=c;
            }
            c=c.next;
        }
        
        return dummy.next;
    }

}