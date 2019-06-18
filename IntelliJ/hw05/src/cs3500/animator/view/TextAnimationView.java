package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.model.shape.IViewOnlyShape;

/**
 * This class implements the interface IView and will show a textual representation of the
 * animation.
 */
public class TextAnimationView implements IView {

  @Override
  public void render(List<IViewOnlyShape> shapes) {
    return;
  }

  @Override
  public void render(String animation) {
    System.out.print(animation);
  }

  @Override
  public void render(ArrayList<Instruction> instructions) {
    return;
  }
}
