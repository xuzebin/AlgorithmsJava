package edu.nyu.algorithms.hashtable;

public class DirectAddressTable {

    private static final int DEFAULT_TABLE_SIZE = 101;

    //currently only support String as the type storing elements.
    private final String[] table;

    public DirectAddressTable() {
	this(DEFAULT_TABLE_SIZE);
    }

    public DirectAddressTable(int tableSize) {
	table = new String[tableSize];
    }

    public void insert(int key, String value) {
	if (key >= DEFAULT_TABLE_SIZE || key < 0) {
	    throw new IllegalArgumentException(String.format("Invalid key: %d", key));
	}
	if (table[key] == null) {
	    table[key] = value;
	} else {
	    //already exist.
	    //we override the original value here.
	    table[key] = value;
	}	
    }
    
    public void remove(int key) {
	if (key >= DEFAULT_TABLE_SIZE || key < 0) {
	    throw new IllegalArgumentException(String.format("Invalid key: %d", key));
	}
	table[key] = null;
    }

    public String search(int key) {
	if (key >= DEFAULT_TABLE_SIZE || key < 0) {
	    throw new IllegalArgumentException(String.format("Invalid key: %d", key));
	}
	return table[key];
    }
}