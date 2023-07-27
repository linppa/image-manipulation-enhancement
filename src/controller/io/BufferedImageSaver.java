package controller.io;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import model.image.ImageState;

public class BufferedImageSaver extends BaseIOMethods implements ImageSaver {
  private final String pathToSave;
  private final ImageState image;
  private ByteArrayOutputStream output;

  public BufferedImageSaver(String pathToSave, ImageState image, ByteArrayOutputStream output)
          throws NullPointerException {
    this.pathToSave = Objects.requireNonNull(pathToSave);
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

        int RGB = (red << 16) | (green << 8) | blue;
        bufferedImage.setRGB(col, row, RGB);
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
