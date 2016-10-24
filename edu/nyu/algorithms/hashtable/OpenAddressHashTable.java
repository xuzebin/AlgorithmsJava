/**
 * Open Address hashing implementations.
 * support insert, search operations
 * Collision resoluions include:
 * linear probing
 * quadratic probing
 * double hashing
 *
 * TODO add remove operation
 */
package edu.nyu.algorithms.hashtable;

public class OpenAddressHashTable {

    private static final int DEFAULT_TABLE_SIZE = 13;//small enough for testing
    private Element[] table;
    private int m;//number of slots in hash table, use m for simplicity.

    public OpenAddressHashTable() {
	this(DEFAULT_TABLE_SIZE);
    }

    public OpenAddressHashTable(int tableSize) {
	table = new Element[tableSize];
	m = table.length;
    }
    
    //return the inserted position (slot index).
    public int insert(int key, String data) {
	for (int i = 0; i < m; ++i) {
	    int hashed = doubleHashing(key, i);
	    if (table[hashed] == null) {
		table[hashed] = new Element();
		table[hashed].key = key;
		table[hashed].data = data;
		return hashed;
	    }
	}
	throw new RuntimeException("hash table full!");
    }

    //TODO add special value to the removed element.
    public void remove(int key) {}
    
    public String search(int key) {
	for (int i = 0; i < m; ++i) {
	    int hashed = doubleHashing(key, i);
	    if (table[hashed] == null) {
		return null;
	    }
	    //assume key and data were created when inserting.
	    //so we don't check if key and data is null here.
	    if (table[hashed].key == key) {
		return table[hashed].data;
	    } 
	}
	return null;
    }

    private int linearProbing(int key, int i) {
	return (hash(key) + i) % m;
    }
    private int quadraticProbing(int key, int i) {
	return (hash(key) + i + i * i) % m;
    }
    private int doubleHashing(int key, int i) {
	return (hash(key) + i * hash2(key)) % m;
    }

    private int hash(int key) {
	int hashed = key % m;
	return hashed < 0 ? hashed + m : hashed;
    }
    private int hash2(int key) {
	return 7 - key % 7;//7 be a prime less than m, greater than 0
    }
}