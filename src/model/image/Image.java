package model.image;

/**
 * This interface represents an image. An image has a width and height,
 * each of which is an integer greater than 0. An image also has a 2D array of pixels, which is
 * represented by a 2D array of Pixel objects.
 */
public interface Image extends ImageState {
  /**
   * Sets the RGB value of the pixel at the given coordinates to the given array.
   *
   * @param x the x coordinate of the pixel
   * @param y the y coordinate of the pixel
   * @param r the red channel value
   * @param g the green channel value
   * @param b the blue channel value
   * @throws IllegalArgumentException if the given coordinates are invalid
   */
  void setPixel(int x, int y, int r, int g, int b);
}
