package controller.commands.filtercommands;

import java.util.Objects;
import java.util.Scanner;

import controller.commands.Command;
import model.ImageModel;
import model.image.ImageState;
import model.transform.SharpenFilter;
import model.transform.Transformation;

/**
 * This class represents a command to sharpen an image. It implements the Command interface.
 * It has a run method that sharpens an image and adds it to the model.
 */
public class SharpenFilterCommand implements Command {
  private ImageModel model;

  /**
   * This constructor creates a SharpenFilterCommand object.
   */
  public SharpenFilterCommand() {
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
    // sharpen image & add to model
    Transformation sharpenFilter = new SharpenFilter();
    ImageState sharpenedImage = sharpenFilter.apply(sourceImage);
    model.addImage(destID, sharpenedImage);
  }
}
