package components;

import org.junit.Before;
import org.junit.Test;

import model.pixel.Pixel;
import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class represents a test class for the pixel.
 */
public class TestPixel {
  Pixel pixel;
  Pixel pixel2;

  @Before
  public void setUp() {
    pixel = new PixelImpl(0, 0, 0);
    pixel2 = new PixelImpl(255, 100, 10);
  }

  // ---------- TEST CONSTRUCTOR ----------

  @Test
  public void testPixelImpl() {

    assertEquals(0, pixel.getRGB()[0]);
    assertEquals(0, pixel.getRGB()[1]);
    assertEquals(0, pixel.getRGB()[2]);

    assertEquals(255, pixel2.getRGB()[0]);
    assertEquals(100, pixel2.getRGB()[1]);
    assertEquals(10, pixel2.getRGB()[2]);
  }

  // ---------- TEST SETTERS ----------

  @Test
  public void testSetRGB() {
    pixel.setRGB(new int[]{255, 255, 255});
    assertEquals(255, pixel.getRGB()[0]);
    assertEquals(255, pixel.getRGB()[1]);
    assertEquals(255, pixel.getRGB()[2]);
  }

  @Test
  public void testSetRGB2() {
    pixel2.setR(250);
    pixel2.setG(255);
    pixel2.setB(255);
    //pixel2.setRGB(250, 255, 255);
    assertEquals(250, pixel2.getRGB()[0]);
    assertEquals(255, pixel2.getRGB()[1]);
    assertEquals(255, pixel2.getRGB()[2]);
  }

  @Test
  public void testSetRGB2XY() {
    pixel2.setRGB(new int[]{250, 255, 255});
    assertEquals(250, pixel2.getRGB()[0]);
    assertEquals(255, pixel2.getRGB()[1]);
    assertEquals(255, pixel2.getRGB()[2]);
  }

  // ---------- TEST GETTERS ----------

  @Test
  public void testGetRGB() {
    assertEquals(0, pixel.getRGB()[0]);
    assertEquals(0, pixel.getRGB()[1]);
    assertEquals(0, pixel.getRGB()[2]);

    assertEquals(255, pixel2.getRGB()[0]);
    assertEquals(100, pixel2.getRGB()[1]);
    assertEquals(10, pixel2.getRGB()[2]);
  }

  // ---------- TEST TOSTRING ----------

  @Test
  public void testToString() {
    assertEquals("(0, 0, 0)", pixel.toString());
    assertEquals("(255, 100, 10)", pixel2.toString());
  }

}
