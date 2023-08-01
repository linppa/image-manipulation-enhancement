package controller.commands;

import java.util.Objects;
import java.util.Scanner;

import model.ImageModel;
import model.image.ImageState;
import model.transform.GreyscaleBlueTransformation;
import model.transform.GreyscaleGreenTransformation;
import model.transform.GreyscaleIntensityTransformation;
import model.transform.GreyscaleLumaTransformation;
import model.transform.GreyscaleRedTransformation;
import model.transform.GreyscaleValueTransformation;
import model.transform.Transformation;

/**
 * This class represents a command to transform an image to greyscale. It implements the Command
 * interface. It has a run method that transforms an image to greyscale and adds it to the model.
 * The user can specify which component of the image to use to transform to greyscale. For example,
 * the user can specify to use the red component of the image to transform to greyscale. Other
 * components include green, blue, luma, intensity, and value.
 */
public class ValueComponentCommand implements Command {
  private ImageModel model;
  private final String component;

  /**
   * Constructs a ValueComponentCommand object.
   *
   * @param component the component to transform to greyscale
   */
  public ValueComponentCommand(String component) {
    this.model = null;
    this.component = component;
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
    Transformation componentTransformation = null;

    // determine which component to transform to greyscale
    if (sourceImage == null) {
      throw new IllegalArgumentException("Image with that ID not found!");
    } else if (this.component.equals("value")) {
      componentTransformation = new GreyscaleValueTransformation();

    } else if (this.component.equals("red")) {
      componentTransformation = new GreyscaleRedTransformation();

    } else if (this.component.equals("green")) {
      componentTransformation = new GreyscaleGreenTransformation();

    } else if (this.component.equals("blue")) {
      componentTransformation = new GreyscaleBlueTransformation();

    } else if (this.component.equals("luma")) {
      componentTransformation = new GreyscaleLumaTransformation();

    } else if (this.component.equals("intensity")) {
      componentTransformation = new GreyscaleIntensityTransformation();

    } else {
      throw new IllegalArgumentException("Invalid component type.");
    }
    // transform image to greyscale & add to model
    ImageState componentImage = componentTransformation.apply(sourceImage);
    model.addImage(destID, componentImage);
  }
}
