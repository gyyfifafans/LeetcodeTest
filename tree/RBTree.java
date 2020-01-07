class RBTNode<T extends Comparable<T>>{
	boolean color;
	T key;
	RBTNode <T> left;
	RBTNode <T> right;
	RBTNode <T> parent;

	public RBTNode(T key,boolean color,RBTNode <T> parent,RBTNode <T> left,RBTNode <T> right){
		this.key = key;
		this.color = color;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}

	public T getKey(){
		return key;
	}

//	public String toString(){
//		return ""+key+((this.color==RED) ? "R" : "B");
//	}
}



public class RBTree <T extends Comparable<T>>{
	private RBTNode <T> mRoot;
	private static final boolean RED = false;
	private static final boolean BLACK = true;



	public RBTree(){
		mRoot = null;
	}

	public RBTNode <T> getRoot(){
		return mRoot;
	}

	private RBTNode <T> parentOf(RBTNode <T> node){
		return node != null ? node.parent : null;
	}

	private boolean colorOf(RBTNode <T> node){
		return node!=null ?node.color : BLACK;
	}

	private boolean isRed(RBTNode <T> node){
		return ((node != null) && (node.color == RED)) ? true: false; 
	}

	private boolean isBlack(RBTNode <T> node){
		return !isRed(node);
	}

	private void setBlack(RBTNode <T> node){
		if (node !=null)
			node.color = BLACK;
	}



	private void setRed(RBTNode <T> node){
		if (node !=null)
			node.color = RED;
	}

	private void setParent(RBTNode <T> node,RBTNode <T> parent){
		if (node != null)
			node.parent = parent;
	}


	private void setColor(RBTNode <T> node ,boolean color){
		if (node != null)
			node.color = color;
	}



	//前序遍历

	//中序遍历

	//后序遍历
	private void postOrder(RBTNode <T> tree){
		if (tree!=null){
			postOrder(tree.left);
			postOrder(tree.right);
			System.out.print(tree.getKey()+" ");
		}
	}
    
    //查找
	private RBTNode <T> search(RBTNode <T> tree,T key){
		if (tree == null)
			return tree;
		int cmp = key.compareTo(tree.key);
		if(cmp<0)
			return search(tree.left,key);
		else if (cmp>0)
			return search(tree.right,key);
		else 
			return tree;
	}


	//查找最小节点
	private T minium(RBTNode <T> tree){
		if (tree==null){
			return null;
		}
		while(tree.left != null){
			tree = tree.left;
		}
		return tree.key;
	}

	//查找最大节点
	private T maxium(RBTNode <T> tree){
		if (tree==null){
			return null;
		}
		while(tree.right != null){
			tree = tree.right;
		}
		return tree.key;
	}




/* 
 * 对红黑树的节点(x)进行左旋转
 *
 * 左旋示意图(对节点x进行左旋)：
 *      px                              px
 *     /                               /
 *    x                               y                
 *   /  \      --(左旋)-.           / \                #
 *  lx   y                          x  ry     
 *     /   \                       /  \
 *    ly   ry                     lx  ly  
 *
 *
 */
    private void leftRotate(RBTNode <T> x){
    	RBTNode <T> y = x.right;

    	x.right = y.left;
    	if (y.left!=null){
    		y.left.parent = x;
    	}
    	y.parent = x.parent;
    	if (x.parent == null){
    		this.mRoot = y;
    	}
    	else{
    		if(x.parent.left == x)
    			x.parent.left = y;
    		else
    			x.parent.right = y;
    	}
    	y.left = x;
    	x.parent = y;
    }




