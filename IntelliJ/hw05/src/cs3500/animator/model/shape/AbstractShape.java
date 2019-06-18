package cs3500.animator.model.shape;

/**
 * An abstract shape which has a name, position, width/height, and color values. Contains common
 * methods such as move, scale, setColor, as well as public getters.
 */
public abstract class AbstractShape implements IShape {

  private final String name;
  private Position2D position;
  private int width;
  private int height;
  private int r;
  private int g;
  private int b;

  /**
   * This constructor creates an AbstractShape2D shape given the position, dimensions, and color
   * values of the shape.
   *
   * @param name   the unique name of the rectangle
   * @param position the position of th object
   * @param height the height of the object
   * @param width  the width of the object
   * @param r        the r color of the object
   * @param g        the g color of the object
   * @param b        the b color of the object
   */
  AbstractShape(String name, Position2D position, int height, int width, int r, int g, int b) {
    this.name = name;
    this.position = position;
    this.height = height;
    this.width = width;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * creates an instance of a shape only given a String name.
   * @param name the name of the shape.
   */
  AbstractShape(String name) {
    this.name = name;
  }

  @Override
  public void move(Position2D position) {
    this.position = position;
  }

  @Override
  public void scale(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("the width and height cannot be negative");
    } else {
      this.height = height;
      this.width = width;
    }
  }

  @Override
  public void setColor(int r, int g, int b) {
    if (r > 0 & g > 0 & b > 0) {
      this.r = r;
      this.g = g;
      this.b = b;
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Position2D getPosition() {
    return this.position;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getR() {
    return this.r;
  }

  @Override
  public int getG() {
    return this.g;
  }

  @Override
  public int getB() {
    return this.b;
  }

}
