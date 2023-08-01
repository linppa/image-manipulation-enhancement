package model.transform;

import model.filter.Filtering;
import model.filter.FilteringImpl;
import model.image.ImageState;

/**
 * This class represents a blur filter. It implements the Transformation interface.
 * A blur filter is a matrix that is applied to each pixel in an image to transform it.
 */
public class BlurFilter extends BaseTransformMethods implements Transformation {

  @Override
  public ImageState apply(ImageState sourceImage) {
    checkNull(sourceImage);

    double[][] blurFilter = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}
    };
    Filtering blurFiltering = new FilteringImpl();
    return blurFiltering.applyFilter(sourceImage, blurFilter);
  }
}