    /* 
 * 对红黑树的节点(y)进行右旋转
 *
 * 右旋示意图(对节点y进行左旋)：
 *            py                               py
 *           /                                /
 *          y                                x                  
 *         /  \      --(右旋)-.            /  \                     #
 *        x   ry                           lx   y  
 *       / \                                   / \                   #
 *      lx  rx                                rx  ry
 * 
 */
    private void rightRotate(RBTNode <T> y){
    	RBTNode <T> x = y.left;
    	y.left = x.right;
    	if (x.right != null){
    		x.right.parent = y;
    	}
    	x.parent = y.parent;
    	if (y.parent == null){
    		this.mRoot = x;
    	}
    	else{
    		if(y == y.parent.right)
    			y.parent.right = x;
    		else
    			y.parent.left = x;

    	}
    	x.right = y;
    	y.parent = x;
    }


    private void insert(RBTNode <T> node){
    	int cmp = 0;
        RBTNode <T> y = null;
        RBTNode <T> x = this.mRoot;

        while(x != null){
        	y = x;
        	cmp = node.key.compareTo(x.key);
        	if (cmp<0)
        		x = x.left;
        	else
        		x = x.right;
        }
        node.parent = y;
        if (y!=null){
        	cmp = node.key.compareTo(y.key);
        	if (cmp<0)
        		y.left = node;
        	else
        		y.right = node;
        }
        else{
        	this.mRoot = node;
        }

        node.color = RED;

        insertFixUp(node);
    }


    public void insert(T key){
    	RBTNode <T> node=new RBTNode <T>(key,BLACK,null,null,null);
    	if (node != null){
    		insert(node);
    	}
    }


    private void insertFixUp(RBTNode <T> node){
    	RBTNode <T> parent,gparent;
    	while(((parent = parentOf(node))!=null) && isRed(parent)){
    		gparent = parentOf(parent);

    		if (parent == gparent.left){
    			RBTNode <T> uncle = gparent.right;
    			if ((uncle !=null) && isRed(uncle)){
    				setBlack(uncle);
    				setBlack(parent);
    				setRed(gparent);
    				node = gparent;
    				continue;
    			}

    			if (parent.right == node){
    				RBTNode <T> tmp;
    				leftRotate(parent);
    				tmp = parent;
    				parent = node;
    				node = tmp;
    			}


    			setBlack(parent);
    			setRed(gparent);
    			rightRotate(gparent);
    		}
    		else{
    			RBTNode <T> uncle = gparent.left;
    			if ((uncle !=null)&& isRed(uncle)){
    				setBlack(uncle);
    				setBlack(parent);
    				setRed(gparent);
    				node = gparent;
    				continue;
    			}

    			if (parent.left == node){
    				RBTNode <T> tmp;
    				rightRotate(parent);
    				tmp = parent;
    				parent = node;
    				node = tmp;

    			}


    			setBlack(parent);
    			setRed(gparent);
    			leftRotate(gparent);
    		}
    	}
    	setBlack(this.mRoot);
    }



    private void remove(RBTNode <T> node){
    	RBTNode <T> child,parent;
    	boolean color;

    	if ((node.left!=null)&&(node.right!=null)){
    		RBTNode <T> replace = node;

    		replace = replace.right;
    		while(replace.left!=null)
    			replace = replace.left;

    		if (parentOf(node)!=null){
    			if (parentOf(node).left == node)
    				parentOf(node).left = replace;
    			else 
    				parentOf(node).right = replace;
    		}
    		else{
    			this.mRoot = replace;
    		}

    		child = replace.right;
    		parent = parentOf(replace);

    		color = colorOf(replace);

    		if (parent == node){
    			parent = replace;
    		}
    		else{
    			if (child!=null)
    				setParent(child,parent);
    			parent.left = child;

    			replace.right = node.right;
    			setParent(node.right,replace);
    		}


    		replace.parent = node.parent;
    		replace.color = node.color;
    		replace.left = node.left;
    		node.left.parent = replace;

    		if (color == BLACK)
    			removeFixUp(child,parent);

    		node = null;
    		return;
    	}
    	if (node.left!=null){
    		child = node.left;
    	}
    	else{
    		child = node.right;
    	}

    	parent = node.parent;
    	color = node.color;

    	if (child!=null)
    		child.parent = parent;


    	if (parent!=null){
    		if(parent.left == node)
    			parent.left = child;
    		else
    			parent.right = child;
    	}
    	else{
    		this.mRoot = child;
    	}

    	if (color == BLACK)
    		removeFixUp(child,parent);
    	node = null;
    }


