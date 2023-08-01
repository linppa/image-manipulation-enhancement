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
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filePath + " not found!");
      throw new IllegalArgumentException("File not found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());

    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    // store image data
    Image image = new ImageImpl(width, height);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        // store rgb values
        image.setPixel(j, i, r, g, b);
      }
    }
    return image;
  }
}
