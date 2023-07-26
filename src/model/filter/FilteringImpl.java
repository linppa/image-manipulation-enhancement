package model.filter;

import model.ImageModel;
import model.image.ImageImpl;
import model.image.ImageState;
import model.transform.Clamp;

/**
 * This class represents a filter implementation that can be applied to an image. A filter is a
 * matrix that is applied to each pixel in an image to transform it.
 */
public class FilteringImpl extends Clamp implements Filtering {
  private ImageModel model;

  public FilteringImpl() {
    this.model = null;
  }

  @Override
  public ImageState applyFilter(ImageState image, double[][] filter)
          throws IllegalArgumentException {
    // check if image/kernel null, kernel not square, or kernel not odd
    if (image == null || filter == null || filter.length % 2 == 0
            || filter.length != filter[0].length) {
      throw new IllegalArgumentException("Image or kernel is null, or kernel is not square.");
    }
    int width = image.getWidth();
    int height = image.getHeight();

    ImageImpl newImage = new ImageImpl(width, height);
    int kernelSize = filter.length;
    int kernelCenter = kernelSize / 2;

    for (int row = 0; row < height; row++) { // for each row
      for (int col = 0; col < width; col++) { // for each column

        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;

        // apply filter to each color channel
        for (int i = 0; i < kernelSize; i++) {
          for (int j = 0; j < kernelSize; j++) {
            int x = col - kernelCenter + j;
            int y = row - kernelCenter + i;

            // check if pixel is out of bounds
            if (x >= 0 && x < width && y >= 0 && y < height) {
              redSum += image.getRedChannel(x, y) * filter[i][j];
              greenSum += image.getGreenChannel(x, y) * filter[i][j];
              blueSum += image.getBlueChannel(x, y) * filter[i][j];
            }
          }
        }
        // clamp values
        int filteredRed = clamp(redSum);
        int filteredGreen = clamp(greenSum);
        int filteredBlue = clamp(blueSum);

        // set new pixel
        newImage.setPixel(col, row, filteredRed, filteredGreen, filteredBlue);
      }
    }
    return newImage;
  }
}




