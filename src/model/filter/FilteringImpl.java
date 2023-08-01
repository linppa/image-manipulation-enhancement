package model.filter;

import model.ImageModel;
import model.image.ImageImpl;
import model.image.ImageState;
import model.transform.BaseTransformMethods;

/**
 * This class represents a filter implementation that can be applied to an image. A filter is a
 * matrix that is applied to each pixel in an image to transform it. Kernel dimensions must be odd
 * and greater than 0, and equal in both dimensions.
 */
public class FilteringImpl extends BaseTransformMethods implements Filtering {

  /**
   * This constructor creates a FilteringImpl object with the given model.
   */
  public FilteringImpl() {
    ImageModel model = null;
  }

  @Override
  public ImageState applyFilter(ImageState image, double[][] filter)
          throws IllegalArgumentException {
    // check if image/kernel null, kernel not square, or kernel not odd
    checkFilter(image, filter);

    int width = image.getWidth();
    int height = image.getHeight();

    // create new image to store filtered pixels
    ImageImpl newImage = new ImageImpl(width, height);
    int kernelSize = filter.length;
    int kernelCenter = kernelSize / 2;

    for (int row = 0; row < height; row++) { // for each row
      for (int col = 0; col < width; col++) { // for each column

        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;

        // apply filter matrix to each pixel
        for (int i = 0; i < kernelSize; i++) {
          for (int j = 0; j < kernelSize; j++) {
            int x = col - kernelCenter + j;
            int y = row - kernelCenter + i;

            // pixel within bounds, apply filter to each pixel channel & add to sum
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




