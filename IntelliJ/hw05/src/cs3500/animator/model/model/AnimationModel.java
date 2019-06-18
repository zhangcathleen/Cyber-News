package cs3500.animator.model.model;

import java.util.List;

import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.model.shape.IViewOnlyShape;

/**
 * This interface represents an AnimationModel. Every object represents an animation.
 */
public interface AnimationModel {

  /**
   * This returns the directions given to the Animation in the constructor. The Directions will
   * declare the shapes the given names and have the tick, position (x, y), dimensions (w, h), and
   * color (r, g, b). Reveals the inside of our model, and allows the user to see an entire outline.
   *
   * @return String directions
   */
  String getDirections();

  /**
   * This returns the instruction given to the Animation in the constructor. The Instructions will
   * declare the shapes the given names and have the tick, position (x, y), dimensions (w, h), and
   * color (r, g, b). Reveals the inside of our model, and allows the user to see an entire outline.
   *
   * @return String instructions
   */
  List<Instruction> getInstructions();

  /**
   * Adds an instruction to the animation model. If it's an operation, it needs to check to see if
   * there exists a that shape before adding it. If it's to create a new object, it needs to check
   * to see if the name is unique before it can be added.
   *
   * @param instruction the instruction to be added to this animation
   */
  void addInstruction(Instruction instruction);

  /**
   * Returns the specific instruction that was removed from the animation model, if it exists.
   *
   * @param instructionName the instruction to be removed from the animation
   * @throws IllegalArgumentException if the given instruction is not a part of the model
   */
  void removeInstruction(String instructionName) throws IllegalArgumentException;

  /**
   * Returns a List of shapes (that can only be viewed) at the given tick.
   * We were told we would be given a method implementing this, so we did not
   * implement it ourselves, but we left it in the interface as something we know we will need.
   * @param tick the given time that we want the state of
   */
  List<IViewOnlyShape> getShapesAtTick(int tick);
}
