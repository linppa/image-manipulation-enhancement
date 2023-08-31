package mocks;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import controller.io.BaseIOMethods;
import view.View;
import view.ViewListener;

public class MockView extends BaseIOMethods implements View {
  public BufferedImage imageToTest;
  private final StringBuilder log;

  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public BufferedImage getImageFromView() {
    return this.imageToTest;
  }

  @Override
  public void setImageView(BufferedImage image) {
    this.imageToTest = image;
    StringBuilder imageLog = new StringBuilder();
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        Color color = new Color(this.imageToTest.getRGB(col, row));
        imageLog.append(color.getRed()).append(" ").append(color.getGreen()).append(" ")
                .append(color.getBlue()).append(" ");
      }
    }
    log.append(imageLog);
  }

  @Override
  public void addViewListener(ViewListener listener) {
    log.append("ViewListener added.\n");
  }

  @Override
  public void requestFrameFocus() {
    log.append("Frame focus requested.\n");
  }

  @Override
  public void emitLoadEvent() {
    log.append("Load event emitted.\n");
  }

  @Override
  public void emitSaveEvent() {
    log.append("Save event emitted.\n");
  }

  @Override
  public void emitBrightenEvent(int value) {
    log.append("Brighten event emitted.\n");
  }

  @Override
  public void emitComponentGreyscaleEvent(int component) {
    log.append("Component greyscale event emitted.\n");
  }

  @Override
  public void emitBlurEvent() {
    log.append("Blur event emitted.\n");
  }

  @Override
  public void emitSharpenEvent() {
    log.append("Sharpen event emitted.\n");
  }

  @Override
  public void emitFilterGreyscaleEvent() {
    log.append("Filter greyscale event emitted.\n");
  }

  @Override
  public void emitSepiaEvent() {
    log.append("Sepia event emitted.\n");
  }

  @Override
  public void emitHistogramEvent() {
    log.append("Histogram event emitted.\n");
  }

  @Override
  public void setVisible(boolean b) {
    log.append("View set visible.\n");
  }

  @Override
  public void displayMessage(String message) {
    log.append("Message was displayed to the user.\n");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    log.append("Action performed.\n");
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // do nothing
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // do nothing
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // do nothing
  }
}

