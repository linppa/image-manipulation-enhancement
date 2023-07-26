package model.transform;

import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageState;

/**
 * This class represents a greyscale transformation that sets each pixel's RGB channel values to
 * the current pixel's maximum channel value.
 */
public class GreyscaleValueTransformation extends Clamp implements Transformation {

  @Override
  public ImageState apply(ImageState sourceImage) throws IllegalArgumentException {
    if (sourceImage == null) {
      throw new IllegalArgumentException("Source image cannot be null.");
    }
    Image newImage = new ImageImpl(sourceImage.getHeight(), sourceImage.getWidth());

    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        // adjust each RGB channel for each pixel
        int maxChannelValue = clamp(maxChannelValue(sourceImage, row, col));
        newImage.setPixel(row, col, maxChannelValue, maxChannelValue, maxChannelValue);
      }
    }
    return newImage;
  }

  /**
   * Returns the maximum of the RGB channels for the pixel at the given row and column.
   * @param sourceImage the image to get the pixel from
   * @param row the row of the pixel
   * @param col the column of the pixel
   * @return the maximum of the RGB channels for the pixel at the given row and column
   */
  private int maxChannelValue(ImageState sourceImage, int row, int col) {
    int r = clamp(sourceImage.getRedChannel(row, col));
    int g = clamp(sourceImage.getGreenChannel(row, col));
    int b = clamp(sourceImage.getBlueChannel(row, col));

    return Math.max(r, Math.max(g, b));
  }
}

