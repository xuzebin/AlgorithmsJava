/**
 * Implemented methods:
 * recursiveSearch
 * iterativeSearch
 * minimum
 * maxmum
 * successor
 * predecessor
 *
 **/

public class BinarySearchTree {
    public class Tree {
	int key;
	Tree left;
	Tree right;
	Tree parent;

	public Tree(int key) {
	    this.key = key;
	    left = null;
	    right = null;
	}
    }

    public Tree recursiveSearch(Tree root, int key) {
	if (root == null || root.key == key) {
	    return root;
	}
	if (key < root.key) {
	    recursiveSearch(root.left, key);
	} else {
	    recursiveSearch(root.right, key);
	}
    }

    public Tree iterativeSearch(Tree root, int key) {
	while (root != null && root.key != key) {
	    if (key < root.key) {
		root = root.left;
	    } else {
		root = root.right;
	    }
	}
	return root;
    }

    public Tree minimum(Tree root) {
	if (root == null) {
	    return null;
	}
	while (root.left != null) {
	    root = root.left;
	}
	return root;
    }

    public Tree maximum(Tree root) {
	if (root == null) {
	    return null;
	}
	while (root.right != null) {
	    root = root.right;
	}
	return root;
    }
    
    public Tree successor(Tree root) {
	if (root == null) {
	    return null;
	}
	if (root.right != null) {
	    return minimum(root.right);
	}
	Tree parent = root.parent;
	while (parent != null && root == parent.right) {
	    root = parent;
	    parent = root.parent;
	}
	return parent;
    }

    public Tree predecessor(Tree root) {
	if (root == null) {
	    return null;
	}
	if (root.left != null) {
	    return maximum(root.left);
	}
	Tree parent = root.parent;
	while (parent != null && root == parent.left) {
	    root = parent;
	    parent = root.parent;
	}
	return parent;
    }
}