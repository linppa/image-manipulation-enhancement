package controller.io;

import model.image.ImageState;

/**
 * This interface represents an image loader for an image processing program.
 */
public interface ImageLoader {
  /**
   * This method runs the image loader and returns the image state.
   *
   * @return the image state
   * @throws IllegalStateException if the image loader cannot be run
   */
  ImageState run();
}
