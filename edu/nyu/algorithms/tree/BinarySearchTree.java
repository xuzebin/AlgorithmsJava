/**
 * Implemented methods: 
 * 
 * recursiveSearch
 * iterativeSearch
 * minimum
 * minimumRecursive
 * maximum
 * maximumRecursive
 * successor
 * predecessor
 * insert
 * insertRecursive
 * delete1 find successor.
 * delete2 find predecessor.
 * delete an optimized method considering tree structure.
 * exist if a node with the specified key exists
 *
 * All the above methods run in O(h) where h is the height of Tree
 *
 * preorder
 * inorder recursive method
 * inorder2 start from minimum and iteratively calling successor method.
 * postorder
 *
 * find kth smallest node (recursive)
 * find kth smallest node (iterative)
 *
 **/
package edu.nyu.algorithms.tree;

import java.util.*;
import edu.nyu.algorithms.tree.BinaryNode;

public class BinarySearchTree<T> {

    private BinaryNode<T> root;

    public BinarySearchTree() {
	this.root = null;
    }

    public BinaryNode<T> recursiveSearch(int key) {
	return recursiveSearch(root, key);
    }
					      
    private BinaryNode<T> recursiveSearch(BinaryNode<T> root, int key) {
	if (root == null || root.key == key) {
	    return root;
	}
	if (key < root.key) {
	    return recursiveSearch(root.left, key);
	} else {
	    return recursiveSearch(root.right, key);
	}
    }

    public BinaryNode<T> iterativeSearch(int key) {
	return iterativeSearch(root, key);
    }

    private BinaryNode<T> iterativeSearch(BinaryNode<T> root, int key) {
	while (root != null && root.key != key) {
	    if (key < root.key) {
		root = root.left;
	    } else {
		root = root.right;
	    }
	}
	return root;
    }

    public BinaryNode<T> minimum() {
	return minimum(root);
    }
    public BinaryNode<T> minimum(BinaryNode<T> root) {
	if (root == null) {
	    return null;
	}
	while (root.left != null) {
	    root = root.left;
	}
	return root;
    }

    public BinaryNode<T> minimumRecursive() {
	return minimumRecursive(root);
    }

    public BinaryNode<T> minimumRecursive(BinaryNode<T> root) {
	if (root == null || root.left == null) {
	    return root;
	}
	return minimumRecursive(root.left);
    }

    public BinaryNode<T> maximum() {
	return maximum(root);
    }
				
    public BinaryNode<T> maximum(BinaryNode<T> root) {
	if (root == null) {
	    return null;
	}
	while (root.right != null) {
	    root = root.right;
	}
	return root;
    }
    
    public BinaryNode<T> maximumRecursive() {
	return maximumRecursive(root);
    }
    public BinaryNode<T> maximumRecursive(BinaryNode<T> root) {
	if (root == null || root.right == null) {
	    return root;
	}
	return maximumRecursive(root.right);
    }

    public BinaryNode<T> successor(BinaryNode<T> root) {
	if (root == null) {
	    return null;
	}
	if (root.right != null) {
	    return minimum(root.right);
	}
	BinaryNode<T> parent = root.parent;
	while (parent != null && root == parent.right) {
	    root = parent;
	    parent = root.parent;
	}
	return parent;
    }

    public BinaryNode<T> predecessor(BinaryNode<T> root) {
	if (root == null) {
	    return null;
	}
	if (root.left != null) {
	    return maximum(root.left);
	}
	BinaryNode<T> parent = root.parent;
	while (parent != null && root == parent.left) {
	    root = parent;
	    parent = root.parent;
	}
	return parent;
    }
    public void insert(BinaryNode<T> node) {
	if (node == null) {
	    return;
	}
	BinaryNode<T> p = null;
	BinaryNode<T> x = this.root;
	while (x != null) {
	    p = x;
	    x = node.key < x.key ? x.left : x.right;
	}
	node.parent = p;
	if (p == null) {
	    //Tree was empty, now the inserted node becomes root of tree.
	    System.out.printf("Tree was empty. Node %d inserted.%n", node.key);
	    this.root = node;
	} else if (node.key < p.key) {
	    System.out.printf("Node %d inserted in the left tree.%n", node.key);
	    p.left = node;
	} else {
	    System.out.printf("Node %d inserted in the right tree.%n", node.key);
	    p.right = node;
	}
    }

