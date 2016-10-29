package edu.nyu.algorithms.tree;

public class BinaryNode<T> {

    int key;
    T data;//satellite data

    BinaryNode<T> left;
    BinaryNode<T> right;
    BinaryNode<T> parent;
    
    public BinaryNode(int key) {
	this(key, null);
    }
    public BinaryNode(int key, T data) {
	this.key = key;
	this.data = data;
    }
}