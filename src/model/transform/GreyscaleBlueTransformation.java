package model.transform;

import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageState;

/**
 * This class represents a greyscale transformation that sets each pixel's RGB channel values to
 * the current pixel's blue channel value. This transformation does not change the image's alpha
 * channel.
 */
public class GreyscaleBlueTransformation extends BaseTransformMethods implements Transformation {

  @Override
  public ImageState apply(ImageState sourceImage) throws IllegalArgumentException {
    checkNull(sourceImage);
    Image newImage = new ImageImpl(sourceImage.getWidth(), sourceImage.getHeight());

    // adjust each RGB channel for each pixel
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int newB = clamp(sourceImage.getBlueChannel(col, row));
        newImage.setPixel(col, row, newB, newB, newB);
      }
    }
    return newImage;
  }
}
