package cs3500.pa03.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.ShipType;
import org.junit.jupiter.api.Test;

/**
 * ShipType test class
 */
public class ShipTypeTest {

  /**
   * test for getSize method in ShipType
   */
  @Test
  public void testGetSize() {
    ShipType carrier = ShipType.CARRIER;
    ShipType battleship = ShipType.BATTLESHIP;
    ShipType destroyer = ShipType.DESTROYER;
    ShipType sub = ShipType.SUBMARINE;

    assertEquals(6, carrier.getSize());
    assertEquals(5, battleship.getSize());
    assertEquals(4, destroyer.getSize());
    assertEquals(3, sub.getSize());


  }
}
