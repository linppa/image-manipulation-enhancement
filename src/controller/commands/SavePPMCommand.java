package controller.commands;

import java.util.Objects;
import java.util.Scanner;

import controller.io.PPMImageSaver;
import model.ImageModel;
import model.image.ImageState;

/**
 * This class represents a command to save a PPM image.
 */
public class SavePPMCommand implements Command {
  private String filepath;
  private ImageModel model;

  /**
   * Constructs a SavePPMCommand object.
   */
  public SavePPMCommand() {
    this.filepath = null;
    this.model = null;
  }

  @Override
  public void run(Scanner scanner, ImageModel model) {
    Objects.requireNonNull(scanner);
    this.model = Objects.requireNonNull(model);

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Second argument must be the file path.");
    } // obtain file path to save to
    this.filepath = scanner.next();

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be image ID.");
    } // obtain image ID
    String sourceImageID = scanner.next();

    ImageState sourceImage = model.getImage(sourceImageID);
    if (sourceImage == null) {
      throw new IllegalArgumentException("Image with that ID not found!");
    }
    // save image to file
    Appendable appendable = new StringBuilder();
    PPMImageSaver saver = new PPMImageSaver(this.filepath, sourceImage, appendable);
    saver.run();
  }
}
