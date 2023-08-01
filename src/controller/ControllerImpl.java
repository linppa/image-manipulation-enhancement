package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import controller.commands.LoadCommand;
import controller.commands.filtercommands.BlurFilterCommand;
import controller.commands.BrightenCommand;
import controller.commands.Command;
import controller.commands.SaveCommand;
import controller.commands.ValueComponentCommand;
import controller.commands.filtercommands.GreyscaleFilterCommand;
import controller.commands.filtercommands.SepiaFilterCommand;
import controller.commands.filtercommands.SharpenFilterCommand;
import model.ImageModel;

/**
 * This class represents a controller for an image processing program. The controller runs the
 * program.
 */
public class ControllerImpl implements Controller {
  private final Readable input;
  private final ImageModel model;
  private final Appendable appendable;
  private final Map<String, Command> commandMap;

  /**
   * Constructs a ControllerImpl object with the given input, model, and appendable. Initializes
   * the map of commands to run. The map is initialized with the following commands: "brighten",
   * "value-component", "red-component", "green-component", "blue-component", "luma-component",
   * "intensity-component", "load", "save", "blur", "sharpen", "greyscale", and "sepia" filters.
   *
   * @param input      the input
   * @param model      the model
   * @param appendable the appendable
   */
  public ControllerImpl(Readable input, ImageModel model, Appendable appendable) {
    this.input = Objects.requireNonNull(input);
    this.model = Objects.requireNonNull(model);
    this.appendable = Objects.requireNonNull(appendable);

    this.commandMap = new HashMap<String, Command>();
    this.commandMap.put("brighten", new BrightenCommand());
    this.commandMap.put("value-component", new ValueComponentCommand("value"));
    this.commandMap.put("red-component", new ValueComponentCommand("red"));
    this.commandMap.put("green-component", new ValueComponentCommand("green"));
    this.commandMap.put("blue-component", new ValueComponentCommand("blue"));
    this.commandMap.put("luma-component", new ValueComponentCommand("luma"));
    this.commandMap.put("intensity-component", new ValueComponentCommand("intensity"));
    this.commandMap.put("load", new LoadCommand());
    this.commandMap.put("save", new SaveCommand());
    this.commandMap.put("blur", new BlurFilterCommand()); // hw 9 added new command below
    this.commandMap.put("sharpen", new SharpenFilterCommand());
    this.commandMap.put("greyscale", new GreyscaleFilterCommand());
    this.commandMap.put("sepia", new SepiaFilterCommand());
  }

  /**
   * Writes the given string to the appendable.
   *
   * @param string the string to write
   * @throws IllegalStateException if the appendable cannot be written to
   */
  private void write(String string) throws IllegalStateException {
    try {
      this.appendable.append(string);
    } catch (Exception e) {
      throw new IllegalStateException("Could not write to appendable.");
    }
  }

  @Override
  public void runController() {
    Scanner scanner = new Scanner(this.input);

    while (scanner.hasNext()) {
      String command = scanner.next();

      Command commandToRun = this.commandMap.getOrDefault(command, null);
      if (commandToRun == null) {
        write("Command not found. ");
        continue;
      }
      try {
        commandToRun.run(scanner, this.model);
      } catch (IllegalStateException | IllegalArgumentException e) {
        write("Could not run command: " + e.getMessage());
      }
    }
  }
}
