package cs3500.pa03.controller;

public interface Controller {

  void start();

  void fleetSelection(int maxShips);

  int getMaxShips();

  void boardCreation();

  void getPlayerSalvo();

  void runSalvo();

  boolean isGameOver();
}
