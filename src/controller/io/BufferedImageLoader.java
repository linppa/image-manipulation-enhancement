package controller.io;

import java.awt.image.BufferedImage;
import java.util.Objects;

import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageState;

/**
 * This class represents a buffered image loader for an image processing program. It implements the
 * ImageLoader interface and extends the BaseIOMethods abstract class. It has a run method that
 * loads a buffered image and returns the image state. Supported file extensions: png, jpeg, jpg.
 */
public class BufferedImageLoader extends BaseIOMethods implements ImageLoader {
  private final String filePath;

  /**
   * Constructs a BufferedImageLoader object.
   * @param filePath the file path
   * @throws NullPointerException if the file path is null
   */
  public BufferedImageLoader(String filePath) throws NullPointerException {
    this.filePath = Objects.requireNonNull(filePath);
  }

  @Override
  public ImageState run() {
    BufferedImage bufferedImage = readImageFile(this.filePath);

    int height = bufferedImage.getHeight();
    int width = bufferedImage.getWidth();
    Image convertedImage = new ImageImpl(width, height);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        // get RGB values from buffered image
        int rGB = bufferedImage.getRGB(col, row);
        int red = (rGB >> 16) & 0xff;
        int green = (rGB >> 8) & 0xff;
        int blue = rGB & 0xff;

        convertedImage.setPixel(col, row, red, green, blue);
      }
    }
    return convertedImage;
  }
}