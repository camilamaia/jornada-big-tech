/** Problem: https://leetcode.com/explore/interview/card/google/59/array-and-strings/3048/ */
class Solution {
  public int maxArea(int[] height) {
    int leftPointer = 0;
    int rightPointer = height.length - 1;
    int result = 0;
    int left;
    int right;
    int currentArea;

    while (leftPointer != rightPointer) {
      left = height[leftPointer];
      right = height[rightPointer];
      currentArea = area(leftPointer, left, rightPointer, right);
      result = Math.max(result, currentArea);

      if (height[leftPointer] < height[rightPointer]) {
        leftPointer++;

        while (height[leftPointer] <= left && leftPointer != rightPointer) {
          leftPointer++;
        }
      } else {
        rightPointer--;

        while (height[rightPointer] <= right && leftPointer != rightPointer) {
          rightPointer--;
        }
      }
    }

    return result;
  }

  private int area(int x1, int y1, int x2, int y2) {
    return (x2 - x1) * Math.min(y1, y2);
  }
}
