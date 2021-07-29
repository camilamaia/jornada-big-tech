/**
 * Problem: https://leetcode.com/explore/interview/card/google/67/sql-2/3046/ . Solution Reference:
 * https://www.youtube.com/watch?v=s_zu2dOkq80&t=7s
 */
class Solution {
  public int totalFruit(int[] fruits) {
    int fruitsSize = fruits.length;

    if (fruitsSize <= 2) {
      return fruitsSize;
    }

    int lastFruit = -1;
    int secondLastFruit = -1;
    int lastFruitCount = 0;
    int currentMax = 0;
    int max = 0;

    for (Integer fruit : fruits) {
      if (fruit == lastFruit || fruit == secondLastFruit) {
        currentMax++;
      } else {
        currentMax = lastFruitCount + 1;
      }

      if (fruit == lastFruit) {
        lastFruitCount++;
      } else {
        lastFruitCount = 1;
      }

      if (fruit != lastFruit) {
        secondLastFruit = lastFruit;
        lastFruit = fruit;
      }

      max = Math.max(currentMax, max);
    }

    return max;
  }
}
