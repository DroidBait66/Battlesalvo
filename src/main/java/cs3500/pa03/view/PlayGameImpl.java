package cs3500.pa03.view;

import cs3500.pa03.model.Board;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Implementation of actual game
 */
public class PlayGameImpl implements PlayGame {

  OutputStream stream;

  public PlayGameImpl(OutputStream stream) {
    this.stream = new PrintStream(stream);
  }

  /**
   * Displays the intro and asks for size
   */
  @Override
  public void introDisplay() {
    String welcome = "Hello! Welcome to the OOD BattleSalvo Game! \n"
        + "Please enter a valid height and width below:";

    PrintStream printStream = new PrintStream(stream);
    printStream.print(welcome);

  }

  /**
   * display if the size is invalid
   */
  @Override
  public void invalidDisplay() {
    String welcome = "Oops, the display size is wrong. make sure it is between 6 to 15 (inclusive)";

    PrintStream printStream = new PrintStream(stream);
    printStream.print(welcome);

  }

  /**
   * asks for fleet selection
   */
  @Override
  public void fleetSelection(int max) {
    String fleetSelect = "Please enter your fleet in the order [Carrier, Battleship,"
        + " Destroyer, Submarine].\nRemember, your fleet may not exceed size " + max;
    PrintStream printStream = new PrintStream(stream);
    printStream.print(fleetSelect);

  }

  /**
   * called when fleetSlection failed
   *
   * @param msg reason for fail
   */
  @Override
  public void invalidFleet(String msg) {
    String fleetFail = "Uh Oh! You've entered " + msg + ".";
    PrintStream printStream = new PrintStream(stream);
    printStream.print(fleetFail);

  }


  /**
   * @param player
   * @param ai
   */
  @Override
  public void displayGameActual(Board player, Board ai) {
   //TODO
  }
}
