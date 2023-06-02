package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for object Board
 */
public class Board {
  private ArrayList<ArrayList<CellStatus>> board;

  public Board(ArrayList<ArrayList<CellStatus>> board) {
    this.board = board;
  }

  /**
   * getter for board
   *
   * @return and arraylist of arraylist of CellStatus, which is the game board
   */
  public ArrayList<ArrayList<CellStatus>> getBoard() {
    return board;
  }

  /**
   * updates the board to include hits and misses
   *
   * @param allOpShots all shots the opponent fired
   * @param damageCoords all shots that damaged your ships
   * @return a board that is updated with misses and hits
   */
  public ArrayList<ArrayList<CellStatus>> updateBoard(List<Coord> allOpShots,
                                                      List<Coord> damageCoords) {
    ArrayList<Coord> missedShots = new ArrayList<>();
    for (Coord allOpShot : allOpShots) {
      if (!damageCoords.contains(allOpShot)) {
        missedShots.add(allOpShot);
      }
    }


    for (Coord temp : missedShots) {
      board.get(temp.getX()).set(temp.getY(), CellStatus.MISS);
    }

    for (Coord temp : damageCoords) {
      board.get(temp.getX()).set(temp.getY(), CellStatus.HIT_);
    }

    return board;
  }

  /**
   * takes in a game board (ArrayList of ArrayList of CellStatus) and hides all mentions of ships
   *
   * @param opBoard unmodified board of opponent
   * @return hidden board for opponent
   */
  public ArrayList<ArrayList<CellStatus>> getOpponentBoard(
      ArrayList<ArrayList<CellStatus>> opBoard) {
    ArrayList<ArrayList<CellStatus>> result = new ArrayList<>();
    for (ArrayList<CellStatus> tempList : opBoard) {
      for (int col = 0; col < tempList.size(); col += 1) {
        if (tempList.get(col).equals(CellStatus.SHIP)) {
          tempList.set(col, CellStatus.EMPT);
        }
      }
      result.add(tempList);
    }
    return result;
  }
}
