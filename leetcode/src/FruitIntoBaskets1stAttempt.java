/**
 * Problem: https://leetcode.com/explore/interview/card/google/67/sql-2/3046/ . Example of a case
 * that it fails: [0,1,1,1,3,3]
 */
class Solution {
  public int totalFruit(int[] fruits) {
    int fruitsSize = fruits.length;

    if (fruitsSize <= 2) {
      return fruitsSize;
    }

    int totalCurrent = 2;
    HashSet<Integer> typesCurrent = new HashSet<Integer>();
    int totalLongest = 0;

    typesCurrent.add(fruits[0]);
    typesCurrent.add(fruits[1]);

    int next;

    for (int i = 1; i < fruitsSize; i++) {
      if (i == fruitsSize - 1) {
        if (totalCurrent > totalLongest) {
          totalLongest = totalCurrent;
        }
      } else {
        next = fruits[i + 1];

        if (typesCurrent.contains(next)) {
          totalCurrent++;
        } else {
          if (typesCurrent.size() == 1) {
            typesCurrent.add(next);
            totalCurrent++;
          } else {
            if (totalCurrent > totalLongest) {
              totalLongest = totalCurrent;
            }
            totalCurrent = 2;
            typesCurrent.clear();
            typesCurrent.add(fruits[i]);
            typesCurrent.add(next);
          }
        }
      }
    }

    return totalLongest;
  }
}
