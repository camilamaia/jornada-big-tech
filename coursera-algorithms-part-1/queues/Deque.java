/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

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
     * @return
     */
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    /**
     * add the item to the front
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
     * add the item to the back
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
            if (secondLast != null) {
                secondLast.next = last;
            }
            size++;
        }

    }

    /**
     * Remove and return the item from the front
     *
     * @return
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
     * remove and return the item from the back
     *
     * @return
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
     * return an iterator over items in order from front to back
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
        System.out.println("Deque<String> d1 = new Deque<String>();");
        Deque<String> d1 = new Deque<String>();
        System.out.println(
                "d1.iterator().hasNext() -> " + d1.iterator().hasNext() + " - false");
        // System.out.println(
        //         d1.iterator().next()); // NoSuchElementException: There are no more items to return
        // d1.iterator().remove(); // UnsupportedOperationException
        System.out.println("d1.isEmpty() -> " + d1.isEmpty() + " - true");
        System.out.println("d1.size() -> " + d1.size() + " - 0");
        // d1.removeFirst(); // NoSuchElementException: Deque is empty
        // d1.removeLast(); // NoSuchElementException: Deque is empty

        System.out.println();
        System.out.println("d1.addFirst(\"oi\");");
        d1.addFirst("oi");
        System.out.println(
                "d1.iterator().hasNext() -> " + d1.iterator().hasNext() + " - true");
        System.out.println("d1.iterator().next() -> " + d1.iterator().next() + " - oi");
        // d1.iterator().remove(); // UnsupportedOperationException
        System.out.println("d1.isEmpty() -> " + d1.isEmpty() + " - false");
        System.out.println("d1.size() -> " + d1.size() + " - 1");
        System.out.println("d1.removeFirst() -> " + d1.removeFirst() + " - oi");
        System.out.println("d1.isEmpty() -> " + d1.isEmpty() + " - true");
        System.out.println("d1.size() -> " + d1.size() + " - 0");

        System.out.println();
        System.out.println("d1.addFirst(\"tchau\");");
        d1.addFirst("tchau");
        System.out.println(
                "d1.iterator().hasNext() -> " + d1.iterator().hasNext() + " - true");
        System.out.println("d1.iterator().next() -> " + d1.iterator().next() + " - tchau");
        // d1.iterator().remove(); // UnsupportedOperationException
        System.out.println("d1.isEmpty() -> " + d1.isEmpty() + " - false");
        System.out.println("d1.size() -> " + d1.size() + " - 1");
        System.out.println("d1.removeLast() -> " + d1.removeLast() + " - tchau");
        System.out.println("d1.isEmpty() -> " + d1.isEmpty() + " - true");
        System.out.println("d1.size() -> " + d1.size() + " - 0");

        System.out.println();
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

        System.out.println("d1.isEmpty() -> " + d1.isEmpty() + " - false");
        System.out.println("d1.size() -> " + d1.size() + " - 3");
        System.out.println("d1.removeLast() -> " + d1.removeLast() + " - hein?");
        System.out.println("d1.removeLast() -> " + d1.removeLast() + " - wazup");
        System.out.println("d1.removeLast() -> " + d1.removeLast() + " - Hey!");
        // d1.removeFirst(); // NoSuchElementException: Deque is empty
        // d1.removeLast(); // NoSuchElementException: Deque is empty
    }
}
