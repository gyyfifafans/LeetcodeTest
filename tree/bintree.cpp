#include <iostream>
using namespace std;

class BinTree{
private:
	struct BinTreeNode
	{
		int data;
		BinTreeNode *left,*right;
		BinTreeNode(const int &item,BinTreeNode *lPtr,BinTreeNode *rPtr):data(item),left(lPtr),right(rPtr){}

        void set_data(int item){
        	data = item;
        }

        int get_data(){
        	return data;
        }

        void set_left(BinTreeNode *l){
        	left = l;
        }

        void set_right(BinTreeNode *r){
        	right = r;
        }

        BinTreeNode *get_left(){
        	return left;
        }

        BinTreeNode *get_right(){
        	return right;
        }
		
	};
	BinTreeNode *root;




public:
	BinTree(BinTreeNode *t=NULL ):root(t){}
	~BinTree(){delete root;}

	void set_root(BinTreeNode *t){
		root = t;
	}

	BinTreeNode* get_root(){
		return root;
	}


	BinTreeNode* create_tree(){
		char item;
	    BinTreeNode *t,*t_l,*t_r;
	    cin>>item;
	    if (item!='#'){
		    BinTreeNode *pTmpNode = new BinTreeNode(item-48);
		    t = pTmpNode;
		    t_l = create_tree();
		    t->set_left(t_l);
		    t_r = create_tree();
		    t->set_right(t_r);
		    retrun t;
	    }
	    else{
		    t = NULL;
		    return t;
	    }
	}
	void pre_order(BinTreeNode *r);
	void in_order(BinTreeNode *r);
	void post_order(BinTreeNode *r);
	void level_order(BinTreeNode *r);
	int get_leaf_num(BinTreeNode *r);
	int get_tree_height(BinTreeNode *r);
	void swap_left_right(BinTreeNode *r);
	BinTreeNode* get_nearest_common_father(BinTreeNode *r);
	void print_rout(BinTreeNode *r,int sum);
	bool is_in_tree(BinTreeNode *r,BinTreeNode *t);



};


void BinTree::pre_order(BinTreeNode *r){
	BinTreeNode *pTmpNode = r;
	if(pTmpNode!=NULL){
		cout<<pTmpNode->get_data()<<" ";
		pre_order(pTmpNode->get_left());
		pre_order(pTmpNode->get_right());
	}
}


void BinTree::in_order(BinTreeNode *r){
	BinTreeNode *pTmpNode = r;
	if(pTmpNode!=NULL){
		in_order(pTmpNode->get_left());
		cout<<pTmpNode->get_data()<<" ";
		in_order(pTmpNode->get_right());
	}
}


void BinTree::post_order(BinTreeNode *r){
    BinTreeNode *pTmpNode = r;
    if(pTmpNode!=NULL){
    	post_order(pTmpNode->get_left());
    	post_order(pTmpNode->get_right());
    	cout<<pTmpNode->get_data()<<" ";
    }
}


void BinTree::level_order(BinTreeNode *r){
	if(r==NULL){
		return;
	}
	deque<BinTreeNode*> q;
	q.push_back(r);
	while(!q.empty()){
		BinTreeNode *pTmpNode = q.front();
		cout<<pTmpNode->get_data()<<" ";
		q.pop_front();
		if(pTmpNode->get_left()!=NULL){
			q.push_back(pTmpNode->get_left());
		}
		if(pTmpNode->get_right()!=NULL){
			q.push_back(pTmpNode->get_right());
		}
	}
}


//获取叶子节点的个数
int BinTree::get_leaf_num(BinTreeNode *r)
{
    if(r == NULL)//该节点是空节点，比如建树时候用'#'表示
    {
        return 0;
    }
    if(r->get_left()==NULL && r->get_right()==NULL)//该节点并不是空的，但是没有孩子节点
    {
        return 1;
    }
    //递归整个树的叶子节点个数 = 左子树叶子节点的个数 + 右子树叶子节点的个数
    return get_leaf_num(r->get_left()) + get_leaf_num(r->get_right());
}

//获得二叉树的高度
int BinTree::get_tree_height(BinTreeNode *r)
{
    if(r == NULL)//节点本身为空
    {
        return 0;
    }
    if(r->get_left()==NULL && r->get_right()==NULL)//叶子节点
    {
        return 1;
    }
    int l_height = get_tree_height(r->get_left());
    int r_height = get_tree_height(r->get_right());
    return l_height >= r_height ? l_height + 1 : r_height + 1; 
}

//交换二叉树的左右儿子
void BinTree::swap_left_right(BinTreeNode *r)
{
    if(r == NULL)
    {
        return;
    }
    BinTreeNode *pTmpNode = r->get_left();
    r->set_left(r->get_right());
    r->set_right(pTmpNode);
    swap_left_right(r->get_left());
    swap_left_right(r->get_right());
}

//判断一个节点t是否在以r为根的子树中
bool BinTree::is_in_tree(BinTreeNode *r,BinTreeNode *t)
{
    if(r == NULL)
    {
        return false;
    }
    else if(r == t)
    {
        return true;
    }
    else
    {
        bool has = false;
        if(r->get_left() != NULL)
        {
            has = is_in_tree(r->get_left(),t);
        }
        if(!has && r->get_right()!= NULL)
        {
            has = is_in_tree(r->get_right(),t);
        }
        return has;
    }
}






int main(int argc,const char *argv[]){
    BinTree tree;
    /*--------------------------------------------------------------------------*/
    cout<<"请输入二叉树前序序列进行建树，'#'代表空节点："<<endl;
    tree.set_root(tree.create_tree());
    cout<<endl;
    /*--------------------------------------------------------------------------*/
    cout<<"前序遍历的结果：";
    tree.pre_order(tree.get_root());
    cout<<endl<<endl;
    /*--------------------------------------------------------------------------*/
    cout<<"中序遍历的结果：";
    tree.in_order(tree.get_root());
    cout<<endl<<endl;
    /*--------------------------------------------------------------------------*/
    cout<<"后序遍历的结果：";
    tree.post_order(tree.get_root());
    cout<<endl<<endl;
    /*--------------------------------------------------------------------------*/
    cout<<"层次遍历的结果：";
    tree.level_order(tree.get_root());
    cout<<endl<<endl;
    /*--------------------------------------------------------------------------*/
    cout<<"该二叉树叶子节点的个数：";
    cout<<tree.get_leaf_num(tree.get_root())<<endl<<endl;
    /*--------------------------------------------------------------------------*/
    cout<<"该二叉树的高度是：";
    cout<<tree.get_tree_height(tree.get_root())<<endl<<endl;
    /*--------------------------------------------------------------------------*/
    tree.swap_left_right(tree.get_root());
    cout<<"交换左右子树之后的先序遍历结果为：";
    tree.pre_order(tree.get_root());
    cout<<endl<<endl;
    /*--------------------------------------------------------------------------*/
    /*--------------------------------------------------------------------------*/
    cout<<"路径如下："<<endl;
    tree.print_rout(tree.get_root(),12);
    return 0;
}


