import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;

import controller.InteractiveControllerImpl;
import controller.io.BaseIOMethods;
import controller.io.BufferedImageLoader;
import controller.io.ImageLoader;
import mocks.MockView;
import model.ImageModel;
import model.ImageModelImpl;
import model.image.ImageState;
import view.ViewListener;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the wiring of the view, controller, and model.
 * It uses a mock view to test the controller's ability to handle events from the view,
 * and the controller's ability to update the model.
 */
public class TestView extends BaseIOMethods {
  ImageModel model;
  ImageLoader loader;
  StringBuilder log;
  MockView mockView;
  ViewListener controller;

  @Before
  public void setUp() {
    model = new ImageModelImpl();
    log = new StringBuilder();
    mockView = new MockView(log);
    // load image
    loader = new BufferedImageLoader("res/anya.png");
    ImageState loadedImage = loader.run();
    BufferedImage bufferedImage = convertImageStateToBuffered(loadedImage);
    // add image to model
    model.addImage("testImage", loadedImage);
    mockView.setImageView(bufferedImage);
    controller = new InteractiveControllerImpl(model, mockView);
  }

  @Test
  public void testMockViewLoad() {
    // mock view appends RGB values & ViewListener to log
    String expected = "104 92 72 216 192 150 89 79 62 156 139 108 255 246 192 156 "
            + "139 108 224 200 156 167 149 116 240 213 166 ViewListener added.\n";
    // log output
    assertEquals(expected, log.toString());
  }

  @Test
  public void testMockViewSave() {
    log.setLength(0); // reset log before test
    // restricts user to save image of supported file types
    controller.handleSaveEvent();
    String expected = "Message was displayed to the user.\n";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testMockViewBrighten() {
    // mock view appends loaded image's RGB values to log
    String expectedBefore = "104 92 72 216 192 150 89 79 62 156 139 108 255 246 192 "
            + "156 139 108 224 200 156 167 149 116 240 213 166 ViewListener added.\n";
    assertEquals(expectedBefore, log.toString());
    log.setLength(0); // reset log before test
    // brighten image applied
    controller.handleBrightenEvent(50);
    String expectedAfter = "Message was displayed to the user.\n";
    assertEquals(expectedAfter, log.toString());
  }

  @Test
  public void testMockViewError() {
    // no image loaded first
    ImageModel model2 = new ImageModelImpl();
    StringBuilder log2 = new StringBuilder();
    MockView mockView2 = new MockView(log2);
    ViewListener controller2 = new InteractiveControllerImpl(model2, mockView2);
    // blur image applied, error message displayed
    controller2.handleBlurEvent();
    String expected = "ViewListener added.\n"
            + "Message was displayed to the user.\n";
    assertEquals(expected, log2.toString());
  }
}
