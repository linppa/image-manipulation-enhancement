package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Objects;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.io.BaseIOMethods;
import controller.io.BufferedImageLoader;
import controller.io.BufferedImageSaver;
import controller.io.ImageLoader;
import controller.io.ImageSaver;
import controller.io.PPMImageLoader;
import controller.io.PPMImageSaver;
import model.ImageModel;
import model.image.ImageState;
import model.transform.BlurFilter;
import model.transform.BrightenTransformation;
import model.transform.GreyscaleBlueTransformation;
import model.transform.GreyscaleFilter;
import model.transform.GreyscaleGreenTransformation;
import model.transform.GreyscaleIntensityTransformation;
import model.transform.GreyscaleLumaTransformation;
import model.transform.GreyscaleRedTransformation;
import model.transform.GreyscaleValueTransformation;
import model.transform.SepiaFilter;
import model.transform.SharpenFilter;
import model.transform.Transformation;
import view.View;
import view.ViewListener;

/**
 * This class represents an interactive controller for the GUI window.
 * It implements the Controller interface & the ViewListener interface. The controller
 * is responsible for handling events from the view and updating the model accordingly.
 */
public class InteractiveControllerImpl extends BaseIOMethods implements Controller, ViewListener {
  private final ImageModel model;
  private final View view;

  /**
   * Constructs an interactive controller object. It takes in a model and a view. Additionally,
   * it adds itself as a listener to the view.
   *
   * @param model the model, cannot be null
   * @param view  the view, cannot be null
   */
  public InteractiveControllerImpl(ImageModel model, View view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.view.addViewListener(this);
  }

  @Override
  public void runController() {
    this.view.requestFrameFocus();
    this.view.setVisible(true);
  }

  /**
   * Helper method for loading an image, converting it to a Buffered Image, and adding it to the
   * model. The view is updated to display the image, and a success message is displayed. Focus is
   * requested for the frame.
   *
   * @param loader the ImageLoader object to use; PPMLoader or BufferedImageLoader depending on
   *               the file extension
   */
  private void loadHelper(ImageLoader loader) {
    ImageState image = loader.run();
    BufferedImage bufferedImage = convertImageStateToBuffered(image);
    model.addImage("currentImage", image);
    view.setImageView(bufferedImage);
    view.displayMessage("Image loaded successfully.");
    view.requestFrameFocus();
  }

  @Override
  public void handleLoadEvent() {
    JFileChooser fileChooser = new JFileChooser();
    // only allow supported image files
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PNG & PPM Images", "jpg", "jpeg", "ppm", "png");
    fileChooser.setFileFilter(filter);

    int returnValue = fileChooser.showOpenDialog(null);
    // valid file selected, behave based on file extension
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();
      String fileExtension = getFileExtension(filePath);

      if (fileExtension.equals("ppm")) {
        loadHelper(new PPMImageLoader(filePath));

      } else { // "jpg, jpeg, png"
        loadHelper(new BufferedImageLoader(filePath));
      }
    }
  }

  /**
   * Checks if an image is loaded in the model. If not, displays an error message.
   *
   * @return true if an image is loaded, false otherwise
   */
  private boolean checkLoaded() {
    try {
      model.getImage("currentImage");
    } catch (IllegalArgumentException e) {
      view.displayMessage("Please load an image first.");
      return false;
    }
    return true;
  }

  /**
   * Helper method for saving an image. Takes in an ImageSaver object and attempts to save
   * the image. If the image cannot be saved, displays an error message.
   *
   * @param saver the ImageSaver object to use
   */
  private void saveHelper(ImageSaver saver) {
    try {
      saver.run();
      view.displayMessage("Image saved successfully.");
      view.requestFrameFocus();
    } catch (IllegalArgumentException e) {
      view.displayMessage(e.getMessage());
    }
  }

  @Override
  public void handleSaveEvent() {
    if (!checkLoaded()) {
      return;
    }
    JFileChooser fileChooser = new JFileChooser();

    int returnValue = fileChooser.showSaveDialog(null);
    // valid file selected, behave based on file extension
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();
      String fileExtension = getFileExtension(filePath);

      if (fileExtension.equals("ppm")) {
        ImageState image = model.getImage("currentImage");
        Appendable appendable = new StringBuilder();
        ImageSaver saver = new PPMImageSaver(filePath, image, appendable);
        saveHelper(saver);

      } else if (fileExtension.equals("jpg") || fileExtension.equals("jpeg")
              || fileExtension.equals("png")) {
        ImageState image = model.getImage("currentImage");
        ByteArrayOutputStream appendable = new ByteArrayOutputStream();
        ImageSaver saver = new BufferedImageSaver(filePath, image, appendable);
        saveHelper(saver);

      } else {
        view.displayMessage("Please save as a supported file format.");
      }
    }
  }

  /**
   * Helper method for applying a filter to the current image. Takes in a Transformation object
   * and applies it to the current image.
   *
   * @param filter the Transformation object to apply
   */
  private void applyFilter(Transformation filter) {
    ImageState image = model.getImage("currentImage");
    ImageState newImage = filter.apply(image);
    BufferedImage bufferedImage = convertImageStateToBuffered(newImage);

    // replace current image with new image
    model.addImage("currentImage", newImage);
    view.setImageView(bufferedImage);
    view.requestFrameFocus();
  }

  @Override
  public void handleBrightenEvent(int value) {
    if (!checkLoaded()) {
      return;
    }
    applyFilter(new BrightenTransformation(value));
    view.displayMessage("Image brightness adjusted successfully.");
  }

  @Override
  public void handleComponentGreyscaleEvent(int component) {
    if (!checkLoaded()) {
      return;
    }
    // apply greyscale based on component & dropdown menu
    switch (component) {
      case 1:
        applyFilter(new GreyscaleValueTransformation());
        view.displayMessage("Image greyscaled with value successfully.");
        break;
      case 2:
        applyFilter(new GreyscaleRedTransformation());
        view.displayMessage("Image greyscaled with red successfully.");
        break;
      case 3:
        applyFilter(new GreyscaleGreenTransformation());
        view.displayMessage("Image greyscaled with green successfully.");
        break;
      case 4:
        applyFilter(new GreyscaleBlueTransformation());
        view.displayMessage("Image greyscaled with blue successfully.");
        break;
      case 5:
        applyFilter(new GreyscaleLumaTransformation());
        view.displayMessage("Image greyscaled with luma successfully.");
        break;
      case 6:
        applyFilter(new GreyscaleIntensityTransformation());
        view.displayMessage("Image greyscaled with intensity successfully.");
        break;
      default:
        view.displayMessage("Please select a greyscale component from the dropdown menu.");
        break;
    }
  }

  @Override
  public void handleBlurEvent() {
    if (!checkLoaded()) {
      return;
    }
    applyFilter(new BlurFilter());
    view.displayMessage("Applied blur filter successfully.");
  }

  @Override
  public void handleSharpenEvent() {
    if (!checkLoaded()) {
      return;
    }
    applyFilter(new SharpenFilter());
    view.displayMessage("Applied sharpen filter successfully.");
  }

  @Override
  public void handleFilterGreyscaleEvent() {
    if (!checkLoaded()) {
      return;
    }
    applyFilter(new GreyscaleFilter());
    view.displayMessage("Applied greyscale filter successfully.");
  }

  @Override
  public void handleSepiaEvent() {
    if (!checkLoaded()) {
      return;
    }
    applyFilter(new SepiaFilter());
    view.displayMessage("Applied sepia filter successfully.");
  }
}
