package controller.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageState;

public class BufferedImageLoader extends BaseIOMethods implements ImageLoader {
  private final String filePath;

  public BufferedImageLoader(String filePath) throws NullPointerException {
    this.filePath = Objects.requireNonNull(filePath);
  }

  @Override
  public ImageState run() {
    BufferedImage bufferedImage = readImageFile(this.filePath);

    int height = bufferedImage.getHeight();
    int width = bufferedImage.getWidth();
    Image convertedImage = new ImageImpl(width, height);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {

        int RGB = bufferedImage.getRGB(col, row);
        int red = (RGB >> 16) & 0xff;
        int green = (RGB >> 8) & 0xff;
        int blue = RGB & 0xff;

        convertedImage.setPixel(col, row, red, green, blue);
      }
    }
    return convertedImage;
  }

//  private BufferedImage readImageFile(String filePath) throws IllegalArgumentException {
//    BufferedImage image;
//    try {
//      image = ImageIO.read(new FileInputStream(filePath));
//    } catch (IOException e) {
//      System.out.println("File error:" + this.filePath);
//      throw new IllegalArgumentException("File not found or invalid!");
//    }
//    return image;
//  }
}