package cs3500.pa03.viewtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.CellStatus;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.model.Shots;
import cs3500.pa03.model.ShotsAi;
import cs3500.pa03.view.PlayGame;
import cs3500.pa03.view.PlayGameImpl;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * PlayGameImpl test class
 */
public class PlayGameTest {


  String name;
  HashMap<ShipType, Integer> specifications;
  int height;
  int width;
  Shots salvo;
  int shipsRemaining;
  ManualPlayer player;
  int[][] salvoArray = new int[][] {{1, 2}, {3, 4}, {5, 6}};
  ShotsAi salvo1;
  AiPlayer aiPlayer;
  String aiName = "ai";

  /**
   * Set up of variables
   */
  @BeforeEach
  public void setUp() {
    name = "Player";
    specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 2);
    specifications.put(ShipType.DESTROYER, 2);
    specifications.put(ShipType.SUBMARINE, 1);

    shipsRemaining = specifications.get(ShipType.CARRIER) + specifications.get(ShipType.BATTLESHIP)
        + specifications.get(ShipType.DESTROYER) + specifications.get(ShipType.SUBMARINE);

    height = 8;
    width = 9;
    salvo = new Shots();

    player = new ManualPlayer(name, shipsRemaining, new Random(2), salvo);
    salvo.setSalvo(salvoArray);