    private void removeFixUp(RBTNode <T> node,RBTNode <T> parent){
    	RBTNode <T> other;

    	while((node==null||isBlack(node)) && (node != this.mRoot)){
    		if (parent.left == node){
    			other = parent.right;
    			if (isRed(other)){
    				setBlack(other);
    				setRed(parent);
    				leftRotate(parent);
    				other = parent.right;
    			}

    			if ((other.left==null || isBlack(other.left))&& (other.right==null || isBlack(other.right))){
    				setRed(other);
    				node = parent;
    				parent = parentOf(node);
    			}
    			else{
    				if (other.right==null || isBlack(other.right)){
    					setBlack(other.left);
    					setRed(other);
    					rightRotate(other);
    					other = parent.right;
    				}
    				setColor(other,colorOf(parent));
    				setBlack(parent);
    				setBlack(other.right);
    				leftRotate(parent);
    				node = this.mRoot;
    				break;
    			}
    		}
    		else{
    			other = parent.left;
    			if(isRed(other)){
    				setBlack(other);
    				setRed(parent);
    				rightRotate(parent);
    				other = parent.left;
    			}

    			if ((other.left==null || isBlack(other.left))&& (other.right==null || isBlack(other.right))){
    				setRed(other);
    				node = parent;
    				parent = parentOf(node);
    			}
    			else{
    				if (other.left==null || isBlack(other.left)){
    					setBlack(other.right);
    					setRed(other);
    					leftRotate(other);
    					other = parent.left;
    				}


    				setColor(other,colorOf(parent));
    				setBlack(parent);
    				setBlack(other.left);
    				rightRotate(parent);
    				node = this.mRoot;
    				break;

    			}
    		}
    	}
    	if (node!=null)
    	    setBlack(node);

    }


    public void remove(T key){
    	RBTNode <T> node;
    	if ((node=search(mRoot,key))!=null)
    		remove(node);
    }

    private void destroy(RBTNode <T> tree){
    	if(tree==null)
    		return;
    	if(tree.left!=null)
    		destroy(tree.left);
    	if(tree.right!=null)
    		destroy(tree.right);
    	tree = null;
    }

     /*
      * 打印"红黑树"
      *
      * key        -- 节点的键值 
      * direction  --  0，表示该节点是根节点;
      *               -1，表示该节点是它的父结点的左孩子;
      *                1，表示该节点是它的父结点的右孩子。
      */

    private void print(RBTNode <T> tree,T key,int direction){
    	if (tree!=null){
    		if(direction==0)
    			System.out.printf("%2d(B) is root\n",tree.getKey());
    		else
    			System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, isRed(tree)?"R":"B", key, direction==1?"right" : "left");

    		print(tree.left,tree.getKey(),-1);
    		print(tree.right,tree.getKey(),1);
    	}
    }


    //前驱节点


    //后继节点



    public static void main(String[]args){
    	int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
    	int i,ilen = a.length;
    	RBTree <Integer> tree = new RBTree <Integer>();
    	System.out.println("==原始数据：");
    	for(i=0;i<ilen;i++){
    		//RBTNode <Integer> node = new RBTNode <Integer>(a[i],BLACK,null,null,null);
    		tree.insert(a[i]);
    	}

    	System.out.println("\n==后序遍历");
    	tree.postOrder(tree.getRoot());

    	System.out.println("\n==插入key=1");
    	tree.insert(1);
    	tree.postOrder(tree.getRoot());

    	System.out.println("\n==删除key=1");
    	tree.remove(1);
    	tree.postOrder(tree.getRoot());


    }




}