package edu.nyu.algorithms;

import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {
	Sorting s = new Sorting();
	int[] arr = { 9, 3, 6, 2, 5, 7, 1, 8};
	System.out.printf("unsorted: %s%n", Arrays.toString(arr));
	int steps = s.insertionSort(arr);
	System.out.printf("sorted: %s%n", Arrays.toString(arr));
	System.out.printf("steps: %d%n", steps);
    }

    //return steps: number of calculations
    public int insertionSort(int[] array) {
	int steps = 0;
	for (int j = 1; j < array.length; ++j) {
	    int key = array[j];
	    int i = j - 1;
	    steps += 4;
	    while (i >= 0 && key < array[i]) {
		array[i + 1] = array[i];
		i = i - 1;
		steps += 2;
	    }
	    array[i + 1] = key;
	    steps += 1;
	}
	return steps;
    }

}
