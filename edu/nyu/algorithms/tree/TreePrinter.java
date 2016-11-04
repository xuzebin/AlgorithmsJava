package edu.nyu.algorithms.tree;
import java.util.*;

public class TreePrinter {
    private final BinaryNode<?> root;//for simplicity, currently only support String type of satellite data
    private final int depth;
    private final int maxWidthOfTree;
    private final int rootNodeIndex;
    
    private class IndexNode {
	BinaryNode<?> node;

	/**
	 * The position of the node, starting from 1 (leftmost).
	 * The number of white spaces to print before the node is (index - 1).
	 */
	int index;
	public IndexNode(BinaryNode<?> node, int index) {
	    this.node = node;
	    this.index = index;
	}
    }

    public TreePrinter(BinaryNode<?> root) {
	this.root = root;
	this.depth = getMaxDepth(root);
	this.maxWidthOfTree = (int) Math.pow(2, depth) - 1;
	this.rootNodeIndex = (int) Math.pow(2, depth - 1);
    }

    //here we define the maximum depth of a tree the maximum number of nodes from root to leaf.
    private int getMaxDepth(BinaryNode<?> node) {
	if (node == null) {
	    return 0;
	}
	return Math.max(getMaxDepth(node.left), getMaxDepth(node.right)) + 1;
    }

    public void print() {
	BinaryNode<?> p = root;
	Queue<IndexNode> queue = new LinkedList<IndexNode>();
	queue.offer(new IndexNode(p, this.rootNodeIndex));

	int level = 1;
	while (!queue.isEmpty()) {
	    final int size = queue.size();
	    final int whiteSpacesToLeft = queue.peek().index - 1;
	    final IndexNode[] nodes = new IndexNode[size];
	    final int offset = (int) Math.pow(2, depth - 1 - level);
	    
	    //nodes in a row
	    for (int i = 0; i < size; ++i) {
		IndexNode t = queue.poll();
		nodes[i] = t;
		printNodes(t, whiteSpacesToLeft);

		if (level < depth) {
		    queue.offer(new IndexNode(t.node != null ? t.node.left : null, (t.index - offset)));
		    queue.offer(new IndexNode(t.node != null ? t.node.right : null, (t.index + offset)));
		}
	    }
	    System.out.println();
	    
	    printLinks(nodes, offset);

	    ++level;
	}
    }
    
    private void printLinks(final IndexNode[] nodesPerLevel, final int numberOfRowsOfLinks) {
	final IndexNode[] nodes = nodesPerLevel;

	for (int i = 0; i < numberOfRowsOfLinks; ++i) {
	    for (int j = 0; j < nodes.length; ++j) {
		printSpaces(nodes[j].index - (j > 0 ? nodes[j - 1].index : -(i + 1)) - 1 - (i + 1) * 2);
		printLeftLink(nodes[j].node);
		printSpaces((i + 1) * 2 - 1);
		printRightLink(nodes[j].node);
	    }
	    System.out.println();
	}
    }

    private void printLeftLink(final BinaryNode<?> node) {
	System.out.print((node != null && node.left != null) ? "/" : " ");
    }

    private void printRightLink(final BinaryNode<?> node) {
	System.out.print((node != null && node.right != null) ? "\\" : " ");
    }

    private void printSpaces(final int number) {
	for (int i = 0; i < number; ++i) {
	    System.out.print(" ");
	}
    }

    private void printNodes(IndexNode indexNode, int space) {
	for (int j = 0; j < space; ++j) {
	    System.out.print(" ");
	}
	String key = indexNode.node == null ? " " : String.valueOf(indexNode.node.key);
	System.out.print(key);
	for (int j = 0; j < space; ++j) {
	    System.out.print(" ");
	}
	System.out.print(" ");
    }
}