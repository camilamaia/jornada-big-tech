# Notes - Algorithms, Part I

## 1.5 UNION-FIND

Steps to developing a usable algorithm.

- Model the problem.
- Find an algorithm to solve it.
- Fast enough? Fits in memory?
- If not, figure out why.
- Find a way to address the problem. - Iterate until satisfied.

The scientific method. Mathematical analysis.

### Dynamic connectivity

Given a set of N objects.

- Union command: connect two objects.
- Find/connected query: is there a path connecting the two objects?

Modeling the objects

When programming, convenient to name objects 0 to N –1.

- Use integers as array index.
- Suppress details not relevant to union-find.

We assume "is connected to" is an equivalence relation:

- Reflexive: p is connected to p.
- Symmetric: if p is connected to q, then q is connected to p.
- Transitive: if p is connected to q and q is connected to r, then p is connected to r.

#### Quick Find

Data structure.

- Integer array id[] of length N.
- Interpretation: p and q are connected iff they have the same id

Methods

- init: set id of each object to itself (N array accesses)
- connected: check if p and q have the same id. (2 array accesses)
- union: To merge components containing p and q, change all entries whose id equals id[p] to id[q]
  (at most 2N + 2 array accesses)

| algorithm  | initialize | union | find |
| ---------- | ---------- | ----- | ---- |
| quick-find | N          | N     | 1    |
|            |            |       |      |

Union is too expensive. It takes N 2 array accesses to process a sequence of
N union commands on N objects

#### Quick Union [lazy approach]

Data structure

- Integer array id[] of length N.
- Interpretation: id[i] is parent of i.
- Root of i is id[id[id[...id[i]...]]].

Methods

- init: set id of each object to itself (N array accesses)
- root (private): chase parent pointers until reach root (depth of i array accesses)
- connected: Check if p and q have the same root.
- union: To merge components containing p and q, set the id of p's root to the id of q's root.
  (depth of p and q array accesses)

| algorithm   | initialize | union | find |
| ----------- | ---------- | ----- | ---- |
| quick-find  | N          | N     | 1    |
| quick-union | N          | N †   | N    |

† includes cost of finding roots

Quick-find defect.

- Union too expensive (N array accesses).
- Trees are flat, but too expensive to keep them flat.

Quick-union defect.

- Trees can get tall.
- Find too expensive (could be N array accesses).

#### Quick-Union Improvements

Improvement 1: weighting

Weighted quick-union.

- Modify quick-union to avoid **tall trees**.
- Keep track of size of each tree (number of objects).
- Balance by linking root of smaller tree to root of larger tree.

Data structure:

- Integer array id[] of length N.
- Integer array sz[] of length N to count number of objects in the tree rooted
  at i

Methods:

- init: same as quick-union, but creating the extra array sz[] and set sz of each object to 1.
- root (private): same as quick-union
- connected: identical to quick-union. return root(p) == root(q);
- union: Modify quick-union to:
  - Link root of smaller tree to root of larger tree.
  - Update the sz[] array.

Running time

- Find: takes time proportional to depth of p and q.
- Union: takes constant time, given roots.

**Proposition: Depth of any node x is at most lg N.**

| algorithm   | initialize | union  | find |
| ----------- | ---------- | ------ | ---- |
| quick-find  | N          | N      | 1    |
| quick-union | N          | N †    | N    |
| weighted QU | N          | lg N † | lg N |

† includes cost of finding roots

Improvement 2: path compression

Simpler one-pass variant: Make every other node in path point to its
grandparent (thereby halving path length).

In practice. No reason not to! Keeps tree almost completely flat.

Proposition. [Hopcroft-Ulman, Tarjan]:

Starting from an empty data structure, any sequence of M union-find ops on N objects
makes ≤ c ( N + M lg\* N ) array accesses.

- Analysis can be improved to N + M α(M, N).
- Simple algorithm with fascinating mathematics

Linear-time algorithm for M union-find ops on N objects?

- Cost within constant factor of reading in the data.
- In theory, WQUPC is not quite linear.
- In practice, WQUPC is linear.

