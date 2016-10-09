/**
 * Implemented methods: 
 * 
 * recursiveSearch
 * iterativeSearch
 * minimum
 * maxmum
 * successor
 * predecessor
 * insert
 * delete
 *
 * All these methods run in O(h) where h is the height of Tree
 **/
package edu.nyu.algorithms.tree;

public class BinarySearchTree {

    private BinaryNode root;

    public BinarySearchTree() {
	this.root = null;
    }
     
    public BinaryNode recursiveSearch(BinaryNode root, int key) {
	if (root == null || root.key == key) {
	    return root;
	}
	if (key < root.key) {
	    return recursiveSearch(root.left, key);
	} else {
	    return recursiveSearch(root.right, key);
	}
    }

    public BinaryNode iterativeSearch(BinaryNode root, int key) {
	while (root != null && root.key != key) {
	    if (key < root.key) {
		root = root.left;
	    } else {
		root = root.right;
	    }
	}
	return root;
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

    public BinaryNode maximum(BinaryNode root) {
	if (root == null) {
	    return null;
	}
	while (root.right != null) {
	    root = root.right;
	}
	return root;
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

    public BinaryNode insert(BinaryNode root, BinaryNode node) {
	BinaryNode p = null;
	BinaryNode x = root;
	while (root != null) {
	    p = x;
	    if (node.key < x.key) {
		x = x.left;
	    } else {
		x = x.right;
	    }
	}
	node.parent = p;
	if (p == null) {
	    //Tree was empty
	    root = node;
	} else if (node.key < p.key) {
	    p.left = node;
	} else {
	    p.right = node;
	}
	return root;
    }

    private BinaryNode transplant(BinaryNode root, BinaryNode u, BinaryNode v) {
	if (u.parent == null) {
	    root = v;
	} else if (u == u.parent.left) {
	    u.parent.left = v;
	} else {
	    u.parent.right = v;
	}

	if (v != null) {
	    v.parent = u.parent;
	}
	return root;
    }
    public BinaryNode delete(BinaryNode root, BinaryNode node) {
	if (node.left == null) {
	    return transplant(root, node, node.right);
	} else if (node.right == null) {
	    return transplant(root, node, node.left);
	} else {
	    BinaryNode suc = minimum(node.right);
	    if (suc.parent != node) {
		transplant(root, suc, suc.right);
		suc.right = node.right;
		suc.right.parent = suc;
	    }
	    transplant(root, node, suc);
	    suc.left = node.left;
	    suc.left.parent = suc;
	    return root;
	}
    }

}