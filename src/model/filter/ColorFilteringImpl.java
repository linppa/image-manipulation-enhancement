package model.filter;

import model.image.ImageImpl;
import model.image.ImageState;
import model.transform.BaseTransformMethods;

public class ColorFilteringImpl extends BaseTransformMethods implements Filtering {

  @Override
  public ImageState applyFilter(ImageState sourceImage, double[][] filter) {
    // check if image/kernel null, kernel not square, or kernel not odd
    checkFilter(sourceImage, filter);

    // create a new image to store filtered pixels
    ImageImpl newImage = new ImageImpl(sourceImage.getWidth(), sourceImage.getHeight());

    for (int row = 0; row < sourceImage.getHeight(); row++) { // for each row
      for (int col = 0; col < sourceImage.getWidth(); col++) { // for each column
        int red = sourceImage.getRedChannel(col, row);
        int green = sourceImage.getGreenChannel(col, row);
        int blue = sourceImage.getBlueChannel(col, row);

        // calculate new RGB values
        int filteredRed = (int) Math.round(red * filter[0][0] + green * filter[0][1] + blue * filter[0][2]);
        int filteredGreen = (int) Math.round(red * filter[1][0] + green * filter[1][1] + blue * filter[1][2]);
        int filteredBlue = (int) Math.round(red * filter[2][0] + green * filter[2][1] + blue * filter[2][2]);

        // clamp values
        filteredRed = clamp(filteredRed);
        filteredGreen = clamp(filteredGreen);
        filteredBlue = clamp(filteredBlue);

        // set new pixel
        newImage.setPixel(col, row, filteredRed, filteredGreen, filteredBlue);
      }
    }
    return newImage;
  }
}