package view;

import java.awt.image.BufferedImage;

/**
 * This interface represents a canvas for an image processing program. The canvas displays the
 * image loaded by the user. When the user applies a transformation to the image, the canvas
 * displays the transformed image and updates the image state. One image is displayed at a time.
 */
public interface Canvas {
  /**
   * This method sets the image to be displayed on the canvas.
   *
   * @param image the image to be displayed
   * @throws NullPointerException if the given image is null
   */
  void setImage(BufferedImage image);
}
