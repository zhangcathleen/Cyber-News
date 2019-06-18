package cs3500.animator.model.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.instruction.CreateInstruction;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.model.instruction.OperateInstruction;
import cs3500.animator.model.instruction.Operation;
import cs3500.animator.model.instruction.ShapeState;
import cs3500.animator.model.shape.IViewOnlyShape;
import cs3500.animator.model.shape.Position2D;
import cs3500.animator.model.shape.ShapeType;
import cs3500.animator.util.AnimationBuilder;

/**
 * This class represents a single animation based on the instructions and implements the
 * AnimationModel. Every object has a list of instructions that should be organized and a list of
 * strings that represent all the shapes in this animation.
 */
public class AnimationModelImpl implements AnimationModel {
  private List<Instruction> instructions;
  private List<String> shapes;
  private List<CreateInstruction> actualShapes;

  /**
   * This constructor represents when there are no instructions passed into the animation model,
   * which means that there are no shapes or movements happening in this model yet.
   */
  public AnimationModelImpl() {
    this.instructions = new ArrayList<>();
    this.shapes = new ArrayList<>();
    this.actualShapes = new ArrayList<>();
  }

  /**
   * This constructor represents the animation model for the given list of instructions.
   */
  public AnimationModelImpl(List<Instruction> instructions) {
    this.instructions = organize(instructions);
    this.shapes = getShapes();
    this.actualShapes = getCreateDirectiosn(instructions);
  }

  /**
   * Returns a list of create instructions with the given list of instructions.
   */
  private List<CreateInstruction> getCreateDirectiosn(List<Instruction> instructions) {
    List<CreateInstruction> temp = new ArrayList<>();
    for (Instruction i : instructions) {
      temp = i.getCreate(temp);
    }
    return temp;
  }

  /**
   * A builder which builds an AnimationModel, allowing users to easily set up and customize the
   * model.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {

    AnimationModel model;

    /**
     * A constructor for the Builder();.
     */
    public Builder() {
      this.model = new AnimationModelImpl();
    }


