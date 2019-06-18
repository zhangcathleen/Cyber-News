package cs3500.animator.model.shape;

/**
 * Represents a location on a 2D screen with a row and a column.
 */
public class Position2D {
  private final int x;
  private final int y;

  /**
   * Creates an instance of a Position2D.
   * @param x the x value
   * @param y the y value
   */
  public Position2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the x value.
   * @return the x value of this Position2D
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the y value.
   * @return the y value of this Position2D
   */
  public int getY() {
    return y;
  }
}
