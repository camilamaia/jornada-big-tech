import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/** Represents a percolation system */
public class Percolation {
  private static final int VIRTUAL_TOP = 0;
  private final boolean[][] grid;
  private final int gridSize;
  private final WeightedQuickUnionUF openConnections;
  private final WeightedQuickUnionUF fullConnections;
  private final int virtualBottom;
  private int totalOpenSites;

  /**
   * Creates n-by-n grid, with all sites initially blocked
   *
   * @param n: the size of the system Throws:
   * @throws IllegalArgumentException: if n <= 0
   */
  public Percolation(int n) {
    if (n <= 0) throw new IllegalArgumentException("N must be greather than 0");

    gridSize = n;
    grid = new boolean[n][n];
    int gridSizeSquared = gridSize * gridSize;
    int openConnectionsSize = gridSizeSquared + 2; // includes virtual top and bottom
    int fullConnectionsSize = gridSizeSquared + 1; // includes only virtual top
    openConnections = new WeightedQuickUnionUF(openConnectionsSize);
    fullConnections = new WeightedQuickUnionUF(fullConnectionsSize);
    virtualBottom = gridSizeSquared + 1;
  }

  /**
   * Opens the site (row, col) if it is not open already
   *
   * @param row: the number of the site's row
   * @param col: the number of the site's column
   * @throws IllegalArgumentException: if row or col ar outside the grid's bounds
   */
  public void open(int row, int col) {
    validateSite(row, col);
    if (isOpen(row, col)) return;

    grid[row - 1][col - 1] = true;
    totalOpenSites++;

    int connectionIndex = connectionsIndex(row, col);

    // Top Row. Connects open site with virtual top
    if (row == 1) {
      openConnections.union(VIRTUAL_TOP, connectionIndex);
      fullConnections.union(VIRTUAL_TOP, connectionIndex);
    }

    // Bottom Row. Connects open site with virtual bottom
    if (row == gridSize) {
      openConnections.union(virtualBottom, connectionIndex);
    }

    // Connects open site with the site on the left if it is open
    connect(connectionIndex, row, col - 1);

    // Connects open site with the site on the right if it is open
    connect(connectionIndex, row, col + 1);

    // Connects open site with above site if it is open
    connect(connectionIndex, row - 1, col);

    // Connects open site with below site if it is open
    connect(connectionIndex, row + 1, col);
  }

  /**
   * Check if the site (row, col) is open
   *
   * @param row: the number of the site's row
   * @param col: the number of the site's column
   * @return: true if it is open, false otherwise
   * @throws IllegalArgumentException: if row or col ar outside the grid's bounds
   */
  public boolean isOpen(int row, int col) {
    validateSite(row, col);
    return grid[row - 1][col - 1];
  }

  /**
   * Checks if the site (row, col) is full
   *
   * @param row: the number of the site's row
   * @param col: the number of the site's column
   * @return: true if it is full, false otherwise
   * @throws IllegalArgumentException: if row or col ar outside the grid's bounds
   */
  public boolean isFull(int row, int col) {
    validateSite(row, col);
    boolean site = grid[row - 1][col - 1];
    if (!site) {
      return false;
    }
    return fullConnections.find(VIRTUAL_TOP) == fullConnections.find(connectionsIndex(row, col));
  }

  /** @return: the number of open sites in the system */
  public int numberOfOpenSites() {
    return totalOpenSites;
  }

  /** @return: true if the system percolates, false otherwise. */
  public boolean percolates() {
    return openConnections.find(VIRTUAL_TOP) == openConnections.find(virtualBottom);
  }

  private int connectionsIndex(int row, int col) {
    return gridSize * (row - 1) + col;
  }

  private void validateSite(int row, int col) {
    if (!isOnGrid(row, col)) {
      throw new IllegalArgumentException("Index is out of grid's bounds");
    }
  }

  private boolean isOnGrid(int row, int col) {
    int shiftRow = row - 1;
    int shiftCol = col - 1;

    return (shiftRow >= 0 && shiftCol >= 0 && shiftRow < gridSize && shiftCol < gridSize);
  }

  private void connect(int openSiteConnectionIndex, int siteRow, int siteCol) {
    if (isOnGrid(siteRow, siteCol) && isOpen(siteRow, siteCol)) {
      openConnections.union(openSiteConnectionIndex, connectionsIndex(siteRow, siteCol));
      fullConnections.union(openSiteConnectionIndex, connectionsIndex(siteRow, siteCol));
    }
  }
}
