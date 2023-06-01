package cs3500.pa03.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.controller.Controller;
import cs3500.pa03.controller.ControllerImpl;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;

public class ControllerImplTest {


  /**
   * Tests the getMaxShips helper method
   */
  @Test
  public void testGetMaxShips() {

    String userInput = "1 22 7 9";
    ByteArrayInputStream inputAsByte = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(inputAsByte);
    Controller cont = new ControllerImpl();

    cont.start();

    assertEquals(7, cont.getMaxShips());

  }

  // figure out how to test this portion
  @Test
  public void testFleetSelection() {
    String userInput2 = "1 22 8 9 2 2 2 0 9 23 1 3 3 2 2 1";
    ByteArrayInputStream inputAsByte2 = new ByteArrayInputStream(userInput2.getBytes());
    System.setIn(inputAsByte2);
    Controller cont = new ControllerImpl();
    cont.start();
    cont.fleetSelection(8);
    cont.boardCreation();

  }




}
