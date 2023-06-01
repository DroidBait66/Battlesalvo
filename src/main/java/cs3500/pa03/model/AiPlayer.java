package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Ai Player class
 */
public class AiPlayer implements Player {
  String playerName;
  int shipsRemaining;
  public Board gameBoard;
  private ArrayList<ArrayList<CellStatus>> opBoard;
  private List<Ship> aiShips;
  Random rand;
  private ShotsAi salvoAi;

  /**
   * Constructor for Ai Player
   *
   * @param playerName name of player (for later impl)
   * @param shipsRemaining how many ships are remaining
   * @param rand random variable, used to set seed in testing
   */
  public AiPlayer(String playerName, int shipsRemaining, Random rand, ShotsAi salvoAi) {
    this.playerName = playerName;
    this.shipsRemaining = shipsRemaining;
    this.rand = rand;
    this.salvoAi = salvoAi;
  }


  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return playerName;
  }

  /**
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    salvoAi.setRemainingShips(shipsRemaining);
    List<Ship> result = new ArrayList<>();
    ArrayList<ArrayList<CellStatus>> tempBoard = createBoard(height, width);

    for (int i = 0; i < specifications.get(ShipType.CARRIER); i += 1) {
      Ship newShip = placeShip(6, tempBoard);
      result.add(newShip);
      List<Coord> newLocation = newShip.getLocation();
      for (Coord coord : newLocation) {
        tempBoard.get(coord.getY()).set(coord.getX(), CellStatus.SHIP);
      }
    }
    for (int i = 0; i < specifications.get(ShipType.BATTLESHIP); i += 1) {
      Ship newShip = placeShip(5, tempBoard);
      result.add(newShip);
      List<Coord> newLocation = newShip.getLocation();
      for (Coord coord : newLocation) {
        tempBoard.get(coord.getY()).set(coord.getX(), CellStatus.SHIP);
      }
    }
    for (int i = 0; i < specifications.get(ShipType.DESTROYER); i += 1) {
      Ship newShip = placeShip(4, tempBoard);
      result.add(newShip);
      List<Coord> newLocation = newShip.getLocation();
      for (Coord coord : newLocation) {
        tempBoard.get(coord.getY()).set(coord.getX(), CellStatus.SHIP);
      }
    }
    for (int i = 0; i < specifications.get(ShipType.SUBMARINE); i += 1) {
      Ship newShip = placeShip(3, tempBoard);
      result.add(newShip);
      List<Coord> newLocation = newShip.getLocation();
      for (Coord coord : newLocation) {
        tempBoard.get(coord.getY()).set(coord.getX(), CellStatus.SHIP);
      }
    }
    gameBoard = new Board(tempBoard);
    aiShips = result;
    return result;
  }

  /**
   * used to create a blank board
   *
   * @param height given height for board
   * @param width given width for board
   * @return 2d arraylist of cellStatus
   */
  private ArrayList<ArrayList<CellStatus>> createBoard(int height, int width) {
    ArrayList<ArrayList<CellStatus>> tempBoard = new ArrayList<>();
    for (int rows = 0; rows < height; rows += 1) {
      ArrayList<CellStatus> tempRow = new ArrayList<>();
      for (int cols = 0; cols < width; cols += 1) {
        tempRow.add(CellStatus.EMPT);
      }
      tempBoard.add(tempRow);
    }
    return tempBoard;
  }

  /**
   * Sets a given size to its equivilent ShipType
   *
   * @param size of ship
   * @return a ShipType
   */
  private ShipType sizeToShipType(int size) {
    return switch (size) {
      case 6 -> ShipType.CARRIER;
      case 5 -> ShipType.BATTLESHIP;
      case 4 -> ShipType.DESTROYER;
      default -> ShipType.SUBMARINE;
    };
  }


  /**
   * places a ship on the board
   *
   * @param size size of the ship
   * @param board current version of the board
   * @return a Ship
   */
  private Ship placeShip(int size, ArrayList<ArrayList<CellStatus>> board) {
    List<Coord> tempLocation = new ArrayList<>();
    List<CellStatus> tempStatus = new ArrayList<>();
    int orientation = rand.nextInt(2);
    if (orientation == 0) {
      int vertStart = rand.nextInt(board.size() - size + 1);
      int horizStart = rand.nextInt(board.get(0).size());
      for (int i = 0; i < size; i += 1) {
        tempLocation.add(new Coord(horizStart, vertStart + i,
            CellStatus.SHIP));
        tempStatus.add(board.get(vertStart + i).get(horizStart));
      }
      if (tempStatus.contains(CellStatus.SHIP)) {
        return placeShip(size, board);
      }
    } else {
      int vertStart = rand.nextInt(board.size());
      int horizStart = rand.nextInt(board.get(0).size() - size + 1);
      for (int i = 0; i < size; i += 1) {
        tempLocation.add(new Coord(horizStart + i, vertStart,
            CellStatus.SHIP));
        tempStatus.add(board.get(vertStart).get(horizStart + i));
      }
      if (tempStatus.contains(CellStatus.SHIP)) {
        return placeShip(size, board);
      }
    }
    return new Ship(sizeToShipType(size), tempLocation);
  }



  /**
   *  calculates Ai Shots
   *
   * @return a list of coords
   */
  @Override
  public List<Coord> takeShots() {
    opBoard = salvoAi.getOpBoard();
    int shotsLeft = shipsRemaining;
    List<Coord> output = new ArrayList<>();
    ArrayList<Coord> prevHits = findHit();
    if (prevHits.size() < 0) {  ///////  MAKE > 0 -------------------------------
      int count = 0;
      while (count < shotsLeft) {
        int i = count;
        if (i >= prevHits.size()) { i = count - prevHits.size(); }

      }
    } else {
      for (int i = 0; i < shotsLeft; i += 1) {
        output.add(randomHit(output));
      }
    }
    return output;
  }

  private boolean notInExclude(List<Coord> exclude, int x, int y) {
    for (Coord c : exclude) {
      if (c.getX() == x && c.getY() == y) {
        return false;
      }
    }
    return true;
  }
  private Coord randomHit(List<Coord> exclude) {
    int x = rand.nextInt(opBoard.get(0).size());
    int y = rand.nextInt(opBoard.size());
    Coord maybeShot = new Coord(x, y, opBoard.get(y).get(x));
    if (maybeShot.getStatus().equals(CellStatus.EMPT) && notInExclude(exclude, x, y)) {
      return maybeShot;
    } else {
      return randomHit(exclude);
    }
  }



  private ArrayList<Coord> findHit() {
    ArrayList<Coord> result = new ArrayList<>();
    for (int i = 0; i < opBoard.size(); i += 1) {
      for (int j = 0; j < opBoard.get(i).size(); j += 1) {
        if (opBoard.get(i).get(j).equals(CellStatus.HIT_)) {
          result.add(new Coord(j, i, CellStatus.HIT_));
        }
      }
    }
    return result;
  }


  private boolean checkLeft(int x, int y, CellStatus status) {
      return opBoard.get(y).get(x - 1).equals(status);
    }

    private boolean checkRight(int x, int y, CellStatus status) {
      return opBoard.get(y).get(x + 1).equals(status);
    }

    private boolean checkUp(int x, int y, CellStatus status) {
      return opBoard.get(y - 1).get(x).equals(status);
    }

    private boolean checkDown(int x, int y, CellStatus status) {
      return opBoard.get(y + 1).get(x).equals(status);
    }



    /**
     * @param opponentShotsOnBoard the opponent's shots on this player's board
     *
     * @return a list of Coords where the opponent damaged ships
     */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    ArrayList<Coord> damageResult = new ArrayList<>();
    for (Ship s : aiShips) {
      for (Coord c : opponentShotsOnBoard) {
        for (Coord shipC : s.getLocation()) {
          if (shipC.getX() == (c.getX()) && shipC.getY() == c.getY()) {
            damageResult.add(c);
            s.getSalvoDamage(new ArrayList<>(Arrays.asList(c)));
          }
        }
      }
    }
    salvoAi.setRemainingShips(shipLeft());
    gameBoard = new Board(gameBoard.updateBoard(opponentShotsOnBoard, damageResult));
    return damageResult;
  }

  /**
   * updates shipsRemaining variable
   *
   * @return the updated amount of ships remaining
   */
  private int shipLeft() {
    for (Ship s : aiShips) {
      if (!s.isFloating()) {
        shipsRemaining -= 1;
      }
    }
    return shipsRemaining;
  }

  /**
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {

  }

  /**
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {

  }
}
