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
