/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/** Represents a random word picker */
public class RandomWord {

  /**
   * Reads a sequence of words from standard input and prints one of those words uniformly at random
   *
   * @param args sequence of words
   */
  public static void main(String[] args) {
    int index = 0;
    String champion = "";

    while (!StdIn.isEmpty()) {
      String word = StdIn.readString();
      boolean accept = StdRandom.bernoulli(1 / (index + 1.0));
      if (accept) {
        champion = word;
      }
      index++;
    }
    StdOut.println(champion);
  }
}
