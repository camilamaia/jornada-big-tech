/* *****************************************************************************
 *  Name: Camila Maia
 *  Date: Aug 17th 2021
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 *  Reference: https://github.com/ufarobot/Deques-and-Randomized-Queues/blob/master/src/RandomizedQueue.java
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Item[] items;

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] randomIndexes = new int[size];
        private int totalSelected = 0;

        public RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                randomIndexes[i] = i;
            }
            StdRandom.shuffle(randomIndexes);
        }

        public boolean hasNext() {
            return totalSelected < size && size != 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more items to return");
            }
            int selectedIndex = randomIndexes[totalSelected++];
            return items[selectedIndex];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Constructs an empty randomized queue
     */
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    /**
     * @return true if the randomized queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return the number of items on the randomized queue
     */
    public int size() {
        return size;
    }

    /**
     * @param item to be added
     */
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item must not be null");
        if (size == items.length) resize(2 * items.length);
        if (size == 0) {
            items[size++] = item;
            return;
        }
        items[size++] = item;
    }

    /**
     * Removes and returns a random item
     *
     * @return a random item
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue is empty");
        if (size > 0 && size == items.length / 4) resize(items.length / 2);

        int selectedIndex = StdRandom.uniform(size);
        Item item = items[selectedIndex];
        items[selectedIndex] = items[--size];
        items[size] = null;
        return item;
    }

    /**
     * Returns a random item (but do not remove it)
     *
     * @return a random item
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue is empty");
        return items[StdRandom.uniform(size)];
    }

    /**
     * @return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    /**
     * unit testing (required)
     *
     * @param args
     */
    public static void main(String[] args) {
        runTests();
    }

    private void resize(int newCapacity) {
        Item[] copy = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            copy[i] = items[i];
        items = copy;
    }

    private static void runTests() {
        System.out.println("\n### TESTS ###");
        testIsEmpty();
        testSize();
        testEnqueue();
        testDequeue();
        testSample();
        testIterator();
    }

    private static void testIsEmpty() {
        System.out.println("\ntestIsEmpty");

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        System.out.println("when size is 0");
        rq.size = 0;
        compare("rq.isEmpty()", rq.isEmpty(), true);

        System.out.println("when size is 1");
        rq.size = 1;
        compare("rq.isEmpty()", rq.isEmpty(), false);
    }

    private static void testSize() {
        System.out.println("\ntestSize");

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        System.out.println("when size is 0");
        rq.size = 0;
        compare("rq.size()", rq.size(), 0);

        System.out.println("when size is 1");
        rq.size = 1;
        compare("rq.size()", rq.size(), 1);
    }

    private static void testEnqueue() {
        System.out.println("\ntestEnqueue");

        System.out.println("when it started empty");
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("A");
        // System.out.println("Items: " + Arrays.toString(rq.items));
        compare("rq.size()", rq.size(), 1);

        System.out.println("when there is more element already there");
        RandomizedQueue<String> rq2 = new RandomizedQueue<String>();
        rq2.enqueue("a");
        rq2.enqueue("b");
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        compare("rq2.size()", rq2.size(), 2);
        rq2.enqueue("c");
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        compare("rq2.size()", rq2.size(), 3);
        rq2.enqueue("d");
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        compare("rq2.size()", rq2.size(), 4);

        System.out.println("when item is null");
        RandomizedQueue<String> rq3 = new RandomizedQueue<String>();
        try {
            rq.enqueue(null);
            System.out.println(
                    "ERROR!!! rq.enqueue() with item null did not raise IllegalArgumentException");
        }
        catch (IllegalArgumentException ignored) {
        }
    }

    private static void testDequeue() {
        System.out.println("\ntestDequeue");

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        System.out.println("when it is empty");
        try {
            rq.dequeue();
            System.out.println("ERROR!!! rq.dequeue() did not raise NoSuchElementException");
        }
        catch (NoSuchElementException ignored) {
        }

        System.out.println("when there is only one element");
        rq.enqueue("A");
        compare("rq.dequeue();", rq.dequeue(), "A");
        compare("rq.size()", rq.size(), 0);
        // System.out.println("Items: " + Arrays.toString(rq.items));

        System.out.println("when there are other empty entries");
        rq.enqueue("a");
        rq.enqueue("b");
        rq.enqueue("c");
        rq.enqueue("d");
        // System.out.println("Items: " + Arrays.toString(rq.items));
        compare("rq.size()", rq.size(), 4);
        rq.dequeue();
        // System.out.println("Items: " + Arrays.toString(rq.items));
        compare("rq.size()", rq.size(), 3);
        rq.enqueue("x");
        rq.enqueue("w");
        rq.enqueue("y");
        // System.out.println("Items: " + Arrays.toString(rq.items));
        if (rq.dequeue() == null) {
            System.out.println("ERROR!!! Dequeue a null item");
        }
        // System.out.println("Items: " + Arrays.toString(rq.items));

        System.out.println("when items is too empty");
        RandomizedQueue<String> rq2 = new RandomizedQueue<String>();
        rq2.enqueue("1");
        rq2.enqueue("2");
        rq2.enqueue("3");
        rq2.enqueue("4");
        rq2.enqueue("5");
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.dequeue();
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.dequeue();
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.dequeue();
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.enqueue("6");
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.enqueue("7");
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.enqueue("8");
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.enqueue("9");
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.dequeue();
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.dequeue();
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.dequeue();
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.dequeue();
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.dequeue();
        // System.out.println("Items: " + Arrays.toString(rq2.items));
        rq2.dequeue();
        // System.out.println("Items: " + Arrays.toString(rq2.items));
    }

    private static void testSample() {
        System.out.println("\ntestSample");

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        System.out.println("when it is empty");
        try {
            rq.sample();
            System.out.println("ERROR!!! rq.sample() did not raise NoSuchElementException");
        }
        catch (NoSuchElementException ignored) {
        }

        System.out.println("does not return a null sample");
        rq.enqueue("x");
        rq.enqueue("y");
        rq.dequeue();
        System.out.println("sample: " + rq.sample());
        if (rq.sample() == null) {
            System.out.println("ERROR!!! Return a null sample");
        }
    }

    private static void testIterator() {
        System.out.println("\ntestIterator");

        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        Iterator<String> it = rq.iterator();
        System.out.println("when it is empty");
        compare("it.hasNext()", it.hasNext(), false);
        try {
            it.next();
            System.out.println("ERROR!!! it.next() did not raise NoSuchElementException");
        }
        catch (NoSuchElementException ignored) {
        }

        rq.enqueue("a");
        // System.out.println("Items: " + Arrays.toString(rq.items));
        rq.enqueue("b");
        // System.out.println("Items: " + Arrays.toString(rq.items));
        rq.enqueue("c");
        // System.out.println("Items: " + Arrays.toString(rq.items));
        rq.enqueue("d");
        // System.out.println("Items: " + Arrays.toString(rq.items));
        rq.enqueue("e");
        // System.out.println("Items: " + Arrays.toString(rq.items));
        rq.dequeue();
        // System.out.println("Items: " + Arrays.toString(rq.items));

        System.out.println("Foor Loop");
        for (String item : rq) {
            System.out.print(item);
        }

        try {
            it.remove();
            System.out.println("ERROR!!! it.remove() did not raise UnsupportedOperationException");
        }
        catch (UnsupportedOperationException ignored) {
        }
    }

    private static void compare(String command, Object result, Object expected) {
        System.out.println(command + " -> " + result + " - " + expected);
        String errorMessage =
                "ERROR!!! Result and expected dont match." +
                        " Result: " + result + " Expected: " + expected;
        if (result != expected) System.out.println(errorMessage);
    }
}