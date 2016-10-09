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
	    this.parent = null;
	}
    }

    private Node root;

    public BinarySearchTree() {
	this.root = null;
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

    public Node insert(Node root, Node node) {
	Node p = null;
	Node x = root;
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

    private Node transplant(Node root, Node u, Node v) {
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
    public Node delete(Node root, Node node) {
	if (node.left == null) {
	    return transplant(root, node, node.right);
	} else if (node.right == null) {
	    return transplant(root, node, node.left);
	} else {
	    Node suc = minimum(node.right);
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