package cs3500.animator.model.instruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.animator.model.shape.Ellipse;
import cs3500.animator.model.shape.IShape;
import cs3500.animator.model.shape.IViewOnlyShape;
import cs3500.animator.model.shape.Position2D;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.ShapeType;
import cs3500.animator.model.shape.Star;

/**
 * This class represents an instruction that operates on the  and extends the InstructionShape
 * interface. Each object will represent a different Instruction. * It will either be declaring a
 * new Shape with the unique name or describing the motion of a * unique shape between two moments
 * of animation with the tick, position (x, y), dimensions (w, * h), and color (r, g, b).
 */
public class OperateInstruction implements Instruction {

  private final String shapeName;
  private final int startTick;
  private final int endTick;
  private final ShapeState startState;
  private final ShapeState endState;
  private final Operation operation;

  /**
   * Constructs an OperateInstruction with the given parameters.
   */
  public OperateInstruction(String shapeName, int startTick, int endTick,
          ShapeState startState, ShapeState endState, Operation operation) {
    this.shapeName = shapeName;
    this.startTick = startTick;
    this.endTick = endTick;
    this.startState = startState;
    this.endState = endState;
    this.operation = operation;
  }

  @Override
  public String printInstruction() {
    return "motion " + this.shapeName + "      " + this.startTick + " " +
            this.startState.printState() + "                " + this.endTick + " " +
            this.endState.printState();
  }

  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  @Override
  public boolean timeOverlapsWith(Instruction instruction) {
    return instruction.timeOverlapsWithOperationInstruction(this);
  }

  /**
   * Returns the starting Tick of this OperateInstruction.
   */
  public int getStartTick() {
    return this.startTick;
  }

  /**
   * Returns the end Tick of this OperateInstruction.
   */
  public int getEndTick() {
    return this.endTick;
  }

  @Override
  public List<IViewOnlyShape> getShapeAtTick(int tick, List<IViewOnlyShape> shapesToReturn,
          List<CreateInstruction> createInstructions) {
    switch (operation) {
      case MOVE:
      case COLOR:
      case SCALE:
      default:
        System.out.print("unrecognized operation: " + operation);
    }

    ShapeType shapeType = null;
    for (CreateInstruction c : createInstructions) {
      IShape shape = null;
      if (c.getShapeName().equals(shapeName)) {
        shapeType = c.getType();
      }
      if (shapeType == null) {
        throw new IllegalArgumentException("this shape hasn't been created yet!");
      }
      switch (shapeType) {
        case RECTANGLE:
          shape = new Rectangle(this.shapeName, getNewPosition(tick),
                  getNewDimensions(tick).getX(), getNewDimensions(tick).getY(),
                  getNewColor(tick).get(0), getNewColor(tick).get(1), getNewColor(tick).get(2));
          break;
        case STAR:
          shape = new Star(this.shapeName, getNewPosition(tick), getNewDimensions(tick).getX(),
                  getNewDimensions(tick).getY(), getNewColor(tick).get(0), getNewColor(tick).get(1),
                  getNewColor(tick).get(2));
          break;
        case ELLIPSE:
          shape = new Ellipse(this.shapeName, getNewPosition(tick), getNewDimensions(tick).getX(),
                  getNewDimensions(tick).getY(), getNewColor(tick).get(0), getNewColor(tick).get(1),
                  getNewColor(tick).get(2));
          break;
        default:
          throw new IllegalStateException("unrecognized shape type: " + shapeType);
      }
      shape.move(getNewPosition(tick));
      shapesToReturn.add(shape);
    }
    return shapesToReturn;
  }

  private Position2D getNewPosition(int tick) {
    if (tick < this.endTick) {
      double frac1 = (double) (this.endTick - tick) / (this.endTick - this.startTick);
      double frac2 = (double) (tick - this.startTick) / (this.endTick - this.startTick);

      int newX = (int)
              (startState.getPosition().getX() * frac1 + endState.getPosition().getX() * frac2);
      int newY = (int)
              (startState.getPosition().getY() * frac1 + endState.getPosition().getY() * frac2);
      return new Position2D(newX, newY);
    }
    return null;
  }

  private Position2D getNewDimensions(int tick) {
    if (tick < this.endTick) {
      double frac1 = (double) (this.endTick - tick) / (this.endTick - this.startTick);
      double frac2 = (double) (tick - this.startTick) / (this.endTick - this.startTick);

      int newW = (int)
              (startState.getWidth() * frac1 + endState.getWidth() * frac2);
      int newH = (int)
              (startState.getHeight() * frac1 + endState.getHeight() * frac2);
      return new Position2D(newW, newH);
    }
    return null;
  }

  private List<Integer> getNewColor(int tick) {
    if (tick < this.endTick) {
      double frac1 = (double) (this.endTick - tick) / (this.endTick - this.startTick);
      double frac2 = (double) (tick - this.startTick) / (this.endTick - this.startTick);

      int newR = (int)
              (startState.getR() * frac1 + endState.getR() * frac2);
      int newG = (int)
              (startState.getG() * frac1 + endState.getG() * frac2);
      int newB = (int)
              (startState.getB() * frac1 + endState.getB() * frac2);
      return new ArrayList<>(Arrays.asList(newR, newG, newB));
    }
    return null;
  }

  @Override
  public List<CreateInstruction> getCreate(List<CreateInstruction> temp) {
    return temp;
  }

  /**
   * Returns the operation, which is a type of operation that the OperateInstruction can do.
   */
  public Operation getOperation() {
    return this.operation;
  }

  @Override
  public boolean isAddable(List<String> shapes) {
    for (String s : shapes) {
      if (s.equals(this.shapeName)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean timeOverlapsWithOperationInstruction(OperateInstruction operateInstruction) {
    return this.operation.equals(operateInstruction.getOperation())
            && (this.startTick < operateInstruction.getEndTick()
            || operateInstruction.getStartTick() < this.endTick)
            && this.shapeName.equals(operateInstruction.getShapeName());
  }

  @Override
  public void addCreateShapeName(List<String> listOfShapes) {
    // nothing happens because this method is only for CreateInstruction to add the string name
    // into the listOfShapes if it is new.
  }

  public ShapeState getStartState() {
    return this.startState;
  }

  public ShapeState getEndState() {
    return this.endState;
  }
}