    public void insertRecursive(BinaryNode<T> node) {
	if (this.root == null) {
	    this.root = node;
	    System.out.printf("Tree was empty. Node %d inserted.%n", node.key);
	} else {
	    insertRecursive(root, node);
	}
    }

    //assume node != null
    private void insertRecursive(BinaryNode<T> current, BinaryNode<T> node) {
	if (current == null) {
	    return;
	} else {
	    if (node.key < current.key) {
		if (current.left == null) {
		    current.left = node;
		    System.out.printf("Node %d inserted in the left tree.%n", node.key);
		    node.parent = current;
		} else {
		    insertRecursive(current.left, node);
		}
	    } else {
		if (current.right == null) {
		    current.right = node;
		    System.out.printf("Node %d inserted in the right tree.%n", node.key);
		    node.parent = current;
		} else {
		    insertRecursive(current.right, node);
		}
	    }
	}
    }

    private void transplant(BinaryNode<T> u, BinaryNode<T> v) {
	if (u.parent == null) {
	    this.root = v;
	} else if (u == u.parent.left) {
	    u.parent.left = v;
	} else {
	    u.parent.right = v;
	}

	if (v != null) {
	    v.parent = u.parent;
	}
    }
    public void delete(int key) {
	BinaryNode<T> node = iterativeSearch(key);
	delete(node);
    }

    /**
     * Replace the node being deleted with its successor
     */
    private void delete1(BinaryNode<T> node) {
	if (node == null) {
	    return;
	}
	if (node.left == null) {
	    transplant(node, node.right);
	} else if (node.right == null) {
	    transplant(node, node.left);
	} else {
	    //find the successor
	    BinaryNode<T> suc = minimum(node.right);
	    if (suc.parent != node) {
		transplant(suc, suc.right);
		suc.right = node.right;
		suc.right.parent = suc;
	    }
	    transplant(node, suc);
	    suc.left = node.left;
	    suc.left.parent = suc;
	}
    }
    /**
     * Replace the node being deleted with its predecessor
     */
    private void delete2(BinaryNode<T> node) {
	if (node == null) {
	    return;
	}
	if (node.left == null) {
	    transplant(node, node.right);
	} else if (node.right == null) {
	    transplant(node, node.left);
	} else {
	    //find the predecessor
	    BinaryNode<T> pre = maximum(node.left);
	    if (pre.parent != node) {
		transplant(pre, pre.left);
		pre.left = node.left;
		pre.left.parent = pre;
	    }
	    transplant(node, pre);
	    pre.right = node.right;
	    pre.right.parent = pre;
	}
    }
    /**
     * A minimal optimized method of deleting a node.
     */
    private void delete(BinaryNode<T> node) {
	if (node == null) {
	    return;
	}
	if (node.left == null) {
	    transplant(node, node.right);
	} else if (node.right == null) {
	    transplant(node, node.left);
	} else {
	    //if left child is a leaf node, replace this node with the deleting node.
	    if (node.left.left == null && node.left.right == null) {
		BinaryNode<T> leaf = node.left;
		transplant(node, leaf);
		leaf.right = node.right;
		leaf.right.parent = leaf;
	    } else {//otherwise find the successor
		BinaryNode<T> suc = minimum(node.right);
		if (suc.parent != node) {
		    transplant(suc, suc.right);
		    suc.right = node.right;
		    suc.right.parent = suc;
		}
		transplant(node, suc);
		suc.left = node.left;
		suc.left.parent = suc;
	    }
	}
    }

    public boolean exist(int key) {
	return iterativeSearch(key) == null ? false : true;
    }

    public void preorderDFS() {
	System.out.print("preorder: ");
	preorderDFS(root);
	System.out.println();
    }
    public void inorderDFS() {
	System.out.print("inorder: ");
	inorderDFS(root);
	System.out.println();
    }
    public void inorderDFS2() {
	inorderDFS2(root);
    }

    public void postorderDFS() {
	System.out.print("postorder: ");
	postorderDFS(root);
	System.out.println();
    }

    public void preorderDFS(BinaryNode<T> root) {
	if (root == null) {
	    return;
	}
	System.out.printf("%d ", root.key);
	preorderDFS(root.left);
	preorderDFS(root.right);
    }
    
