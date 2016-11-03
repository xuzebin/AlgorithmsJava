/**
 * Queue implemented in LinkedList.
 * Provide operations:
 * enqueue
 * dequeue
 * getFirst
 * getLast 
 * getLength
 *
 **/
package edu.nyu.algorithms;

public class Queue<E> {

    private static class Node<E> {
	E value;
	Node<E> next;
	Node(E value) {
	    this.value = value;
	}
    }

    private Node<E> head;
    private Node<E> tail;
    private int length;

    public Queue() {
	head = null;
	tail = null;
	length = 0;
    }

    public void enqueue(E element) {
	Node<E> node = new Node<E>(element);
		
	if (head == null) {
	    head = node;
	    tail = node;
	} else {
	    tail.next = node;
	    tail = tail.next;
	}

	length += 1;
    }
    public E dequeue() {
	if (head == null) {
	    return null;
	}
	if (head == tail) {
	    tail = null;
	}

	E deleteValue = head.value;

	head = head.next;
	length -= 1;

	return deleteValue;
    }

    public E getFirst() {
	return length > 0 ? head.value : null;
    }
    public E getLast() {
	return length > 0 ? tail.value : null;
    }

    public int getLength() {
	return length;
    }

    public static void main(String[] args) {
	Queue<Integer> queue = new Queue<Integer>();
	queue.enqueue(1);
	queue.enqueue(2);
	for (int i = 0; i < 10; ++i) {
	    queue.enqueue(i);
	}

	// queue.dequeue();

	System.out.printf("first: %d%n", queue.getFirst());
	System.out.printf("last: %d%n", queue.getLast());
	System.out.printf("length: %d%n", queue.getLength());
	for (int i = 0; i < 12; ++i) {
	    System.out.printf("%d, ", queue.dequeue());
	}
	System.out.printf("%n");

	System.out.printf("%d%n", queue.getFirst());
	System.out.printf("%d%n", queue.dequeue());
    }

}