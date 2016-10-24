package edu.nyu.algorithms.hashtable;

public class Example {
    public static void main(String[] args) {
// 	ChainingHashTable cht = new ChainingHashTable();
// 	cht.insert(7);
// 	cht.insert(71);
// 	cht.insert(23);
	
// 	System.out.println(cht.contains(8));
// 	System.out.println(cht.contains(7));

	OpenAddressHashTable table = new OpenAddressHashTable(13);
	int slot;
	slot = table.insert(3, "david");
	System.out.printf("inserted in slot %d%n", slot);
	slot = table.insert(7, "xiaovid");
	System.out.printf("inserted in slot %d%n", slot);
	slot = table.insert(4, "zhuhaha");
	System.out.printf("inserted in slot %d%n", slot);
	
	slot = table.insert(14, "leslie");
	System.out.printf("inserted in slot %d%n", slot);
	slot = table.insert(1, "john");
	System.out.printf("inserted in slot %d%n", slot);
	slot = table.insert(15, "julia");
	System.out.printf("inserted in slot %d%n", slot);
	slot = table.insert(16, "anonymous");
	System.out.printf("inserted in slot %d%n", slot);

	int key = 3;
	String result = table.search(key);
	System.out.printf("Search key: %d, found: %s%n", key, result);
	
	key = 15;
	result = table.search(key);
	System.out.printf("Search key: %d, found: %s%n", key, result);

	key = 18;
	result = table.search(key);
	System.out.printf("Search key: %d, found: %s%n", key, result);
	
	
	
    }
}