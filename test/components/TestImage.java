package components;

import org.junit.Before;
import org.junit.Test;

import controller.io.ImageLoader;
import controller.io.PPMImageLoader;
import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageState;
import model.pixel.Pixel;
import model.pixel.PixelImpl;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * This class represents a test class for the image.
 */
public class TestImage {
  Image image;
  Image image2;
  Image image3;

  Pixel pixel;
  Pixel pixel2;
  Pixel pixel3;

  @Before
  public void setUp() {
    image = new ImageImpl(1, 1);
    image2 = new ImageImpl(2, 2);
    image3 = new ImageImpl(3, 3);

    pixel = new PixelImpl(10, 20, 30);
    pixel2 = new PixelImpl(40, 50, 60);
    pixel3 = new PixelImpl(70, 80, 90);
  }


  // ---------- TEST CONSTRUCTOR ----------

  @Test
  public void testConstructor() {
    // ----- test dimensions -----
    assertEquals(1, image.getWidth());
    assertEquals(1, image.getHeight());
    assertEquals(2, image2.getWidth());
    assertEquals(2, image2.getHeight());
    assertEquals(3, image3.getWidth());
    assertEquals(3, image3.getHeight());
  }

  @Test
  public void testInvalidConstructor() {
    // ----- test invalid dimensions -----
    assertThrows(IllegalArgumentException.class, () -> {
      new ImageImpl(0, 0);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new ImageImpl(-1, 1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new ImageImpl(1, -1);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new ImageImpl(-1, -1);
    });
  }

  // ---------- TEST SETTERS & GETTERS ----------

  @Test
  public void testSetManualPixel() {
    // ----- manually set pixel in image using getters -----
    image.setPixel(0, 0, pixel.getR(), pixel.getG(), pixel.getB());
    assertEquals(10, image.getRedChannel(0, 0));
    assertEquals(20, image.getGreenChannel(0, 0));
    assertEquals(30, image.getBlueChannel(0, 0));

    // ----- manually set pixel in image -----
    image2.setPixel(0, 0, 1, 2, 3);
    image2.setPixel(1, 0, 5, 10, 0);
    image2.setPixel(0, 1, 0, 0, 0);
    image2.setPixel(1, 1, 60, 60, 60);
    assertEquals(1, image2.getRedChannel(0, 0));
    assertEquals(2, image2.getGreenChannel(0, 0));
    assertEquals(3, image2.getBlueChannel(0, 0));
    assertEquals(5, image2.getRedChannel(1, 0));
    assertEquals(10, image2.getGreenChannel(1, 0));
    assertEquals(0, image2.getBlueChannel(1, 0));
    assertEquals(0, image2.getRedChannel(0, 1));
    assertEquals(0, image2.getGreenChannel(0, 1));
    assertEquals(0, image2.getBlueChannel(0, 1));
    assertEquals(60, image2.getRedChannel(1, 1));
    assertEquals(60, image2.getGreenChannel(1, 1));
    assertEquals(60, image2.getBlueChannel(1, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelOutOfBounds() {
    // ----- test getting out of bounds pixel -----
    image.setPixel(1, 1, pixel.getR(), pixel.getG(), pixel.getB());
    image.setPixel(0, 1, pixel2.getR(), pixel2.getG(), pixel2.getB());
    image.setPixel(1, 0, pixel3.getR(), pixel3.getG(), pixel3.getB());
  }

  @Test
  public void testImageFromLoader() {
    ImageLoader loader = new PPMImageLoader("res/anya.ppm");
    ImageState image = loader.run();

    // ----- test dimensions & pixel rgb -----
    assertEquals(3, image.getWidth());
    assertEquals(3, image.getHeight());
    assertEquals(95, image.getRedChannel(0, 0));
    assertEquals(68, image.getGreenChannel(0, 0));
    assertEquals(74, image.getBlueChannel(0, 0));
    assertEquals(198, image.getRedChannel(1, 0));
    assertEquals(142, image.getGreenChannel(1, 0));
    assertEquals(154, image.getBlueChannel(1, 0));
    assertEquals(82, image.getRedChannel(2, 0));

    // ----- test out of bounds pixel -----
    assertThrows(IllegalArgumentException.class, () -> {
      image.getRedChannel(0, 3);
    });
  }


}
