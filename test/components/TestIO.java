package components;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;

import controller.io.ImageLoader;
import controller.io.ImageSaver;
import controller.io.PPMImageLoader;
import controller.io.PPMImageSaver;
import model.ImageModel;
import model.ImageModelImpl;
import model.image.ImageState;
import model.transform.BrightenTransformation;
import model.transform.Transformation;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * This class represents a test class for the controller's IO.
 */
public class TestIO {

  @Test
  public void testImageFromLoader() {
    PPMImageLoader loader = new PPMImageLoader("res/anya.ppm");
    ImageState image = loader.run();

    // ----- test dimensions & pixel rgb -----
    assertEquals(3, image.getWidth());
    assertEquals(3, image.getHeight());
    assertEquals(95, image.getRedChannel(0, 0));
    assertEquals(68, image.getGreenChannel(0, 0));
    assertEquals(74, image.getBlueChannel(0, 0));

    // ----- test out of bounds pixel -----
    assertThrows(IllegalArgumentException.class, () -> {
      image.getRedChannel(0, 3);
    });
  }

  @Test
  public void testSavePPM() {
    ImageModel model1 = new ImageModelImpl();
    ImageLoader loader = new PPMImageLoader("res/anya.ppm");
    ImageState image = loader.run();
    model1.addImage("anya", image);

    // ----- test original save -----
    Appendable outputOriginal = new StringBuilder();
    ImageSaver saveOriginalImage = new PPMImageSaver("res/anya.ppm", image, outputOriginal);
    saveOriginalImage.run();
    String expectedOutputOriginal =
            "P3\n"
                    + "3 3\n"
                    + "255\n"
                    + "95 68 74 198 142 154 82 57 67 \n"
                    + "147 100 111 220 197 204 146 101 113 \n"
                    + "194 152 165 135 117 126 204 164 177 \n";
    assertEquals(expectedOutputOriginal, outputOriginal.toString());

    // ----- test save after transformation -----
    Transformation brighten = new BrightenTransformation(10);
    ImageState brightenedImage = brighten.apply(image);
    Appendable output = new StringBuilder();
    ImageSaver saveImage = new PPMImageSaver("res/anyaBrightened.ppm", brightenedImage, output);
    saveImage.run();
    String expectedOutput =
            "P3\n"
                    + "3 3\n"
                    + "255\n"
                    + "105 78 84 208 152 164 92 67 77 \n"
                    + "157 110 121 230 207 214 156 111 123 \n"
                    + "204 162 175 145 127 136 214 174 187 \n";

    assertEquals(expectedOutput, output.toString());
  }

  @Test
  public void testSaveFilePath() {
    ImageModel model1 = new ImageModelImpl();
    ImageLoader loader = new PPMImageLoader("res/anya.ppm");
    ImageState image = loader.run();
    model1.addImage("anya", image);

    // ----- test save after transformation -----
    Transformation brighten = new BrightenTransformation(10);
    ImageState brightenedImage = brighten.apply(image);
    Appendable output = new StringBuilder();
    ImageSaver saveImage = new PPMImageSaver("res/anyaBrightened.ppm", brightenedImage, output);

    File fileOutput = new File("res/anyaBrightened.ppm");
    try (FileWriter writer = new FileWriter(fileOutput)) {
      PPMImageSaver saver = new PPMImageSaver("res/anyaBrightened.ppm", brightenedImage, writer);
      saver.run();
    } catch (Exception e) {
      throw new IllegalStateException("Could not write to output");
    }
    assertEquals("res/anyaBrightened.ppm", fileOutput.getPath());
  }

}
