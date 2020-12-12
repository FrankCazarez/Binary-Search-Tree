//Name of The Student: 				Frank Cazarez
//NedID:							fac160030
//Class:							CE 3345.03 Data Structures and Introduction to Algorithmic Analysis
//Section:							003
//Semester:							Spring 2019
//Project Number/Description:		

import java.util.StringJoiner;

public class RedBlackTree<E extends Comparable<E>> {
	// Static values to identify whether red or black node
	static private boolean RED = false;
	static boolean BLACK = true;
	private Node<E> root;

	// Default Constructor
	RedBlackTree() {
		root = null;
	}
//--------------------------------------------------------------------------------------------------	
	// Node Class that extends Comparable<E>
	public static class Node<E extends Comparable<E>> {

		// Values for Node
		E element;
		Node<E> leftChild;
		Node<E> rightChild;
		Node<E> parent;
		boolean Color;

		// Creates node
		Node(E element, boolean Color) {
			this.element = element;
			this.Color = Color;
		}

		// Always make next node red
		Node(E element) {
			this(element, RED);
		}

		// Prints whether red of black node on tree
		public String toString() {
			if (Color == RED) {
				return ("*" + this.element);
			} 
			else {
				return ("" + this.element);
			}
		}
	}
//--------------------------------------------------------------------------------------------------	INSERT
	//Insert method
	public boolean insert(E element) {
		// Variables
		Node<E> node = root; // Current node
		Node<E> parent = root; // Parent node
		Node<E> create = new Node<E>(element); // Creates new nodes

		// No root
		if (root == null) {
			root = new Node<E>(element, BLACK);
			return true;
		}
		// element is null
		else if (element == null) {
			throw new NullPointerException("Element is null");
		}
		// Run until null
		while (node != null) {

			parent = node;

			// element is lexicographically more than current node element
			if (element.compareTo(node.element) > 0) {
				node = node.rightChild;
			}

			// element is lexicographically less than current node element
			else if (element.compareTo(node.element) < 0) {
				node = node.leftChild;
			}

			// Duplicate found
			else if (element.compareTo(node.element) == 0) {
				return false;
			}
		}
		create.parent = parent; // Create new parent node
		// element is lexicographically more than current parent element
		if (element.compareTo(parent.element) > 0) {
			parent.rightChild = create;
		}
		// element is lexicographically less than current parent element
		else if (element.compareTo(parent.element) < 0) {
			parent.leftChild = create;
		}
		// Rework red black tree
		Rework(create);
		return true;
	}
//--------------------------------------------------------------------------------------------------	CONTAINS
	//Contains method, Checks if object is not null
	public boolean contains(Comparable<E> object) {
		//Check if object is null
		if(object == null) {
			return false;
		}
		//Run recursive contains method
		else {
			return containsRecursive(object, root);
		}
	}
//--------------------------------------------------------------------------------------------------	CONTAINS RECURSIVE
	//Recursive contains method
	public boolean containsRecursive(Comparable<E> object, Node<E> node) {
		//Check if root is not null
		if (node == null) {
			return false;
		}
		//Check if object is bigger
		else if (object.compareTo(node.element) > 0) {
			return containsRecursive(object, node.rightChild);
		}
		//Check if object is smaller
		else if (object.compareTo(node.element) < 0) {
			return containsRecursive(object, node.leftChild);
		}
		//Match found
		else {
			return true;
		}
	}
//--------------------------------------------------------------------------------------------------	REWORK TREE
	//Rework/balance tree
	void Rework(Node<E> node) {
		// Check current parent and parent color ... error if color is checked before validity
		if (node.parent == null || node.parent.Color == BLACK) {
			return;
		}
		// Call SideNode method
		Node<E> side = SideNode(node);
		// Check if current node is red and is valid
		if (side != null && side.Color == RED) {
			side.Color = BLACK;
			node.parent.parent.Color = RED;
			node.parent.Color = BLACK;
			// Re-run with grand parent
			Rework(node.parent.parent);
		}
		
		// Check if current parent is the left child of the grand parent
		else if (node.parent == node.parent.parent.leftChild) {
			// Check if current node is the left child
			if (node.parent.rightChild == node) {
				RotateLeft(node);
			}
			// Change colors if grand parent and parent aren't null; in case of early rotation
			else if(node.parent.parent != null || node.parent != null) {
				node.parent.parent.Color = RED;
				node.parent.Color = BLACK;
				// Rotate Parent
				RotateRight(node.parent);
			}
			//Make sure node doesn't have active children ; all end leaves are supposed to be null
			if (node.leftChild != null) {
				node.Color = BLACK;
				node.parent.Color = RED;
				node.leftChild.Color = RED;
				RotateRight(node);
			}

		}
		// Checks if current node is the right child of the grand parent
		else if (node.parent == node.parent.parent.rightChild){
			if (node.parent.leftChild == node) {
				RotateRight(node);
			}
			// Change colors if grand parent and parent aren't null; in case of early rotation
			else if(node.parent.parent != null || node.parent != null) {
				node.parent.parent.Color = RED;
				node.parent.Color = BLACK;
				// Rotate Parent
				RotateLeft(node.parent);
			}
			//Make sure node doesn't have active children ; all end leaves are supposed to be null
			if (node.rightChild != null) {
				node.Color = BLACK;
				node.parent.Color = RED;
				node.rightChild.Color = RED;
				RotateLeft(node);
			}
		}
		// Change root to black in case of it's red
		root.Color = BLACK;
	}

//--------------------------------------------------------------------------------------------------	SIDENODE
	// Find out what the neighboring side node
	Node<E> SideNode(Node<E> node) {
		Node<E> parent = node.parent;	//Parent node
		Node<E> grand = parent.parent;	//Grand parent node
		// Check parent and grandparent are present
		if (parent == null || grand == null) {
			return null;
		}
		// If parent is the grand parent's right child
		else if (parent == grand.rightChild) {
			return grand.leftChild;
		}
		// If parent is the grand parent's left child
		else {
			return grand.rightChild;
		}
	}
//--------------------------------------------------------------------------------------------------	ROTATELEFT
	//Rotate node to the left
	void RotateLeft(Node<E> node) {
		// Created needed nodes to move tree
		Node<E> Node = node;					//Current node
		Node<E> leftChild = Node.leftChild;		//Left child node
		Node<E> Parent = node.parent;			//Parent node
		Node<E> Grand = Parent.parent;			//Grand parent node
		
		// Check if left Child is not null
		if (leftChild != null) {
			leftChild.parent = Parent;
		}
		//set grand parent as parent
		Node.parent = Grand;
		// Check that grandparent is not null
		if (Grand != null) {
			// Parent is the right child of grandparent
			if (Parent.parent.rightChild == Parent) {
				Grand.rightChild = Node;
			}
			// Parent is the left child of grandparent
			else {
				Grand.leftChild = Node;
			}
		}
		// Set Root
		else {
			this.root = Node;
		}
		//Rotate nodes 
		Node.leftChild = Parent;
		Parent.parent = Node;
		Parent.rightChild = leftChild;
	}
//--------------------------------------------------------------------------------------------------	ROTATERIGHT
	//Rotate node to the right
	void RotateRight(Node<E> node) {
		// Created needed nodes to move tree
		Node<E> Node = node;					//Current node
		Node<E> rightChild = Node.rightChild;	//Right child node
		Node<E> Parent = node.parent;			//Parent node
		Node<E> Grand = Parent.parent;			//Grand parent node
		
		// Check if right Child is not null
		if (rightChild != null) {
			rightChild.parent = Parent;
		}
		//set grand parent as parent
		Node.parent = Grand;
		// Check that grandparent is not null
		if (Grand != null) {
			// Parent is the right child of grandparent
			if (Parent.parent.rightChild == Parent) {
				Grand.rightChild = Node;
			}
			// Parent is the left child of grandparent
			else {
				Grand.leftChild = Node;
			}
		}
		// Set Root
		else {
			this.root = Node;
		}
		//Rotate nodes 
		Node.rightChild = Parent;
		Parent.parent = Node;
		Parent.leftChild = rightChild;
	}
//--------------------------------------------------------------------------------------------------	TOSTRING
	//toString to print out
	public String toString() {
		//Calls recursive toString method
		return toStringRecursive(root);
	}
//--------------------------------------------------------------------------------------------------	TOSTRING RECURSIVE
	//toString to recursively print out all the nodes
	public String toStringRecursive(Node<E> node) {
		//Initialize StringJoiner
		StringJoiner joiner = new StringJoiner(" ");
		//Check if root is null
		if(node == null) {
			return "";
		}
		//Print Pre-Order traversal
		joiner.add(node.toString());
		//Print the left children
		if (node.leftChild != null) {
			joiner.add(toStringRecursive(node.leftChild));
		}
		//Print the right children
		if(node.rightChild != null) {
			joiner.add(toStringRecursive(node.rightChild));
		}
		//Print toString
		return joiner.toString();
	}
}
