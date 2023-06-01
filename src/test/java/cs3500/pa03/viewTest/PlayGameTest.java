package cs3500.pa03.viewTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.model.Shots;
import cs3500.pa03.model.ShotsAi;
import cs3500.pa03.view.PlayGame;
import cs3500.pa03.view.PlayGameImpl;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
  @Test
  public void testIntroDisplay() {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.introDisplay();

    String output = outputStream.toString();
    String testAns = "Hello! Welcome to the OOD BattleSalvo Game! \n"
        + "Please enter a valid height and width below:";

    assertEquals(testAns, output);
  }

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

  @Test
  public void testFleetSelection() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.fleetSelection(8);

    String output = outputStream.toString();
    String testAns = "Please enter your fleet in the order [Carrier, Battleship, "
        + "Destroyer, Submarine].\nRemember, your fleet may not exceed size 8";

    assertEquals(testAns, output);

    ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();

    PlayGame playGame2 = new PlayGameImpl(outputStream2);
    playGame2.fleetSelection(13);

    String output2 = outputStream2.toString();
    String testAns2 = "Please enter your fleet in the order [Carrier, Battleship, "
        + "Destroyer, Submarine].\nRemember, your fleet may not exceed size 13";

    assertEquals(testAns2, output2);

  }

  @Test
  public void testInvalidFleet() {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.invalidFleet("invalid fleet sizes");

    String output = outputStream.toString();
    String testAns = "Uh Oh! You've entered invalid fleet sizes.";

    assertEquals(testAns, output);
  }

  @Test
  public void testDisplayGameBoard() {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    player.setup(height, width, specifications);
    aiPlayer.setup(height, width, specifications);

    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.displayGameBoard(player.gameBoard, aiPlayer.gameBoard);
    String output = outputStream.toString();

    String twoBlankRows = "\u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m "
        + "\u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT"
        + "\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \n\u001B[36mEMPT\u001B[0m "
        + "\u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT"
        + "\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B[36mEMPT\u001B[0m \u001B"
        + "[36mEMPT\u001B[0m \n";

    String empty = "\u001B[36mEMPT\u001B[0m ";
    String ship = "\u001B[35mSHIP\u001B[0m ";

    String expected = twoBlankRows + twoBlankRows + twoBlankRows + twoBlankRows + "\n\n" + empty
        + empty + ship + ship + ship + ship + ship + empty + empty + "\n" + empty + empty + empty
        + empty + empty + empty + empty + empty + empty + "\n" + empty + empty + empty + ship
        + ship + ship + ship + ship + ship + "\n" + empty + empty + empty + empty + empty + empty
        + empty + empty + empty + "\n" + ship + ship + ship + ship + empty + empty + empty + empty
        + empty + "\n" + empty + empty + empty + ship + ship + ship + ship + ship + empty + "\n"
        + empty + empty + empty + empty + empty + empty + empty + ship + empty + "\n"
        + empty + ship + ship + ship + ship + ship + empty + ship + empty + "\n";
    assertEquals(expected, output);

  }

  @Test
  public void testAskFor() {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    PlayGame playGame = new PlayGameImpl(outputStream);
    playGame.askForSalvo(8);

    String output = outputStream.toString();
    String testAns = "Please enter 8 shots: ";
    assertEquals(testAns, output);

  }


}
