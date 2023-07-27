package model.transform;

import model.image.ImageState;

/**
 * This interface represents a transformation that can be applied to an image.
 * A transformation is a function that takes in an image and returns a new image.
 * It may be a color transformation, a blur, a sharpen, etc.
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
