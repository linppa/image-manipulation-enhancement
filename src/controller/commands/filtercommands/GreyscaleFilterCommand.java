package controller.commands.filtercommands;

import java.util.Objects;
import java.util.Scanner;

import controller.commands.Command;
import model.ImageModel;
import model.image.ImageState;
import model.transform.GreyscaleFilter;
import model.transform.Transformation;

public class GreyscaleFilterCommand implements Command {
  private ImageModel model;

  public GreyscaleFilterCommand() {
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
    // grey image & add to model
    Transformation greyFilter = new GreyscaleFilter();
    ImageState greyImage = greyFilter.apply(sourceImage);
    model.addImage(destID, greyImage);
  }
}
