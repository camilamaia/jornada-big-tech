/* *****************************************************************************
 *  Name: Camila Maia
 *  Date: Aug 17th 2021
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    /**
     * Representation of a Node in a linked list
     */
    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    /**
     * Represents a deque iterator, that iterates from front to back
     */
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null)
                throw new NoSuchElementException("There are no more items to return");
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Is the deque empty?
     *
     * @return true if deque is empty, false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }


    /**
     * @return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * Adds the item to the front of the deque
     */
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item must not be null");

        Node second = first;
        first = new Node();

        if (second == null) {
            last = first;
        }
        else {
            first.next = second;
            second.previous = first;
        }
        first.item = item;
        size++;
    }

    /**
     * Adds the item to the back of the deque
     */
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item must not be null");

        if (last == null) {
            addFirst(item);
        }
        else {
            Node secondLast = last;
            last = new Node();
            last.item = item;
            last.previous = secondLast;
            secondLast.next = last;
            size++;
        }

    }

    /**
     * Removes and returns the item from the front
     *
     * @return the first item
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        if (first.next == null) {
            Item firstItem = first.item;
            first = null;
            size--;
            return firstItem;
        }

        Node oldFirst = first;
        first = first.next;
        first.previous = null;
        size--;
        return oldFirst.item;
    }

    /**
     * Removes and returns the item from the back
     *
     * @return the last item
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Node oldLast = last;
        if (oldLast.previous == null) {
            Item lastItem = last.item;
            last = null;
            first = last;
            size--;
            return lastItem;
        }
        last = oldLast.previous;
        last.next = null;
        size--;
        return oldLast.item;
    }

    /**
     * @return an iterator over items in order from front to back
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /**
     * unit testing (required)
     *
     * @param args
     */
    public static void main(String[] args) {
        Deque<String> d1 = new Deque<String>();
        System.out.println(d1.isEmpty());
        System.out.println(d1.size());
        d1.addFirst("hi");
        d1.addLast("hello");
        d1.addLast("world");
        d1.addLast("hey");

        d1.removeFirst();
        d1.removeLast();

        for (String item : d1) {
            System.out.println(item);
        }

        runTests();
    }

    private static void runTests() {
        testEmpty();
        testAddFirstRemoveFirst();
        testAddFirstRemoveLast();
        testAddLastRemoveFirst();
        testAddLastRemoveLast();
        test();
    }

    private static void testEmpty() {
        System.out.println("testAddFirstRemoveFirst");
        System.out.println("Deque<String> d1 = new Deque<String>();");
        Deque<String> d1 = new Deque<String>();
        compare("d1.iterator().hasNext()", d1.iterator().hasNext(), false);
        // System.out.println(
        //         d1.iterator().next()); // NoSuchElementException: There are no more items to return
        // d1.iterator().remove(); // UnsupportedOperationException
        compare("d1.isEmpty()", d1.isEmpty(), true);
        compare("d1.size()", d1.size(), 0);
        // d1.removeFirst(); // NoSuchElementException: Deque is empty
        // d1.removeLast(); // NoSuchElementException: Deque is empty
    }

    private static void testAddFirstRemoveFirst() {
        System.out.println("\ntestAddFirstRemoveFirst");
        System.out.println("Deque<String> d1 = new Deque<String>();");
        Deque<String> d1 = new Deque<String>();
        System.out.println("d1.addFirst(\"oi\");");
        d1.addFirst("oi");
        compare("d1.iterator().hasNext()", d1.iterator().hasNext(), true);
        compare("d1.iterator().next()", d1.iterator().next(), "oi");
        // d1.iterator().remove(); // UnsupportedOperationException
        compare("d1.isEmpty()", d1.isEmpty(), false);
        compare("d1.size()", d1.size(), 1);
        compare("d1.removeFirst()", d1.removeFirst(), "oi");
        compare("d1.isEmpty()", d1.isEmpty(), true);
    }

    private static void testAddFirstRemoveLast() {
        System.out.println("\ntestAddFirstRemoveLast");
        System.out.println("Deque<String> d1 = new Deque<String>();");
        Deque<String> d1 = new Deque<String>();
        System.out.println("d1.addFirst(\"tchau\");");
        d1.addFirst("tchau");
        compare("d1.iterator().hasNext()", d1.iterator().hasNext(), true);
        compare("d1.iterator().next()", d1.iterator().next(), "tchau");
        // d1.iterator().remove(); // UnsupportedOperationException
        compare("d1.isEmpty()", d1.isEmpty(), false);
        compare("d1.size()", d1.size(), 1);
        compare("d1.removeLast()", d1.removeLast(), "tchau");
        compare("d1.size()", d1.size(), 0);
    }

    private static void testAddLastRemoveFirst() {
        System.out.println("\ntestAddLastRemoveFirst");
        System.out.println("Deque<String> d1 = new Deque<String>();");
        Deque<String> d1 = new Deque<String>();
        System.out.println("d1.addLast(\"opa\");");
        d1.addLast("opa");
        compare("d1.iterator().hasNext()", d1.iterator().hasNext(), true);
        compare("d1.iterator().next()", d1.iterator().next(), "opa");
        // d1.iterator().remove(); // UnsupportedOperationException
        compare("d1.isEmpty()", d1.isEmpty(), false);
        compare("d1.size()", d1.size(), 1);
        compare("d1.removeFirst()", d1.removeFirst(), "opa");
        compare("d1.isEmpty()", d1.isEmpty(), true);
        compare("d1.size()", d1.size(), 0);
    }

    private static void testAddLastRemoveLast() {
        System.out.println("\ntestAddLastRemoveLast");
        System.out.println("Deque<String> d1 = new Deque<String>();");
        Deque<String> d1 = new Deque<String>();
        System.out.println("d1.addLast(\"salve\");");
        d1.addLast("salve");
        compare("d1.iterator().hasNext()", d1.iterator().hasNext(), true);
        compare("d1.iterator().next()", d1.iterator().next(), "salve");
        // d1.iterator().remove(); // UnsupportedOperationException
        compare("d1.isEmpty()", d1.isEmpty(), false);
        compare("d1.size()", d1.size(), 1);
        compare("d1.removeLast()", d1.removeLast(), "salve");
        compare("d1.isEmpty()", d1.isEmpty(), true);
        compare("d1.size()", d1.size(), 0);
    }

    private static void test() {
        System.out.println("\ntest");
        System.out.println("Deque<String> d1 = new Deque<String>();");
        Deque<String> d1 = new Deque<String>();
        System.out.println("d1.addLast(\"wazup\");");
        d1.addLast("wazup");
        System.out.println(
                "d1.iterator().hasNext() -> " + d1.iterator().hasNext() + " - true");
        System.out.println("d1.iterator().next() -> " + d1.iterator().next() + " - wazup");
        // d1.iterator().remove(); // UnsupportedOperationException
        System.out.println("d1.isEmpty() -> " + d1.isEmpty() + " - false");
        System.out.println("d1.size() -> " + d1.size() + " - 1");

        System.out.println();
        System.out.println("d1.addLast(\"hein?\");");
        d1.addLast("hein?");
        System.out.println(
                "d1.iterator().hasNext() -> " + d1.iterator().hasNext() + " - true");
        System.out.println("d1.iterator().next() -> " + d1.iterator().next() + " - hein");
        // d1.iterator().remove(); // UnsupportedOperationException
        System.out.println("d1.isEmpty() -> " + d1.isEmpty() + " - false");
        System.out.println("d1.size() -> " + d1.size() + " - 2");

        System.out.println();
        System.out.println("For loop");
        for (String item : d1) {
            System.out.println(item);
        }

        System.out.println("Expected:");
        System.out.println("wazup");
        System.out.println("hein?");

        System.out.println();
        System.out.println("d1.addFirst(\"Hey!\");");
        d1.addFirst("Hey!");

        System.out.println();
        System.out.println("For loop");
        for (String item : d1) {
            System.out.println(item);
        }

        System.out.println("Expected:");
        System.out.println("Hey!");
        System.out.println("wazup");
        System.out.println("hein?");

        compare("d1.isEmpty()", d1.isEmpty(), false);
        compare("d1.size()", d1.size(), 3);
        compare("d1.removeLast()", d1.removeLast(), "hein?");
        compare("d1.removeLast()", d1.removeLast(), "wazup");
        compare("d1.removeLast()", d1.removeLast(), "Hey!");
        // d1.removeFirst(); // NoSuchElementException: Deque is empty
        // d1.removeLast(); // NoSuchElementException: Deque is empty
    }

    private static void compare(String command, Object result, Object expected) {
        System.out.println(command + " -> " + result + " - " + expected);
        String errorMessage =
                "ERROR!!! Result and expected dont match." +
                        " Result: " + result + " Expected: " + expected;
        if (result != expected) System.out.println(errorMessage);
    }
}
