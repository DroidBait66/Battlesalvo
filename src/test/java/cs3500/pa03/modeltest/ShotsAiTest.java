package cs3500.pa03.modeltest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.CellStatus;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ShotsAi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests all methods in the ShotsAi class
 */
public class ShotsAiTest {

  ShotsAi shot;
  ArrayList<ArrayList<CellStatus>> opBoard;
  ArrayList<ArrayList<CellStatus>> myBoard;

  /**
   * setting up vars before tests
   */
  @BeforeEach
  public void setup() {
    shot = new ShotsAi();
    opBoard = new ArrayList<>();
    opBoard.add(new ArrayList<>(
        Arrays.asList(CellStatus.MISS, CellStatus.EMPT, CellStatus.MISS, CellStatus.MISS)));
    opBoard.add(new ArrayList<>(
        Arrays.asList(CellStatus.MISS, CellStatus.HIT_, CellStatus.EMPT, CellStatus.EMPT)));
    opBoard.add(new ArrayList<>(
        Arrays.asList(CellStatus.EMPT, CellStatus.EMPT, CellStatus.EMPT, CellStatus.EMPT)));
    opBoard.add(new ArrayList<>(
        Arrays.asList(CellStatus.EMPT, CellStatus.EMPT, CellStatus.EMPT, CellStatus.EMPT)));

    myBoard = new ArrayList<>();
    myBoard.add(new ArrayList<>(
        Arrays.asList(CellStatus.MISS, CellStatus.EMPT, CellStatus.MISS, CellStatus.MISS)));
    myBoard.add(new ArrayList<>(
        Arrays.asList(CellStatus.MISS, CellStatus.HIT_, CellStatus.EMPT, CellStatus.EMPT)));
    myBoard.add(new ArrayList<>(
        Arrays.asList(CellStatus.EMPT, CellStatus.SHIP, CellStatus.EMPT, CellStatus.EMPT)));
    myBoard.add(new ArrayList<>(
        Arrays.asList(CellStatus.EMPT, CellStatus.SHIP, CellStatus.EMPT, CellStatus.EMPT)));

  }


  /**
   * tests the boardGetter method
   */
  @Test
  public void testBoardGetter() {
    shot.setBoard(new Board(myBoard));
    assertEquals(myBoard, shot.boardGetter().getBoard());
  }

  /**
   * Tests the getOpBoard method
   */
  @Test
  public void testGetOpBoard() {
    shot.setOpBoard(new Board(myBoard));
    assertEquals(opBoard, shot.getOpBoard());
  }

  /**
   * Tests the getRemainingShips method
   */
  @Test
  public void testGetRemainingShips() {
    shot.setRemainingShips(5);
    assertEquals(5, shot.getRemainingShips());
    shot.setRemainingShips(49);
    assertEquals(49, shot.getRemainingShips());
  }

  /**
   * Tests the limit shots method
   */
  @Test
  public void testLimit() {
    shot.setRemainingShips(5);
    shot.setOpBoard(new Board(opBoard));
    assertEquals(5, shot.limitShots());
    shot.setRemainingShips(45);
    shot.setOpBoard(new Board(opBoard));
    assertEquals(11, shot.limitShots());
  }


  /**
   * Tests how many shots were missed from the given
   */
  @Test
  public void testMissedShots() {
    Coord first = new Coord(0, 0, CellStatus.EMPT);
    Coord second = new Coord(0, 1, CellStatus.SHIP);
    Coord third = new Coord(0, 2, CellStatus.EMPT);
    List<Coord> allShots = new ArrayList<>(Arrays.asList(first, second, third));
    ArrayList<Coord> hit = new ArrayList<>();
    hit.add(second);

    shot.setMissedShots(allShots, hit);
    assertEquals(first, shot.getMissedShots().get(0));
    assertEquals(third, shot.getMissedShots().get(1));

  }



}
