/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 
 
 
 c++解法
 */
class Solution {
public:
    int getDecimalValue(ListNode* head) {
         int ans=0;
        while(head!=NULL)
        {
            cout<< (ans<<1) << endl;
            ans = (ans<<1)|(head->val);
            head=head->next;
        }
        return ans;
    }
};


/*
理解的話就是，將二進制數一位一位从右向左进入一个盒子
<<  左移 即乘以2  
| 或  左右两边有一个为1 即为1  

都要以二进制形式 | 运算

*/


//python解法

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def getDecimalValue(self, head: ListNode) -> int:
        rst = ''
        while (head.next != None):
            rst += str(head.val)
            head = head.next
        rst += str(head.val)
        return int(rst, 2)
 
 //遍历出字符串，强转int型