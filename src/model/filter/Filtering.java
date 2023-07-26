package model.filter;

import model.image.ImageState;

/**
 * This interface represents a filter that can be applied to an image. A filter is a matrix that is
 * applied to each pixel in an image to transform it.
 */
public interface Filtering {

  /**
   * This method applies a filter to an image. It takes in an image and a kernel and returns a new
   * image that is the result of applying the kernel to the image. A kernel is a matrix that is
   * applied to each pixel in an image to transform it. Kernel dimensions must be odd and greater
   * than 0, and equal in both dimensions.
   *
   * @param image  the given image.
   * @param kernel the given kernel that will be applied to the given image.
   * @return the result of the filter represented as a new image.
   * @throws IllegalArgumentException if the given image is null, the given kernel is null, the
   *                                  given kernel is not square, or the given kernel is not odd.
   */
  ImageState applyFilter(ImageState image, double[][] kernel);
}
