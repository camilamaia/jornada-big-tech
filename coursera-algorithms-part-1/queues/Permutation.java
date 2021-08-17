/* *****************************************************************************
 *  Name: Camila Maia
 *  Date: Aug 17th 2021
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k;
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        try {
            k = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException nfe) {
            System.out.println("The first argument must be an integer.");
            return;
        }

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            rq.enqueue(word);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(rq.iterator().next());
        }
    }
}
