package model;

import model.image.ImageState;

/**
 * This interface represents a model for an image processing program. The model stores images and
 * allows the user to apply transformations to them. The model can also retrieve images by their
 * ID/name within the model. The model is the only part of the program that can access the images.
 */
public interface ImageModel {
  /**
   * Adds the given image to this model with the given ID/name.
   *
   * @param id    the ID/name of the image
   * @param image the image to add
   * @throws IllegalArgumentException if the given ID/name or image is null
   */
  void addImage(String id, ImageState image);

  /**
   * Applies the given transformation to the image with the given ID/name.
   *
   * @param id the ID/name of the image
   * @return the transformed image
   * @throws IllegalArgumentException if the given ID/name is null
   */
  ImageState getImage(String id);
}
