import org.junit.Before;
import org.junit.Test;

import controller.io.ImageLoader;
import controller.io.ImageSaver;
import controller.io.PPMImageLoader;
import controller.io.PPMImageSaver;
import model.ImageModel;
import model.ImageModelImpl;
import model.image.ImageImpl;
import model.image.ImageState;
import model.transform.BrightenTransformation;
import model.transform.GreyscaleBlueTransformation;
import model.transform.GreyscaleGreenTransformation;
import model.transform.GreyscaleIntensityTransformation;
import model.transform.GreyscaleLumaTransformation;
import model.transform.GreyscaleRedTransformation;
import model.transform.GreyscaleValueTransformation;
import model.transform.Transformation;

import static junit.framework.TestCase.assertEquals;

/**
 * This class represents a test class for the model.
 */
public class TestModel {

  private ImageModel modelTest;
  private ImageState imageTest;

  @Before
  public void setUp() {
    modelTest = new ImageModelImpl();
    ImageLoader loaderTest = new PPMImageLoader("res/anya.ppm");
    imageTest = loaderTest.run();
    modelTest.addImage("anya", imageTest);
  }

  // ---------- TEST CONSTRUCTOR ----------
  @Test
  public void testGetImageFromModel() {
    assertEquals(imageTest, modelTest.getImage("anya"));
    assertEquals(3, modelTest.getImage("anya").getWidth());
    assertEquals(3, modelTest.getImage("anya").getHeight());
  }

