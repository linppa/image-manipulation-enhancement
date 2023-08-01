import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import controller.Controller;
import controller.ControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;

/**
 * This class represents the main class for the program. It runs the program and allows the user to
 * interact with it.
 */
public class Main {

  /**
   * This method runs the program. It creates a new ImageModel and Controller, and runs the
   * controller to allow the user to interact with the program. If the user runs a script, the
   * program will read the script and run it as input to the controller.
   *
   * @param args the command line arguments
   * @throws IllegalArgumentException if the arguments are invalid
   */
  public static void main(String[] args) throws IllegalArgumentException {
    // check if the user runs a script
    if (args.length > 0 && args[0].equals("-file")) {
      if (args.length < 2) {
        throw new IllegalArgumentException("No file name given.");
      }
      // read the script
      String fileName = args[1];
      try {
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        StringBuilder script = new StringBuilder();
        String line;
        while ((line = input.readLine()) != null) {
          script.append(line).append("\n");
        }
        // runs script as input to controller
        Readable scriptInput = new StringReader(script.toString());
        ImageModel model = new ImageModelImpl();
        Controller controller = new ControllerImpl(scriptInput, model, System.out);
        controller.runController();
      } catch (IOException e) {
        System.out.println("File script not found or invalid.");
      }
    } else {
      // runs program normally via keyboard input
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
}
