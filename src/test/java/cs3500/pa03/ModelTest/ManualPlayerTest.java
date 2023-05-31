package cs3500.pa03.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.CellStatus;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Manual player test class
 */
public class ManualPlayerTest {
  String name;
  HashMap<ShipType, Integer> specifications;
  int shipsRemaining;
  ManualPlayer player;
  int height;
  int width;

  HashMap<ShipType, Integer> specificationsMax;

  int shipsRemainingMax;
  ManualPlayer playerMax;
  int heightMax;
  int widthMax;

  HashMap<ShipType, Integer> specificationsMin;


  int shipsRemainingMin;
  ManualPlayer playerMin;
  int heightMin;
  int widthMin;

  /**
   * Set up for ManualPlayer
   */
  @BeforeEach
  public void setUp() {
    name = "Player";
    specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 2);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 1);

    shipsRemaining = specifications.get(ShipType.CARRIER) + specifications.get(ShipType.BATTLESHIP)
        + specifications.get(ShipType.DESTROYER) + specifications.get(ShipType.SUBMARINE);

    height = 8;
    width = 9;

    player = new ManualPlayer(name, shipsRemaining);


    specificationsMax = new HashMap<>();
    specificationsMax.put(ShipType.CARRIER, 13);
    specificationsMax.put(ShipType.BATTLESHIP, 1);
    specificationsMax.put(ShipType.DESTROYER, 1);
    specificationsMax.put(ShipType.SUBMARINE, 1);

    shipsRemainingMax = specificationsMax.get(ShipType.CARRIER)
        + specificationsMax.get(ShipType.BATTLESHIP) + specificationsMax.get(ShipType.DESTROYER)
        + specificationsMax.get(ShipType.SUBMARINE);

    heightMax = 16;
    widthMax = 16;
    playerMax = new ManualPlayer(name, shipsRemainingMax);

    specificationsMin = new HashMap<>();
    specificationsMin.put(ShipType.CARRIER, 1);
    specificationsMin.put(ShipType.BATTLESHIP, 1);
    specificationsMin.put(ShipType.DESTROYER, 1);
    specificationsMin.put(ShipType.SUBMARINE, 1);

    shipsRemainingMin = specificationsMin.get(ShipType.CARRIER)
        + specificationsMin.get(ShipType.BATTLESHIP) + specificationsMin.get(ShipType.DESTROYER)
        + specificationsMin.get(ShipType.SUBMARINE);

    heightMin = 6;
    widthMin = 6;
    playerMin = new ManualPlayer(name, shipsRemainingMin);


  }

  /**
   * Tests the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("Player", player.name());
  }

  /**
   * Tests the setup method (tests 3 different set ups with the board at max size, min size, and
   * a random size)
   */
  @Test
  public void testSetUp() {

    assertEquals(shipsRemaining, player.setup(height, width, specifications).size());

    ArrayList<ArrayList<CellStatus>> test = player.gameBoard.getBoard();

    int numberOfShipBlocks = specifications.get(ShipType.CARRIER) * 6
        + specifications.get(ShipType.BATTLESHIP) * 5 + specifications.get(ShipType.DESTROYER) * 4
        + specifications.get(ShipType.SUBMARINE) * 3;
    int counter = 0;
    for (ArrayList<CellStatus> row : test) {
      for (CellStatus cell : row) {
        if (cell.equals(CellStatus.SHIP)) {
          counter += 1;
        }
      }
    }

    assertEquals(numberOfShipBlocks, counter);

    assertEquals(shipsRemainingMax, playerMax.setup(heightMax, widthMax, specificationsMax).size());

    ArrayList<ArrayList<CellStatus>> testMax = playerMax.gameBoard.getBoard();

    int numberOfShipBlocksMax = specificationsMax.get(ShipType.CARRIER) * 6
        + specificationsMax.get(ShipType.BATTLESHIP) * 5
        + specificationsMax.get(ShipType.DESTROYER) * 4
        + specificationsMax.get(ShipType.SUBMARINE) * 3;
    int counterMax = 0;
    for (ArrayList<CellStatus> row : testMax) {
      for (CellStatus cell : row) {
        if (cell.equals(CellStatus.SHIP)) {
          counterMax += 1;
        }
      }
    }

    assertEquals(numberOfShipBlocksMax, counterMax);

    assertEquals(shipsRemainingMin, playerMin.setup(heightMin, widthMin, specificationsMin).size());

    ArrayList<ArrayList<CellStatus>> testMin = playerMin.gameBoard.getBoard();

    int numberOfShipBlocksMin = specificationsMin.get(ShipType.CARRIER) * 6
        + specificationsMin.get(ShipType.BATTLESHIP) * 5
        + specificationsMin.get(ShipType.DESTROYER) * 4
        + specificationsMin.get(ShipType.SUBMARINE) * 3;

    int counterMin = 0;
    for (ArrayList<CellStatus> row : testMin) {
      for (CellStatus cell : row) {
        if (cell.equals(CellStatus.SHIP)) {
          counterMin += 1;
        }
      }
    }

    assertEquals(numberOfShipBlocksMin, counterMin);

  }
}
