package cs3500.animator.model.shape;

/**
 * This class represents an star. It extends the AbstractShape2D abstract class.
 */
public class Star extends AbstractShape {

  /**
   * This constructor calls on the super to create an Star.
   *
   * @param name   the unique name of the rectangle
   * @param position the position of the object
   * @param height the height of the object
   * @param width  the width of the object
   * @param r        the r color of the object
   * @param g        the g color of the object
   * @param b        the b color of the object
   */
  public Star(String name, Position2D position, int height, int width, int r, int g, int b) {
    super(name, position, height, width, r, g, b);
  }

  /**
   * Creates an instance of an ellipse given only a name.
   * @param name the name of the ellipse.
   */
  public Star(String name) {
    super(name);
  }
}
