//Name of The Student: 				Frank Cazarez
//NedID:							fac160030
//Class:							CE 3345.03 Data Structures and Introduction to Algorithmic Analysis
//Section:							003
//Semester:							Spring 2019
//Project Number/Description:		Project # 3; This project consist of the creation and implementation of a Binary Search Tree with lazy deletion. This project involves 2 java classes/files called, Main.java and LazyBinarySearchTree.java. The LazyBinarySearchTree class consist of 1 subclass called TreeNode which is used to traverse the Binary Search Tree, and being able to set/get the key, status of deletion, height, left/right children, and print the values using toString. The way that the TreeNode class is manipulated is by the Checking methods ( Illegal Argument blockers) and the recursive methods tied to their respective checker method. The recursive methods are: Insert, Delete, FindMin, FindMax, Contains, toString (For PrintTree command), Height, and Size in that respective order. The Main class consist of the interaction the class has with the desired input/output files that the argument line is set to (In this case input file is "input.txt" and output file is "output.txt"). After the class checks the validity of the arguments command line (Checking if both the input/output files are there in command line (If not error is displayed). A try command is executed, this prompts a message telling the user what the output file will be called ("output.txt" in this case). After it creates an instance of the LazyBinarySearchTree class. Then the input file is ran through a while loop to be able to read the keywords with their respective integer values (If not display error). After keywords/values are read they are matched with their own respective if statement: Insert, Delete, FindMin, FindMax, Contains, PrintTree, Height, Size. Each of those methods are also ran through their own try/catch cases to be able to find errors in the input file. Lastly access to the input/output file is closed.

//IDE Used: Eclipse
//-------------------------------------------------------------------------------		
public class LazyBinarySearchTree {
	
	//TreeNode Class
	private class TreeNode {
		//Values
		int key;
		boolean deleted;
		int height;
		TreeNode leftChild;
		TreeNode rightChild;

		//Constructor with argument
		public TreeNode(int key) {
			this.key = key;
			this.deleted = false;
			this.height = 0;
			this.leftChild = null;
			this.rightChild = null;
		}
		
		//Accessor method for key
		int getKey() {
			return key;
		}
		//Accessor method for deleted
		boolean getDeleted() {
			return deleted;
		}
		//Mutator method for deleted
		void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}
		
		//Accessor method for height
		int getHeight() {
			return height;
		}
		//Mutator method for height
		void setHeight(int height) {
			this.height = height;
		}
		
		//Accessor method for leftNode
		TreeNode getleftChild() {
			return leftChild;
		}
		//Mutator method for leftNode
		void setleftChild(TreeNode leftChild) {
			this.leftChild = leftChild;
		}
		
		//Accessor method for right
		TreeNode getrightChild() {
			return rightChild;
		}
		//Mutator method for leftNode
		void setrightChild(TreeNode rightChild) {
			this.rightChild = rightChild;
		}
		@Override
		public String toString() {
			return String.valueOf(this.key);
		}
	}
//----------------------------------------------------------------------------------------------------------------------	
	private TreeNode root;
	
	//Default Constructor
	public LazyBinarySearchTree(){
		this.root = null;
	}
//----------------------------------------------------------------------------------------------------------------------	
	//Checker Insert method if Illegal Arguments
	public boolean Insert(int key) throws IllegalArgumentException{
		//Checks range from 1 to 99, check first in case of 0 integer
		if (key < 1 || key > 99) {
			throw new IllegalArgumentException("Error in Insert: IllegalArgumentException Raised");
		}
		//No nodes on tree
		else if (this.root == null) {
			//sets key, delete to false, height 0, left/right nodes to null
			this.root = new TreeNode(key);
			return true;
		}
		//Runs method if requirements met
		else {
			//Call recursive insert method
			return Insert(key, this.root);
		}
	}
	
	//Recursive Insert method
	public boolean Insert(int key, TreeNode node) {
		boolean insertNode = false;
		//Looks to see if the key is bigger than the current node
		if (key > node.getKey()) {
			//check if right child is valid
			if (node.getrightChild() != null) {
				insertNode = Insert(key, node.getrightChild());
			}
			//Found null node, inserting
			else {
				node.setrightChild(new TreeNode(key));
				insertNode = true;
			}
		}
		//Looks to see if the key is less than the current node
		else if (key < node.getKey()) {
			//check if left child is valid
			if (node.getleftChild() != null) {
				insertNode = Insert(key, node.getleftChild());
			}
			//Found null node, inserting
			else {
				node.setleftChild(new TreeNode(key));
				insertNode = true;
			}
		}
		//In case of duplicate, set from deleted to re-accessible
		else {
			//Check Deletion
			if (node.getDeleted()) {
				node.setDeleted(false);
				insertNode = true;
			}
			//Node duplicate present on tree, only 1 is able to be present
			else {
				insertNode = false;
			}
		}
		//set new height of tree
		node.setHeight((Math.max(Height(node.getrightChild()), Height(node.getleftChild())))+1);
		return insertNode;
	}
