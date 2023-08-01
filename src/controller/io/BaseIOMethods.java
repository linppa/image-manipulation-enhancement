package controller.io;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class contains methods that are used by multiple image loaders and savers.
 */
public abstract class BaseIOMethods {

  /**
   * Returns the file extension of the given filepath.
   *
   * @param filepath the filepath
   * @return the file extension
   * @throws IllegalArgumentException if the filepath does not have an extension
   */
  protected String getFileExtension(String filepath) throws IllegalArgumentException {
    // find last period in filepath
    int periodIndex = filepath.lastIndexOf(".");
    // if no period, throw exception
    if (periodIndex == -1) {
      throw new IllegalArgumentException("Filepath must have an extension.");
    } else {
      // return string after period
      return filepath.substring(periodIndex + 1);
    }
  }

  /**
   * Checks if the given file extension is valid.
   *
   * @param filePath the filepath
   * @return the file extension
   * @throws IllegalArgumentException if the file extension is not valid
   */
  protected BufferedImage readImageFile(String filePath) throws IllegalArgumentException {
    BufferedImage image;
    try {
      image = ImageIO.read(new FileInputStream(filePath));
    } catch (IOException e) {
      System.out.println("File error:" + filePath);
      throw new IllegalArgumentException("File not found or invalid!");
    }
    return image;
  }
}