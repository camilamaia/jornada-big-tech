class Solution {
  public int numUniqueEmails(String[] emails) {
    // hash set to store all the unique emails
    Set<String> uniqueEmails = new HashSet<>();

    for (String email : emails) {
      // split into two parts local and domain
      String[] parts = email.split("@");

      // split local by '+'
      String[] local = parts[0].split("\\+");

      // remove all '.', and concatenate '@' and append domain
      uniqueEmails.add(local[0].replace(".", "") + "@" + parts[1]);
    }

    return uniqueEmails.size();
  }
}