//----------------------------------------------------------------------------------------------------------------------	
	//Checker Delete method if Illegal Arguments
	public boolean Delete(int key) throws IllegalArgumentException{
		//Checks range from 1 to 99, check first in case of 0 integer
		if (key < 1 || key > 99) {
			throw new IllegalArgumentException("Error in Deletion: IllegalArgumentException Raised");
		}
		//Check if no nodes on tree
		else if (this.root == null) {
			//Nothing to delete
			return false;
		}
		//Runs method if requirements met
		else {
			//Calls recursive delete method
			return Delete(key, this.root);
		}
	}
	
	//Recursive Delete method
	public boolean Delete(int key, TreeNode node) {
		boolean isDeleted = true;
		//Check if the key is bigger than the current node
		if (key > node.getKey()) {
			//Check if node's right child is valid
			if (node.getrightChild() != null) {
				isDeleted = Delete(key, node.getrightChild());
			}
			else {
				//node not deleted
				isDeleted = false;
			}
		}
		//Check if the key is less than the current node
		else if (key < node.getKey()) {
			//Check if node's left child is valid
			if (node.getleftChild() != null) {
				isDeleted = Delete(key, node.getleftChild());
			}
			else {
				//node not deleted
				isDeleted = false;
			}
		}
		//Key matches
		else {
			//Check Deletion
			if (node.getDeleted()) {
				isDeleted = false;
			}
			//Delete Node
			else {
				node.setDeleted(true);
				isDeleted = true;
			}
		}
		//Do not need to set height, due to lazy deletion, height stays the same
		return isDeleted;
	}
//----------------------------------------------------------------------------------------------------------------------
	//FindMin method checker, calls recursive
	public int FindMin() {
		//Check if root is valid
		if (this.root != null) {
			//set min
			int newMin = this.root.getKey();
			//Check if left child is present (new min)
			if (this.root.getleftChild() != null) {
				//call recursive findMin method
				return FindMin(newMin, this.root.getleftChild());
			}
			//if no left child is present, root is minimum
			else {
				return newMin;
			}
		}
		return -1;
	}
	
	//Recursive findMin method
	public int FindMin(int newMin, TreeNode node) {
		//Check if node is valid
		if (node.getleftChild() != null) {
			//Check if the child and node are not set as deleted
			if (!node.getleftChild().getDeleted() && !node.getDeleted()) {
				newMin = Math.min(newMin, node.getleftChild().getKey());
			}
			//returns after recursive method is done, checking for more left children
			return FindMin(newMin, node.getleftChild());
		}
		//No left child is present, current node is the minimum
		else {
			return newMin;
		}
	}
//----------------------------------------------------------------------------------------------------------------------
	//FindMax method checker, calls recursive
	public int FindMax() {
		//Check if root is valid
		if (this.root != null) {
			//set max
			int newMax = this.root.getKey();
			//Check if right child is present (new max)
			if (this.root.getrightChild() != null) {
				//call recursive findMax method
				return FindMax(newMax, this.root.getrightChild());
			}
			//if no right child is present, root is maximum
			else {
				return newMax;
			}
		}
		return -1;
	}
	
	//Recursive findMax method
	public int FindMax(int newMax, TreeNode node) {
		//Check if node is valid
		if (node.getrightChild() != null) {
			//Check if the child and node are not set as deleted
			if (!node.getrightChild().getDeleted() && !node.getDeleted()) {
				newMax = Math.max(newMax, node.getrightChild().getKey());
			}
			//returns after recursive method is done, checking for more right children
			return FindMax(newMax, node.getrightChild());
		}
		//No right child is present, current node is the maximum
		else {
			return newMax;
		}
	}
