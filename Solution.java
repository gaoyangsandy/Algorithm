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

	/*
	 * Write an efficient algorithm that searches for a value in an m x n
	 * matrix. This matrix has the following properties:
	 * 
	 * Integers in each row are sorted from left to right. The first integer of
	 * each row is greater than the last integer of the previous row. For
	 * example,
	 * 
	 * Consider the following matrix:
	 * 
	 * [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] Given target = 3,
	 * return true.
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		int rc = matrix.length;
		if (rc == 0)
			return false;
		int cc = matrix[0].length;
		if (cc == 0)
			return false;
		int l = 0;
		int r = rc * cc - 1;
		while (l <= r) {
			int m = (l + r) / 2;
			if (matrix[m / cc][m % cc] == target) {
				return true;
			} else if (matrix[m / cc][m % cc] > target) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return false;
	}

	/*
	 * Given a sorted array of integers, find the starting and ending position
	 * of a given target value.
	 * 
	 * Your algorithm's runtime complexity must be in the order of O(log n).
	 * 
	 * If the target is not found in the array, return [-1, -1].
	 * 
	 * For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
	 */

	public int[] searchRange(int[] a, int target) {
		int[] result = new int[2];
		result[0] = searchFirst(a, target);
		result[1] = searchLast(a, target);

		return result;
	}

	// pre: a is sorted
	// returns the index of first occurance of target or -1 is doesn't exist
	int searchFirst(int[] a, int target) {
		int i = 0;
		int j = a.length - 1;
		while (i <= j) {
			int m = (i + j) / 2;
			if (a[m] == target) {
				if (m == 0 || a[m - 1] != target) {
					return m;
				} else {
					j = m - 1;
				}
			} else if (a[m] > target) {
				j = m - 1;
			} else {
				i = m + 1;
			}
		}
		return -1;
	}

	// pre: a is sorted
	// returns the index of last occurance of target or -1 is doesn't exist
	int searchLast(int[] a, int target) {
		int i = 0;
		int j = a.length - 1;
		while (i <= j) {
			int m = (i + j) / 2;
			if (a[m] == target) {
				if (m == a.length - 1 || a[m + 1] != target) {
					return m;
				} else {
					i = m + 1;
				}
			} else if (a[m] > target) {
				j = m - 1;
			} else {
				i = m + 1;
			}
		}
		return -1;
	}

	/*
	 * Given an array with n objects colored red, white or blue, sort them so
	 * that objects of the same color are adjacent, with the colors in the order
	 * red, white and blue.
	 * 
	 * Here, we will use the integers 0, 1, and 2 to represent the color red,
	 * white, and blue respectively.
	 * 
	 * Note: You are not suppose to use the library's sort function for this
	 * problem.
	 */
	public void sortColors(int[] A) {
		// invariant
		// A[0..i] =0
		// A[i+1..j] = 1
		// A[j+1..k] = 2
		// A[k..n-1] = unknown
		int i = -1;
		int j = -1;
		for (int k = 0; k < A.length; k++) {
			if (A[k] == 0) {
				A[k] = A[j + 1];
				A[j + 1] = A[i + 1];
				A[i + 1] = 0;
				i++;
				j++;
			} else if (A[k] == 1) {
				A[k] = A[j + 1];
				A[j + 1] = 1;
				j++;
			}
		}
	}

	/*
	 * Given a 2D binary matrix filled with 0's and 1's, find the largest
	 * rectangle containing all ones and return its area.
	 * 
	 * complexity O(rrc) where r is numer of rows and c is number of columns
	 */
	public int maximalRectangle(char[][] matrix) {
		int rc = matrix.length;
		if (rc == 0)
			return 0;
		int cc = matrix[0].length;
		if (cc == 0)
			return 0;

		int[][] p = partialColumnSum(matrix);
		int maxArea = 0;
		for (int rs = 0; rs < rc; rs++) {
			for (int re = rs; re < rc; re++) {
				int area = maximalRectangleBetweenRows(p, rs, re);
				maxArea = Math.max(area, maxArea);
			}
		}
		return maxArea;
	}

	// given a matrix m[0..r-1][0..c-1] where
	// r>=1 and c>= 1 and m[i][j] can be either '0' or '1'
	// returns a matrix p[0..r-1][0..c-1] such that
	// p[i][j] = sum(m[k][j]) where 0<=k<=i for all 0<=i<=r-1 and 0<=j<=c-1
	// by definition,
	// p[i][j] = m[i][j] if i=0
	// p[i-1][j] + m[i][j] if i>0
	// complexity: O(r*c)
	int[][] partialColumnSum(char[][] m) {
		int rc = m.length;
		int cc = m[0].length;
		int[][] p = new int[rc][cc];
		for (int i = 0; i < rc; i++) {
			for (int j = 0; j < cc; j++) {
				if (i == 0) {
					p[i][j] = m[i][j] - '0';
				} else {
					p[i][j] = p[i - 1][j] + (m[i][j] - '0');
				}
			}
		}
		return p;
	}

	// returns the area of the maximal rectangle that
	// starts on rs and ends on re
	// complexity: O(c)
	int maximalRectangleBetweenRows(int[][] p, int rs, int re) {
		int rowSize = re - rs + 1;
		int cc = p[0].length;
		// invariant:
		// l will be the length of the largest rectangle
		// that ends on column i-1
		int l = 0;
		int maxLength = 0;
		for (int i = 0; i < cc; i++) {
			// the sum of column i between rs and re
			int columnSum;
			if (rs == 0) {
				columnSum = p[re][i];
			} else {
				columnSum = p[re][i] - p[rs - 1][i];
			}
			// column i between row rs and re are all 1s
			if (columnSum == rowSize) {
				l = l + 1;
			} else {
				l = 0;
			}
			maxLength = Math.max(l, maxLength);
		}
		return maxLength * rowSize;
	}

	/*
	 * Given n non-negative integers representing the histogram's bar height
	 * where the width of each bar is 1, find the area of largest rectangle in
	 * the histogram.
	 */
	public int largestRectangleArea(int[] height) {
		int n = height.length;
		// invariant
		// l[i] = the number of consecutive rectangles to the left of i whose
		// height
		// is greater or equal than height[i]
		int[] l = new int[n];
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < n; i++) {
			while (!s.empty()) {
				int pi = s.peek();
				if (height[pi] >= height[i]) {
					s.pop();
				} else {
					break;
				}
			}
			if (s.empty()) {
				l[i] = i;
			} else {
				l[i] = i - s.peek() - 1;
			}
			s.add(i);
		}

		// invariant
		// r[i] = the number of consecutive rectangles to the right of i whose
		// height
		// is greater or equal than height[i]
		int[] r = new int[n];
		s.clear();
		for (int i = n - 1; i >= 0; i--) {
			while (!s.empty()) {
				int pi = s.peek();
				if (height[pi] >= height[i]) {
					s.pop();
				} else {
					break;
				}
			}
			if (s.empty()) {
				r[i] = n - 1 - i;
			} else {
				r[i] = s.peek() - 1 - i;
			}
			s.add(i);
		}

		int maxArea = 0;
		for (int i = 0; i < n; i++) {
			int area = height[i] * (l[i] + r[i] + 1);
			maxArea = Math.max(area, maxArea);
		}
		return maxArea;
	}

	/*
	 * Given a 2D binary matrix filled with 0's and 1's, find the largest
	 * rectangle containing all ones and return its area.
	 * 
	 * complexity O(rc) where r is numer of rows and c is number of columns
	 */
	public int maximalRectangle2(char[][] matrix) {
		int rc = matrix.length;
		if (rc == 0)
			return 0;
		int cc = matrix[0].length;
		if (cc == 0)
			return 0;

		// for a matrix m[0..r-1][0..c-1], let
		// h[0..r-1][0..c-1] be the height matrix which is defined as
		// h[i][j] = max{k: for all 0<=k<=i, h[i-k][j] == '1'}
		int[][] h = new int[rc][cc];
		for (int i = 0; i < rc; i++) {
			for (int j = 0; j < cc; j++) {
				if (matrix[i][j] == '0') {
					h[i][j] = 0;
				} else {
					h[i][j] = (i > 0 ? h[i - 1][j] : 0) + 1;
				}
			}
		}

		int maxArea = 0;
		for (int i = 0; i < rc; i++) {
			int area = largestRectangleArea(h[i]);
			maxArea = Math.max(area, maxArea);
		}
		return maxArea;

	}

	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		if (lists == null)
			return null;
		ListNode head = null;
		ListNode tail = null;
		while (true) {
			int minNextNodeIndex = -1;
			for (int i = 0; i < lists.size(); i++) {
				ListNode n = lists.get(i);
				if (n != null
						&& (minNextNodeIndex == -1 || n.val < lists
								.get(minNextNodeIndex).val)) {
					minNextNodeIndex = i;
				}
			}
			if (minNextNodeIndex == -1)
				break;

			ListNode nextNode = lists.get(minNextNodeIndex);

			// first node
			if (tail == null) {
				tail = head = nextNode;
			} else {
				tail.next = nextNode;
				tail = nextNode;
			}

			lists.set(minNextNodeIndex, nextNode.next);
		}
		return head;
	}

	/*
	 * Suppose a sorted array is rotated at some pivot unknown to you
	 * beforehand.
	 * 
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 * 
	 * You are given a target value to search. If found in the array return
	 * true, otherwise return false.
	 */
	public boolean search(int[] A, int target) {
		return search(A, target, 0, A.length - 1);
	}

	boolean search(int[] A, int target, int left, int right) {
		if (left > right)
			return false;

		int mid = (left + right) / 2;
		if (A[mid] == target)
			return true;

		// A[left..mid] are sorted
		if (A[mid] > A[left]) {
			if (target >= A[left] && target < A[mid]) {
				return search(A, target, left, mid - 1);
			} else {
				return search(A, target, mid + 1, right);
			}
		}
		// A[mid..right] are sorted
		else if (A[mid] < A[right]) {
			if (target > A[mid] && target <= A[right]) {
				return search(A, target, mid + 1, right);
			} else {
				return search(A, target, left, mid - 1);
			}
		}
		// not sure which end is sorted
		else {
			return search(A, target, left, mid - 1)
					|| search(A, target, mid + 1, right);
		}
	}

	/*
	 * Given a sorted array and a target value, return the index if the target
	 * is found. If not, return the index where it would be if it were inserted
	 * in order.
	 * 
	 * You may assume no duplicates in the array.
	 * 
	 * Here are few examples. [1,3,5,6], 5 ? 2 [1,3,5,6], 2 ? 1 [1,3,5,6], 7 ? 4
	 * [1,3,5,6], 0 ? 0
	 */
	public int searchInsert(int[] A, int target) {
		return searchInsert(A, target, 0, A.length - 1);
	}

	int searchInsert(int[] A, int target, int left, int right) {
		if (left > right) {
			return left;
		}

		int mid = (left + right) / 2;
		if (A[mid] == target)
			return mid;

		if (A[mid] > target)
			return searchInsert(A, target, left, mid - 1);

		return searchInsert(A, target, mid + 1, right);

	}

	/*
	 * Given a m x n matrix, if an element is 0, set its entire row and column
	 * to 0. Do it in place.
	 */
	public void setZeroes(int[][] matrix) {
		int rc = matrix.length;
		if (rc == 0)
			return;

		int cc = matrix[0].length;
		if (cc == 0)
			return;

		boolean clearRow0 = false;
		boolean clearCol0 = false;
		for (int i = 0; i < rc; i++) {
			if (matrix[i][0] == 0) {
				clearCol0 = true;
				break;
			}
		}
		for (int j = 0; j < cc; j++) {
			if (matrix[0][j] == 0) {
				clearRow0 = true;
				break;
			}
		}
		for (int i = 1; i < rc; i++) {
			for (int j = 1; j < cc; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		for (int i = 1; i < rc; i++) {
			if (matrix[i][0] == 0) {
				for (int j = 1; j < cc; j++) {
					matrix[i][j] = 0;
				}
			}
		}
		for (int j = 1; j < cc; j++) {
			if (matrix[0][j] == 0) {
				for (int i = 1; i < rc; i++) {
					matrix[i][j] = 0;
				}
			}
		}
		if (clearRow0) {
			for (int j = 0; j < cc; j++) {
				matrix[0][j] = 0;
			}
		}
		if (clearCol0) {
			for (int i = 0; i < rc; i++) {
				matrix[i][0] = 0;
			}
		}

	}

	// Given an absolute path for a file (Unix-style), simplify it
	// For example,
	// path = "/home/", => "/home"
	// path = "/a/./b/../../c/", => "/c"
	public String simplifyPath(String path) {
		ArrayList<String> p = new ArrayList<String>();
		for (String s : path.split("/")) {
			if (s.length() == 0 || s.equals("."))
				continue;
			if (s.equals("..")) {
				if (p.size() > 0) {
					p.remove(p.size() - 1);
				}
			} else {
				p.add(s);
			}
		}
		StringBuilder sb = new StringBuilder();
		if (p.size() == 0) {
			sb.append('/');
		} else {
			for (int i = 0; i < p.size(); i++) {
				sb.append('/');
				sb.append(p.get(i));
			}
		}
		return sb.toString();

	}

	// Given an integer n, generate a square matrix filled with elements from 1
	// to n2 in spiral order.

	// For example,
	// Given n = 3,

	// You should return the following matrix:
	// [
	// [ 1, 2, 3 ],
	// [ 8, 9, 4 ],
	// [ 7, 6, 5 ]
	// ]

	public int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		int counter = 1;
		for (int i = n; i >= 1; i -= 2) {
			counter = generateMatrix(matrix, counter, i);
		}
		return matrix;
	}

	int generateMatrix(int[][] matrix, int counter, int length) {
		int n = matrix.length;
		int offset = (n - length) / 2;

		if (length == 1) {
			matrix[offset][offset] = counter++;
			return counter;
		}

		for (int i = 0; i < length - 1; i++) {
			matrix[offset][offset + i] = counter++;
		}
		for (int i = 0; i < length - 1; i++) {
			matrix[offset + i][offset + length - 1] = counter++;
		}
		for (int i = 0; i < length - 1; i++) {
			matrix[offset + length - 1][offset + length - 1 - i] = counter++;
		}
		for (int i = 0; i < length - 1; i++) {
			matrix[offset + length - 1 - i][offset] = counter++;
		}
		return counter;
	}

	/*
	 * Requirements for atoi: The function first discards as many whitespace
	 * characters as necessary until the first non-whitespace character is
	 * found. Then, starting from this character, takes an optional initial plus
	 * or minus sign followed by as many numerical digits as possible, and
	 * interprets them as a numerical value.
	 * 
	 * The string can contain additional characters after those that form the
	 * integral number, which are ignored and have no effect on the behavior of
	 * this function.
	 * 
	 * If the first sequence of non-whitespace characters in str is not a valid
	 * integral number, or if no such sequence exists because either str is
	 * empty or it contains only whitespace characters, no conversion is
	 * performed.
	 * 
	 * If no valid conversion could be performed, a zero value is returned. If
	 * the correct value is out of the range of representable values, INT_MAX
	 * (2147483647) or INT_MIN (-2147483648) is returned.
	 */
	public int atoi(String str) {
		str = cleanup(str);
		if (str.length() == 0)
			return 0;
		int result = 0;
		if (str.charAt(0) != '-') {
			// positive
			for (int i = 0; i < str.length(); i++) {
				int d = str.charAt(i) - '0';

				if ((Integer.MAX_VALUE - d) / 10 < result) {
					// over flowed
					result = Integer.MAX_VALUE;
					break;
				} else {
					result = 10 * result + d;
				}
			}
		} else {
			// negative
			for (int i = 1; i < str.length(); i++) {
				int d = str.charAt(i) - '0';

				if ((Integer.MIN_VALUE + d) / 10 > result) {
					// over flowed
					result = Integer.MIN_VALUE;
					break;
				} else {
					result = 10 * result - d;
				}
			}
		}

		return result;
	}

	String cleanup(String s) {
		StringBuilder sb = new StringBuilder();

		boolean begin = false;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ('0' <= c && c <= '9') {
				sb.append(c);
				begin = true;
			} else if (!begin) {
				if (c == '-') {
					sb.append(c);
					begin = true;
				} else if (c == '+') {
					begin = true;
				} else if (c != ' ') {
					break;
				}
			} else {
				break;
			}
		}
		return sb.toString();
	}

	public int sqrt(int x) {

		if (x == 0)
			return 0;
		if (x == 1)
			return 1;

		int i = 0;
		int j = x;
		// invariant:
		// i< sqrt(x) <j
		while (i + 1 < j) {
			int m = (i + j) / 2;
			if (Integer.MAX_VALUE / m < m) {
				j = m;
			} else {
				int m2 = m * m;
				if (m2 == x)
					return m;
				if (m2 > x) {
					j = m;
				} else {
					i = m;
				}
			}
		}
		return i;

	}

	/*
	 * Given a collection of integers that might contain duplicates, S, return
	 * all possible subsets.
	 * 
	 * Note: Elements in a subset must be in non-descending order. The solution
	 * set must not contain duplicate subsets.
	 */
	public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
		Arrays.sort(num);
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		subsetsWithDup(num, 0, path, result);
		return result;
	}

	void subsetsWithDup(int[] num, int offset, ArrayList<Integer> path,
			ArrayList<ArrayList<Integer>> result) {
		int n = num.length;
		if (offset >= n) {
			result.add(new ArrayList<Integer>(path));
			return;
		}

		int dupCount = 1;
		while (offset + dupCount < n && num[offset + dupCount] == num[offset])
			dupCount++;
		for (int i = 0; i <= dupCount; i++) {
			for (int j = 0; j < i; j++) {
				path.add(num[offset]);
			}
			subsetsWithDup(num, offset + dupCount, path, result);
			for (int j = 0; j < i; j++) {
				path.remove(path.size() - 1);
			}
		}

	}

	/*
	 * Given a binary tree, check whether it is a mirror of itself (ie,
	 * symmetric around its center).
	 * 
	 * For example, this binary tree is symmetric:
	 */
	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		return isMirror(root.left, root.right);

	}

	boolean isMirror(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;

		return root1.val == root2.val && isMirror(root1.left, root2.right)
				&& isMirror(root1.right, root2.left);

	}

	/*
	 * Given n, how many structurally unique BST's (binary search trees) that
	 * store values 1...n?
	 * 
	 * For example, Given n = 3, there are a total of 5 unique BST's.
	 */
	public int numTrees(int n) {
		if (n == 0)
			return 1;
		int total = 0;
		for (int i = 0; i < n; i++) {
			int left = i;
			int right = n - i - 1;
			total += numTrees(left) * numTrees(right);
		}
		return total;
	}

	/*
	 * 
	 * Follow up for "Unique Paths":
	 * 
	 * Now consider if some obstacles are added to the grids. How many unique
	 * paths would there be?
	 * 
	 * An obstacle and empty space is marked as 1 and 0 respectively in the
	 * grid.
	 * 
	 * For example, There is one obstacle in the middle of a 3x3 grid as
	 * illustrated below.
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int rc = obstacleGrid.length;
		if (rc == 0)
			return 0;
		int cc = obstacleGrid[0].length;
		if (cc == 0)
			return 0;

		int[][] count = new int[rc][cc];
		for (int i = 0; i < rc; i++) {
			for (int j = 0; j < cc; j++) {
				if (obstacleGrid[i][j] == 1) {
					count[i][j] = 0;
				} else if (i == 0 && j == 0) {
					count[i][j] = 1;
				} else {
					if (i > 0)
						count[i][j] += count[i - 1][j];
					if (j > 0)
						count[i][j] += count[i][j - 1];
				}
			}
		}
		return count[rc - 1][cc - 1];
	}

	/*
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and
	 * ']', determine if the input string is valid.
	 * 
	 * The brackets must close in the correct order, "()" and "()[]{}" are all
	 * valid but "(]" and "([)]" are not.
	 */
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else {
				if (stack.size() == 0)
					return false;
				char p = stack.pop();
				if (!((c == ')' && p == '(') || (c == '}' && p == '{') || (c == ']' && p == '['))) {
					return false;
				}
			}
		}
		if (stack.size() == 0)
			return true;
		return false;
	}

	/*
	 * 
	 * Given a binary tree, determine if it is a valid binary search tree (BST).
	 * 
	 * Assume a BST is defined as follows:
	 * 
	 * The left subtree of a node contains only nodes with keys less than the
	 * node's key. The right subtree of a node contains only nodes with keys
	 * greater than the node's key. Both the left and right subtrees must also
	 * be binary search trees.
	 */
	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public boolean isValidBST(TreeNode root, int lower, int upper) {
		return root == null || root.val > lower && root.val < upper
				&& isValidBST(root.left, lower, root.val)
				&& isValidBST(root.right, root.val, upper);
	}

	/*
	 * 
	 * Implement wildcard pattern matching with support for '?' and '*'.
	 * 
	 * '?' Matches any single character. '*' Matches any sequence of characters
	 * (including the empty sequence).
	 * 
	 * The matching should cover the entire input string (not partial).
	 */
	public boolean isMatch(String s, String p) {
		if (p.length() == 0) {
			return s.length() == 0 || false;
		}
		char p1 = p.charAt(0);
		if (s.length() == 0) {
			return p1 == '*' && isMatch(s, p.substring(1)) || false;
		}
		char s1 = s.charAt(0);

		if (p1 == '*') {
			return isMatch(s.substring(1), p) || isMatch(s, p.substring(1));
		} else if (p1 == '?') {
			return isMatch(s.substring(1), p.substring(1));
		} else {
			return p1 == s1 && isMatch(s.substring(1), p.substring(1));
		}
	}

	/*
	 * 
	 * Implement wildcard pattern matching with support for '?' and '*'.
	 * 
	 * '?' Matches any single character. '*' Matches any sequence of characters
	 * (including the empty sequence).
	 * 
	 * The matching should cover the entire input string (not partial).
	 */
	public boolean isMatch(String s, String p) {
		// let match[i][j] indicate s[0..i-1] and p[0..j-1] is a match
		// match[i][j] =
		// if p[j-1] == '*' then match[i-1][j] || match[i][j-1]
		// else if p[j-1] == '?' then match[i-1][j-1]
		// else then s[i-1]==p[j-1] && match[i-1][j-1]
		// base case match[0][0] = true
		// match[i][0] = false
		// match[0][j] = if p[j] == '*' then match[0][j-1] else false
		// boolean[][] match = new boolean[s.length()+1][p.length()+1];

		// use pm[j] to simulate match[i-1][j]
		// m[j] to simulate match[i][j]
		boolean[] pm = new boolean[p.length() + 1];
		boolean[] m = new boolean[p.length() + 1];
		for (int i = 0; i <= s.length(); i++) {
			for (int j = 0; j <= p.length(); j++) {
				if (i == 0 && j == 0) {
					m[j] = true;
				} else if (j == 0) {
					m[j] = false;
				} else if (i == 0) {
					m[j] = p.charAt(j - 1) == '*' && m[j - 1] || false;
				} else {
					if (p.charAt(j - 1) == '*') {
						m[j] = m[j - 1] || pm[j];
					} else if (p.charAt(j - 1) == '?') {
						m[j] = pm[j - 1];
					} else {
						m[j] = s.charAt(i - 1) == p.charAt(j - 1) && pm[j - 1];
					}
				}
			}
			boolean[] t = pm;
			pm = m;
			m = t;
		}

		return pm[p.length()];
	}

	/*
	 * Given a binary tree, return the level order traversal of its nodes'
	 * values. (ie, from left to right, level by level).
	 */
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<TreeNode> currentLevel = new ArrayList<TreeNode>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		if (root != null)
			currentLevel.add(root);
		while (currentLevel.size() > 0) {
			ArrayList<TreeNode> nextLevel = new ArrayList<TreeNode>();
			ArrayList<Integer> currentResult = new ArrayList<Integer>();
			for (TreeNode node : currentLevel) {
				currentResult.add(node.val);
				if (node.left != null)
					nextLevel.add(node.left);
				if (node.right != null)
					nextLevel.add(node.right);
			}
			result.add(currentResult);
			currentLevel = nextLevel;
		}

		return result;

	}

	/*
	 * Given a binary tree, return the zigzag level order traversal of its
	 * nodes' values. (ie, from left to right, then right to left for the next
	 * level and alternate between).
	 */
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<TreeNode> currentLevel = new ArrayList<TreeNode>();
		if (root != null)
			currentLevel.add(root);
		boolean leftFirst = true;
		while (currentLevel.size() > 0) {
			ArrayList<TreeNode> nextLevel = new ArrayList<TreeNode>();
			ArrayList<Integer> currentResult = new ArrayList<Integer>();
			for (int i = currentLevel.size() - 1; i >= 0; i--) {
				TreeNode node = currentLevel.get(i);
				currentResult.add(node.val);
				if (leftFirst) {
					if (node.left != null)
						nextLevel.add(node.left);
					if (node.right != null)
						nextLevel.add(node.right);
				} else {
					if (node.right != null)
						nextLevel.add(node.right);
					if (node.left != null)
						nextLevel.add(node.left);
				}
			}
			result.add(currentResult);
			currentLevel = nextLevel;
			leftFirst = !leftFirst;
		}
		return result;
	}

	/*
	 * Given a set of candidate numbers (C) and a target number (T), find all
	 * unique combinations in C where the candidate numbers sums to T.
	 * 
	 * The same repeated number may be chosen from C unlimited number of times.
	 */
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates,
			int target) {
		Arrays.sort(candidates);
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> choice = new ArrayList<Integer>();
		combinationSum(candidates, target, 0, choice, result);
		return result;

	}

	// choice represents the choice has already been made
	// only candiates[offset..n-1] are available to choose
	void combinationSum(int[] candidates, int target, int offset,
			ArrayList<Integer> choice, ArrayList<ArrayList<Integer>> result) {
		if (target < 0) {
			return;
		}
		if (target == 0) {
			ArrayList<Integer> ints = new ArrayList<Integer>();
			for (int i : choice) {
				ints.add(i);
			}
			result.add(ints);
			return;
		}

		// make a choice
		for (int i = offset; i < candidates.length; i++) {
			choice.add(candidates[i]);
			combinationSum(candidates, target - candidates[i], i, choice,
					result);
			choice.remove(choice.size() - 1);
		}
	}

	/*
	 * Given a collection of candidate numbers (C) and a target number (T), find
	 * all unique combinations in C where the candidate numbers sums to T.
	 * 
	 * Each number in C may only be used once in the combination.
	 */
	public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
		Arrays.sort(num);
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> choice = new ArrayList<Integer>();
		combinationSum2(num, target, 0, choice, result);
		return result;

	}

	void combinationSum2(int[] num, int target, int offset,
			ArrayList<Integer> choice, ArrayList<ArrayList<Integer>> result) {
		if (target < 0)
			return;
		if (target == 0) {
			result.add(new ArrayList<Integer>(choice));
			return;
		}

		for (int i = offset; i < num.length; i++) {
			if (i == offset || num[i] != num[i - 1]) {
				choice.add(num[i]);
				combinationSum2(num, target - num[i], i + 1, choice, result);
				choice.remove(choice.size() - 1);
			}
		}
	}

	/*
	 * You are climbing a stair case. It takes n steps to reach to the top.
	 * 
	 * Each time you can either climb 1 or 2 steps. In how many distinct ways
	 * can you climb to the top?
	 */
	public int climbStairs(int n) {
		int s0 = 0;
		int s1 = 1;
		int s = 0;
		for (int i = 0; i < n; i++) {
			s = s0 + s1;
			s0 = s1;
			s1 = s;
		}
		return s;
	}

	/*
	 * Given two integers n and k, return all possible combinations of k numbers
	 * out of 1 ... n.
	 */
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		ArrayList<Integer> choice = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		combine(1, n, k, choice, result);
		return result;
	}

	void combine(int lower, int upper, int k, ArrayList<Integer> choice,
			ArrayList<ArrayList<Integer>> result) {
		if (choice.size() == k) {
			result.add(new ArrayList<Integer>(choice));
			return;
		}

		for (int i = lower; i <= upper; i++) {
			choice.add(i);
			combine(i + 1, upper, k, choice, result);
			choice.remove(choice.size() - 1);
		}

	}

}
