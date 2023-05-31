package cs3500.pa03.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.CellStatus;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.model.Shots;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * AiPlayer test class
 */
public class AiPlayerTest {

  String name;
  HashMap<ShipType, Integer> specifications;
  int shipsRemaining;
  AiPlayer player;
  ManualPlayer playerManual;
  int height;
  int width;
  HashMap<ShipType, Integer> specificationsMax;
  int shipsRemainingMax;
  AiPlayer playerMax;
  ManualPlayer manPlayerMax;
  int heightMax;
  int widthMax;
  HashMap<ShipType, Integer> specificationsMin;
  int shipsRemainingMin;
  AiPlayer playerMin;
  ManualPlayer manPlayerMin;
  int heightMin;
  int widthMin;
  List<Coord> shotCoords;
  List<Coord> sinkCarrier;

  int[][] salvoArray = new int[][] {{1, 2}, {3, 4}, {5, 6}};
  Shots salvo;



  /**
   * Set up for ManualPlayer
   */
  @BeforeEach
  public void setUp() {

    name = "Ai";
    specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 2);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 1);

    shipsRemaining = specifications.get(ShipType.CARRIER) + specifications.get(ShipType.BATTLESHIP)
        + specifications.get(ShipType.DESTROYER) + specifications.get(ShipType.SUBMARINE);

    height = 8;
    width = 9;
    salvo = new Shots(playerManual);
    playerManual = new ManualPlayer("player", shipsRemaining, new Random(8), salvo);
    playerManual.setup(height, width, specifications);

    player = new AiPlayer(name, shipsRemaining, new Random(8));
    salvo.setSalvo(salvoArray);



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
    manPlayerMax = new ManualPlayer("player", shipsRemaining, new Random(5), salvo);
    manPlayerMax.setup(height, width, specifications);

    playerMax = new AiPlayer(name, shipsRemainingMax, new Random(9));

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
    manPlayerMin = new ManualPlayer("player", shipsRemaining, new Random(12), salvo);
    manPlayerMin.setup(height, width, specifications);
    playerMin = new AiPlayer(name, shipsRemainingMin, new Random(10));

    // player carrier coords: 2,7 3,7 4,7 5,7 ,6,7 7,7 for seed 8


    shotCoords = new ArrayList<>(Arrays.asList(new Coord(0, 0, CellStatus.EMPT),
        new Coord(2, 7, CellStatus.EMPT), new Coord(2, 0, CellStatus.EMPT),
        new Coord(1, 1, CellStatus.EMPT), new Coord(3, 7, CellStatus.EMPT)));

    sinkCarrier = new ArrayList<>(Arrays.asList(new Coord(4, 7, CellStatus.EMPT),
        new Coord(2, 7, CellStatus.EMPT), new Coord(5, 7, CellStatus.EMPT),
        new Coord(7, 7, CellStatus.EMPT), new Coord(3, 7, CellStatus.EMPT),
        new Coord(6, 7, CellStatus.EMPT)));

  }

  /**
   * Tests the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("Ai", player.name());
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
