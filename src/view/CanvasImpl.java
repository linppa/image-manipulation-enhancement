package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.swing.*;

/**
 * This class represents a canvas for an image processing program. The canvas displays the image
 * loaded by the user. When the user applies a transformation to the image, the canvas displays the
 * transformed image and updates the image state. One image is displayed at a time and is centered
 * on the canvas.
 */
public class CanvasImpl extends JPanel implements Canvas {
  private BufferedImage image;

  /**
   * Constructs a canvas object. It sets the background color to gray.
   */
  public CanvasImpl() {
    setBackground(Color.gray);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      // center the image from its size
      int x = (getWidth() - image.getWidth()) / 2;
      int y = (getHeight() - image.getHeight()) / 2;
      g.drawImage(image, x, y, this);
      repaint();
    }
  }

  @Override
  public Dimension getPreferredSize() {
    if (image != null) {
      // set preferred size to image size
      return new Dimension(image.getWidth(), image.getHeight());
    } else {
      return super.getPreferredSize();
    }
  }

  @Override
  public void setImage(BufferedImage image) throws NullPointerException {
    this.image = Objects.requireNonNull(image);
    revalidate();
    repaint();
  }
}
