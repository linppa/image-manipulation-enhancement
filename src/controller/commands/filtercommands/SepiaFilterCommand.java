package controller.commands.filtercommands;

import java.util.Objects;
import java.util.Scanner;

import controller.commands.Command;
import model.ImageModel;
import model.image.ImageState;
import model.transform.SepiaFilter;
import model.transform.Transformation;

/**
 * This class represents a command to sepia an image. It implements the Command interface.
 * It has a run method that sepia-tones an image and adds it to the model.
 */
public class SepiaFilterCommand implements Command {
  private ImageModel model;

  /**
   * This constructor creates a SepiaFilterCommand object.
   */
  public SepiaFilterCommand() {
    this.model = null;
  }

  @Override
  public void run(Scanner scanner, ImageModel model) {
    Objects.requireNonNull(scanner);
    this.model = Objects.requireNonNull(model);

    if (!scanner.hasNext()) {
      throw new IllegalArgumentException("Second argument must be the image ID.");
    } // obtain image ID
    String sourceImageID = scanner.next();

    if (!scanner.hasNext()) {
      throw new IllegalArgumentException("Third argument must be the destination image ID.");
    } // obtain destination image ID
    String destID = scanner.next();

    ImageState sourceImage = model.getImage(sourceImageID);
    if (sourceImage == null) {
      throw new IllegalArgumentException("Image with that ID not found!");
    }
    // sepia image & add to model
    Transformation sepiaFilter = new SepiaFilter();
    ImageState sepiaImage = sepiaFilter.apply(sourceImage);
    model.addImage(destID, sepiaImage);
  }
}
