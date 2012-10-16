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
        if(lists==null) return null;
        ListNode head=null;
        ListNode tail=null;
        while(true){
            int minNextNodeIndex = -1;
            for(int i=0;i<lists.size();i++){
                ListNode n = lists.get(i);
                if(n!=null && 
                (minNextNodeIndex==-1 || n.val<lists.get(minNextNodeIndex).val)){
                    minNextNodeIndex=i;
                }
            }
            if(minNextNodeIndex==-1) break;
            
            ListNode nextNode=lists.get(minNextNodeIndex);

            // first node
            if(tail==null){
                tail = head = nextNode;
            }else{
                tail.next = nextNode;
                tail=nextNode;
            }
         
            lists.set(minNextNodeIndex,nextNode.next);
        }
        return head;
    }
}
