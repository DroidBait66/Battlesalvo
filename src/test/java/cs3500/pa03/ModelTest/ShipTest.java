package cs3500.pa03.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.model.CellStatus;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for Ship object
 */
public class ShipTest {

  /**
   * Tests the getTotalDamaged method
   */
  @Test
  public void testGetTotalDamaged() {
    List<Coord> location = new ArrayList<>(Arrays.asList(new Coord(1, 2, CellStatus.SHIP),
        new Coord(1, 3, CellStatus.SHIP), new Coord(1, 4, CellStatus.SHIP)));

    List<Coord> damage = new ArrayList<>(Arrays.asList(new Coord(1, 2, CellStatus.SHIP),
        new Coord(1, 3, CellStatus.SHIP)));

    Ship ex1 = new Ship(ShipType.CARRIER, location);
    assertEquals(new ArrayList<Coord>(), ex1.getTotalDamage()); //check before any salvos

    ex1.getSalvoDamage(damage);
    assertEquals(damage, ex1.getTotalDamage()); //check after first salvo

    damage.add(new Coord(1, 4, CellStatus.SHIP));
    ex1.getSalvoDamage(damage); //salvo with coords already in damagedCells
    assertEquals(damage, ex1.getTotalDamage()); // test with second salvo


  }


  /**
   * checks to see if the ship is still floating
   */
  @Test
  public void testIsFloating() {
    List<Coord> location = new ArrayList<>(Arrays.asList(new Coord(1, 2, CellStatus.SHIP),
        new Coord(1, 3, CellStatus.SHIP), new Coord(1, 4, CellStatus.SHIP)));

    List<Coord> damage = new ArrayList<>(Arrays.asList(new Coord(1, 2, CellStatus.SHIP),
        new Coord(1, 3, CellStatus.SHIP)));

    Ship ex = new Ship(ShipType.SUBMARINE, location);
    assertTrue(ex.isFloating()); //check before any salvos

    ex.getSalvoDamage(damage);
    assertTrue(ex.isFloating()); //check after first salvo

    damage.add(new Coord(1, 4, CellStatus.SHIP));
    ex.getSalvoDamage(damage); //salvo with coords already in damagedCells
    assertFalse(ex.isFloating()); // test with second salvo

  }
}
