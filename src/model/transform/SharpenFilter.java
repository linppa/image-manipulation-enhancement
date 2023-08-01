package model.transform;

import model.filter.Filtering;
import model.filter.FilteringImpl;
import model.image.ImageState;

/**
 * This class represents a sharpen filter transformation. It implements the Transformation
 * interface and extends the BaseTransformMethods class. A sharpen filter is a matrix that is
 * applied to each pixel in an image to transform it.
 */
public class SharpenFilter extends BaseTransformMethods implements Transformation {

  @Override
  public ImageState apply(ImageState sourceImage) {
    checkNull(sourceImage);

    double[][] sharpenFilter = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}
    };
    Filtering sharpenFiltering = new FilteringImpl();
    return sharpenFiltering.applyFilter(sourceImage, sharpenFilter);
  }
}
