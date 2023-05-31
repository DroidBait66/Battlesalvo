package cs3500.pa03.viewTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.view.PlayGame;
import cs3500.pa03.view.PlayGameImpl;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

public class PlayGameTest {

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
    String testAns = "Oops, the display size is wrong. make sure it is between 6 to 15 (inclusive)";

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


}
