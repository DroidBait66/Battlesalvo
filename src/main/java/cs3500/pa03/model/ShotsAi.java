package cs3500.pa03.model;

import java.util.ArrayList;

public class ShotsAi {
  private int ships;
  private ArrayList<ArrayList<CellStatus>> opBoard;
  private int emptySpaces;



//Maybe delete
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

  // Maybe delete
  public ArrayList<ArrayList<CellStatus>> getOpBoard() {
    return opBoard;
  }

  // Maybe delete
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
