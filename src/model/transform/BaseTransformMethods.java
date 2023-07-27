package model.transform;

import model.image.ImageState;

/**
 * This class represents a clamp for values to be between 0 and 255.
 */
public abstract class BaseTransformMethods {

  /**
   * Clamps the given value to be between 0 and 255.
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
   * @param object the image to check
   * @throws IllegalArgumentException if the object is null
   */
  protected void checkNull(Object object) throws IllegalArgumentException {
    if (object == null) {
      throw new IllegalArgumentException("Object cannot be null.");
    }
  }

  protected void checkFilter(ImageState image, double[][] filter) {
    if (image == null || filter == null || filter.length % 2 == 0
            || filter.length != filter[0].length) {
      throw new IllegalArgumentException("Image or kernel is null, or kernel is not square.");
    }
  }
}
