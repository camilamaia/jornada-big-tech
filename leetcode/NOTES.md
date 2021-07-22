## String

The java string replace() method returns a string replacing all the old char or CharSequence to new
char or CharSequence.

The java string length() method length of the string. It returns count of total number of
characters. The length of java string is same as the unicode code units of the string.

The java string toUpperCase() method returns the string in uppercase letter. In other words,
it converts all characters of the string into upper case letter.

### Substring

The java string substring() method returns a part of the string.

We pass begin index and end index number position in the java substring method where start index is
inclusive and end index is exclusive. In other words, start index starts from 0 whereas end index
starts from 1.

There are two types of substring methods in java string.

```java
public String substring(int startIndex)
```

and

```java
public String substring(int startIndex, int endIndex)
```

If you don't specify endIndex, java substring() method will return all the characters from startIndex.

### Join

The java string join() method returns a string joined with given delimiter. In string join method, delimiter is copied for each elements.

In case of null element, "null" is added. The join() method is included in java string since JDK 1.8.

```java
public static String join(CharSequence delimiter, CharSequence... elements)
```

Ex:

```java
String joinString1=String.join("-","welcome","to","javatpoint");
```
