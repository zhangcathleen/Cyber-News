package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;

import cs3500.animator.model.shape.IViewOnlyShape;

/**
 * An object which knows how to display itself on the screen, used for drawing our animation.
 */
public class DrawingPanel extends JPanel implements IDrawingPanel {
  List<IViewOnlyShape> shapes = null;

  public DrawingPanel() {
      super();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if ( shapes != null ) {
      g.setColor( Color.pink );
      for ( IViewOnlyShape shape : shapes ) {
        g.fillRect(shape.getPosition().getX(), shape.getPosition().getY(),shape.getWidth(),
                shape.getHeight());
      }
    }
  }

  @Override
  public void draw(List<IViewOnlyShape> shapes) {
    this.shapes = shapes;
    repaint();
  }

}
