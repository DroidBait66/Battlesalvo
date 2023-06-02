package cs3500.pa03.modeltest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.CellStatus;
import cs3500.pa03.model.Shots;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests all methods in the Shots class
 */
public class ShotsTest {

  Shots shot;
  ArrayList<ArrayList<CellStatus>> opBoard;
  ArrayList<ArrayList<CellStatus>> myBoard;

  /**
   * setting up vars before tests
   */
  @BeforeEach
  public void setup() {
    shot = new Shots();
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
   * Tests the getSalvo method
   */
  @Test
  public void testGetSalvo() {
    int[][] testArray = new int [3][2];
    testArray[0][0] = 1;
    testArray[0][1] = 2;
    testArray[1][0] = 3;
    testArray[1][1] = 4;
    testArray[2][0] = 5;
    testArray[2][1] = 6;
    shot.setSalvo(testArray);
    assertEquals(1, shot.getSalvo().get(0).getX());
    assertEquals(2, shot.getSalvo().get(0).getY());
    assertEquals(3, shot.getSalvo().get(1).getX());
    assertEquals(4, shot.getSalvo().get(1).getY());
    assertEquals(5, shot.getSalvo().get(2).getX());
    assertEquals(6, shot.getSalvo().get(2).getY());
    assertEquals(CellStatus.EMPT, shot.getSalvo().get(0).getStatus());
    assertEquals(CellStatus.EMPT, shot.getSalvo().get(1).getStatus());
    assertEquals(CellStatus.EMPT, shot.getSalvo().get(2).getStatus());
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
    shot.setOpponentEmpty(new Board(opBoard));
    assertEquals(5, shot.limitShots());
    shot.setRemainingShips(45);
    shot.setOpponentEmpty(new Board(opBoard));
    assertEquals(11, shot.limitShots());
  }



}
