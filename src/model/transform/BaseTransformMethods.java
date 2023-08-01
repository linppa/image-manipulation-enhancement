package model.transform;

import model.image.ImageState;

/**
 * This class represents the base methods for image transformations. It contains the clamp method
 * that clamps values to be between 0 and 255, and the checkNull method that checks if an object is
 * null.
 */
public abstract class BaseTransformMethods {

  /**
   * Clamps the given value to be between 0 and 255. If the value is less than 0, it returns 0. If
   * the value is greater than 255, it returns 255. Otherwise, it returns the value.
   *
   * @param value the value to clamp
   * @return the clamped value
   */
  protected int clamp(int value) {
    if (value < 0) {
      return 0;
    } else if (value > 255) {
      return 255;
    }
    return value;
  }

  /**
   * Checks if the given object is null.
   *
   * @param object the image to check
   * @throws IllegalArgumentException if the object is null
   */
  protected void checkNull(Object object) throws IllegalArgumentException {
    if (object == null) {
      throw new IllegalArgumentException("Object cannot be null.");
    }
  }

  /**
   * Checks if the given image is null.
   *
   * @param image the image to check
   * @param filter the filter to check
   * @throws IllegalArgumentException if the image is null
   */
  protected void checkFilter(ImageState image, double[][] filter)
          throws IllegalArgumentException {
    if (image == null || filter == null || filter.length % 2 == 0
            || filter.length != filter[0].length) {
      throw new IllegalArgumentException("Image or kernel is null, or kernel is not square.");
    }
  }
}
