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
    public class Node {
	int key;
	Node left;
	Node right;
	Node parent;

	public Node(int key) {
	    this.key = key;
	    this.left = null;
	    this.right = null;
	}
    }

    public Node recursiveSearch(Node root, int key) {
	if (root == null || root.key == key) {
	    return root;
	}
	if (key < root.key) {
	    return recursiveSearch(root.left, key);
	} else {
	    return recursiveSearch(root.right, key);
	}
    }

    public Node iterativeSearch(Node root, int key) {
	while (root != null && root.key != key) {
	    if (key < root.key) {
		root = root.left;
	    } else {
		root = root.right;
	    }
	}
	return root;
    }

    public Node minimum(Node root) {
	if (root == null) {
	    return null;
	}
	while (root.left != null) {
	    root = root.left;
	}
	return root;
    }

    public Node maximum(Node root) {
	if (root == null) {
	    return null;
	}
	while (root.right != null) {
	    root = root.right;
	}
	return root;
    }
    
    public Node successor(Node root) {
	if (root == null) {
	    return null;
	}
	if (root.right != null) {
	    return minimum(root.right);
	}
	Node parent = root.parent;
	while (parent != null && root == parent.right) {
	    root = parent;
	    parent = root.parent;
	}
	return parent;
    }

    public Node predecessor(Node root) {
	if (root == null) {
	    return null;
	}
	if (root.left != null) {
	    return maximum(root.left);
	}
	Node parent = root.parent;
	while (parent != null && root == parent.left) {
	    root = parent;
	    parent = root.parent;
	}
	return parent;
    }


}