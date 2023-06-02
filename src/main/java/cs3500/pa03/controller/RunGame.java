package cs3500.pa03.controller;

public class RunGame {

  public void run() {
    Controller controller = new ControllerImpl();
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
