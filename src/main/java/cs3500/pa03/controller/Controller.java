package cs3500.pa03.controller;

/**
 * Interface for controller
 */
public interface Controller {

  /**
   * first method that should be ran. Sets the variables width and height equal the w and h of the
   * game
   */
  void start();

  /**
   * used to create the specs hashmap. player selects fleet
   */
  void fleetSelection();

  /**
   * Gets the max number of ships allowed. needed to make fleetSelection work
   *
   * @return integer that is lower
   */
  int getMaxShips();

  /**
   * creates the initial board for the game
   */
  void boardCreation();

  /**
   * Gets the players salvo via user input and also calls the Ai players salvo
   */
  void getPlayerSalvo();

  /**
   * displays the shots of both players and updates/calls the board
   */
  void printSalvos();

  /**
   * Displays the result of the game
   */
  void gameResult();

  /**
   * Checks if either the Ai or the player still have shots left
   *
   * @return boolean true or false
   */
  boolean isGameOver();
}
