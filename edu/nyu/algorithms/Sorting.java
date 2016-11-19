/**
 * Implement basic sorting algorithms.
 * Includes:
 * insertion sort
 * merge sort
 * quick sort
 * selection sort (not included in the book) O(n^2)
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
	int[] arr3 = Arrays.copyOf(arr, arr.length);
	int[] arr4 = Arrays.copyOf(arr, arr.length);
	System.out.printf("unsorted: %s%n", Arrays.toString(arr));
	int steps = s.insertionSort(arr);
	System.out.printf("insertion sort: %s%n", Arrays.toString(arr));
	System.out.printf("steps: %d%n", steps);


	int msteps = s.mergeSort(arr2);
	System.out.printf("merge sort: %s%n", Arrays.toString(arr2));
	System.out.printf("steps: %d%n", msteps);

	int qsteps = s.quickSort(arr3);
	System.out.printf("quick sort: %s%n", Arrays.toString(arr3));
	System.out.printf("steps: %d%n", qsteps);

	s.selectionSort(arr4);
	System.out.printf("selection sort: %s%n", Arrays.toString(arr4));
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

    private int qsteps = 0;

    public int quickSort(int[] array) {
	quickSort(array, 0, array.length - 1);
	return qsteps;
    }

    private void quickSort(int[] array, int p, int r) {
	System.out.println(Arrays.toString(array));
	if (p < r) {
	    int q = partition(array, p, r);
	    quickSort(array, p, q - 1);
	    quickSort(array, q + 1, r);
	    qsteps += 4;
	}
    }

    private int partition(int[] array, int p, int r) {
	int pivot = array[r];
	int j = p - 1;
	for (int i = p; i < r; ++i) {
	    if (array[i] <= pivot) {
		++j;
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;

		qsteps += 1;
	    }
	    qsteps += 1;
	}
	
	qsteps += 1;
	
	array[r] = array[j + 1];
	array[j + 1] = pivot;
	return j + 1;
    }


    public void selectionSort(int[] array) {
	if (array == null || array.length() == 0) {
	    return;
	}
	for (int i = 0; i < array.length - 1; ++i) {
	    int min = i;
	    for (int j = i + 1; j < array.length; ++j) {
		if (array[j] < array[min]) {
		    min = j;
		}
	    }
	    if (min != i) {
		int temp = array[min];
		array[min] = array[i];
		array[i] = temp;
	    }
	}
    }
}

