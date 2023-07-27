package controller.commands;

import java.util.Objects;
import java.util.Scanner;

import controller.io.BaseIOMethods;
import controller.io.BufferedImageLoader;
import controller.io.PPMImageLoader;
import model.ImageModel;
import model.image.ImageState;

/**
 * This class represents a command to load a PPM image.
 */
public class LoadPPMCommand extends BaseIOMethods implements Command {
  private String filepath;
  private ImageModel model;

  /**
   * Constructs a LoadPPMCommand object.
   */
  public LoadPPMCommand() {
    this.filepath = null;
    this.model = null;
  }

  @Override
  public void run(Scanner scanner, ImageModel model) {
    Objects.requireNonNull(scanner);
    this.model = Objects.requireNonNull(model);

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Second argument must be the filename.");
    } // obtain filename
    this.filepath = scanner.next();
    String fileExtension = getFileExtension(this.filepath);

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be the image ID.");
    } // obtain image ID
    String sourceImageID = scanner.next();

    // load image according to file extension & add to model
    if (fileExtension.equals("ppm")) {
      PPMImageLoader loader = new PPMImageLoader(this.filepath);
      ImageState loadedImage = loader.run();
      model.addImage(sourceImageID, loadedImage);

    } else if (fileExtension.equals("png") || fileExtension.equals("jpeg")
            || fileExtension.equals("jpg")) {
      BufferedImageLoader loader = new BufferedImageLoader(this.filepath);
      ImageState loadedImage = loader.run();
      model.addImage(sourceImageID, loadedImage);

    } else {
      throw new IllegalArgumentException("File extension not supported!");
    }
  }
}
