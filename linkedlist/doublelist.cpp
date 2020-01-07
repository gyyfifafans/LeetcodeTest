#include <iostream>
using namespace std;

class doubleList{
public:
	doubleList(){create_List();}
	~doubleList(){clear();}


	//head
	void create_List();
	//insert
	void insert(const int &d);
	//insert_pos
	void insert_pos(const int &d,const int &d1);
	//delete
	void erase(const int &d);
	//update
	void updata(const int &d,const int &d1);
	//reverse
	void reverse();
	//print
	void preprint();
	//print
	void nextprint();
	//numNode
	 int GetListLength(const int &d);


private:
	struct Node
	{
		int val;
		Node *pre,*next;
		Node(const int &d):val(d),pre(NULL),next(NULL){}
		
	};
	
	Node *head;
	void clear(){

	}

	Node *find(const int &d){
		Node *p = head;
		for(;p;p=p->next){
			if(p->val==d) break;
		}
	return p;
	}
};


void doubleList::create_List(){
	head = new Node(0);
}
//不断线插入
void doubleList::insert(const int &d){
	Node *p = new Node(d);
	p->next = head->next;
	p->pre = head;
	head->next = p;
	if(p->next) p->next->pre = p;

}

void doubleList::nextprint(){
	for(Node *p=head;p;p=p->next){
		cout<<p->val<<endl;
	}
}

//?
void doubleList::preprint(){
	for(Node *p=head->pre;p;p=p->pre){
		cout<<p->val<<endl;
	}
}
//
void doubleList::insert_pos(const int &d,const int &d1){
	Node *p = find(d);
	Node *q = new Node(d1);
	q->next = p->next;
	p->next = q;
	q->pre = p;
}

//删除p节点（有的是删除p节点的后继节点）
void doubleList::erase(const int &d){
	Node *p = find(d);
	Node *q = p->next;
	Node *r = p->pre;
	q->pre = p->pre;
	r->next = q;
	delete p;
}

void doubleList::updata(const int &d,const int &d1){
	Node *p = find(d);
	p->val = d1;
}


void doubleList::reverse(){

}

int main(int argc,const char argv[]){
    doubleList list;
    list.insert(30);
    list.insert(20);
    list.insert(10);
    list.insert(5);
    list.nextprint();
    cout<<"------------"<<endl;
    list.insert_pos(5,6);
    list.nextprint();
    cout<<"-------------"<<endl;
    list.erase(20);
    list.nextprint();
    cout<<"-------------"<<endl;
    list.updata(10,1);
    list.nextprint();
    cout<<"--------------"<<endl;
	return 0;
}

