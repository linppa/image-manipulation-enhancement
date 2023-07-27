package model.image;

import model.pixel.Pixel;
import model.pixel.PixelImpl;

/**
 * This class represents an image. An image has a width and height,
 * each of which is an integer greater than 0. An image also has a 2D array of pixels, which is
 * represented by a 2D array of Pixel objects.
 */
public class ImageImpl implements Image {
  private final int width;
  private final int height;
  private final Pixel[][] data;

  /**
   * Constructs an ImageImpl object with the given width and height.
   *
   * @param width  the width of the image
   * @param height the height of the image
   * @throws IllegalArgumentException if the given width or height is invalid
   */
  public ImageImpl(int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid width or height");
    }
    this.width = width;
    this.height = height;
    this.data = new Pixel[height][width];
  }

  /* ----- getters ----- */

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getRedChannel(int x, int y) throws IllegalArgumentException {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("Invalid red pixel channel.");
    }
    return this.data[y][x].getR();
  }

  @Override
  public int getGreenChannel(int x, int y) throws IllegalArgumentException {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("Invalid green pixel channel.");
    }
    return this.data[y][x].getG();
  }

  @Override
  public int getBlueChannel(int x, int y) throws IllegalArgumentException {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("Invalid blue pixel channel.");
    }
    return this.data[y][x].getB();
  }

  /* ----- setters ----- */

  @Override
  public void setPixel(int x, int y, int r, int g, int b) throws IllegalArgumentException {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("Unable to set pixel.");
    }
    this.data[y][x] = new PixelImpl(r, g, b);

  }

}
