package edu.nyu.algorithms.tree;
import java.util.*;

public class WordNode {
    Map<Character, WordNode> children;
    int count;
    public WordNode() {	
	this.children = new HashMap<>();
	this.count = 0;
    }
}