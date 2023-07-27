package model.filter;

import model.image.ImageState;

/**
 * This interface represents a filter that can be applied to an image. A filter is a matrix that is
 * applied to each pixel in an image to transform it.
 */
public interface Filtering {

  /**
   * This method applies a filter to an image. It takes in an image and a filter and returns a new
   * image that is the result of applying the filter to the image. A filter is a matrix that is
   * applied to each pixel in an image to transform it. Kernel dimensions must be odd and greater
   * than 0, and equal in both dimensions.
   *
   * @param image  the given image.
   * @param filter the given filter that will be applied to the given image.
   * @return the result of the filter represented as a new image.
   * @throws IllegalArgumentException if the given image is null, the given filter is null, the
   *                                  given filter is not square, or the given filter is not odd.
   */
  ImageState applyFilter(ImageState image, double[][] filter);
}
