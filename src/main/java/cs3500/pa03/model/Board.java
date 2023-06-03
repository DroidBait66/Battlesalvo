package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for object Board
 */
public class Board {
  private final ArrayList<ArrayList<CellStatus>> board;

  /**
   * constructor for board
   *
   * @param board arraylist of arraylist of board
   */
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
    for (Coord all : allOpShots) {
      if (!damageCoords.contains(all)) {
        missedShots.add(all);
      }
    }


    for (Coord temp : missedShots) {
      board.get(temp.getY()).set(temp.getX(), CellStatus.MISS);
    }

    for (Coord temp : damageCoords) {
      board.get(temp.getY()).set(temp.getX(), CellStatus.HIT_);
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
      ArrayList<CellStatus> temp = new ArrayList<>();
      for (int col = 0; col < tempList.size(); col += 1) {
        if (tempList.get(col).equals(CellStatus.SHIP)) {
          temp.add(CellStatus.EMPT);
        } else {
          temp.add(tempList.get(col));
        }
      }
      result.add(temp);
    }
    return result;
  }
}