Bottom line: Weighted quick union (with path compression) makes it
possible to solve problems that could not otherwise be addressed.

| algorithm                      | worst-case time |
| ------------------------------ | --------------- |
| quick-find                     | M N             |
| quick-union                    | M N             |
| weighted QU                    | N + M log N     |
| QU + path compression          | N + M log N     |
| weighted QU + path compression | N + M lg\* N    |

\* M union-find operations on a set of N objects

Ex. [10^9 unions and finds with 10^9 objects]
・WQUPC reduces time from 30 years to 6 seconds.
・Supercomputer won't help much; good algorithm enables solution.

### Percolation Assignment

- We model a percolation system using an n-by-n grid of sites
- Each site is either open or blocked
- A full site:
  - open site
  - can be connected to an open site in the top row via a chain of
    neighboring (left, right, up, down) open sites.
- We say the system percolates if:
  - There is a full site in the bottom row
  - In other words, a system percolates if we fill all open sites connected to the top row and that
    process fills some open site on the bottom row

## 1.4 ANALYSIS OF ALGORITHMS

### Cost of basic operations

<p>
  <img
    src="images/image1.png"
    width="500"
    alt="Cost of basic operations"
  >
</p>

<p>
  <img
    src="images/image2.png"
    width="500"
    alt="Cost of basic operations"
  >
</p>

<p>
  <img
    src="images/image3.png"
    width="500"
    alt="Cost of basic operations"
  >
</p>

<p>
  <img
    src="images/image4.png"
    width="500"
    alt="Cost of basic operations"
  >
</p>

### Common order-of-growth classifications

<p>
  <img
    src="images/image5.png"
    width="500"
    alt="Common order-of-growth classifications"
  >
</p>

### Binary search: Java implementation

<p>
  <img
    src="images/image6.png"
    width="500"
    alt="Binary search: Java implementation"
  >
</p>

### Commonly-used notations

<p>
  <img
    src="images/image7.png"
    width="500"
    alt="Commonly-used notations"
  >
</p>

### Typical memory usage for primitive types and arrays

<p>
  <img
    src="images/image8.png"
    width="500"
    alt="Typical memory usage for primitive types and arrays"
  >
</p>

### Typical memory usage for objects in Java

<p>
  <img
    src="images/image9.png"
    width="500"
    alt="Typical memory usage for objects in Java"
  >
</p>

<p>
  <img
    src="images/image10.png"
    width="500"
    alt="Typical memory usage for objects in Java"
  >
</p>

Total memory usage for a data type value:

- Primitive type: 4 bytes for int, 8 bytes for double, ...
- Object reference: 8 bytes.
- Array: 24 bytes + memory for each array entry.
- Object: 16 bytes + memory for each instance variable
  \+ 8 bytes if inner class (for pointer to enclosing class).
- Padding: round up to multiple of 8 bytes.

Shallow memory usage: Don't count referenced objects.

Deep memory usage: If array entry or instance variable is a reference, add memory (recursively) for referenced object.

## 2.1 ELEMENTARY SORTS

## Sort

Goal: Sort any type of data.

Question: How can sort() know how to compare data of type Double, String, and
java.io.File without any information about the type of an item's key?

- Callback = reference to executable code.
- Client passes array of objects to sort() function.
- The sort() function calls back object's compareTo() method as needed.

Implementing callbacks.

- Java: interfaces.
- C: function pointers.
- C++: class-type functors.
- C#: delegates.
- Python, Perl, ML, Javascript: first-class functions.

<p>
  <img
    src="images/image11a.png"
    width="500"
    alt="Sort: callbacks"
  >
</p>

Definition: A total order is a binary relation ≤ that satisfies:

- Antisymmetry: if v ≤ w and w ≤ v, then v = w.
- Transitivity: if v ≤ w and w ≤ x, then v ≤ x.
- Totality: either v ≤ w or w ≤ v or both.

Surprising but true: The <= operator for double is not a total order. (!)

- violates totality: (Double.NaN <= Double.NaN) is false

### Comparable API

Implement compareTo() so that v.compareTo(w)

