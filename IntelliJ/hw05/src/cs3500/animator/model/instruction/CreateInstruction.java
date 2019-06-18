package cs3500.animator.model.instruction;

import java.util.List;

import cs3500.animator.model.shape.IViewOnlyShape;
import cs3500.animator.model.shape.ShapeType;

/**
 * Represents an isntruction that creates a new Shape which is a Shapetype. Each shape has an
 * InstructionId which is the name of this instruction and a shapeName which is the name of the
 * shape.
 */
public class CreateInstruction implements Instruction {
  private final String shapeName;
  private final ShapeType type;

  /**
   * Constructs a CreateInstruction with the given parameters.
   */
  public CreateInstruction(String shapeName, ShapeType type) {
    this.shapeName = shapeName;
    this.type = type;
  }

  @Override
  public String printInstruction() {
    return "shape " + this.shapeName + " " + this.type;
  }

  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  @Override
  public boolean timeOverlapsWith(Instruction instruction) {
    return false;
  }

  @Override
  public boolean isAddable(List<String> shapes) {
    for (String s : shapes) {
      if (s.equals(this.shapeName)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean timeOverlapsWithOperationInstruction(OperateInstruction operateInstruction) {
    return false;
  }

  @Override
  public void addCreateShapeName(List<String> listOfShapes) {
    boolean isNew = true;
    for (String s : listOfShapes) {
      if (s.equals(this.shapeName)) {
        isNew = false;
      }
    }
    if (isNew) {
      listOfShapes.add(this.shapeName);
    }
  }

  @Override
  public int getStartTick() {
    return -1;
  }

  @Override
  public int getEndTick() {
    return -1;
  }

  @Override
  public List<IViewOnlyShape> getShapeAtTick(int tick, List<IViewOnlyShape> shapesToReturn,
          List<CreateInstruction> createInstructions) {
    return shapesToReturn;
  }

  @Override
  public List<CreateInstruction> getCreate(List<CreateInstruction> temp) {
    temp.add(this);
    return temp;
  }

  /**
   * Returns the type of the shape of this CreateInstruction.
   */
  public ShapeType getType() {
    return this.type;
  }
}
