/**
 * Implement a binary heap based on CLRS.
 *
 **/
package edu.nyu.algorithms;

import java.util.Arrays;

public class Heap {
    public enum HeapType {
	MIN, MAX
	    };

    public int getParent(int index) {
	return (index - 1) >> 1;
    }
    public int getLeftChild(int index) {
	return (index << 1) + 1;
    }
    public int getRightChild(int index) {
	return (index << 1) + 2;
    }
    public void heapify(int[] heapArray, int heapSize, int i, HeapType type) {
	int left = getLeftChild(i);
	int right = getRightChild(i);

	int largest = i;//when HeapType is MIN, is should refer to smallest.
	if (left < heapSize && compare(heapArray[left], heapArray[i], type)) {
	    largest = left;
	}
	if (right < heapSize && compare(heapArray[right], heapArray[largest], type)) {
	    largest = right;
	}
	if (largest != i) {
	    swap(heapArray, i, largest);
	    heapify(heapArray, heapSize, largest, type);
	}
    }
    public void heapify(int[] heapArray, int i, HeapType type) {
	heapify(heapArray, heapArray.length, i, type);
    }
    private boolean compare(int one, int another, HeapType type) {
	switch(type) {
	case MIN:
	    return one < another;
	case MAX:
	    return one > another;
	default:
	    throw new IllegalArgumentException(String.format("Unrecognized HeapType %s", type.name()));
	}
    }
    private void swap(int[] heapArray, int i, int largest) {
	final int temp = heapArray[i];
	heapArray[i] = heapArray[largest];
	heapArray[largest] = temp;
    }
    public void buildHeap(int[] array, HeapType type) {
	for (int i = array.length / 2 - 1; i >= 0; --i) {
	    heapify(array, i, type);
	}
    }
    
    public void heapSort(int[] array, HeapType type) {
	buildHeap(array, type);
	int heapSize = array.length;
	for (int i = array.length - 1; i > 0; --i) {
	    swap(array, 0, i);
	    heapSize -= 1;
	    heapify(array, heapSize, 0, type);
	}
    }

    public static void main(String[] args) {
	Heap heap = new Heap();
	int[] array = new int[]{4,1,3,2,16,9,10,14,8,7};

	heap.heapSort(array, Heap.HeapType.MIN);
	System.out.println(Arrays.toString(array));

    }    
}	   
	    
