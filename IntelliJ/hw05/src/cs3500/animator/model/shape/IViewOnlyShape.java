package cs3500.animator.model.shape;

/**
 * This interface represents the shapes. Every object would be a different shape. Only getter
 * methods are in this interface. We have two interfaces for shapes as opposed to one so that we
 * can supply non-mutable shapes to the controller.
 */
public interface IViewOnlyShape {
  /**
   * Returns the name of this shape.
   * @return the name of this shape.
   */
  String getName();

  /**
   * Returns the position of this shape.
   * @return the position of this shape.
   */
  Position2D getPosition();

  /**
   * Returns the width of this shape.
   * @return the width of this shape.
   */
  int getWidth();

  /**
   * Returns the height of this shape.
   * @return the height of this shape.
   */
  int getHeight();

  /**
   * Returns the red color value for this shape.
   * @return the red color value for this shape.
   */
  int getR();

  /**
   * Returns the green color value for this shape.
   * @return the green color value for this shape.
   */
  int getG();

  /**
   * Returns the blue color value for this shape.
   * @return the blue color value for this shape.
   */
  int getB();

}
