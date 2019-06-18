import org.junit.Test;


import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.IShape;
import cs3500.animator.model.shape.Position2D;
import cs3500.animator.model.shape.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This class tests all the methods included in the IShape package.
 */
public class IShapeTest {

  private Position2D p1;
  private IShape shapeRectangleNameView;
  private IShape shapeRectangleNameCompleteView;
  private Position2D p2;
  private IShape shapeEllipseNameView;
  private IShape shapeEllipseNameCompleteView;
  private Position2D positionMove;

  private void init() {
    p1 = new Position2D(100, 100);
    shapeRectangleNameView = new Rectangle("bob");
    shapeRectangleNameCompleteView = new Rectangle("bobby", p1, 10, 10, 250, 250, 250);
    p2 = new Position2D(50, 50);
    shapeEllipseNameView = new Ellipse("jen");
    shapeEllipseNameCompleteView = new Ellipse("jenny", p2, 5, 5, 200, 200, 200);
    positionMove = new Position2D(11, 11);
  }

  @Test
  public void testPosition2D() {
    init();
    assertEquals(p1.getX(), 100);
    assertEquals(p1.getY(), 100);
    assertEquals(p2.getX(), 50);
    assertEquals(p2.getY(), 50);
    assertEquals(positionMove.getX(), 11);
    assertEquals(positionMove.getY(), 11);
  }

  @Test
  public void testSetColor() {
    init();
    assertEquals(250, shapeRectangleNameCompleteView.getR());
    assertEquals(250, shapeRectangleNameCompleteView.getG());
    assertEquals(250, shapeRectangleNameCompleteView.getB());
    shapeRectangleNameCompleteView.setColor(300, 301, 302);
    assertEquals(300, shapeRectangleNameCompleteView.getR());
    assertEquals(301, shapeRectangleNameCompleteView.getG());
    assertEquals(302, shapeRectangleNameCompleteView.getB());
    assertEquals(0, shapeRectangleNameView.getHeight());
    assertEquals(0, shapeRectangleNameView.getWidth());
    shapeRectangleNameView.setColor(278, 279, 277);
    assertEquals(278, shapeRectangleNameView.getR());
    assertEquals(279, shapeRectangleNameView.getG());
    assertEquals(277, shapeRectangleNameView.getB());

    assertEquals(200, shapeEllipseNameCompleteView.getR());
    assertEquals(200, shapeEllipseNameCompleteView.getG());
    assertEquals(200, shapeEllipseNameCompleteView.getB());
    shapeEllipseNameCompleteView.setColor(300, 301, 302);
    assertEquals(300, shapeEllipseNameCompleteView.getR());
    assertEquals(301, shapeEllipseNameCompleteView.getG());
    assertEquals(302, shapeEllipseNameCompleteView.getB());
    assertEquals(0, shapeEllipseNameView.getHeight());
    assertEquals(0, shapeEllipseNameView.getWidth());
    shapeEllipseNameView.setColor(278, 279, 277);
    assertEquals(278, shapeEllipseNameView.getR());
    assertEquals(279, shapeEllipseNameView.getG());
    assertEquals(277, shapeEllipseNameView.getB());
  }

  @Test
  public void testScale() {
    init();
    assertEquals(10, shapeRectangleNameCompleteView.getHeight());
    assertEquals(10, shapeRectangleNameCompleteView.getWidth());
    shapeRectangleNameCompleteView.scale(5, 7);
    assertEquals(7, shapeRectangleNameCompleteView.getHeight());
    assertEquals(5, shapeRectangleNameCompleteView.getWidth());
    assertEquals(0, shapeRectangleNameView.getHeight());
    assertEquals(0, shapeRectangleNameView.getWidth());
    shapeRectangleNameView.scale(14, 13);
    assertEquals(13, shapeRectangleNameView.getHeight());
    assertEquals(14, shapeRectangleNameView.getWidth());


    assertEquals(5, shapeEllipseNameCompleteView.getHeight());
    assertEquals(5, shapeEllipseNameCompleteView.getWidth());
    shapeEllipseNameCompleteView.scale(5, 7);
    assertEquals(7, shapeEllipseNameCompleteView.getHeight());
    assertEquals(5, shapeEllipseNameCompleteView.getWidth());
    assertEquals(0, shapeEllipseNameView.getHeight());
    assertEquals(0, shapeEllipseNameView.getWidth());
    shapeEllipseNameView.scale(14, 13);
    assertEquals(13, shapeEllipseNameView.getHeight());
    assertEquals(14, shapeEllipseNameView.getWidth());
  }

  @Test
  public void testMove() {
    init();
    assertEquals(p1, shapeRectangleNameCompleteView.getPosition());
    shapeRectangleNameCompleteView.move(positionMove);
    assertEquals(positionMove, shapeRectangleNameCompleteView.getPosition());
    assertNull(shapeRectangleNameView.getPosition());
    shapeRectangleNameView.move(positionMove);
    assertEquals(positionMove, shapeRectangleNameView.getPosition());


    assertEquals(p2, shapeEllipseNameCompleteView.getPosition());
    shapeEllipseNameCompleteView.move(positionMove);
    assertEquals(positionMove, shapeEllipseNameCompleteView.getPosition());
    assertNull(shapeEllipseNameView.getPosition());
    shapeEllipseNameView.move(positionMove);
    assertEquals(positionMove, shapeEllipseNameView.getPosition());
  }

  @Test
  public void testIViewOnlyShape() {
    init();
    assertEquals(shapeRectangleNameView.getName(), "bob");
    assertEquals(shapeRectangleNameView.getB(), 0);
    assertNull(shapeRectangleNameView.getPosition());
    assertEquals(shapeRectangleNameView.getHeight(), 0);
    assertEquals(shapeRectangleNameView.getWidth(), 0);
    assertEquals(shapeRectangleNameView.getR(), 0);
    assertEquals(shapeRectangleNameView.getG(), 0);
    assertEquals(shapeRectangleNameCompleteView.getName(), "bobby");
    assertEquals(shapeRectangleNameCompleteView.getPosition(), p1);
    assertEquals(shapeRectangleNameCompleteView.getHeight(), 10);
    assertEquals(shapeRectangleNameCompleteView.getWidth(), 10);
    assertEquals(shapeRectangleNameCompleteView.getR(), 250);
    assertEquals(shapeRectangleNameCompleteView.getG(), 250);
    assertEquals(shapeRectangleNameCompleteView.getB(), 250);

    assertEquals(shapeEllipseNameView.getName(), "jen");
    assertEquals(shapeEllipseNameView.getB(), 0);
    assertNull(shapeEllipseNameView.getPosition());
    assertEquals(shapeEllipseNameView.getHeight(), 0);
    assertEquals(shapeEllipseNameView.getWidth(), 0);
    assertEquals(shapeEllipseNameView.getR(), 0);
    assertEquals(shapeEllipseNameView.getG(), 0);
    assertEquals(shapeEllipseNameCompleteView.getName(), "jenny");
    assertEquals(shapeEllipseNameCompleteView.getPosition(), p2);
    assertEquals(shapeEllipseNameCompleteView.getHeight(), 5);
    assertEquals(shapeEllipseNameCompleteView.getWidth(), 5);
    assertEquals(shapeEllipseNameCompleteView.getR(), 200);
    assertEquals(shapeEllipseNameCompleteView.getG(), 200);
    assertEquals(shapeEllipseNameCompleteView.getB(), 200);
  }
}
