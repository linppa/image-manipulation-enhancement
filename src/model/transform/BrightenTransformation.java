package model.transform;

import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageState;

/**
 * This class represents a transformation that brightens an image.
 */
public class BrightenTransformation extends BaseTransformMethods implements Transformation {
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
    checkNull(sourceImage);

    int height = sourceImage.getHeight();
    int width = sourceImage.getWidth();

    Image newImage = new ImageImpl(width, height);

    // adjust each RGB channel for each pixel
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int newR = (sourceImage.getRedChannel(col, row) + this.brightenValue);
        int newG = (sourceImage.getGreenChannel(col, row) + this.brightenValue);
        int newB = (sourceImage.getBlueChannel(col, row) + this.brightenValue);

        // clamp the values
        newR = clamp(newR);
        newG = clamp(newG);
        newB = clamp(newB);

        newImage.setPixel(col, row, newR, newG, newB);
      }
    }
    return newImage;
  }
}
