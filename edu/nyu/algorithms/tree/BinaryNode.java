package edu.nyu.algorithms.tree;

public class BinaryNode {
    int key;
    BinaryNode left;
    BinaryNode right;
    BinaryNode parent;

    
    BinaryNode(int key) {
	this(key, null, null, null);
    }

    BinaryNode(int key, BinaryNode left, BinaryNode right, BinaryNode parent) {
	this.key = key;
	this.left = left;
	this.right = right;
	this.parent = parent;
    }
}