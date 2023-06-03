package cs3500.pa03.view;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface for the view
 */
public interface PlayGame {

  /**
   * Displays the intro and asks for size
   */
  void introDisplay();

  /**
   * display if the size is invalid
   */
  void invalidDisplay();

  /**
   * asks for fleet selection
   *
   * @param max the max amount of ship there can be
   */
  void fleetSelection(int max);

  /**
   * called when fleetSlection failed
   *
   */
  void invalidFleet();

  /**
   * displays the two given boards in the current state
   *
   * @param player players board
   * @param ai AI's board, location of ships is hidden
   */
  void displayGameBoard(Board player, Board ai);

  /**
   * asks the player for their salvo
   *
   * @param shots max number of shots they can take
   */
  void askForSalvo(int shots);

  /**
   * used when the incorrect amount of shots is given (not enough shots)
   */
  void salvoFail();

  /**
   * Displays the results of a salvo in text
   *
   * @param playerHit player shots that hit
   * @param playerMiss player shots that missed
   * @param aiHits ai shots that hit
   */
  void displayShots(List<Coord> playerHit, ArrayList<Coord> playerMiss, List<Coord> aiHits);

  /**
   * Displays the result of the game
   *
   * @param result game result enum that determines the outcome of the game
   */
  void didPlayerWinDisplay(GameResult result);


}
