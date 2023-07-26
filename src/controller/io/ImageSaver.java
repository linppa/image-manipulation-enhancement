package controller.io;

/**
 * This interface represents an image saver for an image processing program.
 */
public interface ImageSaver {
  /**
   * This method runs the image saver, which saves the image to a file.
   *
   * @throws IllegalStateException if the image saver cannot be run
   */
  void run();

}
