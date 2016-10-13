/**
 * Trie Tree implementation for storing String.
 * Implements:
 * iterative/recursive insert
 * iterative/recursive search for the whole word
 * iterative/recursive serach for the prefix
 * recursive delete
 *
 **/
package edu.nyu.algorithms.tree;

import java.util.*;

public class Trie {

    private final TrieNode root;

    public Trie() {
	this.root = new TrieNode();
    }

    //iterative insert
    public void insert(String word) {
	if (word == null || word.length() == 0) {
	    return;
	}
	TrieNode current = root;
	for (int i = 0; i < word.length(); ++i) {
	    char c = word.charAt(i);
	    TrieNode node = current.children.get(c);
	    if (node == null) {
		node = new TrieNode();
		current.children.put(c, node);
	    }
	    current = node;
	}
	current.end = true;
    }
    
    //recursive insert
    public void insertRecursive(String word) {
	if (word == null || word.length() == 0) {
	    return;
	}
	insertRecursive(root, word, 0);
    }

    private void insertRecursive(TrieNode current, String word, int index) {
	if (index == word.length()) {
	    current.end = true;
	    return;
	}
	char c = word.charAt(index);
	TrieNode node = current.children.get(c);
	if (node == null) {
	    node = new TrieNode();
	    current.children.put(c, node);
	}
	insertRecursive(node, word, index + 1);
    }


    //iterative search if the whole word exists
    public boolean search(String word) {
	if (word == null || word.length() == 0) {
	    return false;
	}
	TrieNode current = root;
	for (int i = 0; i < word.length(); ++i) {
	    char c = word.charAt(i);
	    TrieNode node = current.children.get(c);
	    if (node == null) {
		return false;
	    }
	    current = node;
	}
	return current.end;
    }

    //recursive search for a whole word
    public boolean searchRecursive(String word) {
	if (word == null || word.length() == 0) {
	    return false;
	}
	return searchRecursive(root, word, 0);
    }

    private boolean searchRecursive(TrieNode current, String word, int index) {
	if (index == word.length()) {
	    return current.end;
	}
	
	char c = word.charAt(index);
	TrieNode node = current.children.get(c);
	if (node == null) {
	    return false;
	}
	return searchRecursive(node, word, index + 1);
    }

    
    //iterative search if the prefix exists
    public boolean searchPrefix(String prefix) {
	if (prefix == null || prefix.length() == 0) {
	    return false;
	}
	TrieNode current = root;
	for (int i = 0; i < prefix.length(); ++i) {
	    char c = prefix.charAt(i);
	    TrieNode node = current.children.get(c);
	    if (node == null) {
		return false;
	    }
	    current = node;
	}
	return true;
    }

    //recursive search for prefix
    public boolean searchPrefixRecursive(String prefix) {
	return searchPrefixRecursive(root, prefix, 0);
    }

    private boolean searchPrefixRecursive(TrieNode current, String prefix, int index) {
	if (index == prefix.length()) {
	    return true;
	}
	char c = prefix.charAt(index);
	TrieNode node = current.children.get(c);
	if (node == null) {
	    return false;
	}
	return searchPrefixRecursive(node, prefix, index + 1);
    }
    
    //iterative delete
//     public boolean deleteIterative(String word) {
// 	if (word == null || word.length() == 0) {
// 	    return false;
// 	}
// 	TrieNode current = root;
// 	for (int i = 0; i < prefix.length(); ++i) {
// 	    char c = word.charAt(i);
// 	    TrieNode node = current.children.get(c);
// 	    if (node == null) {
// 		return false;//the word does not exist
// 	    }
// 	    current = node;
// 	}
// 	if (!current.end) {
// 	    return false;//the word does not exist
// 	}
// 	if (current.children.size() > 0) {
// 	    return true;//children has other node, deletion completes.
// 	}
	
//     }

