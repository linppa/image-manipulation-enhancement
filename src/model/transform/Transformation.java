package model.transform;

import model.image.ImageState;

/**
 * This interface represents a transformation that can be applied to an image.
 */
public interface Transformation {
  /**
   * Applies the transformation to the given image.
   * @param sourceImage the image to apply the transformation to
   * @return the transformed image
   * @throws IllegalArgumentException if the given image is null
   */
  ImageState apply(ImageState sourceImage);
}
