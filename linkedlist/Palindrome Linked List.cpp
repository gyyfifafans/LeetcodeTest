/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    bool isPalindrome(ListNode* head) {
        if (!head || !head->next) return true;
        ListNode *slow = head, *fast = head;
        while (fast->next && fast->next->next) {
            slow = slow->next;
            fast = fast->next->next;
        }
        ListNode *last = slow->next, *pre = head;
        while (last->next) {
            ListNode *tmp = last->next;
            last->next = tmp->next;
            tmp->next = slow->next;
            slow->next = tmp;
        }
        while (slow->next) {
            slow = slow->next;
            if (pre->val != slow->val) return false;
            pre = pre->next;
        }
        return true;
    }
};

/**
用快慢指针找到中点
这道题的 Follow up 让我们用 O(1) 的空间，那就是说我们不能使用 stack 了，那么如何代替 stack 的作用呢，用 stack 的目的是为了利用其后进先出的特点，好倒着取出前半段的元素。
那么现在不用 stack 了，如何倒着取元素呢。我们可以在找到中点后，将后半段的链表翻转一下，这样我们就可以按照回文的顺序比较了，


**/