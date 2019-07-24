
//N-ary Tree Preorder Traversal

/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
public:
    vector<int> preorder(Node* root) {
        if(root!=NULL){
            res.push_back(root->val);
            for(auto n : root->children)
                preorder(n);

            }
        return res;
        }
private:
    vector<int> res;
    
};









//N-ary Tree Postorder Traversal

/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
public:
    vector<int> postorder(Node* root) {
        if(root != NULL)
        {
            for(auto n : root->children)
                postorder(n);
            res.push_back(root->val);
        }
        return res;
    }
private:
    vector<int> res;
};
