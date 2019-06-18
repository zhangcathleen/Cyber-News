package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.model.shape.IViewOnlyShape;

/**
 * This class implements the interface IView and will draw and play the animation inside a window.
 */
public class VisualAnimationView extends JFrame implements IView {
  DrawingPanel panel;
  JScrollPane scrollPane;

  /**
   * Sets up the drawing panel which will be used to display this view.
   */
  public VisualAnimationView() {
    super();
    panel = new DrawingPanel();
    panel.setMinimumSize( new Dimension(500,500));
    panel.setPreferredSize( new Dimension(2000,2000));
    panel.setBackground(Color.yellow);


    scrollPane = new JScrollPane(panel);

    setSize(800,800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(200,200);

    add(scrollPane);

    setVisible(true);
  }

  @Override
  public void render(List<IViewOnlyShape> shapes) {
    panel.draw(shapes);
  }

  @Override
  public void render(String animation) {
    return;
  }

  @Override
  public void render(ArrayList<Instruction> instructions) {
    return;
  }
}
