package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * This interface represents a view for an image processing program. The view displays a
 * graphical user interface (GUI) to the user. Events are emitted when the user interacts with the
 * GUI and are handled by the controller to update the model.
 */
public interface View extends ActionListener, KeyListener {

  /**
   * This method gets the image currently displayed on the canvas of the view.
   *
   * @return the image currently displayed on the canvas of the view
   */
  BufferedImage getImageFromView();

  /**
   * This method sets the image to be displayed on the canvas of the view.
   *
   * @param image the image to be displayed
   * @throws NullPointerException if the given image is null
   */
  void setImageView(BufferedImage image);

  /**
   * This method adds a listener to the view. The listener is notified when the user interacts with
   * the GUI.
   *
   * @param listener the listener to be added
   */
  void addViewListener(ViewListener listener);

  /**
   * Focuses on the main frame of the view.
   */
  void requestFrameFocus();

  /**
   * This method emits an event when the user clicks the load button.
   */
  void emitLoadEvent();

  /**
   * This method emits an event when the user clicks the save button.
   */
  void emitSaveEvent();

  /**
   * This method emits an even when the user clicks the brighten button. It takes in the value
   * of the slider bar.
   *
   * @param value the value to brighten/darken by
   */
  void emitBrightenEvent(int value);

  /**
   * This method emits an event when the user clicks the greyscale button. It takes in the value
   * of the selected dropdown menu item. The value corresponds to the component of the image to
   * be greyscaled.
   *
   * @param component the component of the image to be greyscaled
   */
  void emitComponentGreyscaleEvent(int component);

  /**
   * This method emits an event when the user clicks the blur button.
   */
  void emitBlurEvent();

  /**
   * This method emits an event when the user clicks the sharpen button.
   */
  void emitSharpenEvent();

  /**
   * This method emits an event when the user clicks the filter greyscale button.
   */
  void emitFilterGreyscaleEvent();

  /**
   * This method emits an event when the user clicks the sepia button.
   */
  void emitSepiaEvent();

  /**
   * This method emits an event when the user clicks the histogram button.
   */
  void emitHistogramEvent();

  /**
   * This method makes the view visible or invisible.
   *
   * @param b true if the view should be visible, false otherwise
   */
  void setVisible(boolean b);

  /**
   * This method displays a message to the user. It is used to show error messages or if the
   * operation was successful.
   *
   * @param message the message to be displayed
   */
  void displayMessage(String message);
}
