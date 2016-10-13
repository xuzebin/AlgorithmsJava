package edu.nyu.algorithms.hashtable;

public interface HashTable {
    void insert(int key);
    void remove(int key);
    boolean contains(int key);
    void clearTable();
}