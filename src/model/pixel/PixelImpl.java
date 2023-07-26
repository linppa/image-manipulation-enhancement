package model.pixel;

/**
 * This class implements the Pixel interface and represents a pixel with RGB values.
 */
public class PixelImpl implements Pixel {
  private int r;
  private int g;
  private int b;

  /**
   * Constructs a PixelImpl object with the given RGB values.
   *
   * @param r the red channel value
   * @param g the green channel value
   * @param b the blue channel value
   * @throws IllegalArgumentException if any of the RGB values are invalid
   */
  public PixelImpl(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Invalid RGB value.");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Constructs a PixelImpl object with the given RGB value array.
   *
   * @param rgb the RGB value array
   * @throws IllegalArgumentException if size of the RGB value array is not 3
   */
  public PixelImpl(int[] rgb) throws IllegalArgumentException {
    if (rgb.length != 3) {
      throw new IllegalArgumentException("Invalid RGB value array length.");
    }
    this.r = rgb[0];
    this.g = rgb[1];
    this.b = rgb[2];
  }

  /* ----- setters ----- */

  @Override
  public void setR(int r) throws IllegalArgumentException {
    if (r < 0 || r > 255) {
      throw new IllegalArgumentException("Invalid RGB value.");
    }
    this.r = r;
  }

  @Override
  public void setG(int g) throws IllegalArgumentException {
    if (g < 0 || g > 255) {
      throw new IllegalArgumentException("Invalid RGB value.");
    }
    this.g = g;
  }

  @Override
  public void setB(int b) throws IllegalArgumentException {
    if (b < 0 || b > 255) {
      throw new IllegalArgumentException("Invalid RGB value.");
    }
    this.b = b;
  }

  @Override
  public void setRGB(int[] rgb) throws IllegalArgumentException {
    if (rgb.length != 3) {
      throw new IllegalArgumentException("Invalid RGB value.");
    }
    this.r = rgb[0];
    this.g = rgb[1];
    this.b = rgb[2];
  }

  /* ----- getters ----- */

  @Override
  public int getR() {
    return this.r;
  }

  @Override
  public int getG() {
    return this.g;
  }

  @Override
  public int getB() {
    return this.b;
  }

  @Override
  public int[] getRGB() {
    return new int[]{this.r, this.g, this.b};
  }

  @Override
  public double getAlpha() {
    return 1.0; // to update in future version
  }

  /* ----- to string ----- */

  @Override
  public String toString() {
    return String.format("(%d, %d, %d)", this.r, this.g, this.b);
  }
}
