package cs3500.pa03.model;

/**
 * Class for new object Coord
 * Used as coordinates on the board
 */
public class Coord {

  private int x;
  private int y;
  private CellStatus status;

  /**
   * Constructor for Coord class
   *
   * @param x is the X coord of the cell
   * @param y is the Y coord of the cell
   */
  public Coord(int x, int y, CellStatus status) {
    this.x = x;
    this.y = y;
    this.status = status;
  }

  /**
   * getter for the X
   *
   * @return x coord of cell
   */
  public int getX() {
    return this.x;
  }

  /**
   * getter for the Y
   *
   * @return y coord of cell
   */
  public int getY() {
    return this.y;
  }

  /**
   * getter for the status of a given cell
   *
   * @return the CellStatus
   */
  public CellStatus getStatus() {
    return this.status;
  }

  public void changeStatus(CellStatus c) {
    status = c;
  }

}
