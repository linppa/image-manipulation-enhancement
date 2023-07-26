package model.transform;

import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageState;

/**
 * This class represents a greyscale transformation that sets each pixel's RGB channel values to
 * the current pixel's blue channel value.
 */
public class GreyscaleBlueTransformation extends Clamp implements Transformation {

  @Override
  public ImageState apply(ImageState sourceImage) throws IllegalArgumentException {
    if (sourceImage == null) {
      throw new IllegalArgumentException("Source image cannot be null.");
    }
    Image newImage = new ImageImpl(sourceImage.getHeight(), sourceImage.getWidth());

    // adjust each RGB channel for each pixel
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int newB = clamp(sourceImage.getBlueChannel(row, col));
        newImage.setPixel(row, col, newB, newB, newB);
      }
    }
    return newImage;
  }
}
