package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {

  ShipType type;
  List<Coord>  location;
  private List<Coord> salvoDamage = new ArrayList<>();
  private List<Coord> damagedCells = new ArrayList<>();

  public Ship(ShipType type, List<Coord> location) {
    this.type = type;
    this.location = location;
  }

  private void updateDamage() {
    for (Coord c : salvoDamage) {
      if (!damagedCells.contains(c)) {
        damagedCells.add(c);
      }
    }
  }

  public void getSalvoDamage(List<Coord> inputCoords) {
    salvoDamage = inputCoords;
  }

  public List<Coord> getTotalDamage() {
    updateDamage();
    return damagedCells;
  }

}
