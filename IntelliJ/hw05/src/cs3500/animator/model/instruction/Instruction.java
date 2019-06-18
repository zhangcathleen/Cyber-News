package cs3500.animator.model.instruction;

import java.util.List;

import cs3500.animator.model.shape.IViewOnlyShape;

/**
 * This interface represents the Instruction. Each object will represent a different Instruction. It
 * will either be declaring a new Shape with the unique name or describing the motion of a unique
 * shape between two moments of animation with the tick, position (x, y), dimensions (w, h), and
 * color (r, g, b).
 */
public interface Instruction {
  /**
   * Returns a String in the correct format to be printed to the console.
   */
  String printInstruction();

  /**
   * Returns the name of the unique shape for the Instruction.
   */
  String getShapeName();

  /**
   * Returns false if it is a CreateInstruction because it cannot overlap since it does not have a
   * time interval. Returns true if this instruction's time intervals overlap with the given
   * instruction's time intervals. Returns true if it is the same type of operation. It can overlap
   * if they are different types of operations.
   */
  boolean timeOverlapsWith(Instruction instruction);

  /**
   * Returns true if this instruction is addable based on the given list of iShapes. If this
   * instruction is a CreateInstruction, then there cannot be another shape in the list that has the
   * same shapeID. If this instruction is an OperateInstruction, there has to be another shape in
   * the list with the same shapeID.
   */
  boolean isAddable(List<String> shapes);

  /**
   * Returns false if it a CreateInstruction because it cannot overlap since it does not have a time
   * interval. Returns true if this instruction's time intervals overlaps with the given
   * instruction's time intervals. Returns true if it is the same type of operation. It can overlap
   * if they are different types of operations.
   */
  boolean timeOverlapsWithOperationInstruction(OperateInstruction operateInstruction);

  /**
   * Adds this shape name to the given listOfShapes if it is a CreateInstruction. Otherwise, nothing
   * happens.
   */
  void addCreateShapeName(List<String> listOfShapes);

  /**
   * Returns the starting tick for the OperateInstruction, but returns -1 for CreateInstruction
   * because there shouldn't be any CreateInstruction for this. This is for the sortTemp method in
   * AnimationModelImpl.
   */
  int getStartTick();

  /**
   * Returns the ending tick for OperateInstruction, but returns -1 for CreateInstruction.
   */
  int getEndTick();

  /**
   * Returns the given list with the given instruction added on if it is within the tick.
   *
   * @param tick           the given time that the shape needs to be a part of
   * @param shapesToReturn the list of shapes that should already be returned
   */
  List<IViewOnlyShape> getShapeAtTick(int tick, List<IViewOnlyShape> shapesToReturn,
                                      List<CreateInstruction> createInstructions);

  /**
   * Returns a list of create instruction by adding this instruction onto the given list of create
   * instructions if it is an instruction. Helps with creating the list of create instructions in
   * the animation model impl.
   */
  List<CreateInstruction> getCreate(List<CreateInstruction> temp);
}
