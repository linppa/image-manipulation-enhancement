package controller.commands;

import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.Scanner;

import controller.io.BaseIOMethods;
import controller.io.BufferedImageSaver;
import controller.io.PPMImageSaver;
import model.ImageModel;
import model.image.ImageState;

/**
 * This class represents a command to save a PPM image. It implements the Command interface and
 * extends the BaseIOMethods class. It has a run method that saves a PPM image.
 */
public class SaveCommand extends BaseIOMethods implements Command {
  private String filepath;
  private ImageModel model;

  /**
   * Constructs a SaveCommand object.
   */
  public SaveCommand() {
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
    String fileExtension = getFileExtension(this.filepath);

    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be image ID.");
    } // obtain image ID
    String sourceImageID = scanner.next();

    ImageState sourceImage = model.getImage(sourceImageID);
    if (sourceImage == null) {
      throw new IllegalArgumentException("Image with that ID not found!");
    }
    // save image according to file extension & add to model
    if (fileExtension.equals("ppm")) {
      Appendable appendable = new StringBuilder();
      PPMImageSaver saver = new PPMImageSaver(this.filepath, sourceImage, appendable);
      saver.run();

    } else if (fileExtension.equals("png") || fileExtension.equals("jpeg")
            || fileExtension.equals("jpg")) {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      BufferedImageSaver saver = new BufferedImageSaver(this.filepath, sourceImage, outputStream);
      saver.run();

    } else {
      throw new IllegalArgumentException("File extension not supported!");
    }

  }
}
