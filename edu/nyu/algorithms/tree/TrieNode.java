package edu.nyu.algorithms.tree;

import java.util.*;

public class TrieNode {
    Map<Character, TrieNode> children;
    boolean end;
    public TrieNode() {	
	this.children = new HashMap<>();
	this.end = false;
    }

}
