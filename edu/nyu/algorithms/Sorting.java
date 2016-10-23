/**
 * Implement basic sorting algorithms.
 * Includes:
 * insertion sort
 * merge sort
 */
package edu.nyu.algorithms;

import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {
	Sorting s = new Sorting();
	int[] arr;
	if (args.length == 0) {
	    arr = new int[] { 9, 3, 6, 2, 5, 7, 1, 8 };
	} else {
	    arr = new int[args.length];
	    for (int i = 0; i < args.length; ++i) {
		arr[i] = Integer.parseInt(args[i]);
	    }
	}

	int[] arr2 = Arrays.copyOf(arr, arr.length);
	System.out.printf("unsorted: %s%n", Arrays.toString(arr));
	int steps = s.insertionSort(arr);
	System.out.printf("insertion sort: %s%n", Arrays.toString(arr));
	System.out.printf("steps: %d%n", steps);


	int msteps = s.mergeSort(arr2);
	System.out.printf("merge sort: %s%n", Arrays.toString(arr2));
	System.out.printf("steps: %d%n", msteps);
	
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
    

    private int msteps = 0;
    public int mergeSort(int[] array) {
	msteps = 0;
	mergeSort(array, 0, array.length - 1);
	return msteps;
    }

    private void mergeSort(int[] array, int start, int end) {
	if (start < end) {
	    int mid = (start + end) / 2;
	    mergeSort(array, start, mid);
	    mergeSort(array, mid + 1, end);
	    merge(array, start, mid, end);
	    msteps += 3;
	}
    }
    private void merge(int[] array, int start, int mid, int end) {
	int n1 = mid - start + 1;
	int n2 = end - mid;

	msteps += (n1 + n2 + end - start + 1);

	//could have used Arrays.copyOf method, but to demonstrate
	//the iteration that is counted in running time, we copy the arrays
	//using loop.
	int[] left = new int[n1];
	int[] right = new int[n2];
	for (int i = 0; i < n1; ++i) {
	    left[i] = array[start + i];
	}
	for (int i = 0; i < n2; ++i) {
	    right[i] = array[mid + 1 + i];
	}

	int i = 0, j = 0, k = start;
	while (k <= end && i < n1 && j < n2) {
	    if (left[i] <= right[j]) {
		array[k] = left[i];
		++i;
	    } else {
		array[k] = right[j];
		++j;
	    }	    
	    ++k;
	}
	while (i < n1) {
	    array[k++] = left[i++];
	}
	while (j < n2) {
	    array[k++] = right[j++];
	}
	
    }

}
