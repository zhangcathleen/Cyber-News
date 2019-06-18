package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.shape.IViewOnlyShape;

/**
 * The interface for a drawing panel, which allows user to display objects on the screen with Swing.
 */
public interface IDrawingPanel {
  void draw(List<IViewOnlyShape> shapes);
}
