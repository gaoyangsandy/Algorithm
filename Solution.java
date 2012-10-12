public class Solution {
	/**
	 * Given a sorted linked list, delete all duplicates such that each element
	 * appear only once.
	 * 
	 * For example, Given 1->1->2, return 1->2. Given 1->1->2->3->3, return
	 * 1->2->3.
	 */
	public ListNode deleteDuplicates(ListNode head) {
		ListNode c = head;
		while (c != null) {
			while (c.next != null && c.val == c.next.val) {
				c.next = c.next.next;
			}
			c = c.next;
		}
		return head;
	}

	/**
	 * Given a sorted linked list, delete all nodes that have duplicate numbers,
	 * leaving only distinct numbers from the original list.
	 * 
	 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given
	 * 1->1->1->2->3, return 2->3.
	 */
	public ListNode deleteDuplicates(ListNode head) {
		// dummy node makes it easier to remove the head node
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode p = dummy;
		ListNode c = head;
		// flag to indicate if the current node is a duplicate
		boolean dup = false;
		while (c != null) {
			// remove all dups after current
			while (c.next != null && c.next.val == c.val) {
				dup = true;
				c.next = c.next.next;
			}
			// remove current if it contains dup
			if (dup) {
				p.next = c.next;
				dup = false;
			} else {
				p = c;
			}
			c = c.next;
		}

		return dummy.next;
	}

	/**
	 * Given an array and a value, remove all instances of that value in place
	 * and return the new length. The order of elements can be changed. It
	 * doesn't matter what you leave beyond the new length.
	 */
	public int removeElement(int[] A, int elem) {

		int n = A.length;
		// loop invariant
		// A[0..i] does not contain elem
		// A[j..n-1] have not been examed
		// -1<=i<j<=n and n>=2
		int i = -1;
		for (int j = 0; j < n; j++) {
			if (A[j] != elem) {
				swap(A, ++i, j);
			}
		}
		return i + 1;
	}

	void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	/**
	 * Given a linked list, remove the nth node from the end of list and return
	 * its head.
	 * 
	 * For example,
	 * 
	 * Given linked list: 1->2->3->4->5, and n = 2.
	 * 
	 * After removing the second node from the end, the linked list becomes
	 * 1->2->3->5. Note: Given n will always be valid. Try to do this in one
	 * pass.
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		// fast is n step ahead of slow
		// when fast reaches the end, slow is the node to remove
		ListNode slowPre = null;
		ListNode slow = head;
		ListNode fast = head;
		for (int i = 0; i < n; i++) {
			fast = fast.next;
		}
		while (fast != null) {
			fast = fast.next;
			slowPre = slow;
			slow = slow.next;
		}
		if (slowPre == null) {
			return head.next;
		} else {
			slowPre.next = slow.next;
			return head;
		}
	}

	/**
	 * Reverse digits of an integer.
	 * 
	 * Example1: x = 123, return 321 Example2: x = -123, return -321
	 */
	public int reverse(int x) {
		if (x < 0)
			return -reverse(-x);
		int r = 0;
		while (x != 0) {
			r = r * 10 + x % 10;
			x /= 10;
		}

		return r;
	}

	/**
	 * Given a string S, find the longest palindromic substring in S. You may
	 * assume that the maximum length of S is 1000, and there exists one unique
	 * longest palindromic substring.
	 */
	public String longestPalindrome(String s) {
		String l = "";
		for (int i = 0; i < s.length(); i++) {
			// centered at gap before s[i]
			String t = expand(s, i, i);
			if (t.length() > l.length())
				l = t;

			// centered at s[i]
			t = expand(s, i, i + 1);
			if (t.length() > l.length())
				l = t;
		}
		return l;

	}

	// pre: s[i..j-1] is a palindrome
	// 0<=i<n, i<=j, 0<=j<=n
	// returns the longest palindrome centered at a[i..j-1]
	String expand(String s, int i, int j) {
		while (i - 1 >= 0 && j < s.length() && s.charAt(i - 1) == s.charAt(j)) {
			i--;
			j++;
		}
		return s.substring(i, j);
	}
}