  @Test
  public void testAddImageToModel() {
    ImageState image = new ImageImpl(2, 2);
    modelTest.addImage("image", image);
    assertEquals(image, modelTest.getImage("image"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetImageFromModel() {
    modelTest.getImage("notAnImage");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidAddImageToModel() {
    ImageState image = new ImageImpl(0, 0);
    modelTest.addImage("invalid", image);
  }

  // ---------- TEST TRANSFORMATIONS ----------

  @Test
  public void testBrighten() {
    ImageModel model1 = new ImageModelImpl();
    ImageLoader loader = new PPMImageLoader("imagebackup/anya.ppm");
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
    Transformation brighten = new BrightenTransformation(50);
    ImageState brightenedImage = brighten.apply(image);
    Appendable output = new StringBuilder();
    ImageSaver saveImage = new PPMImageSaver("res/anyaBrightened.ppm", brightenedImage, output);
    saveImage.run();
    String expectedOutput =
            "P3\n"
                    + "3 3\n"
                    + "255\n"
                    + "145 118 124 248 192 204 132 107 117 \n"
                    + "197 150 161 255 247 254 196 151 163 \n"
                    + "244 202 215 185 167 176 254 214 227 \n";

    assertEquals(expectedOutput, output.toString());
  }

  @Test
  public void testDarken() {
    ImageModel model1 = new ImageModelImpl();
    ImageLoader loader = new PPMImageLoader("imagebackup/anya.ppm");
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
    Transformation brighten = new BrightenTransformation(-50);
    ImageState brightenedImage = brighten.apply(image);
    Appendable output = new StringBuilder();
    ImageSaver saveImage = new PPMImageSaver("res/anyaDarkened.ppm", brightenedImage, output);
    saveImage.run();
    String expectedOutput =
            "P3\n"
                    + "3 3\n"
                    + "255\n"
                    + "45 18 24 148 92 104 32 7 17 \n"
                    + "97 50 61 170 147 154 96 51 63 \n"
                    + "144 102 115 85 67 76 154 114 127 \n";

    assertEquals(expectedOutput, output.toString());
  }

  @Test
  public void testIntensityGrey() {
    ImageModel modelTest = new ImageModelImpl();
    ImageLoader loaderTest = new PPMImageLoader("imagebackup/fourbyfour.ppm");
    ImageState imageTest = loaderTest.run();
    modelTest.addImage("four", imageTest);

    // ----- test original save -----
    Appendable outputOriginal = new StringBuilder();
    ImageSaver saveOriginalImage = new PPMImageSaver("res/fourbyfour.ppm", imageTest,
            outputOriginal);
    saveOriginalImage.run();
    String expectedOutputOriginal =
            "P3\n"
                    + "4 4\n"
                    + "255\n"
                    + "255 0 0 0 255 0 0 0 255 255 255 255 \n"
                    + "0 0 0 255 0 0 0 255 0 0 0 255 \n"
                    + "255 0 255 0 0 0 255 0 0 0 255 0 \n"
                    + "0 255 255 255 0 255 0 0 0 255 0 0 \n";
    assertEquals(expectedOutputOriginal, outputOriginal.toString());

    // ----- test save after transformation -----
    Transformation intensityGrey = new GreyscaleIntensityTransformation();
    ImageState greyImage = intensityGrey.apply(imageTest);
    Appendable output = new StringBuilder();
    ImageSaver saveImage = new PPMImageSaver("res/fourbyfourIntensityGrey.ppm", greyImage, output);
    saveImage.run();
    String expectedOutput =
            "P3\n"
                    + "4 4\n"
                    + "255\n"
                    + "85 85 85 85 85 85 85 85 85 255 255 255 \n"
                    + "0 0 0 85 85 85 85 85 85 85 85 85 \n"
                    + "170 170 170 0 0 0 85 85 85 85 85 85 \n"
                    + "170 170 170 170 170 170 0 0 0 85 85 85 \n";
    assertEquals(expectedOutput, output.toString());
  }

  @Test
  public void testValueGrey() {
    ImageModel model1 = new ImageModelImpl();
    ImageLoader loader = new PPMImageLoader("imagebackup/anya.ppm");
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
    Transformation intensityGrey = new GreyscaleValueTransformation();
    ImageState greyImage = intensityGrey.apply(image);
    Appendable output = new StringBuilder();
    ImageSaver saveImage = new PPMImageSaver("res/anyaValueGrey.ppm", greyImage, output);
    saveImage.run();
    String expectedOutput =
            "P3\n"
                    + "3 3\n"
                    + "255\n"
                    + "95 95 95 198 198 198 82 82 82 \n"
                    + "147 147 147 220 220 220 146 146 146 \n"
                    + "194 194 194 135 135 135 204 204 204 \n";
    assertEquals(expectedOutput, output.toString());
  }

  @Test
  public void testLumaGrey() {
    ImageModel model1 = new ImageModelImpl();
    ImageLoader loader = new PPMImageLoader("imagebackup/pigeon.ppm");
    ImageState image = loader.run();
    model1.addImage("pigeon", image);

    // ----- test original save -----
    Appendable outputOriginal = new StringBuilder();
    ImageSaver saveOriginalImage = new PPMImageSaver("res/pigeon.ppm", image, outputOriginal);
    saveOriginalImage.run();
    String expectedOutputOriginal =
            "P3\n" + "5 5\n"
                    + "255\n"
                    + "105 49 12 225 130 7 235 148 31 180 107 15 111 57 23 \n"
                    + "96 45 28 154 80 13 216 113 27 184 107 30 194 122 19 \n"
                    + "76 34 20 140 77 44 133 73 49 186 111 41 213 127 0 \n"
                    + "115 60 41 124 82 56 119 76 46 209 133 28 216 127 0 \n"
                    + "102 57 40 110 60 42 121 70 40 139 85 36 218 131 8 \n";
    assertEquals(expectedOutputOriginal, outputOriginal.toString());

    // ----- test save after transformation -----
    Transformation lumaGrey = new GreyscaleLumaTransformation();
    ImageState greyImage = lumaGrey.apply(image);
    Appendable output = new StringBuilder();
    ImageSaver saveImage = new PPMImageSaver("res/pigeonLumaGrey.ppm", greyImage, output);
    saveImage.run();
    String expectedOutput =
            "P3\n"
                    + "5 5\n"
                    + "255\n"
                    + "58 58 58 141 141 141 158 158 158 116 116 116 66 66 66 \n"
                    + "55 55 55 91 91 91 129 129 129 118 118 118 130 130 130 \n"
                    + "42 42 42 88 88 88 84 84 84 122 122 122 136 136 136 \n"
                    + "70 70 70 89 89 89 83 83 83 142 142 142 137 137 137 \n"
                    + "65 65 65 69 69 69 79 79 79 93 93 93 141 141 141 \n";
    assertEquals(expectedOutput, output.toString());
  }

  @Test
  public void testRGBGrey() {
    ImageModel model1 = new ImageModelImpl();
    ImageLoader loader = new PPMImageLoader("imagebackup/pigeon.ppm");
    ImageState image = loader.run();
    model1.addImage("pigeon", image);

    // ----- test original save -----
    Appendable outputOriginal = new StringBuilder();
    ImageSaver saveOriginalImage = new PPMImageSaver("res/pigeon.ppm", image, outputOriginal);
    saveOriginalImage.run();
    String expectedOutputOriginal =
            "P3\n" + "5 5\n"
                    + "255\n"
                    + "105 49 12 225 130 7 235 148 31 180 107 15 111 57 23 \n"
                    + "96 45 28 154 80 13 216 113 27 184 107 30 194 122 19 \n"
                    + "76 34 20 140 77 44 133 73 49 186 111 41 213 127 0 \n"
                    + "115 60 41 124 82 56 119 76 46 209 133 28 216 127 0 \n"
                    + "102 57 40 110 60 42 121 70 40 139 85 36 218 131 8 \n";
    assertEquals(expectedOutputOriginal, outputOriginal.toString());

    // ----- test save after red transformation -----
    Transformation redGrey = new GreyscaleRedTransformation();
    ImageState greyRedImage = redGrey.apply(image);
    Appendable output = new StringBuilder();
    ImageSaver saveImage = new PPMImageSaver("res/pigeonRedGrey.ppm", greyRedImage, output);
    saveImage.run();
    String expectedOutput =
            "P3\n"
                    + "5 5\n"
                    + "255\n"
                    + "105 105 105 225 225 225 235 235 235 180 180 180 111 111 111 \n"
                    + "96 96 96 154 154 154 216 216 216 184 184 184 194 194 194 \n"
                    + "76 76 76 140 140 140 133 133 133 186 186 186 213 213 213 \n"
                    + "115 115 115 124 124 124 119 119 119 209 209 209 216 216 216 \n"
                    + "102 102 102 110 110 110 121 121 121 139 139 139 218 218 218 \n";
    assertEquals(expectedOutput, output.toString());

    // ----- test save after green transformation -----
    Transformation greenGrey = new GreyscaleGreenTransformation();
    ImageState greyGreenImage = greenGrey.apply(image);
    Appendable output2 = new StringBuilder();
    ImageSaver saveImage2 = new PPMImageSaver("res/pigeonGreenGrey.ppm", greyGreenImage, output2);
    saveImage2.run();
    String expectedOutput2 =
            "P3\n"
                    + "5 5\n"
                    + "255\n"
                    + "49 49 49 130 130 130 148 148 148 107 107 107 57 57 57 \n"
                    + "45 45 45 80 80 80 113 113 113 107 107 107 122 122 122 \n"
                    + "34 34 34 77 77 77 73 73 73 111 111 111 127 127 127 \n"
                    + "60 60 60 82 82 82 76 76 76 133 133 133 127 127 127 \n"
                    + "57 57 57 60 60 60 70 70 70 85 85 85 131 131 131 \n";
    assertEquals(expectedOutput2, output2.toString());

    // ----- test save after blue transformation -----
    Transformation blueGrey = new GreyscaleBlueTransformation();
    ImageState greyBlueImage = blueGrey.apply(image);
    Appendable output3 = new StringBuilder();
    ImageSaver saveImage3 = new PPMImageSaver("res/pigeonBlueGrey.ppm", greyBlueImage, output3);
    saveImage3.run();
    String expectedOutput3 =
            "P3\n"
                    + "5 5\n"
                    + "255\n"
                    + "12 12 12 7 7 7 31 31 31 15 15 15 23 23 23 \n"
                    + "28 28 28 13 13 13 27 27 27 30 30 30 19 19 19 \n"
                    + "20 20 20 44 44 44 49 49 49 41 41 41 0 0 0 \n"
                    + "41 41 41 56 56 56 46 46 46 28 28 28 0 0 0 \n"
                    + "40 40 40 42 42 42 40 40 40 36 36 36 8 8 8 \n";
    assertEquals(expectedOutput3, output3.toString());
  }

  @Test
  public void testBlurFilter() {
    // ----- test original save -----
    Appendable outputOriginal = new StringBuilder();
    ImageSaver saveOriginalImage = new PPMImageSaver("res/anya.ppm", imageTest, outputOriginal);
    saveOriginalImage.run();
    String expectedOutputOriginal =
            "P3\n"
                    + "3 3\n"
                    + "255\n"
                    + "95 68 74 198 142 154 82 57 67 \n"
                    + "147 100 111 220 197 204 146 101 113 \n"
                    + "194 152 165 135 117 126 204 164 177 \n";
    assertEquals(expectedOutputOriginal, outputOriginal.toString());
  }


  // ---------- TEST INVALID TRANSFORMATIONS ----------

  @Test (expected = NullPointerException.class)
  public void testNullImageTransform() {
    ImageModel model1 = new ImageModelImpl();
    ImageLoader loader = new PPMImageLoader("imagebackup/biggestpigeon.ppm");
    ImageState image = loader.run();
    model1.addImage("biggestpigeon", image);

    Appendable outputOriginal = new StringBuilder();
    ImageSaver saveOriginalImage = new PPMImageSaver("res/pigeon.ppm", null, outputOriginal);
    saveOriginalImage.run();
  }

  @Test (expected = IllegalStateException.class)
  public void testNullAppendableTransform() {
    ImageModel model1 = new ImageModelImpl();
    ImageLoader loader = new PPMImageLoader("imagebackup/biggestpigeon.ppm");
    ImageState image = loader.run();
    model1.addImage("biggestpigeon", image);

    Appendable outputOriginal = new StringBuilder();
    ImageSaver saveOriginalImage = new PPMImageSaver("res/pigeon.ppm", image, null);
    saveOriginalImage.run();
  }

  @Test (expected = NullPointerException.class)
  public void testNullPixelTransformation() {
    ImageModel model1 = new ImageModelImpl();
    ImageState image = new ImageImpl(1, 1);

    Transformation redGrey = new GreyscaleRedTransformation();
    ImageState greyRedImage = redGrey.apply(image);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullImageTransformation() {
    ImageModel model1 = new ImageModelImpl();
    ImageState image = new ImageImpl(1, 1);

    Transformation redGrey = new GreyscaleRedTransformation();
    ImageState greyRedImage = redGrey.apply(null);
  }

}

