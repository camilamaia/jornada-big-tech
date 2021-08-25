/** Problem:https://leetcode.com/explore/interview/card/google/59/array-and-strings/3047/ */
class Solution {
  public int lengthOfLongestSubstring(String s) {
    int start = 0;
    int end = 0;
    int currentStart = 0;
    int currentEnd = 0;
    int len = s.length();

    if (len == 0 || len == 1) {
      return len;
    }

    for (int i = 0; i < len; i++) {
      char letter = s.charAt(i);

      if (i == 0) {
        continue;
      }

      int lastOccurrence = s.substring(currentStart, currentEnd + 1).lastIndexOf(letter);

      if (lastOccurrence == -1) {
        currentEnd++;
      } else {
        if (currentEnd - currentStart > end - start) {
          start = currentStart;
          end = currentEnd;
        }
        currentStart = currentStart + lastOccurrence + 1;
        currentEnd = i;
      }
    }

    if (currentEnd - currentStart > end - start) {
      start = currentStart;
      end = currentEnd;
    }

    return end - start + 1;
  }
}
