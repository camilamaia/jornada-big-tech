/**
 * Problem: https://leetcode.com/explore/interview/card/google/67/sql-2/3045/ References:
 * https://massivealgorithms.blogspot.com/2019/03/leetcode-975-odd-even-jump.html
 * https://www.youtube.com/watch?v=r2I7KIqHTPU
 */
class Solution {
  public int oddEvenJumps(int[] arr) {
    int size = arr.length;
    int totalGoodIndexes = 1;

    boolean[] higher = new boolean[size];
    boolean[] lower = new boolean[size];

    higher[size - 1] = true;
    lower[size - 1] = true;

    TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    treeMap.put(arr[size - 1], size - 1);

    for (int i = size - 2; i >= 0; i--) {
      Map.Entry nextHigher = treeMap.ceilingEntry(arr[i]);
      Map.Entry nextLower = treeMap.floorEntry(arr[i]);

      if (nextHigher != null) {
        higher[i] = lower[(int) nextHigher.getValue()];
      }

      if (nextLower != null) {
        lower[i] = higher[(int) nextLower.getValue()];
      }

      if (higher[i]) {
        totalGoodIndexes++;
      }

      treeMap.put(arr[i], i);
    }

    return totalGoodIndexes;
  }
}
