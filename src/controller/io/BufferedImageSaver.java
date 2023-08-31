package controller.io;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import model.image.ImageState;

/**
 * This class represents a buffered image saver for an image processing program. It implements the
 * ImageSaver interface and extends the BaseIOMethods abstract class. It has a run method that saves
 * the image state to a file. Supported file extensions: png, jpeg, jpg.
 */
public class BufferedImageSaver extends BaseIOMethods implements ImageSaver {
  private String pathToSave;
  private final ImageState image;
  private ByteArrayOutputStream output;

  /**
   * Constructs a BufferedImageSaver object.
   *
   * @param pathToSave the path to save the image to
   * @param image      the image state
   * @param output     the output stream
   * @throws NullPointerException if the path to save or image state is null
   */
  public BufferedImageSaver(String pathToSave, ImageState image, ByteArrayOutputStream output)
          throws NullPointerException {
    // check if file extension is supported
    this.pathToSave = Objects.requireNonNull(pathToSave);
    String extension = getFileExtension(pathToSave);
    if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")
            || extension.equals("ppm")) {
      this.pathToSave = pathToSave;
    } else {
      throw new IllegalArgumentException("File extension not supported!");
    }

    this.image = Objects.requireNonNull(image);
    this.output = output;
  }

  @Override
  public void run() {
    int height = this.image.getHeight();
    int width = this.image.getWidth();
    // create buffered image object
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {

        int red = this.image.getRedChannel(col, row);
        int green = this.image.getGreenChannel(col, row);
        int blue = this.image.getBlueChannel(col, row);

        int rGB = (red << 16) | (green << 8) | blue;
        bufferedImage.setRGB(col, row, rGB);
      }
    }
    // array to hold bytes
    this.output = new ByteArrayOutputStream();
    try {
      // write bytes to output stream
      String extension = getFileExtension(pathToSave);

      ImageIO.write(bufferedImage, extension, this.output);
      this.output.close();

      // save image to file
      File outputFile = new File(pathToSave);
      ImageIO.write(bufferedImage, extension, outputFile);
      System.out.println("Image " + extension + " saved to file.");

    } catch (IOException e) {
      System.out.println("File error:" + this.pathToSave);
      throw new IllegalArgumentException("Could not save file!");
    }

  }
}
