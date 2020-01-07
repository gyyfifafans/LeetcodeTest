import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


// 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树  

//List<Node> nodelist = new LinkedList<Node>()
//nodelist.add(new Node(array[index])
//for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {  
    // 左孩子  
//    nodeList.get(parentIndex).leftChild = nodeList.get(parentIndex * 2 + 1);  
    // 右孩子  
//    nodeList.get(parentIndex).rightChild = nodeList.get(parentIndex * 2 + 2);   

//关于二叉树的遍历还说一点：
//前序序列获得的是根(第一个节点);
//中序序列获得的是左右子树划分;
//后序序列获得的是根(最后一个节点)。
//前序序列和后序序列只能获得根，不能获得左右子树划分,所以不能唯一确定一颗二叉树。
//(前序+中序)或者(后序+中序)都能唯一确定一颗二叉树。
//可以看到首先就通过先序a确立了根。通过中序确立了a 的左右子树。
class Node{
    private char key;
    private Node left,right;
    public Node(char key){
        this(key,null,null);
    }
    public Node(char key,Node left,Node right){
        this.key = key;
        this.right = right;
        this.left = left;
    }
    
    public char getKey(){
        return key;
    }
    
    public void setKey(char key){
        this.key = key;
    }
    
    public Node getLeft(){
        return left;
    }
    
    public Node getRight(){
        return right;
    }
    
    public void setLeft(Node left){
        this.left = left;
    }
    
    public void setRight(Node right){
        this.right = right;
    }
}


public class BinTree {
    protected Node root;
    
    public BinTree(Node root){
        this.root = root;
    }
    
    public Node getRoot(){
        return root;
    }
    
    //构造树
    public static Node init(){
        Node a = new Node('A');
        Node b = new Node('B', null, a);    
        Node c = new Node('C');    
        Node d = new Node('D', b, c);    
        Node e = new Node('E');    
        Node f = new Node('F', e, null);    
        Node g = new Node('G', null, f);    
        Node h = new Node('H', d, g);
        return h;//root
    }
    //访问节点
    public static void visit(Node p){
        System.out.print(p.getKey()+" ");
    }
    
    //前序遍历
    protected static void preorder(Node p){
        if(p!=null){
            visit(p);
            preorder(p.getLeft());
            preorder(p.getRight());
        }
    }
    /** 递归实现中序遍历 */    
    protected static void inorder(Node p) {    
        if (p != null) {    
            inorder(p.getLeft());    
            visit(p);    
            inorder(p.getRight());    
        }    
    }    
    
    /** 递归实现后序遍历 */    
    protected static void postorder(Node p) {    
        if (p != null) {    
            postorder(p.getLeft());    
            postorder(p.getRight());    
            visit(p);    
        }
    }
    
    
    /**********************************************************************************************/  
    /** 非递归实现前序遍历 */    
    protected static void iterativePreorder(Node p) {    
        Stack<Node> stack = new Stack<Node>();    
        if (p != null) {    
            stack.push(p);    
            while (!stack.empty()) {    
                p = stack.pop();    
                visit(p);    
                if (p.getRight() != null)    
                    stack.push(p.getRight());    
                if (p.getLeft() != null)  //为什么p.getLeft() 在后，getRight()在前应为while 循环第一句就是pop visit所以要把left放上，先访问。之中方法是即压即访问法。  
                    stack.push(p.getLeft());    
            }    
        }    
    }    
      
    /** 非递归实现中序遍历 */  //思路与上面iterativePreorder 一致。  
    protected static void iterativeInorder(Node p) {    
        Stack<Node> stack = new Stack<Node>();    
        while (p != null) {    
            while (p != null) {    
                if (p.getRight() != null)    
                    stack.push(p.getRight());// 当前节点右子入栈    
                    stack.push(p);// 当前节点入栈    
                    p = p.getLeft();    
            }    
            p = stack.pop();    
            while (!stack.empty() && p.getRight() == null) {    
                visit(p);    
                p = stack.pop();    
            }    
            visit(p);    
            if (!stack.empty())    
                p = stack.pop();    
            else    
                p = null;    
        }    
    }  
  
/*******************************************************************************************/  
      
/*******************************************************************************************/    
    /** 非递归实现前序遍历2 */    
    protected static void iterativePreorder2(Node p) {    
        Stack<Node> stack = new Stack<Node>();    
        Node node = p;    
        while (node != null || stack.size() > 0) {    
            while (node != null) {//压入所有的左节点，压入前访问它。左节点压入完后pop访问右节点。像这样算法时思考规律性的东西在哪。不管哪个节点都要压所节点判断右节点。    
                visit(node);    
                stack.push(node);    
                node = node.getLeft();    
            }    
            if (stack.size() > 0) {//    
                node = stack.pop();    
                node = node.getRight();    
            }    
        }    
    }    
      
