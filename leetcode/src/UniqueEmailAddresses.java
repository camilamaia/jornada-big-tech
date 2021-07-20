/**
 * Problem: https://leetcode.com/explore/interview/card/google/67/sql-2/3044/
 */

class Solution {
  public int numUniqueEmails(String[] emails) {
    Set<String> uniqueEmails = new HashSet<String>();

    for (String email: emails) {
      String[] emailParts = email.split("@");
      String localName = emailParts[0];
      String domainName = emailParts[1];

      if (localName.contains("+")){
        String localNameWithPlusSign = localName;
        localName = localNameWithPlusSign.split("\\+")[0];
      }

      if (localName.contains(".")){
        String localNameWithPeriod = localName;
        localName = localNameWithPeriod.replace(".","");
      }

      String formattedEmail = localName + "@" + domainName;
      uniqueEmails.add(formattedEmail);
    }

    return uniqueEmails.size();
  }
}