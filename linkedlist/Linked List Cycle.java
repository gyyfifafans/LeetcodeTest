/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head;
        ListNode low = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            low = low.next;

            if (low.equals(fast)) {
                return true;
            }
        }
        return false;
    }
}

/**

快慢指针应用
while的条件有点把握不好
**/