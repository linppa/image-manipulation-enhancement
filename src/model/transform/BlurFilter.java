package model.transform;

import model.filter.Filtering;
import model.filter.FilteringImpl;
import model.image.ImageState;

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
