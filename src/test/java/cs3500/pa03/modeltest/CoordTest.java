package cs3500.pa03.modeltest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.CellStatus;
import cs3500.pa03.model.Coord;
import org.junit.jupiter.api.Test;

/**
 * Test class for Coord object
 */
public class CoordTest {

  /**
   * Tests getters in Coord object
   */
  @Test
  public void testCoord() {
    Coord ex1 = new Coord(1, 4, CellStatus.SHIP);
    final Coord ex2 = new Coord(5, 7, CellStatus.EMPT);

    assertEquals(1, ex1.getX());
    assertEquals(4, ex1.getY());
    assertEquals(CellStatus.SHIP, ex1.getStatus());
    assertEquals(5, ex2.getX());
    assertEquals(7, ex2.getY());
    assertEquals(CellStatus.EMPT, ex2.getStatus());

  }
}
