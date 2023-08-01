package controller.commands;

import java.util.Scanner;

import model.ImageModel;

/**
 * Represents a command or transformation that can be run on an ImageModel. It has a run method
 * that takes a scanner to read input from and an ImageModel image to run the command on.
 */
public interface Command {

  /**
   * Runs the command or transformation on the given model.
   * @param scanner the scanner to read input from
   * @param model the model to run the command on
   */
  void run(Scanner scanner, ImageModel model);
}
