package model.transform;

import model.filter.ColorFilteringImpl;
import model.filter.Filtering;

import model.image.ImageState;

/**
 * This class represents a greyscale filter. It implements the Transformation interface.
 * A greyscale filter is a matrix that is applied to each pixel in an image to transform it.
 */
public class GreyscaleFilter extends BaseTransformMethods implements Transformation {

  @Override
  public ImageState apply(ImageState sourceImage) {
    checkNull(sourceImage);

    double[][] greyscaleFilter = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}
    };
    Filtering greyscaleFiltering = new ColorFilteringImpl();
    return greyscaleFiltering.applyFilter(sourceImage, greyscaleFilter);
  }
}
