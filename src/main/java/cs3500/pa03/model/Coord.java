package cs3500.pa03.model;

/**
 * Class for new object Coord
 * Used as coordinates on the board
 */
public class Coord {

  public int x;
  public int y; //not sure if should be public or private

  /**
   * Constructor for Coord class
   *
   * @param x is the X coord of the cell
   * @param y is the Y coord of the cell
   */
  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
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

}
