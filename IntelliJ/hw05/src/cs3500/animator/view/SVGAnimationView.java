package cs3500.animator.view;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.instruction.CreateInstruction;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.model.instruction.OperateInstruction;
import cs3500.animator.model.shape.IViewOnlyShape;
import cs3500.animator.model.shape.ShapeType;

/**
 * This class implements the interface IView and will produce a SVG. It will produce a textual
 * description of the animation that is compliant with the popular SVG file format, an
 * XML-based format that can be used to describe images and animations.
 */
public class SVGAnimationView implements IView {

  @Override
  public void render(List<IViewOnlyShape> shapes) {
    return;
  }

  @Override
  public void render(String animation) {
    String output = "";
    String[] lines = animation.split(System.getProperty("line.separator"));
    String type = "";
    for (String line : lines) {
      String input = line;

      int i = input.indexOf(' ');
      String word = input.substring(0, i);
      String rest = input.substring(i);

      if (word.equals("shape")) {
        int j = rest.indexOf(' ');
        String newRest = input.substring(i);
        j = newRest.indexOf(' ');
        String newWord = newRest.substring(0, i);
        type = newWord;
      }

      if (word.equals("motion")) {
        int j = rest.indexOf(' ');
        String newRest = rest.substring(j + 6);
        output += "<" + type.substring(1) + ">\n    " + newRest + "\n<" + type + ">\n";
      }
    }
    System.out.print(output);
  }

  @Override
  public void render(ArrayList<Instruction> instructions) {
    String output = "<svg>\n";
    String type = "";
    for (Instruction i : instructions) {
      if (i instanceof CreateInstruction) {
        if (((CreateInstruction) i).getType().equals(ShapeType.RECTANGLE)) {
          type = "rect";
        }
        if (((CreateInstruction) i).getType().equals(ShapeType.ELLIPSE)) {
          type = "ellipse";
        }
        if (((CreateInstruction) i).getType().equals(ShapeType.STAR)) {
          type = "star";
        }
      }
      if (i instanceof OperateInstruction) {
        output += "   <" + type + "x=" +
                ((OperateInstruction) i).getStartState().getPosition().getX()
                + " y=" + ((OperateInstruction) i).getStartState().getPosition().getY() + " width="
                + ((OperateInstruction) i).getStartState().getWidth() + " height="
                + ((OperateInstruction) i).getStartState().getHeight() + ">\n";
      }
    }
    output += "</svg>";
    System.out.print(output);
  }
}