    /** 非递归实现中序遍历2 */    
    protected static void iterativeInorder2(Node p) {    
        Stack<Node> stack = new Stack<Node>();    
        Node node = p;    
        while (node != null || stack.size() > 0) {    
            while (node != null) {    
                stack.push(node);    
                node = node.getLeft();    
            }    
            if (stack.size() > 0) {    
                node = stack.pop();    
                visit(node);   //与iterativePreorder2比较只有这句话的位置不一样，弹出时再访问。  
                node = node.getRight();    
            }    
        }    
    }  
      
 /*******************************************************************************************/  
    
    /** 非递归实现后序遍历 */    
    protected static void iterativePostorder(Node p) {    
        Node q = p;    
        Stack<Node> stack = new Stack<Node>();    
        while (p != null) {    
            // 左子树入栈    
            for (; p.getLeft() != null; p = p.getLeft())    
                stack.push(p);    
            // 当前节点无右子或右子已经输出    
            while (p != null && (p.getRight() == null || p.getRight() == q)) {    
                visit(p);    
                q = p;// 记录上一个已输出节点    
                if (stack.empty())    
                    return;    
                p = stack.pop();    
            }    
            // 处理右子    
            stack.push(p);    
            p = p.getRight();    
        }    
    }    
    
    /** 非递归实现后序遍历 双栈法 */    
    protected static void iterativePostorder2(Node p) {//理解左子树   右子树 根递归性质，把它运用到循环当中去。    
        Stack<Node> lstack = new Stack<Node>();//左子树栈    
        Stack<Node> rstack = new Stack<Node>();//右子树栈  
        Node node = p, right;    
        do {    
            while (node != null) {    
                right = node.getRight();    
                lstack.push(node);    
                rstack.push(right);    
                node = node.getLeft();    
            }    
            node = lstack.pop();    
            right = rstack.pop();    
            if (right == null) {    
                visit(node);    
            } else {    
                lstack.push(node);    
                rstack.push(null);    
            }    
            node = right;    
        } while (lstack.size() > 0 || rstack.size() > 0);    
    }    
    
    /** 非递归实现后序遍历 单栈法*/    
    protected static void iterativePostorder3(Node p) {    
        Stack<Node> stack = new Stack<Node>();    
        Node node = p, prev = p;    
        while (node != null || stack.size() > 0) {    
            while (node != null) {    
                stack.push(node);    
                node = node.getLeft();    
            }    
            if (stack.size() > 0) {    
                Node temp = stack.peek().getRight();    
                if (temp == null || temp == prev) {    
                    node = stack.pop();    
                    visit(node);    
                    prev = node;    
                    node = null;    
                } else {    
                    node = temp;    
                }    
            }    
    
        }    
    }    
    
    /** 非递归实现后序遍历4 双栈法*/    
    protected static void iterativePostorder4(Node p) {    
        Stack<Node> stack = new Stack<Node>();    
        Stack<Node> temp = new Stack<Node>();    
        Node node = p;    
        while (node != null || stack.size() > 0) {    
            while (node != null) {    
                temp.push(node);    
                stack.push(node);    
                node = node.getRight();    
            }    
            if (stack.size() > 0) {    
                node = stack.pop();    
                node = node.getLeft();    
            }    
        }    
        while (temp.size() > 0) {//把插入序列都插入到了temp。  
            node = temp.pop();    
            visit(node);    
        }    
    }
    
    
    //交换左右子树
    protected  static void reverserLeftAndRight(Node p) {  
        if(p==null){//这句话绝对不能丢。不然有空指针异常，因为后面操作了p.getLeft();递归的话递归结束条件很重要，所以p==null 判断很重要，不要根节点就不会。要考虑所有情况。  
            return;  
        }  
        if(null==p.getLeft()&&null==p.getRight())  
            return;  
        Node temp=p.getLeft();  
        p.setLeft(p.getRight());  
        p.setRight(temp);  
        reverserLeftAndRight(p.getLeft());  
        reverserLeftAndRight(p.getRight());  
    }  
      
    protected  static void reverserLeftAndRight1(Node root) {  
        if(root==null){  
            return;  
        }  
        if(null==root.getLeft()&&null==root.getRight())  
            return;  
        Queue<Node> qu=new LinkedList<Node>();  
        qu.add(root);  
        Node temp;  
        Node q=root;  
        while(!qu.isEmpty()){  
            if(null!=q.getLeft()){//这个必须有。不然把是null 的也加进来了。  
                qu.add(q.getLeft());  
            }  
            if(null!=q.getRight()){  
                qu.add(q.getRight());  
            }  
            temp=q.getLeft();  
            q.setLeft(q.getRight());  
            q.setRight(temp);  
            q=qu.remove();  
        }  
    }

