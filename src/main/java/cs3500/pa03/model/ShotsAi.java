package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements dependency injection for Ai player
 */
public class ShotsAi {
  private int ships;
  private ArrayList<ArrayList<CellStatus>> opBoard;
  private ArrayList<Coord> missedPlayerShots;

  private int emptySpaces;
  private Board board;

  /**
   * setter for how many shots missed
   *
   * @param allShots all fired shots
   * @param hit all shots that hit
   */
  public void setMissedShots(List<Coord> allShots, ArrayList<Coord> hit) {
    ArrayList<Coord> temp = new ArrayList<>();
    for (Coord all : allShots) {
      if (!hit.contains(all)) {
        temp.add(all);
      }
    }
    this.missedPlayerShots = temp;
  }

  /**
   * All shots the player missed on the ai
   *
   * @return arraylist of coords
   */
  public ArrayList<Coord> getMissedShots() {
    return missedPlayerShots;
  }



  /**
   * Tells what the maximum possible shots for the player is
   *
   * @param opponentBoard current state of opponent board, used to see the empty spaces there are
   */
  public void setOpBoard(Board opponentBoard) {
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
   * limits the amount of shots a player can take
   *
   * @return smaller int between ships and emptySpaces
   */
  public int limitShots() {
    return Math.min(ships, emptySpaces);
  }


  /**
   * Getter for opponent board
   *
   * @return gets current state of opponents board (minus ship placement)
   */
  public ArrayList<ArrayList<CellStatus>> getOpBoard() {
    return opBoard;
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

  /**
   * Setter for the board
   *
   * @param boards current state of Ai board
   */
  public void setBoard(Board boards) {
    this.board = boards;
  }

  /**
   * Getter for the current state of the board
   *
   * @return current state of board
   */
  public Board boardGetter() {
    return board;
  }
}
