/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
//高級。。
class Solution {
public:
    int rangeSumBST(TreeNode* root, int L, int R) {
        int leftSum = 0;
        int rightSum = 0;
        if(root == NULL) return 0;
        if (root->val > L) 
        {
            leftSum = rangeSumBST(root->left, L, R);
        }
        if (root->val < R)
        {
            rightSum = rangeSumBST(root->right, L, R);
        }
        if (L <= root->val && root->val <= R){
            return leftSum + rightSum + root->val;
        }
        return leftSum + rightSum;
    }
    }
};

//一般想法
class Solution {
public:    
    int rangeSumBST(TreeNode* root, int L, int R) {
        if(root == NULL) return 0;
        if (root->val < L) 
        {
            return  rangeSumBST(root->right, L, R);
        }
        else if (root->val > R)
        {
            return  rangeSumBST(root->left, L, R);
        }
        else {
            return rangeSumBST(root->left, L, R) + rangeSumBST(root->right, L, R) + root->val;
        }
    }
};
/*

1. 若它的左子树不为空，那么左子树上所有节点的key都小于根节点的key。

2. 若它的右子树不为空，那么右子树上所有节点的key都大于根节点的key。

3. 它的左右子树也分别为二叉排序树。
*/
