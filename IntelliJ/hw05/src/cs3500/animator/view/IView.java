package cs3500.animator.view;

import java.util.List;
import java.util.ArrayList;

import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.model.shape.IViewOnlyShape;

/**
 * The view interface which has one method. Each object is a different view.
 */
public interface IView {

  /**
   * Renders the given list of IViewOnlyShapes and displays the output depending on what child
   * class it is.
   */
  void render(List<IViewOnlyShape> shapes);

  void render(String animation);

  void render(ArrayList<Instruction> instructions);
}
