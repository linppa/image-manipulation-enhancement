package model.pixel;

/**
 * This interface represents the pixel state, which is the state of a pixel at a given time. A pixel
 * state has a red, green, and blue channel, each of which is an integer between 0 and 255
 * inclusive. A pixel state also has an alpha channel, which is a double between 0 and 1 inclusive.
 * By separating the pixel state from the pixel, we can ensure that the pixel is immutable to the
 * outside classes.
 */
public interface PixelState {

  /**
   * Gets the red channel of this pixel.
   *
   * @return the red channel value
   */
  int getR();

  /**
   * Gets the green channel of this pixel.
   *
   * @return the green channel value
   */
  int getG();

  /**
   * Gets the blue channel of this pixel.
   *
   * @return the blue channel value
   */
  int getB();

  int[] getRGB();

  double getAlpha();
}
