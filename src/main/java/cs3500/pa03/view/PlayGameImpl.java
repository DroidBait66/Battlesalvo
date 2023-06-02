package cs3500.pa03.view;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.CellStatus;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Implementation of actual game
 */
public class PlayGameImpl implements PlayGame {
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";

  PrintStream printStream;

  public PlayGameImpl(OutputStream stream) {
    this.printStream = new PrintStream(stream);
  }

  /**
   * Displays the intro and asks for size
   */
  @Override
  public void introDisplay() {
    String welcome = "Hello! Welcome to the OOD BattleSalvo Game! \n"
        + "Please enter a valid height and width below:\n";

    printStream.print(welcome);

  }

  /**
   * display if the size is invalid
   */
  @Override
  public void invalidDisplay() {
    String welcomeFail =
        "Oops, the display size is wrong. make sure it is between 6 to 15 (inclusive)\n";


    printStream.print(welcomeFail);

  }

  /**
   * asks for fleet selection
   */
  @Override
  public void fleetSelection(int max) {
    String fleetSelect = "Please enter your fleet in the order [Carrier, Battleship,"
        + " Destroyer, Submarine].\nRemember, your fleet may not exceed size " + max + "\n";

    printStream.print(fleetSelect);

  }

  /**
   * called when fleetSlection failed
   *
   */
  @Override
  public void invalidFleet() {
    String fleetFail = "Uh Oh! You've entered invalid fleet.\n";
    printStream.print(fleetFail);

  }


  /**
   * displays the two given boards in the current state
   *
   * @param player players board
   * @param ai ai board, location of ships is hidden
   */
  @Override
  public void displayGameBoard(Board player, Board ai) {
   ArrayList<ArrayList<CellStatus>> playerBoard = player.getBoard();
    ArrayList<ArrayList<CellStatus>> aiBoard = ai.getOpponentBoard(ai.getBoard());

    String aiString = "";
    for (ArrayList<CellStatus> row : aiBoard) {
      for (CellStatus c : row) {
        String coloredOut = coloredString(c.toString());
        aiString += coloredOut + " ";
      }
      aiString += "\n";
    }
    String playString = "";
    for (ArrayList<CellStatus> row : playerBoard) {
      for (CellStatus c : row) {
        String coloredOut = coloredString(c.toString());

        playString += coloredOut + " ";
      }
      playString += "\n";
    }

    String output = aiString + "\n\n" + playString;
    printStream.print(output);

  }

  /**
   * Colors in a given string, makes game more readable
   *
   * @param s given string
   * @return string that is colored in
   */
  private String coloredString(String s) {
    return switch (s) {
      case "SHIP" -> ANSI_PURPLE + s + ANSI_RESET;
      case "HIT_" -> ANSI_RED + s + ANSI_RESET;
      case "MISS" -> ANSI_GREEN + s + ANSI_RESET;
      case "EMPT" -> ANSI_CYAN + s + ANSI_RESET;
      default -> s;
    };
  }

  /**
   * @param shots
   */
  @Override
  public void askForSalvo(int shots) {
    String getSalvo = "Please enter " + shots + " shots: \n";

    printStream.print(getSalvo);

  }

  @Override
  public void salvoFail() {
    String get = "Incorrect amount of salvos, please try again\n";
    printStream.print(get);
  }


}
