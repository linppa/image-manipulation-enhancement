import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.Controller;
import controller.ControllerImpl;
import controller.io.ImageLoader;
import controller.io.PPMImageLoader;
import controller.io.PPMImageSaver;
import model.ImageModel;
import model.ImageModelImpl;
import model.image.ImageState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * This class represents a test class for the controller.
 */
public class TestController {
  private ImageModel modelEmpty;

  /**
   * This method sets up the test.
   */
  @Before
  public void setUp() {
    ImageModel model = new ImageModelImpl();
    ImageLoader pigeonImage = new PPMImageLoader("res/biggestpigeon.ppm");
    ImageState image = pigeonImage.run();
    model.addImage("pigeon", image);

    ImageModel model2 = new ImageModelImpl();
    ImageLoader anyaImage = new PPMImageLoader("res/anya.ppm");
    ImageState image2 = anyaImage.run();
    model2.addImage("anya", image2);

    modelEmpty = new ImageModelImpl();
  }

  // ---------- TEST CONTROLLER OUTPUT ----------
  @Test
  public void testScriptOutputMessage() {
    // ----- test script input - no error -----
    Reader input = new StringReader("load res/anya.ppm anya");
    ImageModel model = new ImageModelImpl();
    Appendable output = new StringBuilder();
    Controller controller = new ControllerImpl(input, model, output);
    controller.runController();
    assertEquals("", output.toString());

    // ----- test script input - no error -----
    Reader input2 = new StringReader("save res/anya-save-command.ppm anya");
    Controller controller2 = new ControllerImpl(input2, model, output);
    controller2.runController();
    assertEquals("", output.toString());
  }

  @Test
  public void testScriptOutputNoFileError() {
    // ----- test script input - file not found error -----
    Reader input = new StringReader("load res/anya1.ppm anya");
    ImageModel model = new ImageModelImpl();
    Appendable output = new StringBuilder();
    Controller controller = new ControllerImpl(input, model, output);
    controller.runController();
    assertEquals("Could not run command: File not found", output.toString());
  }

  @Test
  public void testScriptOutputNoCommandError() {
    // ----- test script input - invalid command error -----
    Reader input = new StringReader("InvalidCommand res/anya.ppm anya");
    ImageModel model = new ImageModelImpl();
    Appendable output = new StringBuilder();
    Controller controller = new ControllerImpl(input, model, output);
    controller.runController();
    assertEquals("Command not found. Command not found. Command not found. ", output.toString());
  }

  @Test
  public void testScriptOutputNoImageFoundError() {
    // ----- test script input - image not found error -----
    Reader input = new StringReader("save res/anya.ppm anya2");
    ImageModel model = new ImageModelImpl();
    Appendable output = new StringBuilder();
    Controller controller = new ControllerImpl(input, model, output);
    controller.runController();
    assertEquals("Could not run command: Image with that ID not found!", output.toString());
  }

  // ---------- TEST EXCEPTIONS ----------

  @Test
  public void exceptionLoad() {
    // ----- test invalid file path -----
    assertThrows(IllegalArgumentException.class, () -> {
      ImageLoader loader = new PPMImageLoader("res/doesnotexist.ppm");
      ImageState image = loader.run();
    });
  }

  @Test
  public void exceptionSave() {
    // ----- test non-existing image in empty model -----
    assertThrows(IllegalArgumentException.class, () -> {
      Appendable output = new StringBuilder();
      ImageState image = modelEmpty.getImage("pigeon");
      PPMImageSaver saver = new PPMImageSaver("res/doesnotexist.ppm", image, output);
      saver.run();
    });
  }


}
