import java.io.InputStreamReader;
import java.io.Reader;

import controller.Controller;
import controller.ControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;

/**
 * This class represents the main class for the program.
 */
public class Main {

  /**
   * This method runs the program. It creates a new ImageModel and Controller, and runs the
   * controller to allow the user to interact with the program.
   * @param args the command line arguments
   * @throws IllegalArgumentException if the arguments are invalid
   */
  public static void main(String[] args) throws IllegalArgumentException {
    try {
      Reader input = new InputStreamReader(System.in);
      ImageModel model = new ImageModelImpl();
      Controller controller = new ControllerImpl(input, model, System.out);
      controller.runController();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
