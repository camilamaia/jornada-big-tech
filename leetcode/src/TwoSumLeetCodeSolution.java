/** Problem: https://leetcode.com/explore/interview/card/google/59/array-and-strings/3049/ */
class Solution {
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];

      if (map.containsKey(complement)) {
        return new int[] {map.get(complement), i};
      }

      map.put(nums[i], i);
    }

    return null;
  }
}