    /**
     * Constructs a final model.
     */
    @Override
    public AnimationModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      ShapeType type1 = null;
      switch (type) {
        case "rectangle":
          type1 = ShapeType.RECTANGLE;
          break;
        case "ellipse":
          type1 = ShapeType.ELLIPSE;
          break;
        case "star":
          type1 = ShapeType.STAR;
          break;
        default:
          break;
      }
      if (type1 == null) {
        throw new IllegalArgumentException("boo, this isn't a type");
      }
      Instruction instruction = new CreateInstruction(name, type1);
      this.model.addInstruction(instruction);
      return this;
    }


    /**
     * Adds a transformation to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                      int h1, int r1, int g1, int b1, int t2,
                                                      int x2, int y2, int w2, int h2, int r2,
                                                      int g2, int b2) {
      Operation transform = null;
      if ((h1 != h2) || (w1 != w2)) {
        transform = Operation.SCALE;
      } else if ((x1 != x2) || (y1 != y2)) {
        transform = Operation.MOVE;
      } else if ((r1 != r2) || (g1 != g2) || (b1 != b2)) {
        transform = Operation.COLOR;
      } else {
        // nothing because if else, it shouldn't be anything
      }
      ShapeState start = new ShapeState(new Position2D(x1, y1), w1, h1, r1, g1, b1);
      ShapeState end = new ShapeState(new Position2D(x2, y2), w2, h2, r2, g2, b2);
      Instruction instruction = new OperateInstruction(name, t1, t2, start, end, transform);
      this.model.addInstruction(instruction);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w,
                                                        int h, int r, int g, int b) {
      Operation transform = null;
      ShapeState start = new ShapeState(new Position2D(x, y), w, h, r, g, b);
      ShapeState end = new ShapeState(new Position2D(x, y), w, h, r, g, b);
      Instruction instruction = new OperateInstruction(name, t, t, start, end, Operation.MOVE);
      this.model.addInstruction(instruction);
      return this;
    }
  }


  /**
   * Organizes the given instructions by shape and time. Also checks to make sure that the
   * instructions don't have any time overlaps.
   *
   * @param instructions the instructions that need to be organized
   */
  private List<Instruction> organize(List<Instruction> instructions) {
    List<Instruction> result = new ArrayList<>();
    List<Instruction> copy = new ArrayList<>(instructions);
    for (Instruction i : instructions) {
      if (i instanceof CreateInstruction) {
        result.add(i);
        copy.remove(i);
        List<Instruction> temp = new ArrayList<>();
        for (Instruction j : copy) {
          if (j instanceof OperateInstruction && j.getShapeName().equals(i.getShapeName())) {
            temp.add(j);
          }
        }
        if (temp.size() > 0) {
          result.addAll(sortTemp(temp));
        }
      }

    }
    for (Instruction i : instructions) {
      if (!result.contains(i)) {
        result.add(i);
      }
    }
    return result;
  }

  /**
   * This method sorts the list of operate instructions by time for the organize method. Although it
   * says the type is Instruction, the only type there is should be OperateInstruction.
   */
  private List<Instruction> sortTemp(List<Instruction> instructions) {
    List<Instruction> result = new ArrayList<>();
    List<Instruction> temp = new ArrayList<>(instructions);
    for (int i = 0; i < temp.size(); i++) {
      Instruction next = findFirst(temp);
      result.add(next);
      temp.remove(next);
    }
    return result;
  }

  private Instruction findFirst(List<Instruction> instructions) {
    Instruction first = instructions.get(0);
    for (Instruction i : instructions) {
      if (i.getStartTick() < first.getStartTick()) {
        first = i;
      }
    }
    return first;
  }

  /**
   * Gets a list of unique shapes in the instructions.
   */
  private List<String> getShapes() {
    List<String> listOfShapes = new ArrayList<>();
    for (Instruction i : this.instructions) {
      i.addCreateShapeName(listOfShapes);
    }
    return listOfShapes;
  }

  @Override
  public String getDirections() {
    String result = "# t == tick\n" +
            "# (x,y) == position\n" +
            "# (w,h) == dimensions\n" +
            "# (r,g,b) == color (with values between 0 and 255)\n" +
            "#                       start                                   end\n" +
            "#             -----------------------------         ------------" +
            "----------------\n" +
            "#             t   x   y   w   h   r   g   b         t   x   y   w" +
            "   h   r   g  b\n";
    for (Instruction i : this.instructions) {
      result += i.printInstruction() + "\n";
    }
    return result;
  }

  @Override
  public List<Instruction> getInstructions() {
    return this.instructions;
  }

  @Override
  public void addInstruction(Instruction instruction) throws IllegalArgumentException {
    for (Instruction i : this.instructions) {
      if (i.timeOverlapsWith(instruction)) {
        throw new IllegalArgumentException("Operation overlaps with " + i.toString()
                + " time interval/type of operation.");
      } else if (!i.isAddable(this.shapes)) {
        throw new IllegalArgumentException(i.printInstruction() + " cannot be added");
      }
    }
    this.instructions.add(instruction);
    this.instructions = organize(this.instructions);
    addShape(instruction);
  }

  /**
   * Adds the String name of the instruction to the list of shape names.
   */
  private void addShape(Instruction i) {
    if (!this.shapes.contains(i.getShapeName())) {
      shapes.add(i.getShapeName());
    }
  }

  @Override
  public void removeInstruction(String instructionID) {
    for (int i = 0; i < this.instructions.size(); i++) {
      if (this.instructions.get(i).getInstructionID().equals(instructionID)) {
        this.instructions.remove(i);
        break;
      }
    }
  }

  @Override
  public List<IViewOnlyShape> getShapesAtTick(int tick) {
    List<IViewOnlyShape> shapesToReturn = new ArrayList<>();

    for (Instruction i : instructions) {
      shapesToReturn = (i.getShapeAtTick(tick, shapesToReturn, actualShapes));
    }
    return shapesToReturn;
  }
}
