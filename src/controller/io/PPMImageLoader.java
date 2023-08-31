package controller.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageState;

/**
 * This class represents a PPM image loader for an image processing program. It implements the
 * ImageLoader interface. It has a run method that loads a PPM image and returns the image state.
 */
public class PPMImageLoader implements ImageLoader {
  private final String filePath;

  /**
   * Constructs a PPMImageLoader object.
   *
   * @param filePath the file path
   * @throws NullPointerException if the file path is null
   */
  public PPMImageLoader(String filePath) throws NullPointerException {
    this.filePath = Objects.requireNonNull(filePath);
  }

  @Override
  public ImageState run() throws IllegalArgumentException {
    Scanner scanner;
    try {
      scanner = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filePath + " not found!");
      throw new IllegalArgumentException("File not found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (scanner.hasNextLine()) {
      String s = scanner.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    scanner = new Scanner(builder.toString());
    String token;
    token = scanner.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = scanner.nextInt();
    int height = scanner.nextInt();
    int maxValue = scanner.nextInt();
    // store image data
    Image image = new ImageImpl(width, height);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = scanner.nextInt();
        int g = scanner.nextInt();
        int b = scanner.nextInt();
        // store rgb values
        image.setPixel(j, i, r, g, b);
      }
    }
    return image;
  }
}
