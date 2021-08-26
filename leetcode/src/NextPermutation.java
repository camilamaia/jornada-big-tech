/**
 * Problem: https://leetcode.com/explore/interview/card/google/59/array-and-strings/3050/ Reference:
 * https://www.youtube.com/watch?v=quAS1iydq7U
 */
class Solution {
  public void nextPermutation(int[] nums) {
    int size = nums.length;
    int pointer = size - 1;

    while (pointer > 0) {
      if (nums[pointer] > nums[pointer - 1]) {
        int nextGreaterIndex = findNextGreaterIndex(nums, pointer - 1);
        swap(nums, pointer - 1, nextGreaterIndex);
        reverse(nums, pointer);
        return;
      }

      pointer--;
    }

    reverse(nums, pointer);
  }

  private int findNextGreaterIndex(int[] nums, int currentIndex) {
    int nextPointer = nums.length - 1;
    while (nums[nextPointer] <= nums[currentIndex]) {
      nextPointer--;
    }

    return nextPointer;
  }

  private void reverse(int[] nums, int start) {
    int rightPointer = nums.length - 1;

    while (start < rightPointer) {
      swap(nums, start, rightPointer);
      start++;
      rightPointer--;
    }
  }

  private void swap(int[] nums, int position1, int position2) {
    int old1 = nums[position1];
    nums[position1] = nums[position2];
    nums[position2] = old1;
  }
}
