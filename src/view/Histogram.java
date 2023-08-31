package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.*;

/**
 * This class represents a histogram for an image. It displays the histogram for the image loaded
 * by the user. The histogram is displayed as a bar graph, with the x-axis representing the pixel
 * intensity and the y-axis representing the frequency of that intensity.
 */
public class Histogram extends JPanel implements Canvas {
  private final int[][] imageData;

  /**
   * Constructs a histogram object. It initializes the image data to be empty.
   * The image data is a 3x256 array, where each row represents a channel (r, g, b) and each column
   * represents a pixel intensity.
   */
  public Histogram() {
    // 3 x 256 array, each row represents channel (r, g, b)
    this.imageData = new int[3][256];
    setPreferredSize(new Dimension(300, 300));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int width = getWidth();
    int height = getHeight();
    // calculate width each bar, so all bars fit
    int barWidth = width / 256;

    // iterate over rgb channels, finding max frequency
    for (int channel = 0; channel < 3; channel++) {
      // convert array to stream of ints, find max value, or 1 if pixel empty
      int maxFrequency = Arrays.stream(imageData[channel]).max().orElse(1);
      g.setColor(getColorForChannel(channel));
      // iterate over pixel intensities
      for (int intensity = 0; intensity < 256; intensity++) {
        // calculate bar height based on frequency & component height
        int barHeight = (int) (height * ((double) imageData[channel][intensity] / maxFrequency));
        // calculate starting x & y coordinates for bar
        int x = intensity * barWidth;
        int y = height - barHeight;
        // draw bar as rectangle with width of barWidth & height of barHeight
        g.fillRect(x, y, barWidth, barHeight);
      }
    }
  }

  /**
   * Returns the color for the given channel. The channel must be 0, 1, or 2, representing the
   * red, green, and blue channels respectively.
   *
   * @param channel the channel to get the color for
   * @return the color for the given channel
   */
  private Color getColorForChannel(int channel) {
    // return color based on channel (r, g, b)
    switch (channel) {
      case 0:
        return Color.RED;
      case 1:
        return Color.GREEN;
      case 2:
        return Color.BLUE;
      default:
        throw new IllegalArgumentException("Invalid channel");
    }
  }

  /**
   * Sets the image for the histogram. It resets the histogram data and recalculates the histogram
   * for the given image.
   *
   * @param image the image to set
   */
  @Override
  public void setImage(BufferedImage image) {
    // reset histogram data
    for (int channel = 0; channel < 3; channel++) {
      Arrays.fill(imageData[channel], 0);
    }
    calculateHistogram(image);
    revalidate();
    repaint();
  }

  /**
   * Calculates the histogram for the given image. It iterates over each pixel in the image and
   * increments the frequency for the corresponding pixel intensity. The histogram data is stored
   * in the imageData array.
   *
   * @param image the image to calculate the histogram for
   */
  protected void calculateHistogram(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Color pixel = new Color(image.getRGB(col, row));
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();
        // increment frequency for each channel
        this.imageData[0][red]++;
        this.imageData[1][green]++;
        this.imageData[2][blue]++;
      }
    }
    repaint();
  }
}
