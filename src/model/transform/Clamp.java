package model.transform;

/**
 * This class represents a clamp for values to be between 0 and 255.
 */
public abstract class Clamp {

  /**
   * Clamps the given value to be between 0 and 255.
   * @param value the value to clamp
   * @return the clamped value
   */
  protected int clamp(int value) {
    if (value < 0) {
      return 0;
    } else if (value > 255) {
      return 255;
    }
    return value;
  }
}
