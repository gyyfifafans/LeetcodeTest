#include <iostream>
using namespace std;

//list.a()  . 左边是实体  公有成员
//p->next   ->左边是指针  私有成员

class List{
public:
	List(){create_List();}
	~List(){clear();}

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
	void print();
	//numNode
	int GetListLength(const int &d);
	//detect_circle
	bool DetectCircle();

private:
	struct Node
	{
		int data;
		Node *next;
		//Node(const int &d):data(d),next(NULL){}
		Node(const int &d){
			this->data = d;//->的左边必须是指针，比如this
			this->next = NULL;
		}
		
	};
	Node *head;
	void clear(){
		Node *p = head;
		while(p){
			Node*q = p->next;
			delete p;
			p = q;
		}
	}

	Node *find(const int &d){
		Node *p = head;
		for(;p;p = p->next){
			//p->data?
			if(p->next->data == d)
				break;
		}
		return p;
	}
};


void List::create_List(){
	head = new Node(0);
}
//从头插入
void List::insert(const int &d){
	Node *p = new Node(d);
	p->next = head->next;
	head->next = p;
}

void List::print(){
	for (Node *p = head;p;p = p->next){
		cout<< p->data << endl;
	}
}
//从任意位置插入
void List::insert_pos(const int &d,const int &d1){
    Node *p = find(d);
    Node *q = new Node(d1);
    q->next = p->next;
    p->next = q;
}

void List::erase(const int &d){
	Node *p = find(d);//删除的前一个节点
	Node *q = p->next;//删除的节点
	p->next = p->next->next;
	delete q;
}

void List::updata(const int &d,const int &d1){
	Node *p = find(d);
	//p->next?
	p->next->data = d1;
}
//三个指针前中后，遍历链表反转
void List::reverse(){
/* //solution 1
	Node *p = head->next;
	Node *q = head->next->next;
	Node *m = head->next->next->next;
	p->next = NULL;
	while(m){
		q->next = p;
		p = q;
		q = m;
		m = m->next;
	}
	q->next = p;
	head->next = q;
	*/
	//solution 2
	// Node *p = head;
	// Node *q = head->next;
	// Node *r = head->next->next;
	// p->next = NULL;
	// while(r){
	// 	q->next = p;
	// 	p = q;//指针赋值是让p指向q所指向的空间，指针p后移
	// 	q = r;
	// 	r = r->next;
	// }
	// q->next = p;
	// p = q;
	// head = p;
	

	//头插法
	Node *p = NULL;
	while(head!=NULL){
	    Node *next = head->next;
		head->next = p->next;
		p->next = head;
		head = next;
	}
}

int List::GetListLength(const int &d){
	Node *p = find(d);
	if (head == NULL) return 0;
	int len = 0;
	Node *q = p;
	while(q != NULL){
		len++;
		q = q->next;//指针后移
	}
	return len;
}

//判断是否有环
bool List::DetectCircle(){
	Node *pFast = head->next;
	Node *pSlow = head->next;

	if(!pFast) return false;
	while(pFast->next && pFast->next->next){
		pFast = pFast->next->next;
		pSlow = pSlow->next;
		if(pFast == pSlow){
			return true;
		}
	}
	return false;
}

int main(int argc,const char *argv[]){
	// cout<<"結果是 : "<<(0<<1)<<endl;
	// cout<<(2|0)<<endl;
	// cout<< (4|1)<<endl;
	List list;
	list.insert(30);
	list.insert(30);
	list.insert(20);
	list.insert(10);
	list.insert(10);
	list.print();

	// cout<<"----------"<<endl;
	// list.insert_pos(10,5);
	// list.insert_pos(20,25);
	// list.print();
	// cout << "---------"<<endl;
	// list.erase(10);
	// list.print();
	// cout <<"----------"<<endl;
	// list.reverse();
	// list.print();
	// cout << "----------"<<endl;
	// list.updata(5,8);
	// list.print();
	// cout <<"-----------"<<endl;
	// //for(int i=9;i;i--){
	// //	cout<< i <<endl;
 	// //}
 	// cout << list.GetListLength(8) << endl;


	return 0;

}
