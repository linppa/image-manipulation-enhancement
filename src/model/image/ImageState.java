package model.image;

/**
 * This interface represents the state of an image.
 */
public interface ImageState {
  /**
   * Returns the width of this image.
   *
   * @return the width of this image
   */
  int getWidth();

  /**
   * Returns the height of this image.
   *
   * @return the height of this image
   */
  int getHeight();

  /**
   * Returns the red channel value of the pixel at the given coordinates.
   *
   * @param x the x coordinate of the pixel
   * @param y the y coordinate of the pixel
   * @return the red channel value of the pixel at the given coordinates
   * @throws IllegalArgumentException if the given coordinates are invalid
   */
  int getRedChannel(int x, int y);

  /**
   * Returns the green channel value of the pixel at the given coordinates.
   *
   * @param x the x coordinate of the pixel
   * @param y the y coordinate of the pixel
   * @return the green channel value of the pixel at the given coordinates
   * @throws IllegalArgumentException if the given coordinates are invalid
   */
  int getGreenChannel(int x, int y);

  /**
   * Returns the blue channel value of the pixel at the given coordinates.
   *
   * @param x the x coordinate of the pixel
   * @param y the y coordinate of the pixel
   * @return the blue channel value of the pixel at the given coordinates
   * @throws IllegalArgumentException if the given coordinates are invalid
   */
  int getBlueChannel(int x, int y);
}
