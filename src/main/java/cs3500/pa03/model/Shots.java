package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Dependency injection class
 */
public class Shots {
  private List<Coord> curSalvo = new ArrayList<>();
  private int ships;
  private ManualPlayer player;
  private ArrayList<ArrayList<CellStatus>> opBoard;
  private int emptySpaces;

  /**
   * Constructor that takes in a player
   * @param p manual player
   */
  public Shots(ManualPlayer p) {
    this.player = p;
  }

  /**
   * converts a 2d array of ints into a list of coords (user input into)
   * @param inputSalvo given 2d array
   */
  public void setSalvo(int[][] inputSalvo) {
    List<Coord> tempSalvo = new ArrayList<>();
    for (int[] xy : inputSalvo) {
      Coord salvo = new Coord(xy[0], xy[1], CellStatus.EMPT);
      tempSalvo.add(salvo);
    }
    curSalvo = tempSalvo;
  }

  /**
   * Getter for the salvo
   *
   * @return a list of coords
   */
  public List<Coord> getSalvo() {
    return curSalvo;
  }


  /**
   * Tells what the maximum possible shots for the player is
   *
   * @param opponentBoard current state of opponent board, used to see the empty spaces there are
   */
  public void setOpponentEmpty(Board opponentBoard) {
    opBoard = opponentBoard.getOpponentBoard(opponentBoard.getBoard());
    emptySpaces = 0;
    for (ArrayList<CellStatus> row : opBoard) {
      for (CellStatus c : row) {
        if (c.equals(CellStatus.EMPT)) {
          emptySpaces += 1;
        }
      }
    }
  }

  /**
   * getter to check how many empty spaces the opponent has
   *
   * @return integer of empty spaces
   */
  public int getEmptySpaces() {
    return emptySpaces;
  }


  /**
   * setter to figure out how many ships are left
   *
   * @param shipsLeft integer of ships left
   */
  public void setRemainingShips(int shipsLeft) {
    ships = shipsLeft;
  }

  /**
   * getter of remaining ships (will be called in controller)
   *
   * @return an integer
   */
  public int getRemainingShips() {
    return ships;
  }
}
