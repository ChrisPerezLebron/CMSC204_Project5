import java.util.ArrayList;

/**
 * this class implements the actual binary tree for our morse code converter. The 
 * tree created by this class will, essentially, serve as a modified binary search 
 * tree for our morse code values. That is, a bad alternative to using a dictionary lol. 
 * 
 * NOTE: many of these methods are public although I do not think they should be. I am 
 * simply following the Java Docs and JUnit tests provided. 
 * @author Christopher Perez Lebron
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String>{

	TreeNode<String> root; 
	
	/**
	 * this sets up the tree. It will call buildTree to begin the setup process. There 
	 * is only one "version" that can be instantiated thus this is the only provided 
	 * constructor
	 */
	public MorseCodeTree() {
		root = new TreeNode<String>(""); 
		buildTree(); 
	}
	
	/**
	 * begins building the tree by calling insert a ridiculous amount of times. The 
	 * letters are inserted into the tree by level order to ensure no null pointer 
	 * exceptions occur
	 */
	public void buildTree() {
		//level 2
		insert(".", "e");
		insert("-", "t");
		
		//level 3
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		
		//level 4
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		
		//level 5
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}
	
	/**
	 * this method's sole purpose is to call the recursive method addNode to 
	 * complete the task of adding the node to the binary tree
	 * @param code the morse code describing the location of insertion
	 * @param letter the letter to be inserted at the specified location
	 */
	public void insert(String code, String letter) {
		addNode(root, code, letter); 
	}
	
	/**
	 * recursively traverses the binary tree until the specified location is found 
	 * and then inserts the specified letter in that location. The location for 
	 * insertion is specified using the code where . means left and - means right
	 * @param root the current root for the recursive method [root moves down the 
	 * tree as method recurses "deeper" 
	 * @param code the current code or left over instructions at this point in the 
	 * "directions/traversal" 
	 * @param the letter to be inserted at the final position
	 * @throws IllegalArgumentException if a code is given that is not "in reach" 
	 * [EX: if only one node exists and the client, in this case one of our other 
	 * methods passes ... , then that insertion node would be unreachable and 
	 * thus the exception is thrown]. NOTE: this exception never gets thrown 
	 * because we control insertions but it was useful for initial debugging 
	 * purposes and in case any changes are made in the future. 
	 */
	public void addNode(TreeNode<String> root, String code, String letter) {
		if(root == null) {
			throw new IllegalArgumentException(); 
		}
		if(code.length() == 1) { //base case
			if(code.equals(".")) 
				root.setLeftChild(new TreeNode<String>(letter));
			if(code.equals("-"))
				root.setRightChild(new TreeNode<String>(letter));
		}
		
		if(code.length() > 1) {
			if(code.charAt(0) == '.')
				addNode(root.getLeftChild(), code.substring(1), letter);

			if(code.charAt(0) == '-')
				addNode(root.getRightChild(), code.substring(1), letter);
		}
		
	}
	
	/**
	 * this methods sole purpose is to call the recursive fetchNode method to 
	 * obtain the "string" [really just a single character] associated with the 
	 * given morse code
	 * @param code the full morse code describing the traversal directions needed 
	 * to arrive at the associated letter
	 */
	public String fetch(String code) {
		return fetchNode(root, code); 
	}
	
	/**
	 * this method uses recursion to retrieve the letter associated with a given 
	 * code. 
	 * @param root the current root for the recursive method [root moves down the 
	 * tree as method recurses "deeper" 
	 * @param code the current code or left over instructions at this point in the 
	 * "directions/traversal" 
	 * @throws IllegalArgumentException with the message "token" if the code provided 
	 * maps to a nonexistent node in the binary search tree. 
	 * @throws IllegalArgumentExceptipon with the message "char" if the code contains 
	 * some illegal character 
	 * 
	 * @returns null [this is just to stop the compiler from crying.  
	 */
	public String fetchNode(TreeNode<String> root, String code) {
		
		if(root == null ) {
			throw new IllegalArgumentException("token"); 
		}
		/*
		 * check to ensure current char is a valid morse code character 
		 * meaning . or - or / or " " (that being space) 
		 */
		if(code.charAt(0) != '.' && code.charAt(0) != '-'
				&& code.charAt(0) != '/' && code.charAt(0) != ' ') {
			throw new IllegalArgumentException("char"); 
			
		}
		
		if(code.length() == 1) { //base case
			if(code.equals(".")) {
				TreeNode<String> newRoot = root.getLeftChild(); 
				if(newRoot == null) {
					throw new IllegalArgumentException("token");
				}
				return newRoot.getData();
			}
			if(code.equals("-")) {
				TreeNode<String> newRoot = root.getRightChild(); 
				if(newRoot == null) {
					throw new IllegalArgumentException("token");
				}
				return newRoot.getData();
			}
		}
		
		if(code.length() > 1) {
			if(code.charAt(0) == '.')
				return fetchNode(root.getLeftChild(), code.substring(1));

			if(code.charAt(0) == '-')
				return fetchNode(root.getRightChild(), code.substring(1));
		}
		
		return null;
	}
	
	/**
	 * this retrieves and returns a copy of this tree's root. However, 
	 * this method is never utilized within this project. It is here 
	 * because the Java Docs or JUnits specified it should be here  
	 * @return a copy of this root node
	 */
	public TreeNode<String> getRoot() {
		/*
		 * this is the ONLY location, within the entire project, where a deep copy would be useful. 
		 * However, there is no purpose in using this method within this project. 
		 */
		return new TreeNode<String>(root); 
	}
	
	/**
	 * sets the root for this tree. Once again, this method 
	 * is never used within this project it is only defined to 
	 * meet the requirements specified in Java Docs and JUnits. 
	 * @param newNode the new root for this tree
	 */
	public void setRoot(TreeNode<String> newNode) {
		this.root = newNode; 
	}
	
	/**
	 * deletes a node from this tree. This method is unsupported so it simply 
	 * throws a UnsupportedOperationException
	 * @throws UnsupportedOperationException because this operation is not 
	 * supported
	 */
	public MorseCodeTree delete(String code) {
		throw new UnsupportedOperationException(); 
	}
	
	/**
	 *  This method is unsupported so it simply 
	 * throws a UnsupportedOperationException
	 * @throws UnsupportedOperationException because this operation is not 
	 * supported
	 */
	public MorseCodeTree update() {
		throw new UnsupportedOperationException(); 
	}
	
	/**
	 * returns an array list containing the labels for each node in this 
	 * binary tree. The labels are added to the array list using the 
	 * in-order or LNR traversal order. This method employs a recursive method 
	 * to facilitate this. 
	 * @return an ArrayList<String> object named resultList containing the labels 
	 * of this tree in in-order traversal order
	 */
	public ArrayList<String> toArrayList() {
		ArrayList<String> resultList = new ArrayList<>(); 
		
		LNRoutputTraversal(root, resultList); 
		
		return resultList; 
	}
	
	/**
	 * recursive method implementing the in-order traversal for the toArrayList method. 
	 * Upon completion of this method the ArrayList passed in by the toArrayList method 
	 * will be filled. That method will still have a reference to the object so it can return 
	 * it. 
	 * @param root the current root for the recursion
	 * @param list a ArrayList<String> object which will be filled according to in-order 
	 * traversal scheme
	 */
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if(root.hasLeftChild())
			LNRoutputTraversal(root.getLeftChild(), list); 
		
		list.add(root.getData()); 
		
		if(root.hasRightChild())
			LNRoutputTraversal(root.getRightChild(), list);
	}
	
	
	
	
	
}
