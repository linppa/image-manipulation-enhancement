package controller.io;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class BaseIOMethods {

  protected String getFileExtension(String filepath) {
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