    public void inorderDFS(BinaryNode<T> root) {
	if (root == null) {
	    return;
	}
	inorderDFS(root.left);
	System.out.printf("%d ", root.key);
	inorderDFS(root.right);
    }
    public void postorderDFS(BinaryNode<T> root) {
	if (root == null) {
	    return;
	}
	postorderDFS(root.left);
	postorderDFS(root.right);
	System.out.printf("%d ", root.key);
    }
    //not using recursive algorithms
    public void inorderDFS2(BinaryNode<T> root) {
	if (root == null) {
	    return;
	}
	BinaryNode<T> min = minimum(root);
	System.out.printf("inorder2: [%d]:'%s'", min.key, min.data == null ? "null" : min.data);
	BinaryNode<T> next = successor(min);
	while (next != null) {
	    System.out.printf(" [%d]:'%s'", next.key, next.data == null ? "null" : next.data);
	    next = successor(next);
	}
	System.out.println();
    }

    //recursive method
    public BinaryNode kthSmallestRecursive(int k) {
	return kthSmallest(root, k, new int[]{k});
    }

    private BinaryNode kthSmallest(BinaryNode root, int k, int[] c) {
	if (root == null) {
	    return null;
	}
	BinaryNode left = kthSmallest(root.left, k, c);
	if (left != null) {
	    return left;
	}
	if (--c[0] == 0) {
	    return root;
	}
	BinaryNode right = kthSmallest(root.right, k, c);
	return right;
    }

    //iterative method
    public BinaryNode kthSmallest(int k) {
	if (root == null) {
	    return null;
	}
	Stack<BinaryNode> stack = new Stack<>();
	BinaryNode node = root;
	while (node != null) {
	    stack.push(node);
	    node = node.left;
	}
	while (!stack.isEmpty()) {
	    BinaryNode p = stack.pop();
	    if (--k == 0) {
		return p;
	    }
	    BinaryNode r = p.right;
	    while (r != null) {
		stack.push(r);
		r = r.left;
	    }
	}
	return null;
    }

    public static void main(String[] args) {
	BinarySearchTree<String> bst = new BinarySearchTree<String>();
	
	bst.insertRecursive(new BinaryNode<String>(3, "abc"));
	bst.insertRecursive(new BinaryNode<String>(5, "ok"));
	bst.insertRecursive(new BinaryNode<String>(1, "java"));
	bst.insertRecursive(new BinaryNode<String>(8, "c++"));
	bst.insertRecursive(new BinaryNode<String>(7, "python"));
	bst.insert(new BinaryNode<String>(6, "javascript"));
	bst.insert(new BinaryNode<String>(2, "ruby"));
	bst.insert(new BinaryNode<String>(0, "OpenGL"));
	bst.insert(new BinaryNode<String>(9, "cv"));
	bst.insert(new BinaryNode<String>(4, "Android"));
	bst.insert(new BinaryNode<String>(10, "ml"));

	bst.preorderDFS();
	bst.inorderDFS();
	bst.postorderDFS();

	System.out.printf("maxmimum: %d%n", bst.maximum() == null ? -1 : bst.maximum().key);
	System.out.printf("minimum: %d%n", bst.minimum() == null ? -1 : bst.minimum().key);
	System.out.printf("maxmimumRecursive: %d%n", bst.maximumRecursive() == null ? -1 : bst.maximumRecursive().key);
	System.out.printf("minimumRecursive: %d%n", bst.minimumRecursive() == null ? -1 : bst.minimumRecursive().key);


	bst.delete(4);
	
	System.out.printf("maxmimum: %d%n", bst.maximum() == null ? -1 : bst.maximum().key);
	System.out.printf("minimum: %d%n", bst.minimum() == null ? -1 : bst.minimum().key);
	System.out.printf("maxmimumRecursive: %d%n", bst.maximumRecursive() == null ? -1 : bst.maximumRecursive().key);
	System.out.printf("minimumRecursive: %d%n", bst.minimumRecursive() == null ? -1 : bst.minimumRecursive().key);


	bst.inorderDFS();

	bst.inorderDFS2();


	Scanner in = new Scanner(System.in);
	System.out.println("find the kth smallest node in the above binary search tree.");
	System.out.println("please input a k...");
	String k = in.nextLine();
	BinaryNode node = bst.kthSmallest(Integer.parseInt(k));
	if (node != null) {
	    System.out.printf("the %s smallest node is [%d]='%s'%n", k, node.key, node.data);
	}
	
    }
}