    //recursive delete
    public void delete(String word) {
	if (word == null || word.length() == 0) {
	    return;
	}
	delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
	if (index == word.length()) {
	    if (!current.end) {
		return false;//word does not exist, should not delete
	    }
	    current.end = false;
	    return current.children.size() == 0;//delete current node if current children is empty
	}
	
	char c = word.charAt(index);
	TrieNode node = current.children.get(c);
	if (node == null) {
	    return false;//word does not exist
	}
	boolean shouldDelete = delete(node, word, index + 1);
	if (shouldDelete) {
	    current.children.remove(c);
	    return current.children.size() == 0;
	}
	return false;	
    }

    public static void main(String[] args) {
	Trie trie = new Trie();

	//iterative insert, insertPrefix, search tests
	System.out.println("iterative methods:");
	System.out.println("insert: abc, abck, omg, american");
	System.out.println("============");

	trie.insert("abc");
	trie.insert("abck");
	trie.insert("omg");
	
	System.out.printf("search \"abc\": %s%n", trie.search("abc"));
	System.out.printf("search \"abcd\": %s%n", trie.search("abcd"));
	System.out.printf("search \"ab\": %s%n", trie.search("ab"));
	System.out.printf("search \"omg\": %s%n", trie.search("omg"));
	System.out.printf("search \"omg!\": %s%n", trie.search("omg!"));
	System.out.printf("searchPrefix \"a\" %s%n", trie.searchPrefix("a"));
	System.out.printf("searchPrefix \"ab\" %s%n", trie.searchPrefix("ab"));
	System.out.printf("searchPrefix \"abc\" %s%n", trie.searchPrefix("abc"));

	System.out.println("============");

	trie.delete("abc");

	System.out.println("delete: abc");
	System.out.println("============");
        System.out.printf("search \"abc\": %s%n", trie.search("abc"));
        System.out.printf("search \"abcd\": %s%n", trie.search("abcd"));
        System.out.printf("search \"ab\": %s%n", trie.search("ab"));
        System.out.printf("search \"omg\": %s%n", trie.search("omg"));
        System.out.printf("search \"omg!\": %s%n", trie.search("omg!"));


	trie.delete("abck");
	trie.delete("omg");

	//recursive insert, insertPrefix, search tests
	System.out.println("========================");
        System.out.println("iterative methods:");
        System.out.println("insert: abc, abck, omg, american");
        System.out.println("============");

        trie.insertRecursive("abc");
        trie.insertRecursive("abck");
        trie.insertRecursive("omg");

        System.out.printf("search \"abc\": %s%n", trie.searchRecursive("abc"));
        System.out.printf("search \"abcd\": %s%n", trie.searchRecursive("abcd"));
        System.out.printf("search \"ab\": %s%n", trie.searchRecursive("ab"));
        System.out.printf("search \"omg\": %s%n", trie.searchRecursive("omg"));
        System.out.printf("search \"omg!\": %s%n", trie.searchRecursive("omg!"));
        System.out.printf("searchPrefix \"a\" %s%n", trie.searchPrefixRecursive("a"));
        System.out.printf("searchPrefix \"ab\" %s%n", trie.searchPrefixRecursive("ab"));
        System.out.printf("searchPrefix \"abc\" %s%n", trie.searchPrefixRecursive("abc"));

        System.out.println("============");

        trie.delete("abc");

        System.out.println("delete: abc");
        System.out.println("============");
        System.out.printf("search \"abc\": %s%n", trie.searchRecursive("abc"));
        System.out.printf("search \"abcd\": %s%n", trie.searchRecursive("abcd"));
        System.out.printf("search \"ab\": %s%n", trie.searchRecursive("ab"));
        System.out.printf("search \"omg\": %s%n", trie.searchRecursive("omg"));
        System.out.printf("search \"omg!\": %s%n", trie.searchRecursive("omg!"));
	System.out.printf("searchPrefix \"a\" %s%n", trie.searchPrefixRecursive("a"));
        System.out.printf("searchPrefix \"ab\" %s%n", trie.searchPrefixRecursive("ab"));
        System.out.printf("searchPrefix \"abc\" %s%n", trie.searchPrefixRecursive("abc"));
	

    }

}