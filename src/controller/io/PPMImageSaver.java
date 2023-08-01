package controller.io;

import java.io.File;
import java.io.FileWriter;
import java.util.Objects;

import model.image.ImageState;

/**
 * This class represents a PPM image saver for an image processing program. It implements the
 * ImageSaver interface. It saves the .ppm image to a file.
 */
public class PPMImageSaver implements ImageSaver {
  private final String pathToSave;
  private final ImageState image;
  private final Appendable appendable;

  /**
   * Constructs a PPMImageSaver object.
   *
   * @param pathToSave the path to save the image to
   * @param image      the image to save
   * @param output     the output
   * @throws NullPointerException if any of the arguments are null
   */
  public PPMImageSaver(String pathToSave, ImageState image, Appendable output)
          throws NullPointerException {
    this.pathToSave = Objects.requireNonNull(pathToSave);
    this.image = Objects.requireNonNull(image);
    this.appendable = output;
  }

  /**
   * Writes the given message to the appendable.
   *
   * @param message the message to write
   * @throws IllegalStateException if the message could not be written
   */
  private void write(String message) throws IllegalStateException {
    try {
      this.appendable.append(message);
    } catch (Exception e) {
      throw new IllegalStateException("Could not write to output");
    }
  }

  @Override
  public void run() {
    // write header of .PPM file
    write("P3\n");
    write(String.format("%d %d\n", this.image.getWidth(), this.image.getHeight()));
    write("255\n"); // max color value

    //iterate over the image
    for (int row = 0; row < this.image.getHeight(); row++) {
      for (int col = 0; col < this.image.getWidth(); col++) {
        int red = this.image.getRedChannel(col, row);
        int green = this.image.getGreenChannel(col, row);
        int blue = this.image.getBlueChannel(col, row);
        this.write(String.format("%d %d %d ", red, green, blue));
      }
      this.write("\n");
    }
    // write to file
    File file = new File(this.pathToSave);
    try {
      java.io.FileWriter writer = new FileWriter(file);
      System.out.println("Image ppm saved to file.");
      writer.write(this.appendable.toString());
      writer.close();
    } catch (Exception e) {
      throw new IllegalStateException("Could not write to file");
    }
  }
}
