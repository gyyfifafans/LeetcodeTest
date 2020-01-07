#include <iostream>
using namespace std;

class circleList{
public:
	circleList(){create_List();}
	~circleList(){clear();}

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


private:
	struct Node
	{
		int val;
		Node *next;
		Node(const int &d):val(d),next(NULL){}

	};
	Node *head;
	void clear(){
		Node *p = head;
		while(p){
			Node *q = p->next;
			delete p;
			p = q;
		}
	}

	Node *find(const int &d){
		for(Node *p = head;p;p=p->next){
			if(p->next->val==d){
				break;
			}
		}
		return p;
	}
};

void circleList::create_List(){
	head = new Node(0);
	head->next = head;
}

void circleList::insert(const int &d){
	Node *p = new Node(d);
	p->next = head->next;
	head->next = p;
}

void circleList::insert_pos(const int &d,const int &d1){
	Node *p = find(d);
	Node *q = new Node(d1);
	q->next = p->next;
	p->next = q;
}

void circleList::print(){
	Node *p = head;
	Node *q = p->next;
	cout<<p->val<<endl;
	for(q;q->next;q = q->next){
		if(q!=head){
			cout<<q->val<<endl;
		}
		else break;
	}
}

//被删掉的是10，代码写的是删掉下一个,和find函数有关
void circleList::erase(const int &d){
	Node *p = find(d);
	Node *q = p->next;
	p->next = q->next;
	delete q;
}

void circleList::updata(const int &d,const int &d1){
	Node *p = find(d);
	p->next->val = d1;//
}


//
void circleList::reverse(){
		Node *p = head;
		Node *q = head->next;
		Node *r = head->next->next;
		while(r!=head){
			q->next = p;
			p = q;
			q = r;
			r = r->next;
		}
		q->next = p;
		p = q;
		head->next = p;//p此时指向tail
		head = p;//反转后head到tail,tail变成head
}


//
int circleList::GetListLength(const int &d){
	Node *p = find(d);
	if (head == NULL or p == head) return 0;
	int len = 0;
	Node *q = p;
	while(q != head){
		len++;
		q = q->next;
	}
	return len;
}

int main (int argc,const char *args[]){
	circleList list;
	list.insert(30);
	list.insert(20);
	list.insert(10);
	/*list.print();
	cout<<"----------"<<endl;
	
	list.insert_pos(10,5);
	list.insert_pos(20,25);
	list.print();
	cout << "---------"<<endl;
	list.erase(20);
	list.print();
	cout <<"----------"<<endl;
	list.reverse();
	list.print();
	cout << "----------"<<endl;
	list.updata(5,8);
	list.print();
	cout <<"-----------"<<endl;
	//for(int i=9;i;i--){
	//	cout<< i <<endl;
 	//}
 	cout << list.GetListLength(8) << endl;*/
 	return  0;
}