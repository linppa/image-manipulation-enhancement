package model.transform;

import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageState;

/**
 * This class represents a transformation that brightens an image.
 */
public class BrightenTransformation extends Clamp implements Transformation {
  final private int brightenValue;

  /**
   * Constructs a BrightenTransformation object with the given brighten value. If the given value
   * is negative, the image will be darkened instead.
   * @param brightenValue the value to brighten/darken the image by
   */
  public BrightenTransformation(int brightenValue) {
    this.brightenValue = brightenValue;
  }

  @Override
  public ImageState apply(ImageState sourceImage) throws IllegalArgumentException {
    if (sourceImage == null) {
      throw new IllegalArgumentException("Source image cannot be null.");
    }
    Image newImage = new ImageImpl(sourceImage.getHeight(), sourceImage.getWidth());

    // adjust each RGB channel for each pixel
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int newR = clamp(sourceImage.getRedChannel(row, col) + this.brightenValue);
        int newG = clamp(sourceImage.getGreenChannel(row, col) + this.brightenValue);
        int newB = clamp(sourceImage.getBlueChannel(row, col) + this.brightenValue);
        newImage.setPixel(row, col, newR, newG, newB);
      }
    }
    return newImage;
  }
}
