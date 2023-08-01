package model.transform;

import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageState;

/**
 * This class represents a greyscale transformation that sets each pixel's RGB channel values to
 * the current pixel's maximum channel value. This transformation does not change the image's alpha
 * channel.
 */
public class GreyscaleValueTransformation extends BaseTransformMethods implements Transformation {

  @Override
  public ImageState apply(ImageState sourceImage) throws IllegalArgumentException {
    checkNull(sourceImage);
    Image newImage = new ImageImpl(sourceImage.getWidth(), sourceImage.getHeight());

    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        // adjust each RGB channel for each pixel
        int maxChannelValue = clamp(maxChannelValue(sourceImage, col, row));
        newImage.setPixel(col, row, maxChannelValue, maxChannelValue, maxChannelValue);
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

