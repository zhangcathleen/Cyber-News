package cs3500.animator.model.shape;

/**
 * An interface for all shape objects. All shapes must include getters to reveal their information,
 * as well as methods which mutate the shape in predefined ways.
 */
public interface IShape extends IViewOnlyShape {
  /**
   * This method moves the shape by the given x and y. It is used to update the state of a shape
   * to be passed to the controller.
   *
   * @param position the new position to move to.
   */
  void move(Position2D position);

  /**
   * This method changes the shape's width and height to the given width and height. It is used to
   * update the state of a shape to be passed to the controller.
   *
   * @param width  the width the shape needs to resize to
   * @param height the height the shape needs to resize to
   */
  void scale(int width, int height);

  /**
   * This method changes the shape's color to the rgb color. It is used to
   * update the state of a shape to be passed to the controller.
   *
   * @param r the red color value of the new color.
   * @param g the green color value of the new color.
   * @param b the blue color value of the new color.
   */
  void setColor(int r, int g, int b);
}
