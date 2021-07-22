/** Problem: https://leetcode.com/explore/interview/card/google/67/sql-2/472/ */
class Solution {
  public String licenseKeyFormatting(String s, int k) {
    s = s.replace("-", "");
    s = s.toUpperCase();

    int size = s.length();
    int remainder = size % k;
    int quotient = size / k;
    int numberOfGroups = remainder == 0 ? quotient : quotient + 1;

    String[] groups = new String[numberOfGroups];
    int start;
    int end;

    for (int i = 0; i < numberOfGroups; i++) {
      if (remainder != 0) {
        start = i == 0 ? 0 : (i - 1) * k + remainder;
        end = i * k + remainder;
      } else {
        start = i * k;
        end = (i + 1) * k;
      }

      groups[i] = s.substring(start, end);
    }

    String formattedKey = String.join("-", groups);

    return formattedKey;
  }
}
