package cs3500.pa03.controller;

import cs3500.pa03.view.PlayGame;
import cs3500.pa03.view.PlayGameImpl;

/**
 * Extra class that calls all controller methods so the driver only needs to call one method
 */
public class RunGame {

  /**
   * calls all methods from controller in proper order
   */
  public void run() {
    PlayGame viewOutput = new PlayGameImpl(System.out);
    Controller controller = new ControllerImpl(viewOutput);
    controller.start();
    controller.fleetSelection();
    controller.boardCreation();
    while (!controller.isGameOver()) {
      controller.getPlayerSalvo();
      controller.printSalvos();
    }
    controller.gameResult();

  }
}
