/**
 * This class implements a Node for the binary tree. Each node has a left child, a right 
 * child, and a data field
 * @author Christopher Perez Lebron
 *
 * @param <T> this is the data field's type
 */
public class TreeNode<T> {
	private TreeNode<T> leftChild;
	private T data;  
	private TreeNode<T> rightChild; 
	
	/**
	 * constructor creates a new node containing the specified data and 
	 * sets both children to null
	 * @param dataNode the data to be placed in this node
	 */
	public TreeNode(T dataNode) { 
		leftChild = null; 
		data = dataNode;
		rightChild = null;
	}
	
	/**
	 * creates a new TreeNode containing the specified left and right children 
	 * alongside the specified data. This is only used for the copy constructor
	 * @param left the left child to be inserted
	 * @param dataObj the data to be inserted
	 * @param right the right child to be inserted
	 */
	public TreeNode(TreeNode<T> left, T dataObj, TreeNode<T> right) {
		this.leftChild = left;
		this.data = dataObj; 
		this.rightChild = right; 
	}
	
	/**
	 * this is a copy constructor. It utilizes a private recursive method to 
	 * create a copy of the given node. 
	 * @param node the TreeNode object to be copied
	 */
	public TreeNode(TreeNode<T> node) {
		TreeNode<T> copyLeft = recursiveCopy(this.leftChild);
		TreeNode<T> copyRight = recursiveCopy(this.rightChild);
		
		this.leftChild = copyLeft; 
		this.data = node.data; 
		this.rightChild = copyRight;
	}
	
	/**
	 * helps create a recursive copy of the given node. This will taken 
	 * in the left or right child of the root node being copied and 
	 * return a new TreeNode object for that left or right child which 
	 * is then "set up" in the public constructor. 
	 * @param currentNode the currentNode for the recursion 
	 * @return a copy of currentNode [at the end this means a complete
	 *  deep copy of the left or right subtree of the root passed into 
	 *  the copy constructor] 
	 */
	private TreeNode<T> recursiveCopy(TreeNode<T> currentNode) {
		if(currentNode == null) {
			return null; 
		}
		
		TreeNode<T> copyLeft = recursiveCopy(this.leftChild);
		TreeNode<T> copyRight = recursiveCopy(this.rightChild); 
		
		return new TreeNode<T>(copyLeft, currentNode.data, copyRight); 
	}
	
	/**
	 * retrieves this TreeNode object's data
	 * @return data field reference
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * set this nodes left child
	 * @param left the new left child for this node
	 */
	public void setLeftChild(TreeNode<T> left) {
		this.leftChild = left;
	}
	
	/**
	 * retrieve the left child of this ndoe
	 * @return leftChild field
	 */
	public TreeNode<T> getLeftChild() {
		return this.leftChild;
	}
	
	/**
	 * set this node's right child 
	 * @param right the new right child for this node
	 */
	public void setRightChild(TreeNode<T> right) {
		this.rightChild = right;
	}
	
	public TreeNode<T> getRightChild() {
		return this.rightChild;
	}
	
	/**
	 * determine if this node has a left child
	 * @return true if this node has a left child and false
	 * otherwise
	 */
	public boolean hasLeftChild() {
		return leftChild != null; 
	}
	
	/**
	 * determine if this node has a right child
	 * @return true if this node has a right child and false
	 * otherwise 
	 */
	public boolean hasRightChild() {
		return rightChild != null; 
	}
	
}
