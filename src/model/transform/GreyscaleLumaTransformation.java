package model.transform;

import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageState;

/**
 * This class represents a greyscale transformation that uses the luma or a weighted average
 * of the RGB channels. The weights are 0.2126 for red, 0.7152 for green, and 0.0722 for blue.
 * This transformation sets each pixel's RGB channel values to the current pixel's luma value.
 */
public class GreyscaleLumaTransformation extends Clamp implements Transformation {

  @Override
  public ImageState apply(ImageState sourceImage) throws IllegalArgumentException {
    if (sourceImage == null) {
      throw new IllegalArgumentException("Source image cannot be null.");
    }
    Image newImage = new ImageImpl(sourceImage.getHeight(), sourceImage.getWidth());

    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        // adjust each RGB channel for each pixel
        int greyscaleLuma = clamp(greyscaleLuma(sourceImage, row, col));
        newImage.setPixel(row, col, greyscaleLuma, greyscaleLuma, greyscaleLuma);
      }
    }
    return newImage;
  }

  /**
   * Returns the luma of the RGB channels for the pixel at the given row and column.
   * @param sourceImage the image to get the pixel from
   * @param row the row of the pixel
   * @param col the column of the pixel
   * @return the luma of the RGB channels for the pixel at the given row and column
   */
  private int greyscaleLuma(ImageState sourceImage, int row, int col) {
    int r = clamp(sourceImage.getRedChannel(row, col));
    int g = clamp(sourceImage.getGreenChannel(row, col));
    int b = clamp(sourceImage.getBlueChannel(row, col));

    return (int) Math.round((r * 0.2126) + (g * 0.7152) + (b * 0.0722));
  }

}