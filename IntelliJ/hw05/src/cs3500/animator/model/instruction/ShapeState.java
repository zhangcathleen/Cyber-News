package cs3500.animator.model.instruction;

import cs3500.animator.model.shape.Position2D;

/**
 * Represents the state of the Shape in the OperateInstruction. It is usually either the beginning
 * or ending tick. It has the Position2D position, the width and height of the shape, the color,
 * which is the r, g, b for the
 */
public class ShapeState {
  private final Position2D position;
  private final int width;
  private final int height;
  private final int r;
  private final int g;
  private final int b;

  /**
   * creates an instance of a ShapeState given a position, width and height, and color parameters.
   * @param position the x y position of the shape state
   * @param width the width of the shape
   * @param height the height of the shape
   * @param r the red value of this shape state (0-255)
   * @param g the green value of this shape state (0-255)
   * @param b the blue value of this shape state (0-255)
   */
  public ShapeState(Position2D position, int width, int height, int r, int g, int b) {
    this.position = position;
    this.width = width;
    this.height = height;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Returns the state of the shape in string format.
   */
  public String printState() {
    return this.position.getX() + " " + this.position.getY() + " " + this.width + " " + this.height
            + " " + this.r + " " + this.g + " " + this.b;
  }

  public Position2D getPosition() {
    return position;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getR() {
    return r;
  }

  public int getG() {
    return g;
  }

  public int getB() {
    return b;
  }
}
