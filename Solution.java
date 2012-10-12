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

	/**
	 * Reverse a linked list from position m to n. Do it in-place and in
	 * one-pass.
	 * 
	 * For example: Given 1->2->3->4->5->NULL, m = 2 and n = 4,
	 * 
	 * return 1->4->3->2->5->NULL.
	 * 
	 * Note: Given m, n satisfy the following condition: 1 <= m <= n <= length
	 * of list.
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode p = null;
		ListNode c = head;
		for (int i = 0; i < m - 1; i++) {
			p = c;
			c = c.next;
		}
		for (int i = 0; i < n - m; i++) {
			ListNode t = c.next;
			c.next = t.next;
			if (p != null) {
				t.next = p.next;
				p.next = t;
			} else {
				t.next = head;
				head = t;
			}
		}
		return head;
	}

	/**
	 * Given a linked list, reverse the nodes of a linked list k at a time and
	 * return its modified list.
	 * 
	 * If the number of nodes is not a multiple of k then left-out nodes in the
	 * end should remain as it is.
	 * 
	 * You may not alter the values in the nodes, only nodes itself may be
	 * changed.
	 * 
	 * Only constant memory is allowed.
	 * 
	 * For example, Given this linked list: 1->2->3->4->5
	 * 
	 * For k = 2, you should return: 2->1->4->3->5
	 * 
	 * For k = 3, you should return: 3->2->1->4->5
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		if (k <= 1)
			return head;
		ListNode p = null;
		ListNode c = head;
		while (countGreaterOrEqual(c, k)) {
			// reverse c up to kth node
			for (int i = 0; i < k - 1; i++) {
				ListNode t = c.next;
				c.next = t.next;
				if (p != null) {
					t.next = p.next;
					p.next = t;
				} else {
					t.next = head;
					head = t;
				}
			}
			p = c;
			c = c.next;
		}
		return head;
	}

	// returns true if the list contains more than k nodes
	boolean countGreaterOrEqual(ListNode head, int k) {
		for (int i = 0; i < k; i++) {
			if (head == null)
				return false;
			head = head.next;
		}
		return true;
	}

	/*
	 * You are given an n x n 2D matrix representing an image.
	 * 
	 * Rotate the image by 90 degrees (clockwise).
	 * 
	 * Follow up: Could you do this in-place?
	 */
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = n; i > 0; i -= 2) {
			rotate(matrix, i);
		}
	}

	// rotate the square with length l.
	// pre: 1<=l<=n
	void rotate(int[][] m, int l) {
		int n = m.length;
		int offset = (n - l) / 2;
		for (int i = 0; i < l - 1; i++) {
			rotate(m, offset, offset + i);
		}
	}

	// m[r][c] => m[c][n-1-r] => m[n-1-r][n-1-c] => m[n-1-c][r]
	// pre: 0<=r<=c<=n-1
	void rotate(int[][] m, int r, int c) {
		int n = m.length;
		int t = m[r][c];
		m[r][c] = m[n - 1 - c][r];
		m[n - 1 - c][r] = m[n - 1 - r][n - 1 - c];
		m[n - 1 - r][n - 1 - c] = m[c][n - 1 - r];
		m[c][n - 1 - r] = t;
	}

	/*
	 * Given a list, rotate the list to the right by k places, where k is
	 * non-negative.
	 * 
	 * For example: Given 1->2->3->4->5->NULL and k = 2, return
	 * 4->5->1->2->3->NULL.
	 */
	public ListNode rotateRight(ListNode head, int k) {
		int n = listSize(head);
		if (n == 0)
			return head;
		k = k % n;
		if (k == 0) {
			return head;
		}
		ListNode p = null;
		ListNode c = head;
		for (int i = 0; i < n - k; i++) {
			p = c;
			c = c.next;
		}
		p.next = null;
		p = c;
		while (c.next != null)
			c = c.next;
		c.next = head;
		return p;
	}

	int listSize(ListNode head) {
		int count = 0;
		while (head != null) {
			count++;
			head = head.next;
		}
		return count;
	}

	/*
	 * Given two binary trees, write a function to check if they are equal or
	 * not.
	 * 
	 * Two binary trees are considered equal if they are structurally identical
	 * and the nodes have the same value.
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p == null || q == null)
			return false;
		return p.val == q.val && isSameTree(p.left, q.left)
				&& isSameTree(p.right, q.right);

	}
}
