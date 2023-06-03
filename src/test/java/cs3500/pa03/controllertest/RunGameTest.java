package cs3500.pa03.controllertest;

import cs3500.pa03.controller.RunGame;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

public class RunGameTest {

  @Test
  public void testRun() {
    /*
    1 incorrect size
    2 correct size
    3 and 4: fleet selction fail
    5 correct fleet
    6 + 7 player salvo
     */
    String wrongSalvo = "1 1 1 1 1 3 1 4 1 5 1 6 1 7 0 0";
    String rightSalvo = "0 0 1 0 2 0 3 0 4 0 5 0 6 0 7 0 8 0 0 1 1 1 2 1 3 1 4 1 5 1 6 1 7 1 8 1 0 "
        + "2 1 2 2 2 3 2 4 2 5 2 6 2 7 2 8 2 0 3 1 3 2 3 3 3 4 3 5 3 6 3 7 3 8 3 0 4 1 4 2 4 3 "
        + "4 4 4 5 4 6 4 7 4 8 4 0 5 1 5 2 5 3 5 4 5 5 5 6 5 7 5 8 5 0 6 1 6 2 6 3 6 4 6 5 6 6 6"
        + " 7 6 8 6 0 7 1 7 2 7 3 7 4 7 5 7 6 7 7 7 8 7";
    String userInput2 = String.format("1 22%s 8 9%s 2 2 2 0%s 9 23 1 3 %s 3 2 2 1"
            + "%s" + wrongSalvo + "%s" + rightSalvo,
        System.lineSeparator(),
        System.lineSeparator(),
        System.lineSeparator(),
        System.lineSeparator(),
        System.lineSeparator(),
        System.lineSeparator());
    ByteArrayInputStream inputAsByte2 = new ByteArrayInputStream(userInput2.getBytes());
    System.setIn(inputAsByte2);

    RunGame game = new RunGame();
    game.run();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.out.println(outputStream.toString().contains("In this game"));




  }
}