    //2.4 求二叉树中叶子节点的个数

//树中的叶子节点的个数 = 左子树中叶子节点的个数 + 右子树中叶子节点的个数。利用递归代码也是相当的简单，易懂。 
    
protected static int get_leaf_num(Node root){
    if (root==null){
        return 0;
    }
    if (root.getRight()==null && root.getLeft()==null){
        return 1;
    }
    return get_leaf_num(root.getRight())+get_leaf_num(root.getLeft());
}



    //2.5 求二叉树的高度

//求二叉树的高度也是非常简单，不用多说：树的高度 = max(左子树的高度，右子树的高度) + 1 。
protected static int get_tree_height(Node root){
    if (root == null){
        return 0;
    }
    if (root.getRight()==null && root.getLeft()==null){
        return 1;
    }
    int l_right = get_tree_height(root.getRight());
    int l_left = get_tree_height(root.getLeft());
    return l_right>=l_left ? l_right+1:l_left+1;
}




    //2.6 交换二叉树的左右儿子

//交换二叉树的左右儿子，可以先交换根节点的左右儿子节点，然后递归以左右儿子节点为根节点继续进行交换。树中的操作有先天的递归性
    
protected static void swap_left_right(Node root){
    if (root==null){
        return;
    }
    Node temp = root.getLeft();
    root.setLeft(root.getRight());
    root.setRight(temp);
    swap_left_right(root.getLeft());
    swap_left_right(root.getRight());
}


   //2.7 判断一个节点是否在一颗子树中

//可以和当前根节点相等，也可以在左子树或者右子树中

protected boolean is_has(Node root,Node t){
    if (root == null){
        return false;
    }
    else if(root == t){
        return true;
    }
    else{
        boolean has = false;
        if(root.getRight()!=null){
            has = is_has(root.getRight(),t);
        }
        if (root.getLeft()!=null){
            has = is_has(root.getLeft(),t);
        }
        return has;
    }
}

    /*
    2.8 求两个节点的最近公共祖先

求两个节点的公共祖先可以用到上面的：判断一个节点是否在一颗子树中。
（1）如果两个节点同时在根节点的右子树中，则最近公共祖先一定在根节点的右子树中。
（2）如果两个节点同时在根节点的左子树中，则最近公共祖先一定在根节点的左子树中。
（3）如果两个节点一个在根节点的右子树中，一个在根节点的左子树中，则最近公共祖先一定是根节点。
当然，要注意的是：可能一个节点pNode1在以另一个节点pNode2为根的子树中，这时pNode2就是这两个节点的最近公共祖先了。显然这也是一个递归的过程啦
    */


    //2.9 从根节点开始找到所有路径，使得路径上的节点值和为某一数值（路径不一定以叶子节点结束）

//这道题要找到所有的路径，显然是用深度优先搜索（DFS）啦。但是我们发现DFS所用的栈和输出路径所用的栈应该不是一个栈，栈中的数据是相反的。看看代码：注意使用的两个栈



     


     //层序遍历
    protected static void levelRead(Node root){
        if (root == null) return;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while(queue.size()!=0){
            int len = queue.size();
            for(int i=0;i<len;i++){
                Node temp = queue.poll();
                System.out.print(temp.getKey()+" ");
                if (temp.getLeft()!=null) queue.add(temp.getLeft());
                if (temp.getRight()!=null) queue.add(temp.getRight());
            }
        }
    }
    
    
    public static void main(String[] args) {  
        BinTree tree = new BinTree(init());   
        System.out.print("\n 递归遍历");    
        System.out.print("\n Pre-Order:");    
        preorder(tree.getRoot());    
           
        System.out.print("\n In-Order:");    
        inorder(tree.getRoot());  
          
        System.out.print("\n Post-Order:");    
        postorder(tree.getRoot());    
          
        System.out.print("\n 非递归遍历");  
        System.out.print("\n Pre-Order:");    
        iterativePreorder(tree.getRoot());    
          
        System.out.print("\n Pre-Order2:");    
        iterativePreorder2(tree.getRoot());    
           
        System.out.print("\n In-Order:");    
        iterativeInorder(tree.getRoot());  
          
        System.out.print("\n In-Order2:");    
        iterativeInorder2(tree.getRoot());    
          
        System.out.print("\n Post-Order:");    
        iterativePostorder(tree.getRoot());    
         
        System.out.print("\n Post-Order2:");    
        iterativePostorder2(tree.getRoot());    
           
        System.out.print("\n Post-Order3:");    
        iterativePostorder3(tree.getRoot());    
           
        System.out.print("\n Post-Order4:");    
        iterativePostorder4(tree.getRoot());    
        
        System.out.print("\n reverserLeftAndRight:");
        reverserLeftAndRight(tree.getRoot());
        preorder(tree.getRoot());

        System.out.print("\n 层序遍历");
        System.out.print("\n levelRead:");
        levelRead(tree.getRoot());


        System.out.print("\n 二叉树高度");
        System.out.printf("\n get_tree_height:%d",get_tree_height(tree.getRoot()));
    
    }    
        
       

}



