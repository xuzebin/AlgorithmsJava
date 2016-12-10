/**
 * Implement a binary heap and a (max) priority queue based on the heap in the CLRS book.
 * For simplicity, each element in a priority queue contains is a key.
 **/
package edu.nyu.algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Heap {

    public static void main(String[] args) {
        int[] array = new int[] {4,1,3,2,16,9,10,14,8,7};
        List<Integer> arrayList = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            arrayList.add(array[i]);
        }

        Heap heap = new Heap(arrayList);
        HeapType type = HeapType.MAX;
        System.out.println("heapsort: ");
        heap.heapSort(arrayList, type);
        System.out.println(arrayList.toString());

        System.out.println("rebuild the heap...");
        heap.buildHeap(type);
        System.out.println("insert 5, 12, 20 into the max heap...");
        heap.maxHeapInsert(5);
        heap.maxHeapInsert(12);
        heap.maxHeapInsert(20);
        for (int i = 0; i < heap.getHeapSize(); i++) {
            int max = heap.extractMax();
            System.out.printf("extract max: %d%n", max);
        }
        
    }

    private static enum HeapType {
        MIN, MAX
    };

    private int heapSize;

    private List<Integer> array;

    public Heap(List<Integer> array) {
        this.array = array;
        this.heapSize = array.size();
    }

    public int getHeapSize() {
        return array.size();
    }

    public int getParent(int index) {
        return (index - 1) >> 1;
    }
    public int getLeftChild(int index) {
        return (index << 1) + 1;
    }
    public int getRightChild(int index) {
        return (index << 1) + 2;
    }

    //Time: O(logn), n is the number of nodes.
    public void heapify(List<Integer> heapArray, int heapSize, int i, HeapType type) {
        int left = getLeftChild(i);
        int right = getRightChild(i);

        int largest = i;//when HeapType is MIN, is should refer to smallest.
        if (left < heapSize && compare(heapArray.get(left), heapArray.get(i), type)) {
            largest = left;
        }
        if (right < heapSize && compare(heapArray.get(right), heapArray.get(largest), type)) {
            largest = right;
        }
        if (largest != i) {
            swap(heapArray, i, largest);
            heapify(heapArray, heapSize, largest, type);
        }
    }

    public void heapify(List<Integer> heapArray, int i, HeapType type) {
        heapify(heapArray, heapArray.size(), i, type);
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

    private void swap(List<Integer> heapArray, int i, int largest) {
        final int temp = heapArray.get(i);
        heapArray.set(i, heapArray.get(largest));
        heapArray.set(largest, temp);
    }

    //interface
    public void buildHeap(HeapType type) {
        buildHeap(this.array, type);
    }

    //Time: O(n)
    public void buildHeap(List<Integer> array, HeapType type) {
        for (int i = array.size() / 2 - 1; i >= 0; --i) {
            heapify(array, i, type);
        }
    }

    //Time: O(nlogn)
    public void heapSort(List<Integer> array, HeapType type) {
        buildHeap(array, type);
        int size = array.size();
        for (int i = array.size() - 1; i > 0; --i) {
            swap(array, 0, i);
            size -= 1;
            heapify(array, size, 0, type);
        }
    }

    /******* priority queue *******/


    public int heapMaximum() {
        return heapMaximum(this.array);
    }

    public int extractMax() {
        return extractMax(this.array);
    }

    public void heapIncreaseKey(int i, int key) {
        heapIncreaseKey(this.array, i, key);
    }

    public void maxHeapInsert(int key) {
        maxHeapInsert(this.array, key);
    }

    /**
     * Returns the largest key.
     * Time: O(1)
     */
    public int heapMaximum(List<Integer> array) {
        return array.get(0);
    }
    
    /**
     * Removes and returns the largest key.
     * Time: O(logn)
     */ 
    public int extractMax(List<Integer> array) {
        if (array == null || array.size() < 1) {
            throw new IllegalArgumentException("heap null or underflow");
        }
        int max = heapMaximum(array);
        array.set(0, array.get(this.heapSize - 1));
        this.heapSize -= 1;
        heapify(array, heapSize, 0, HeapType.MAX);
        return max;
    }

    /**
     * Increases the current key at i to a new key which is no smaller than the current key.
     * Time: O(logn)
     */
    public void heapIncreaseKey(List<Integer> array, int i, int key) {
        if (key < array.get(i)) {
            throw new IllegalArgumentException("new key is smaller than current key");
        }
        array.set(i, key);
        while (i > 0 && array.get(getParent(i)) < array.get(i)) {
            swap(array, i, getParent(i));
            i = getParent(i);
        }
    }

    /**
     * Inserts a key into the heap.
     * Time: O(logn)
     */
    public void maxHeapInsert(List<Integer> array, int key) {
        this.heapSize += 1;
        array.add(Integer.MIN_VALUE);
        heapIncreaseKey(array, this.heapSize - 1, key);
    }
}	   
	    

