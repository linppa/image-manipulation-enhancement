package controller.commands;

import java.util.Objects;
import java.util.Scanner;

import model.ImageModel;
import model.image.ImageState;
import model.transform.BrightenTransformation;
import model.transform.Transformation;

/**
 * This class represents a command to brighten an image. It implements the Command interface.
 * It has a run method that brightens an image and adds it to the model.
 */
public class BrightenCommand implements Command {
  private ImageModel model;

  /**
   * Constructs a BrightenCommand object.
   */
  public BrightenCommand() {
    this.model = null;
  }

  @Override
  public void run(Scanner scanner, ImageModel model) {
    Objects.requireNonNull(scanner);
    this.model = Objects.requireNonNull(model);

    if (!scanner.hasNextInt()) {
      throw new IllegalArgumentException("Second argument must be an integer");
    } // obtain brightness value
    int value = scanner.nextInt();

    if (!scanner.hasNext()) {
      throw new IllegalArgumentException("Third argument must be the image ID.");
    } // obtain image ID
    String sourceImageID = scanner.next();

    if (!scanner.hasNext()) {
      throw new IllegalArgumentException("Fourth argument must be the destination image ID.");
    } // obtain destination image ID
    String destID = scanner.next();

    ImageState sourceImage = model.getImage(sourceImageID);
    if (sourceImage == null) {
      throw new IllegalArgumentException("Image with that ID not found!");
    }
    // brighten image & add to model
    Transformation brightenTransformation = new BrightenTransformation(value);
    ImageState brightenedImage = brightenTransformation.apply(sourceImage);
    model.addImage(destID, brightenedImage);
  }
}
