package a01;

import java.util.NoSuchElementException;

/**
 * NumberList is a singly-linked list of double values.
 * It is designed as a practice opportunity to
 * learn how to manipulate linked structures.
 * 
 * @author CS Starter Code + chantay
 */
public class NumberList {
    private Node head; // first node of the list or null
    private Node tail; // last node of the list or null
    private int n;     // number of elements in the list

    /**
     * Node of LinkedList that stores the item and a
     * single reference to the next node.
     */
    private static class Node {
        private double item;
        private Node next;
    }
    
    /**
     * Returns the number of elements in the list.
     * 
     * @return the number of elements in the list.
     */   
    public int size() {
        return n;
    }
      
    /** 
     * Determines whether the list is empty or not.
     * 
     * @return true if there are no elements in the list, false otherwise.
     */
    public boolean isEmpty() {
        return n == 0;
    }
    
    /** 
     * Adds a node containing the new item at the end of the list.
     * 
     * @param item the item to be added to the list.
     */
    public void add(double item) {
        Node newNode = new Node();
        newNode.item = item;
        
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        n++;
    }
    
    /**
     * Returns the first element of the list.
     * 
     * Example: 
     * [1.1, 2.5, 3.0, 4.4, 5.5] -> returns 1.1
     * 
     * @return the first element.
     * @throws NoSuchElementException if the list is empty.
     */
    public double firstElement() { 
    	if (isEmpty()) throw new NoSuchElementException("The list is empty.");
        return head.item;
    }
    
    /**
     * Determines if the last element is a negative number.
     * 
     * @return true if the last element is negative, false otherwise.
     * @throws IllegalStateException if the list is empty.
     */
    public boolean endsNegative() {
    	if (tail.item < 0) return true;
        return false;
    }
    
    /**
     * Calculates and returns the product of all elements in the linked list.
     *
     * Example: 
     * [1.5, 2.2, 3.0, 4.4] -> returns 43.56 // 1.5 * 2.2 * 3 * 4.4
     *
     * @return the product of the elements in the linked list.
     * @throws IllegalStateException if the list is empty.
     */
    public double product() {
    	if (isEmpty()) throw new IllegalStateException("The list is empty.");
    	double total = 1;
    	Node current = head;
    	while (current != null) {
    		total *= current.item;
    		current = current.next;
    	}
    	return total;
    }
    
    /**
     * Inserts a new item at the specified index in the linked list.
     * The index should be in the range [0, n], where n is the current size of the list.
     * If the index is equal to n, the item is added to the end of the list.
     *
     * Example: index = 2 and item = 0.5
     * [1.1, 2.5, 3.0, 4.4, 5.5] -> [1.1, 2.5, 0.5, 3.0, 4.4, 5.5]
     * 
     * @param index the index at which the new item will be inserted.
     * @param item the item to be inserted into the linked list.
     * @throws IndexOutOfBoundsException if the index is outside the valid range [0, n].
     */
    public void insert(int index, double item) {
        if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException("Index outside of valid range");
        }

        Node newNode = new Node();
        newNode.item = item;

        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (n == 0) {
                tail = newNode; 
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;

            if (index == n) {
                tail = newNode; 
            }
        }

        n++;
    }
    
    /**
     * Rotates the linked list to the left by a specified number 
     * of positions in a cyclic manner.
     * If the list is empty or <code>positions</code> is a multiple of n, no rotation 
     * will be performed.
     *     
     * Example: positions = 2
     * [1.1, 2.2, 3.3, 4.4, 5.5] -> [3.3, 4.4, 5.5, 1.1, 2.2]
     *
     * Example: positions = 6
     * [1.1, 2.2, 3.3, 4.4, 5.5] -> [2.2, 3.3, 4.4, 5.5, 1.1]
     * 
     * @param positions the number of positions to rotate the linked list to the left.
     * @throws IllegalArgumentException if the number of positions is not positive.
     */
    public void rotateLeft(int positions) {
        if (positions <= 0) {
            throw new IllegalArgumentException("positions must be positive");
        }
        if (isEmpty() || positions % n == 0) {
            return; 
        }

        positions = positions % n; 

        Node newTail = head;
        for (int i = 1; i < positions; i++) {
            newTail = newTail.next;
        }

        Node newHead = newTail.next;

        newTail.next = null;

        tail.next = head;
        head = newHead;
        tail = newTail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        
        while (current != null) {
            sb.append(current.item).append(" ");
            current = current.next;
        }
        
        return sb.toString().trim();
    }
    
    /* * * * * * * * Test Client * * * * * * */
	
    public static void main(String[] args) {
        NumberList list = new NumberList();

        try {
            list.firstElement();
            System.out.println("FAIL: firstElement() should have thrown an exception");
        } catch (NoSuchElementException e) {
            System.out.println("PASS: firstElement() correctly threw on empty list");
        }

        try {
            list.product();
            System.out.println("FAIL: product() should have thrown an exception");
        } catch (IllegalStateException e) {
            System.out.println("PASS: product() correctly threw on empty list");
        }

        list.add(1.1);
        list.add(2.2);
        list.add(3.3);
        list.add(4.4);
        list.add(5.5);
        System.out.println("List after adding: " + list);  

        System.out.println("First element: " + list.firstElement()); 

        System.out.println("Ends negative " + list.endsNegative()); 

        list.add(-6.6);
        System.out.println("Ends negative after adding -6.6 " + list.endsNegative()); 

        System.out.printf("Produc", list.product()); 

        list.insert(2, 9.9);
        System.out.println("After insert at index 2: " + list);  

        list.insert(0, 0.0);
        System.out.println("After insert at beginning: " + list); 

        list.insert(list.size(), 7.7);
        System.out.println("After insert at end: " + list); 

        list.rotateLeft(3);
        System.out.println("After rotateLeft(3): " + list);

        list.rotateLeft(10); 
        System.out.println("After rotateLeft(10): " + list);

        try {
            list.rotateLeft(0);
            System.out.println("FAIL should hjave thrown error");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS correctly throw error");
        }

        try {
            list.insert(-1, 99.9);
            System.out.println("FAIL throw error");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("PASS correctly throw error");
        }

        try {
            list.insert(list.size() + 1, 99.9);
            System.out.println("FAIL should have throw error");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("PASS correctly throw error");
        }
    }


}
