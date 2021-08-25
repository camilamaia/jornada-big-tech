/** Problem: https://leetcode.com/explore/interview/card/google/59/array-and-strings/3049/ */
class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();

    Arrays.sort(nums);

    for (int i = 0; i < nums.length; i++) {
      if (i != 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      twoSum(i, nums, results);
    }

    return results;
  }

  private void twoSum(int i, int[] nums, List<List<Integer>> results) {
    int leftPointer = i + 1;
    int rightPointer = nums.length - 1;
    int target = -nums[i];

    while (leftPointer < rightPointer) {
      int sum = nums[leftPointer] + nums[rightPointer];

      if (sum == target) {
        List<Integer> triple = new ArrayList<Integer>();
        triple.add(nums[i]);
        triple.add(nums[leftPointer]);
        triple.add(nums[rightPointer]);
        results.add(triple);
        leftPointer++;

        while (leftPointer < rightPointer && nums[leftPointer] == nums[leftPointer - 1]) {
          leftPointer++;
        }
      } else if (sum < target) {
        leftPointer++;
      } else {
        rightPointer--;
      }
    }
  }
}
