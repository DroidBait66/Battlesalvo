package cs3500.pa03.modeltest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.CellStatus;
import cs3500.pa03.model.Coord;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for board object
 */
public class BoardTest {
  Board first;
  ArrayList<ArrayList<CellStatus>> boardList;
  ArrayList<Coord> hit;
  ArrayList<Coord> all;

  /**
   * Setting up for the next tests
   */
  @BeforeEach
  public void setUpBoard() {
    boardList = new ArrayList<>();
    boardList.add(new ArrayList<>(
        Arrays.asList(CellStatus.EMPT, CellStatus.EMPT, CellStatus.EMPT, CellStatus.EMPT)));
    boardList.add(new ArrayList<>(
        Arrays.asList(CellStatus.EMPT, CellStatus.SHIP, CellStatus.EMPT, CellStatus.EMPT)));
    boardList.add(new ArrayList<>(
        Arrays.asList(CellStatus.EMPT, CellStatus.SHIP, CellStatus.EMPT, CellStatus.EMPT)));
    boardList.add(new ArrayList<>(
        Arrays.asList(CellStatus.EMPT, CellStatus.EMPT, CellStatus.EMPT, CellStatus.EMPT)));

    first = new Board(boardList);

    all = new ArrayList<>(Arrays.asList(new Coord(0, 0, CellStatus.EMPT),
        new Coord(0, 1, CellStatus.EMPT), new Coord(2, 0, CellStatus.EMPT),
        new Coord(1, 1, CellStatus.SHIP), new Coord(3, 0, CellStatus.EMPT)));
    hit = new ArrayList<>();
    hit.add(new Coord(1, 1, CellStatus.SHIP));
  }

  /**
   * test for getBoard method
   */
  @Test
  public void testGetBoard() {
    assertEquals(boardList, first.getBoard());
  }

  /**
   * test for updateBoard method
   */
  @Test
  public void testUpdateBoard() {

    first.updateBoard(all, hit);
    assertEquals(CellStatus.MISS, boardList.get(0).get(0));
    assertEquals(CellStatus.MISS, boardList.get(0).get(2));
    assertEquals(CellStatus.MISS, boardList.get(0).get(3));
    assertEquals(CellStatus.MISS, boardList.get(1).get(0));
    assertEquals(CellStatus.HIT_, boardList.get(1).get(1));


    ArrayList<Coord> tester = new ArrayList<>();
    tester.add(new Coord(3, 2, CellStatus.EMPT));
    ArrayList<Coord> testerHit = new ArrayList<>();
    testerHit.add(new Coord(2, 1, CellStatus.SHIP));
    first.updateBoard(tester, testerHit);
    assertEquals(CellStatus.MISS, boardList.get(2).get(3));
    assertEquals(CellStatus.HIT_, boardList.get(1).get(2));
  }

  /**
   * Test for getOpponentBoard method
   */
  @Test
  public void testGetOpponentBoard() {
    ArrayList<ArrayList<CellStatus>> opBoard = new ArrayList<>();
    opBoard.add(new ArrayList<>(
        Arrays.asList(CellStatus.MISS, CellStatus.EMPT, CellStatus.MISS, CellStatus.MISS)));
    opBoard.add(new ArrayList<>(
        Arrays.asList(CellStatus.MISS, CellStatus.HIT_, CellStatus.EMPT, CellStatus.EMPT)));
    opBoard.add(new ArrayList<>(
        Arrays.asList(CellStatus.EMPT, CellStatus.EMPT, CellStatus.EMPT, CellStatus.EMPT)));
    opBoard.add(new ArrayList<>(
        Arrays.asList(CellStatus.EMPT, CellStatus.EMPT, CellStatus.EMPT, CellStatus.EMPT)));

    ArrayList<ArrayList<CellStatus>> testOp = first.getOpponentBoard(first.updateBoard(all, hit));
    assertEquals(opBoard, testOp);

  }


}
