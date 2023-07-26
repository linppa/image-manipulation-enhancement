package model.pixel;

/**
 * This interface represents a pixel, which is a single point in an image. A pixel has a red, green,
 * and blue channel, each of which is an integer between 0 and 255 inclusive. A pixel also has an
 * alpha channel, which is a double between 0 and 1 inclusive.
 */
public interface Pixel extends PixelState {

  /**
   * Sets the red channel of this pixel to the given value.
   *
   * @param r the new red channel value
   * @throws IllegalArgumentException if the given value is invalid
   */
  void setR(int r);

  /**
   * Sets the green channel of this pixel to the given value.
   *
   * @param g the new green channel value
   * @throws IllegalArgumentException if the given value is invalid
   */
  void setG(int g);

  /**
   * Sets the blue channel of this pixel to the given value.
   *
   * @param b the new blue channel value
   * @throws IllegalArgumentException if the given value is invalid
   */
  void setB(int b);

  /**
   * Sets the RGB value of this pixel to the given array.
   *
   * @param rgb the new RGB value
   * @throws IllegalArgumentException if the given array is invalid
   */
  void setRGB(int[] rgb);
}
