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
 * delete
 * exist
 *
 * All the above methods run in O(h) where h is the height of Tree
 *
 * preorder
 * inorder
 * postorder
 *
 **/
package edu.nyu.algorithms.tree;

import java.util.*;

public class BinarySearchTree {

    private BinaryNode root;

    public BinarySearchTree() {
	this.root = null;
    }

    public BinaryNode recursiveSearch(int key) {
	return recursiveSearch(root, key);
    }
					      
    private BinaryNode recursiveSearch(BinaryNode root, int key) {
	if (root == null || root.key == key) {
	    return root;
	}
	if (key < root.key) {
	    return recursiveSearch(root.left, key);
	} else {
	    return recursiveSearch(root.right, key);
	}
    }

    public BinaryNode iterativeSearch(int key) {
	return iterativeSearch(root, key);
    }

    private BinaryNode iterativeSearch(BinaryNode root, int key) {
	while (root != null && root.key != key) {
	    if (key < root.key) {
		root = root.left;
	    } else {
		root = root.right;
	    }
	}
	return root;
    }

    public BinaryNode minimum() {
	return minimum(root);
    }
    public BinaryNode minimum(BinaryNode root) {
	if (root == null) {
	    return null;
	}
	while (root.left != null) {
	    root = root.left;
	}
	return root;
    }

    public BinaryNode minimumRecursive() {
	return minimumRecursive(root);
    }

    public BinaryNode minimumRecursive(BinaryNode root) {
	if (root == null || root.left == null) {
	    return root;
	}
	return minimumRecursive(root.left);
    }

    public BinaryNode maximum() {
	return maximum(root);
    }
				
    public BinaryNode maximum(BinaryNode root) {
	if (root == null) {
	    return null;
	}
	while (root.right != null) {
	    root = root.right;
	}
	return root;
    }
    
    public BinaryNode maximumRecursive() {
	return maximumRecursive(root);
    }
    public BinaryNode maximumRecursive(BinaryNode root) {
	if (root == null || root.right == null) {
	    return root;
	}
	return maximumRecursive(root.right);
    }

    public BinaryNode successor(BinaryNode root) {
	if (root == null) {
	    return null;
	}
	if (root.right != null) {
	    return minimum(root.right);
	}
	BinaryNode parent = root.parent;
	while (parent != null && root == parent.right) {
	    root = parent;
	    parent = root.parent;
	}
	return parent;
    }

    public BinaryNode predecessor(BinaryNode root) {
	if (root == null) {
	    return null;
	}
	if (root.left != null) {
	    return maximum(root.left);
	}
	BinaryNode parent = root.parent;
	while (parent != null && root == parent.left) {
	    root = parent;
	    parent = root.parent;
	}
	return parent;
    }
    public void insert(BinaryNode node) {
	if (node == null) {
	    return;
	}
	BinaryNode p = null;
	BinaryNode x = this.root;
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

    private void transplant(BinaryNode u, BinaryNode v) {
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
	BinaryNode node = iterativeSearch(key);
	delete(node);
    }
    private void delete(BinaryNode node) {
	if (node == null) {
	    return;
	}
	if (node.left == null) {
	    transplant(node, node.right);
	} else if (node.right == null) {
	    transplant(node, node.left);
	} else {
	    BinaryNode suc = minimum(node.right);
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
    public void postorderDFS() {
	System.out.print("postorder: ");
	postorderDFS(root);
	System.out.println();
    }

    public void preorderDFS(BinaryNode root) {
	if (root == null) {
	    return;
	}
	System.out.printf("%d ", root.key);
	preorderDFS(root.left);
	preorderDFS(root.right);
    }
    
    public void inorderDFS(BinaryNode root) {
	if (root == null) {
	    return;
	}
	inorderDFS(root.left);
	System.out.printf("%d ", root.key);
	inorderDFS(root.right);
    }
    public void postorderDFS(BinaryNode root) {
	if (root == null) {
	    return;
	}
	postorderDFS(root.left);
	postorderDFS(root.right);
	System.out.printf("%d ", root.key);
    }


    public static void main(String[] args) {
	BinarySearchTree bst = new BinarySearchTree();
	
// 	for (int i = 0; i < 10; ++i) {
// 	    bst.insert(new BinaryNode(i));
// 	}
	bst.insert(new BinaryNode(3));
	bst.insert(new BinaryNode(5));
	bst.insert(new BinaryNode(1));
	bst.insert(new BinaryNode(8));
	bst.insert(new BinaryNode(7));
	bst.insert(new BinaryNode(6));
	bst.insert(new BinaryNode(2));
	bst.insert(new BinaryNode(0));
	bst.insert(new BinaryNode(9));
	bst.insert(new BinaryNode(4));

	bst.insert(new BinaryNode(10));

	bst.preorderDFS();
	bst.inorderDFS();
	bst.postorderDFS();

	System.out.printf("maxmimum: %d%n", bst.maximum() == null ? -1 : bst.maximum().key);
	System.out.printf("minimum: %d%n", bst.minimum() == null ? -1 : bst.minimum().key);
	System.out.printf("maxmimumRecursive: %d%n", bst.maximumRecursive() == null ? -1 : bst.maximumRecursive().key);
	System.out.printf("minimumRecursive: %d%n", bst.minimumRecursive() == null ? -1 : bst.minimumRecursive().key);


	bst.delete(10);
	
	System.out.printf("maxmimum: %d%n", bst.maximum() == null ? -1 : bst.maximum().key);
	System.out.printf("minimum: %d%n", bst.minimum() == null ? -1 : bst.minimum().key);
	System.out.printf("maxmimumRecursive: %d%n", bst.maximumRecursive() == null ? -1 : bst.maximumRecursive().key);
	System.out.printf("minimumRecursive: %d%n", bst.minimumRecursive() == null ? -1 : bst.minimumRecursive().key);



	
    }
}