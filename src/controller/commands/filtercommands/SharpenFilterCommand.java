package controller.commands.filtercommands;

import java.util.Scanner;
import java.util.logging.Filter;

import controller.commands.Command;
import model.ImageModel;
import model.filter.Filtering;
import model.filter.FilteringImpl;
import model.image.ImageState;

public class SharpenFilterCommand implements Command {
  private ImageModel model;

  public SharpenFilterCommand() {
    this.model = null;
  }

  @Override
  public void run(Scanner scanner, ImageModel model) {
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

    double[][] sharpenFilter = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}
    };

    // sharpen image & add to model
    Filtering sharpenFiltering = new FilteringImpl();
    ImageState sharpenedImage = sharpenFiltering.applyFilter(sourceImage, sharpenFilter);
    model.addImage(destID, sharpenedImage);
  }
}
