package model.transform;

import model.filter.ColorFilteringImpl;
import model.filter.Filtering;
import model.image.ImageState;

/**
 * This class represents a Sepia Filter. A Sepia Filter
 * is a matrix that is applied to each pixel in an image to transform it. It implements the
 * Transformation interface and extends the BaseTransformMethods class.
 */
public class SepiaFilter extends BaseTransformMethods implements Transformation {

  @Override
  public ImageState apply(ImageState sourceImage) {
    double[][] sepiaFilter = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    };
    Filtering sepiaFiltering = new ColorFilteringImpl();
    return sepiaFiltering.applyFilter(sourceImage, sepiaFilter);
  }

}