    salvo1 = new ShotsAi();
    aiPlayer = new AiPlayer(aiName, shipsRemaining, new Random(2), salvo1);
  }

  /**
   * Tests the introDisplay method
   */
  @Test
  public void testIntroDisplay() {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.introDisplay();

    String output = outputStream.toString();
    String testAns = "Hello! Welcome to the OOD BattleSalvo Game! \n"
        + "Please enter a valid height and width below:\n";

    assertEquals(testAns, output);
  }

  /**
   * tests the method invalidIntro
   */
  @Test
  public void testInvalidIntro() {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.invalidDisplay();

    String output = outputStream.toString();
    String testAns =
        "Oops, the display size is wrong. make sure it is between 6 to 15 (inclusive)\n";

    assertEquals(testAns, output);
  }

  /**
   * tests the method with the fleetSelection prompt
   */
  @Test
  public void testFleetSelection() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.fleetSelection(8);

    String output = outputStream.toString();
    String testAns = "Please enter your fleet in the order [Carrier, Battleship, "
        + "Destroyer, Submarine].\nRemember, your fleet may not exceed size 8\n";

    assertEquals(testAns, output);

    ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();

    PlayGame playGame2 = new PlayGameImpl(outputStream2);
    playGame2.fleetSelection(13);

    String output2 = outputStream2.toString();
    String testAns2 = "Please enter your fleet in the order [Carrier, Battleship, "
        + "Destroyer, Submarine].\nRemember, your fleet may not exceed size 13\n";

    assertEquals(testAns2, output2);

  }

  /**
   * tests the invalid fleet method
   */
  @Test
  public void testInvalidFleet() {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.invalidFleet();

    String output = outputStream.toString();
    String testAns = "Uh Oh! You've entered invalid fleet.\n";

    assertEquals(testAns, output);
  }

  /**
   * Tests the displayGameBoard method. Should display the updated gameboard where the ai
   * does not have ships visible to the player and all cells are colored in
   */
  @Test
  public void testDisplayGameBoard() {

    player.setup(height, width, specifications);
    aiPlayer.setup(height, width, specifications);
    int[][] salvoGive = new int[2][2];
    salvoGive[0][0] = 0;
    salvoGive[0][1] = 0;
    salvoGive[1][0] = 2;
    salvoGive[1][1] = 0;
    salvo.setSalvo(salvoGive);
    List<Coord> shots = player.takeShots();
    player.reportDamage(shots);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.displayGameBoard(salvo.boardGetter(), salvo1.boardGetter());
    String output = outputStream.toString();

    String twoBlankRows = "\u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m "
        + "\u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT"
        + "\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \n\u001B[36mEMPT\u001B[0m "
        + "\u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT"
        + "\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B"
        + "[36mEMPT\u001B[0m \n";

    String empty = "\u001B[36mEMPT\u001B[0m ";
    String ship = "\u001B[35mSHIP\u001B[0m ";
    String hit = "\u001B[31mHIT_\u001B[0m ";
    String miss = "\u001B[32mMISS\u001B[0m ";

    String expected = twoBlankRows + twoBlankRows + twoBlankRows + twoBlankRows + "\n\n" + miss
        + empty + hit + ship + ship + ship + ship + empty + empty + "\n" + empty + empty + empty
        + empty + empty + empty + empty + empty + empty + "\n" + empty + empty + empty + ship
        + ship + ship + ship + ship + ship + "\n" + empty + empty + empty + empty + empty + empty
        + empty + empty + empty + "\n" + ship + ship + ship + ship + empty + empty + empty + empty
        + empty + "\n" + empty + empty + empty + ship + ship + ship + ship + ship + empty + "\n"
        + empty + empty + empty + empty + empty + empty + empty + ship + empty + "\n"
        + empty + ship + ship + ship + ship + ship + empty + ship + empty + "\n";
    assertEquals(expected, output);

  }

  /**
   * tests the method that displays the prompt to ask for users shots
   */
  @Test
  public void testAskFor() {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.askForSalvo(8);

    String output = outputStream.toString();
    String testAns = "Please enter 8 shots: \n";
    assertEquals(testAns, output);

  }

  /**
   * Tests if displayShots works. displays the users hits, misses and the ai hits
   */
  @Test
  public void testDisplayShots() {

    List<Coord> playerHit = new ArrayList<>();
    playerHit.add(new Coord(1, 2, CellStatus.SHIP));
    playerHit.add(new Coord(1, 3, CellStatus.SHIP));
    playerHit.add(new Coord(1, 4, CellStatus.SHIP));
    ArrayList<Coord> playerMiss = new ArrayList<>();
    playerMiss.add(new Coord(2, 2, CellStatus.EMPT));
    playerMiss.add(new Coord(5, 3, CellStatus.EMPT));
    playerMiss.add(new Coord(6, 7, CellStatus.EMPT));
    List<Coord> aiHit = new ArrayList<>();
    aiHit.add(new Coord(3, 4, CellStatus.SHIP));
    aiHit.add(new Coord(4, 4, CellStatus.SHIP));
    aiHit.add(new Coord(5, 4, CellStatus.SHIP));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    PlayGameImpl playGame = new PlayGameImpl(outputStream);
    playGame.displayShots(playerHit, playerMiss, aiHit);
    String output = outputStream.toString();

    String outputResult = "Player shots that hit:\nX: 1 Y: 2\nX: 1 Y: 3\nX: 1 Y: 4\n"
        + "\nPlayer shots that missed:\nX: 2 Y: 2\nX: 5 Y: 3\nX: 6 Y: 7\n"
        + "\nAi shots that hit:\nX: 3 Y: 4\nX: 4 Y: 4\nX: 5 Y: 4\n";
    assertEquals(outputResult, output);

  }

  /**
   * Tests the salvoFail method
   */
  @Test
  public void testSalvoFail() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.salvoFail();
    String output = outputStream.toString();
    String outputResult = "Incorrect amount of salvos, please try again\n";
    assertEquals(outputResult, output);

  }

  /**
   * Tests didPlayerWin method
   * all 3 possible results
   */
  @Test
  public void testDidPlayerWin() {
    ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
    PlayGame playGame1 = new PlayGameImpl(outputStream1);
    playGame1.didPlayerWinDisplay(GameResult.WON);
    String output1 = outputStream1.toString();
    String outputResult1 = "In this game, you WON";
    assertEquals(outputResult1, output1);

    ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
    PlayGame playGame2 = new PlayGameImpl(outputStream2);
    playGame2.didPlayerWinDisplay(GameResult.LOST);
    String output2 = outputStream2.toString();
    String outputResult2 = "In this game, you LOST";
    assertEquals(outputResult2, output2);

    ByteArrayOutputStream outputStream3 = new ByteArrayOutputStream();
    PlayGame playGame3 = new PlayGameImpl(outputStream3);
    playGame3.didPlayerWinDisplay(GameResult.TIED);
    String output3 = outputStream3.toString();
    String outputResult3 = "In this game, you TIED";
    assertEquals(outputResult3, output3);

  }


}
