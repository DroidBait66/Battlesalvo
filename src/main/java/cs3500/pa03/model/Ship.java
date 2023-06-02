package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the object ship
 */
public class Ship {

  final ShipType type;
  private final List<Coord> location;
  private List<Coord> salvoDamage = new ArrayList<>();
  private final List<Coord> damagedCells = new ArrayList<>(); //Do not make final

  /**
   * Constructor for Class Ship
   *
   * @param type is a ShipType
   * @param location is the location of the ship
   */
  public Ship(ShipType type, List<Coord> location) {
    this.type = type;
    this.location = location;
  }

  /**
   * Checks that no part of a salvo is already in a damaged cell. Adds the salvo into the
   * damagedCell list
   */
  private void updateDamage() {
    for (Coord c : salvoDamage) {
      if (!damagedCells.contains(c)) {
        damagedCells.add(c);
        for (Coord l : location) {
          if (l.getX() == c.getX() && l.getY() == c.getY()) {
            l.changeStatus(CellStatus.HIT_);
          }
        }
      }
    }
  }

  /**
   * takes an input of salvos from another class and uses it to update all the damaged cells
   *
   * @param inputCoords coords of salvo
   */
  public void getSalvoDamage(List<Coord> inputCoords) {
    salvoDamage = inputCoords;
    updateDamage();
  }

  /**
   * Gets the damaged cells of a ship
   *
   * @return a list of coords
   */
  public List<Coord> getTotalDamage() {
    return damagedCells;
  }

  /**
   * Checks if every CellStatus in the location list is a Ship or a Hit
   *
   * @return boolean true (meaning is floating) or false (meaning sunk)
   */
  public boolean isFloating() {
    for (Coord c : location) {
      if (c.getStatus().toString().equals("SHIP")) {
        return true;
      }
    }
    return false;
  }

  /**
   * getter for the location of a ship. this is needed because the game needs to know
   * the location of every single ship
   *
   * @return a list of coord
   */
  public List<Coord> getLocation() {
    return this.location;
  }


}
