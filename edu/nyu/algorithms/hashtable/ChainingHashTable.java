package edu.nyu.algorithms.hashtable;

import java.util.*;

public class ChainingHashTable implements HashTable {

    private static final int DEFAULT_TABLE_SIZE = 101;
    private final LinkedList<Integer>[] hashTable;
    private int currentSize;

    public ChainingHashTable() {
	this(DEFAULT_TABLE_SIZE);
    }
    public ChainingHashTable(int tableSize) {
	hashTable = new LinkedList[tableSize];
	for (int i = 0; i < tableSize; ++i) {
	    hashTable[i] = new LinkedList<Integer>();
	}
	currentSize = 0;
    }

    @Override public void insert(int key) {
	LinkedList<Integer> list = hashTable[hash(key)];
	if (!list.contains(key)) {
	    list.add(key);
	    ++currentSize;
	    //TODO deal with situation when current size exceeds hash table's size.
	}
    }

    @Override public void remove(int key) {
	LinkedList<Integer> list = hashTable[hash(key)];
	if (list.contains(key)) {
	    list.remove(key);
	    --currentSize;
	}
    }

    @Override public boolean contains(int key) {
	LinkedList<Integer> list = hashTable[hash(key)];
	return list.contains(key);
    }
    @Override public void clearTable() {
	for (int i = 0; i < hashTable.length; ++i) {
	    hashTable[i].clear();
	}
	currentSize = 0;
    }
    
    private int hash(int key) {
	int hashValue = key % hashTable.length;
	return hashValue < 0 ? hashValue + hashTable.length : hashValue;
    }
}
