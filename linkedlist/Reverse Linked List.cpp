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
    ListNode* reverseList(ListNode* head) {
    //头插法
	ListNode *p = NULL;
	while(head!=NULL){
	    ListNode *next = head->next;
		head->next = p;
		p = head;
		head = next;
    }
        return p;
    }
};