- Is a total order.
- Returns a negative integer, zero, or positive integer
  if v is less than, equal to, or greater than w, respectively.
- Throws an exception if incompatible types (or either is null).

Built-in comparable types: Integer, Double, String, Date, File, ...

User-defined comparable types: Implement the Comparable interface.

<p>
  <img
    src="images/image11b.png"
    width="500"
    alt="Sort: Comparable interface"
  >
</p>

Two useful sorting abstractions:

```java
private static boolean less(Comparable v, Comparable w)
{  return v.compareTo(w) < 0;  }
```

```java
private static void exch(Comparable[] a, int i, int j) {
  Comparable swap = a[i];
  a[i] = a[j];
  a[j] = swap;
}
```

### Testing

Test if an array is sorted.

```java
private static boolean isSorted(Comparable[] a)
{
   for (int i = 1; i < a.length; i++)
      if (less(a[i], a[i-1])) return false;
   return true;
}
```

## Selection sort

- Algorithm:

  - Steps:
    - In iteration i, find index min of smallest remaining entry.
    - Swap a[i] and a[min].
  - Scans from left to right.
    - Move the pointer to the right: i++
    - Identify index of minimum entry on right.
    - Exchange into position.

- Mathematical analysis
  - (N–1) + (N–2) + ... + 1 + 0 ~ N^2 / 2 compares
  - N exchanges

<p>
  <img
    src="images/image11c.png"
    width="500"
    alt="Selection sort: mathematical analysis"
  >
</p>

## Implementation

<p>
  <img
    src="images/image11d.png"
    width="500"
    alt="Selection sort: implementation"
  >
</p>

## Insertion sort

- Algorithm:

  - In iteration i, swap a[i] with **each** larger entry to its left.
  - scans from left to right.
  - Steps:
    - Move the pointer to the right.
    - Moving from right to left, exchange a[i] with each larger entry to its left.

- Mathematical analysis
  - To sort a randomly-ordered array with distinct keys
    - ~ 1⁄4 N^2 compares on average
    - ~ 1⁄4 N^2 exchanges on average
    - (a bit hard to prove it)

<p>
  <img
    src="images/image12.png"
    width="500"
    alt="Insertion sort: mathematical analysis"
  >
</p>

- Best case.

  - If the array is in ascending order
  - insertion sort makes N - 1 compares
  - 0 exchanges.
  - Ex: `A E E L M O P R S T X`

- Worst case.
  - If the array is in descending order (and no duplicates),
  - insertion sort makes ~ 1⁄2 N^2 compares
  - and ~ 1⁄2 N 2 exchanges.
  - Ex: `X T S R P O M L E E A`

Def: `An inversion is a pair of keys that are out of order.`

- Ex: `A E E L M O T R X P S`
- `T-R T-P T-S R-P X-P X-S`(6 inversions)

Def: An array is partially sorted if the number of inversions is ≤ c N.

- c is a constant
- in other words, an array is partially sorted if the number of inversions is linear
- Ex 1: A subarray of size 10 appended to a sorted subarray of size N.
- Ex 2. An array of size N with only 10 entries out of place.

Proposition: For partially-sorted arrays, insertion sort runs in linear time.

- Pf. Number of exchanges equals the number of inversions.
- number of compares <= exchanges + (N – 1)

## Implementation

<p>
  <img
    src="images/image13.png"
    width="500"
    alt="Insertion sort: implementation"
  >
</p>

More info at: https://www.youtube.com/watch?v=ufIET8dMnus

## Shellsort

- Improvement of Insertion Sort
- Idea: Move entries more than one position at a time by h-sorting the array.
- How to h-sort an array? Insertion sort, with stride length h.
- Detailed explanation: https://www.youtube.com/watch?v=ZtjCHnHTK5Q

- Why insertion sort?
  - When the increment is big ⇒ it needs to sort a small sub-array
  - When the increments is small ⇒ the array is nearly in order

Proposition: A g-sorted array remains g-sorted after h-sorting it.

- Challenge. Prove this fact—it's more subtle than you'd think!

