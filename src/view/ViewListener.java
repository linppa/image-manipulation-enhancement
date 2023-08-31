package view;

/**
 * This interface represents a listener for the view. The listener handles events from the view
 * and updates the model accordingly.
 */
public interface ViewListener {

  /**
   * This method handles a load event from the view. It takes note of whether the file extension
   * is valid & handles the image format accordingly when loading the image.
   */
  void handleLoadEvent();

  /**
   * This method handles a save event from the view. It takes note of whether the file extension
   * is valid & handles the image format accordingly when saving the image.
   */
  void handleSaveEvent();

  /**
   * This method handles a brightness event from the view. It takes in the value of the slider bar
   * and brightens/darkens the image accordingly.
   *
   * @param value the value to brighten/darken by
   */
  void handleBrightenEvent(int value);

  /**
   * This method handles a greyscale event from the view. It takes in the component to greyscale
   * and greyscales the image accordingly to the component selected.
   *
   * @param component the component of the image to be greyscaled
   */
  void handleComponentGreyscaleEvent(int component);

  /**
   * This method handles a blur event from the view. It uses a filter to blur the image.
   */
  void handleBlurEvent();

  /**
   * This method handles a sharpen event from the view. It uses a filter to sharpen the image.
   */
  void handleSharpenEvent();

  /**
   * This method handles a filter greyscale event from the view. It uses a filter to greyscale
   * the image.
   */
  void handleFilterGreyscaleEvent();

  /**
   * This method handles a sepia event from the view. It uses a filter to sepia the image.
   */
  void handleSepiaEvent();
}
