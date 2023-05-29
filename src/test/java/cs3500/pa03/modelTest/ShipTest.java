package cs3500.pa03.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ShipTest {

  @Test
  public void testGetTotalDamaged() {
    List<Coord> location = new ArrayList<>(Arrays.asList(new Coord(1,2), new Coord(1,3),
        new Coord(1,4)));

    List<Coord> damage = new ArrayList<>(Arrays.asList(new Coord(1,2), new Coord(1,3)));

    Ship ex1 = new Ship(ShipType.CARRIER, location);
    assertEquals(new ArrayList<Coord>(), ex1.getTotalDamage()); //check before any salvos

    ex1.getSalvoDamage(damage);
    assertEquals(damage, ex1.getTotalDamage()); //check after first salvo

    damage.add(new Coord(1,4));
    ex1.getSalvoDamage(damage); //salvo with coords already in damagedCells
    assertEquals(damage, ex1.getTotalDamage()); // test with second salvo

  }
}
