/** Problem: https://leetcode.com/explore/interview/card/google/67/sql-2/3045/ */
class Solution {

  public int oddEvenJumps(int[] arr) {
    int totalGoodIndexes = 0;

    for (int i = 0; i < arr.length; i++) {
      int next = i;
      int nextJump = 1;
      boolean hasNext = true;

      while (hasNext) {
        if (next == arr.length - 1) {
          totalGoodIndexes++;
          hasNext = false;
        } else if (next == -1) {
          hasNext = false;
        } else {
          next = nextIndex(next, nextJump, arr);
          nextJump++;
        }
      }
    }

    return totalGoodIndexes;
  }

  private int nextIndex(int currentIndex, int jump, int[] arr) {
    if (jump % 2 != 0) {
      return nextOddJump(currentIndex, arr);
    } else {
      return nextEvenJump(currentIndex, arr);
    }
  }

  private int nextOddJump(int currentIndex, int[] arr) {
    int indexOfSmallest = -1;

    for (int i = currentIndex + 1; i < arr.length; i++) {
      int nextValue = arr[i];

      if (nextValue >= arr[currentIndex]
          && (indexOfSmallest == -1 || nextValue < arr[indexOfSmallest])) {
        indexOfSmallest = i;
      }
    }

    return indexOfSmallest;
  }

  private int nextEvenJump(int currentIndex, int[] arr) {
    int indexOfLargest = -1;

    for (int i = currentIndex + 1; i < arr.length; i++) {
      int nextValue = arr[i];

      if (nextValue <= arr[currentIndex]
          && (indexOfLargest == -1 || nextValue > arr[indexOfLargest])) {
        indexOfLargest = i;
      }
    }

    return indexOfLargest;
  }
}
