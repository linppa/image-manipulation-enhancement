package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import model.image.ImageState;

/**
 * This class represents a model for an image processing program. The model stores images and
 * allows the user to apply transformations to them. The model can also retrieve images by their
 * ID/name within the model. The model is the only part of the program that can access the images.
 */
public class ImageModelImpl implements ImageModel {
  private final Map<String, ImageState> images;

  /**
   * Constructs an ImageModelImpl object. Initializes the map of images.
   */
  public ImageModelImpl() {
    this.images = new HashMap<String, ImageState>();
  }

  @Override
  public void addImage(String id, ImageState image) throws IllegalArgumentException {
    if (id == null || image == null) {
      throw new IllegalArgumentException("Null argument");
    }
    this.images.put(id, image);
  }

  @Override
  public ImageState getImage(String id) throws IllegalArgumentException {
    Objects.requireNonNull(id);
    if (!this.images.containsKey(id)) {
      throw new IllegalArgumentException("Image with that ID not found!");
    }
    return this.images.get(id);
  }
}