//----------------------------------------------------------------------------------------------------------------------
	//Checker Contains method if Illegal Arguments
	public boolean Contains(int key) throws IllegalArgumentException{
		//Checks range from 1 to 99, check first in case of 0 integer
		if (key < 1 || key > 99) {
			throw new IllegalArgumentException("Error in Contains: IllegalArgumentException Raised");
		}
		//Check if no nodes on tree
		else if (this.root == null) {
			//Couldn't find node
			return false;
		}
		//Runs method if requirements met
		else {
			//Calls recursive contains method
			return Contains(key, this.root);
		}
	}
	
	//Recursive Contains method
	public boolean Contains(int key, TreeNode node) {
		boolean foundNode = true;
		
		if (key > node.getKey()) {
			//Check if node's right child is valid
			if (node.getrightChild() != null) {
				foundNode = Contains(key, node.getrightChild());
			}
			//Right child is null
			else {
				node.setrightChild(new TreeNode(key));
				foundNode = false;
			}
		}
		//Check if the key is less than the current node
		else if (key < node.getKey()) {
			//Check if node's left child is valid
			if (node.getleftChild() != null) {
				foundNode = Contains(key, node.getleftChild());
			}
			//Left child is null
			else {
				//Don't need to check left children further since no lesser value
				foundNode = false;
			}
		}
		//Key matches
		else {
			//Check Deletion
			if (node.getDeleted()) {
				foundNode = false;
			}
			//Found Node
			else {
				foundNode = true;
			}
		}
		return foundNode;
	}
//----------------------------------------------------------------------------------------------------------------------
	//toString method
	public String toString() {
		//Initialize StringBuffer to convert integer into String
		StringBuffer buffer = new StringBuffer();
		//Call recursive preorderTraversal method
		buffer = preorderTraversal(buffer, this.root);
		//Prints String
		return buffer.toString();
	}
	
	//Recursive Pre-order Traversal method to let toString return in order
	public StringBuffer preorderTraversal(StringBuffer buffer, TreeNode node){
		//Check if node is valid
		if (node != null) {
			//Check if node is marked as deleted
			if (node.getDeleted()) {
				//Add "*" to show that integer is deleted
				buffer.append("*" + node.getKey() + " ");
			}
			//Node is not marked as deleted, goes through left side first then right side (In order Traversal)
			else {
				//Prints integers like normal
				buffer.append(node.getKey() + " ");
			}
			//Go through the left children
			preorderTraversal(buffer, node.getleftChild());
			//Go through the right children
			preorderTraversal(buffer, node.getrightChild());
		}
		return buffer;
	}
//----------------------------------------------------------------------------------------------------------------------
	//Height method; Used in insert method
	public int Height(TreeNode height) {
		//No height defined
		if (height == null) {
			return 0;
		}
		//Get height from TreeNode class,
		else {
			return height.getHeight();
		}
	}
	
	//Regular height method
	public int Height() {
		return this.root.getHeight();
	}
//----------------------------------------------------------------------------------------------------------------------
	//Size Method checker, calls recursive
	public int Size() {
		//Check that root is valid, not null
		if (this.root != null) {
			//Call recursive size method
			return Size(0, this.root);
		}
		//null
		return 0;
	}
	
	//Recursive size method
	public int Size(int totalSize, TreeNode node) {
		//Double check that node is not null
		if (node != null) {
			//Add 1 to the total size
			totalSize += 1;
			//Traverse recursively right and left children so that totalSize increases by 1 with each count
			totalSize = Size(totalSize, node.getrightChild());
			totalSize = Size(totalSize, node.getleftChild());
		}
		return totalSize;
	}
//----------------------------------------------------------------------------------------------------------------------
}
