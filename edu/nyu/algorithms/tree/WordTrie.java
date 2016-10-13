package edu.nyu.algorithms.tree;

import java.util.HashSet;
import java.util.HashMap;
import java.util.*;
/**
 * This class is used for counting word's frequency
 *
 **/
public class WordTrie {

    private final WordNode root;

    public WordTrie() {
	this.root = new WordNode();
    }

    public void insert(String word) {
	if (word == null || word.length() == 0) {
	    return;
	}
	WordNode current = root;
	for (int i = 0; i < word.length(); ++i) {
	    char c = word.charAt(i);
	    WordNode node = current.children.get(c);
	    if (node == null) {
		node = new WordNode();
		current.children.put(c, node);
	    }
	    current = node;
	}
	current.count += 1;
    }
    
    public int getCount(String word) {
	if (word == null || word.length() == 0) {
	    return 0;
	}
	WordNode current = root;
	for (int i = 0; i < word.length(); ++i) {
	    char c = word.charAt(i);
	    WordNode node = current.children.get(c);
	    if (node == null) {
		return 0;
	    }
	    current = node;
	}
	return current.count;
    }



    public static void main(String[] args) {
	WordTrie wt = new WordTrie();
	String[] words = new String[] {"hello", "abc", "hel", "hell", "what", "hello",
				       "hello", "the", "fellow", "hello"};

	HashSet<String> set = new HashSet<>();
	

	for (String word : words) {
	    wt.insert(word);
	    set.add(word);
	}
	System.out.printf("hello: %d%n", wt.getCount("hello"));
	System.out.printf("he: %d:%n", wt.getCount("he"));
	

	for (String s : set) {
	    System.out.printf("%s: %d%n", s, wt.getCount(s));
	}

    }
    
}