Which increment sequence to use?

- Powers of two minus one.
  - 1, 3, 7, 15, 31, 63, ...
  - Maybe.
- **3x + 1**.
  - 1, 4, 13, 40, 121, 364, ...
  - OK. Easy to compute.

## Implementation

<p>
  <img
    src="images/image14.png"
    width="500"
    alt="Shellsort: implementation"
  >
</p>

### Example

input = `S H E L L S O R T E X A M P L E`

size = 16

h => 3x + 1

- h = 1
- h = 3.h + 1 = 3\*1 + 1 = 4
- h = 3.h + 1 = 3\*4 + 1 = 13
- h = 3.h + 1 = 3\*13 + 1 = 40 => 40 > 16 STOP!

1st h = 13, 2nd h = 4, 3rd h = 1.

**1st step: h = 13**

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    S  H  E  L  L  S  O  R  T  E  X  A  M  P  L  E

(0, 13) S - P => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    P  H  E  L  L  S  O  R  T  E  X  A  M  S  L  E

(1, 14) H - L => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    P  H  E  L  L  S  O  R  T  E  X  A  M  S  L  E

(2, 15) E - E => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    P  H  E  L  L  S  O  R  T  E  X  A  M  S  L  E

**2st step: h = 4**

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    P  H  E  L  L  S  O  R  T  E  X  A  M  S  L  E

(0, 4) P - L => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  H  E  L  P  S  O  R  T  E  X  A  M  S  L  E

(1, 5) H - S => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  H  E  L  P  S  O  R  T  E  X  A  M  S  L  E

(2, 6) E - O => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  H  E  L  P  S  O  R  T  E  X  A  M  S  L  E

(3, 7) L - R => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  H  E  L  P  S  O  R  T  E  X  A  M  S  L  E

(4, 8) P - T => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  H  E  L  P  S  O  R  T  E  X  A  M  S  L  E

(5, 9) S - E => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  H  E  L  P  E  O  R  T  S  X  A  M  S  L  E

(1, 5) H - E => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  L  P  H  O  R  T  S  X  A  M  S  L  E

(6, 10) O - X => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  L  P  H  O  R  T  S  X  A  M  S  L  E

(7, 11) R - A => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  L  P  H  O  A  T  S  X  R  M  S  L  E

(3, 7) L - A => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  A  P  H  O  L  T  S  X  R  M  S  L  E

(8, 12) T - M => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  A  P  H  O  L  M  S  X  R  T  S  L  E

(4, 8) P - M => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  A  M  H  O  L  P  S  X  R  T  S  L  E

(0, 4) L - M => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  A  M  H  O  L  P  S  X  R  T  S  L  E

(9, 13) S - S => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  A  M  H  O  L  P  S  X  R  T  S  L  E

(10, 14) X - L => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  A  M  H  O  L  P  S  L  R  T  S  X  E

(6, 10) O - L => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  A  M  H  L  L  P  S  O  R  T  S  X  E

(2, 6) E - L => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  A  M  H  L  L  P  S  O  R  T  S  X  E

(11, 15) R - E => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  A  M  H  L  L  P  S  O  E  T  S  X  R

(7, 11) L - E => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  A  M  H  L  E  P  S  O  L  T  S  X  R

(3, 7) A - E => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  A  M  H  L  E  P  S  O  L  T  S  X  R

**3st step: h = 1 => same as Insertion sort**

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    L  E  E  A  M  H  L  E  P  S  O  L  T  S  X  R

(0, 1) L - E => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    E  L  E  A  M  H  L  E  P  S  O  L  T  S  X  R

(1, 2) L - E => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    E  E  L  A  M  H  L  E  P  S  O  L  T  S  X  R

(0, 1) E - E => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    E  E  L  A  M  H  L  E  P  S  O  L  T  S  X  R

(2, 3) L - A => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    E  E  A  L  M  H  L  E  P  S  O  L  T  S  X  R

(1, 2) E - A => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    E  A  E  L  M  H  L  E  P  S  O  L  T  S  X  R

