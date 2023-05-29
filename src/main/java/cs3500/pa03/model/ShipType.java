package cs3500.pa03.model;

/**
 * Creates the enum ShipType
 */
public enum ShipType {
  CARRIER(6), BATTLESHIP(5), DESTROYER(4), SUBMARINE(3);

  private final int size;

  /**
   * Constructor for ship type
   *
   * @param size the size of the ship
   */
  ShipType(int size) {
    this.size = size;
  }

  /**
   * getter for the size of the ships
   *
   * @return integer that is the size
   */
  public int getSize() {
    return this.size;
  }
}
