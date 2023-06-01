package cs3500.pa03.controller;

import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.model.Shots;
import cs3500.pa03.model.ShotsAi;
import cs3500.pa03.view.PlayGame;
import cs3500.pa03.view.PlayGameImpl;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ControllerImpl implements Controller {

  private HashMap<ShipType, Integer> specs = new HashMap<>();
  private ManualPlayer player1;
  private AiPlayer player2;

  private int width = 0;
  private int height = 0;
  private int playerShipsRemaining;
  private int aiShipsRemaining;
  private List<Ship> playerShips;
  private List<Ship> aiShips;
  private Shots playerSalvos = new Shots();
  private ShotsAi aiSalvos = new ShotsAi();
  Scanner scanner = new Scanner(System.in);





  /**
   * first method that should be ran. Sets the variables width and height equal the w and h of the
   * game
   */
  @Override
  public void start() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.introDisplay();
    String output = outputStream.toString();
    System.out.println(output);

    int preHeight = scanner.nextInt();
    int preWidth = scanner.nextInt();

    if (Math.min(preHeight, preWidth) < 6 || Math.max(preHeight, preWidth) > 15) {
      ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
      new PlayGameImpl(outputStream2).invalidDisplay();
      String output2 = outputStream2.toString();
      System.out.println(output2);
      start();
    } else {
      height = preHeight;
      width = preWidth;
    }
  }

  /**
   * used to create the specs hashmap. player selects fleet
   *
   * @param maxShips max number of ships avaliable
   */
  @Override
  public void fleetSelection(int maxShips) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PlayGame pickFleet = new PlayGameImpl(outputStream);
    pickFleet.fleetSelection(maxShips);
    String output = outputStream.toString();
    System.out.println(output);

    clearSpecs();
    int count = 0;
    int[] numShips =  new int[4];
    while (scanner.hasNextInt() && count < 4) {
      numShips[count] = scanner.nextInt();
      count += 1;
    }
    playerShipsRemaining = numShips[0] + numShips[1] + numShips[2] + numShips[3];
    if (playerShipsRemaining > maxShips ||
        numShips[0] == 0 || numShips[1] == 0 || numShips[2] == 0 || numShips[3] == 0) {
      fleetSelection(maxShips);
    } else {
      specs.put(ShipType.CARRIER, numShips[0]);
      specs.put(ShipType.BATTLESHIP, numShips[1]);
      specs.put(ShipType.DESTROYER, numShips[2]);
      specs.put(ShipType.SUBMARINE, numShips[3]);
      aiShipsRemaining = playerShipsRemaining;

    }

  }

  /**
   * Clears the specs hashmap, helper for fleet selection
   */
  private void clearSpecs() {
    specs.put(ShipType.CARRIER, 0);
    specs.put(ShipType.BATTLESHIP, 0);
    specs.put(ShipType.DESTROYER, 0);
    specs.put(ShipType.SUBMARINE, 0);
  }

  /**
   * Gets the max number of ships allowed. needed to make fleetSelection work
   */
  public int getMaxShips() {
    return Math.min(height, width);
  }

  /**
   * creates the initial board for the game
   */
  @Override
  public void boardCreation() {
    player1 = new ManualPlayer("Player", playerShipsRemaining, new Random(), playerSalvos);
    player2 = new AiPlayer("Ai", aiShipsRemaining, new Random(), aiSalvos);

    playerShips = player1.setup(height, width, specs);
    aiShips = player2.setup(height, width, specs);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.displayGameBoard(player1.gameBoard, player2.gameBoard);
    String output = outputStream.toString();
    System.out.println(output);

  }

  /**
   *
   */
  @Override
  public void runSalvo() {

  }

  /**
   * @return
   */
  @Override
  public boolean isGameOver() {
    return false;
  }
}
