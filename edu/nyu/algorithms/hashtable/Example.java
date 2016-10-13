package edu.nyu.algorithms.hashtable;

public class Example {
    public static void main(String[] args) {
	ChainingHashTable cht = new ChainingHashTable();
	cht.insert(7);
	cht.insert(71);
	cht.insert(23);
	
	System.out.println(cht.contains(8));
	System.out.println(cht.contains(7));
    }
}