(0, 1) E - A => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  L  M  H  L  E  P  S  O  L  T  S  X  R

(3, 4) L - M => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  L  M  H  L  E  P  S  O  L  T  S  X  R

(4, 5) M - H => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  L  H  M  L  E  P  S  O  L  T  S  X  R

(3, 4) L - H => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  H  L  M  L  E  P  S  O  L  T  S  X  R

(2, 3) E - H => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  H  L  M  L  E  P  S  O  L  T  S  X  R

(5, 6) M - L => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  H  L  L  M  E  P  S  O  L  T  S  X  R

(4, 5) L - L => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  H  L  L  M  E  P  S  O  L  T  S  X  R

(6, 7) M - E => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  H  L  L  E  M  P  S  O  L  T  S  X  R

(5, 6) L - E => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  H  L  E  L  M  P  S  O  L  T  S  X  R

(4, 5) L - E => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  H  E  L  L  M  P  S  O  L  T  S  X  R

(3, 4) H - E => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  M  P  S  O  L  T  S  X  R

(2, 3) E - E => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  M  P  S  O  L  T  S  X  R

(7, 8) M - P => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  M  P  S  O  L  T  S  X  R

(9, 10) S - O => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  M  P  O  S  L  T  S  X  R

(8, 9) P - O => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  M  O  P  S  L  T  S  X  R

(7, 8) M - O => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  M  O  P  S  L  T  S  X  R

(10, 11) S - L => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  M  O  P  L  S  T  S  X  R

(9, 10) P - L => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  M  O  L  P  S  T  S  X  R

(8, 9) O - L => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  M  L  O  P  S  T  S  X  R

(7, 8) M - L => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  L  M  O  P  S  T  S  X  R

(6, 7) L - L => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  L  M  O  P  S  T  S  X  R

(11, 12) S - T => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  L  M  O  P  S  T  S  X  R

(12, 13) T - S => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  L  M  O  P  S  S  T  X  R

(11, 12) S - S => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  L  M  O  P  S  S  T  X  R

(13, 14) T - X => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  L  M  O  P  S  S  T  X  R

(14, 15) X - R => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  L  M  O  P  S  S  T  R  X

(13, 14) T - R => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  L  M  O  P  S  S  R  T  X

(12, 13) S - R => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  L  M  O  P  S  R  S  T  X

(11, 12) S - R => Swap

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  L  M  O  P  R  S  S  T  X

(10, 11) P - R => Do nothing

    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
    A  E  E  E  H  L  L  L  M  O  P  R  S  S  T  X

Final result: `A E E E H L L L M O P R S S T X`

## Shuffling

Goal: Rearrange array so that result is a uniformly random permutation.

How to shuffle an array?

### Shuffle sort

- Generate a random real number for each array entry.
  (useful for shuffling columns in a spreadsheet)
- Sort the array

<p>
  <img
    src="images/image15.png"
    width="500"
    alt="Shuffle sort"
  >
</p>

Proposition: Shuffle sort produces a uniformly random permutation
of the input array, provided no duplicate values.

Drawback: you need to pay the cost of sort

### Knuth shuffle

Algorithm:

- In iteration i, pick integer r between 0 and i uniformly at random.
- Swap a[i] and a[r].

Attention!

- Common bug: pick integer r between 0 and N – 1
- correct variant: between i and N – 1

```java
public class StdRandom {
  ...
  public static void shuffle(Object[] a) {
    int N = a.length;
    for (int i = 0; i < N; i++) {
      int r = StdRandom.uniform(i + 1); // between 0 and i
      exch(a, i, r);
    }
  }
}
```

Proposition: Knuth shuffling algorithm produces a uniformly random permutation of the input array
in **linear time**.

Best practices for shuffling (if your business depends on it).

- Use a hardware random-number generator that has passed both the FIPS 140-2 and the NIST statistical test suites.
- Continuously monitor statistic properties:
  hardware random-number generators are fragile and fail silently.
- Use an unbiased shuffling algorithm.
