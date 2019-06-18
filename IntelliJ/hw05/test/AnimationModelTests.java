import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.instruction.CreateInstruction;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.model.instruction.OperateInstruction;
import cs3500.animator.model.instruction.Operation;
import cs3500.animator.model.instruction.ShapeState;
import cs3500.animator.model.model.AnimationModel;
import cs3500.animator.model.model.AnimationModelImpl;
import cs3500.animator.model.shape.Position2D;
import cs3500.animator.model.shape.ShapeType;

import static org.junit.Assert.assertEquals;

/**
 * Contains tests for all public methods of MarbleSolitaireModelImpl.
 */
public class AnimationModelTests {
  @Test
  public void testAddingInstruction() {
    Instruction i1 = new CreateInstruction("Fred", ShapeType.RECTANGLE);
    Instruction i2 = new OperateInstruction("Fred", 1, 10,
            new ShapeState(new Position2D(20, 20), 5, 10, 255, 0, 0),
            new ShapeState(new Position2D(20, 100), 5, 10, 255, 0, 0), Operation.MOVE);
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(i2);
    instructions.add(i1);
    AnimationModel model = new AnimationModelImpl(instructions);

    assertEquals("# t == tick\n" +
                    "# (x,y) == position\n" +
                    "# (w,h) == dimensions\n" +
                    "# (r,g,b) == color (with values between 0 and 255)\n" +
                    "#                  start                               end\n" +
                    "#        -----------------------------     ----------------------------\n" +
                    "#        t   x   y   w   h   r   g   b     t   x   y   w   h   r   g  b\n" +
                    "shape Fred RECTANGLE\n" +
                    "motion Fred 1 20 20 5 10 255 0 0            10 20 100 5 10 255 0 0\n",
            model.getDirections());
  }

  @Test
  public void testEmptyAminator() {
    AnimationModel model = new AnimationModelImpl();

    assertEquals("# t == tick\n" +
            "# (x,y) == position\n" +
            "# (w,h) == dimensions\n" +
            "# (r,g,b) == color (with values between 0 and 255)\n" +
            "#                  start                               end\n" +
            "#        -----------------------------     ----------------------------\n" +
            "#        t   x   y   w   h   r   g   b     t   x   y   w   h   r   g  b\n",
            model.getDirections());
  }

  @Test
  public void testRemovingInstruction() {
    Instruction i1 = new CreateInstruction( "Fred", ShapeType.RECTANGLE);
    Instruction i2 = new CreateInstruction( "John", ShapeType.ELLIPSE);
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(i1);
    instructions.add(i2);
    AnimationModel model = new AnimationModelImpl(instructions);

    assertEquals("# t == tick\n" +
            "# (x,y) == position\n" +
            "# (w,h) == dimensions\n" +
            "# (r,g,b) == color (with values between 0 and 255)\n" +
            "#                  start                               end\n" +
            "#        -----------------------------     ----------------------------\n" +
            "#        t   x   y   w   h   r   g   b     t   x   y   w   h   r   g  b\n" +
            "#                       start                                   end\n" +
            "#             -----------------------------         ----------------------------\n" +
            "#             t   x   y   w   h   r   g   b         t   x   y   w   h   r   g  b\n" +
            "shape Fred RECTANGLE\n" +
            "shape John ELLIPSE\n", model.getDirections());

    model.removeInstruction("2");

    assertEquals("# t == tick\n" +
            "# (x,y) == position\n" +
            "# (w,h) == dimensions\n" +
            "# (r,g,b) == color (with values between 0 and 255)\n" +
            "#                  start                               end\n" +
            "#        -----------------------------     ----------------------------\n" +
            "#        t   x   y   w   h   r   g   b     t   x   y   w   h   r   g  b\n" +
            "#                       start                                   end\n" +
            "#             -----------------------------         ----------------------------\n" +
            "#             t   x   y   w   h   r   g   b         t   x   y   w   h   r   g  b\n" +
            "shape Fred RECTANGLE\n", model.getDirections());
  }

  @Test
  public void testOverlappingInstructionDifferentTypes() {
    Instruction i1 = new CreateInstruction( "Fred", ShapeType.RECTANGLE);
    Instruction i2 = new OperateInstruction("Fred", 1, 10,
            new ShapeState(new Position2D(20, 20), 5, 10, 255, 0, 0),
            new ShapeState(new Position2D(20, 100), 5, 10, 255, 0, 0), Operation.MOVE
            );
    Instruction i3 = new OperateInstruction("Fred", 1, 10,
            new ShapeState(new Position2D(20, 100), 5, 10, 255, 0, 0),
            new ShapeState(new Position2D(20, 100), 5, 20, 255, 0, 0), Operation.SCALE);
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(i1);
    instructions.add(i2);
    instructions.add(i3);
    AnimationModel model = new AnimationModelImpl(instructions);

    assertEquals("# t == tick\n" +
                    "# (x,y) == position\n" +
                    "# (w,h) == dimensions\n" +
                    "# (r,g,b) == color (with values between 0 and 255)\n" +
                    "#                  start                               end\n" +
                    "#        -----------------------------     ----------------------------\n" +
                    "#        t   x   y   w   h   r   g   b     t   x   y   w   h   r   g  b\n" +
                    "shape Fred RECTANGLE\n" +
                    "motion Fred 1 20 20 5 10 255 0 0            10 20 100 5 10 255 0 0\n" +
                    "motion Fred 1 20 100 5 10 255 0 0            10 20 100 5 20 255 0 0\n",
            model.getDirections());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingInstructionSameTypes() {
    Instruction i1 = new CreateInstruction("Fred", ShapeType.RECTANGLE);
    Instruction i2 = new OperateInstruction("Fred", 1, 10,
            new ShapeState(new Position2D(20, 20), 5, 10, 255, 0, 0),
            new ShapeState(new Position2D(20, 100), 5, 10, 255, 0, 0), Operation.MOVE);
    Instruction i3 = new OperateInstruction("Fred", 1, 10,
            new ShapeState(new Position2D(20, 100), 5, 10, 255, 0, 0),
            new ShapeState(new Position2D(100, 100), 5, 10, 255, 0, 0), Operation.MOVE);
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(i1);
    instructions.add(i2);
    AnimationModel model = new AnimationModelImpl(instructions);
    model.addInstruction(i3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddingOperationToNoShape() {
    Instruction i1 = new CreateInstruction("John", ShapeType.RECTANGLE);
    Instruction i2 = new OperateInstruction("Fred", 1, 10,
            new ShapeState(new Position2D(20, 20), 5, 10, 255, 0, 0),
            new ShapeState(new Position2D(20, 100), 5, 10, 255, 0, 0), Operation.MOVE);
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(i1);
    AnimationModel model = new AnimationModelImpl(instructions);
    model.addInstruction(i2);
  }

  @Test
  public void testPrintLargerModel() {
    Instruction i1 = new CreateInstruction("Fred", ShapeType.RECTANGLE);
    Instruction i2 = new OperateInstruction("John", 1, 10,
            new ShapeState(new Position2D(20, 20), 5, 10, 255, 0, 0),
            new ShapeState(new Position2D(20, 100), 5, 10, 255, 0, 0), Operation.MOVE);
    Instruction i3 = new CreateInstruction("John", ShapeType.ELLIPSE);
    Instruction i4 = new CreateInstruction("Cat", ShapeType.STAR);
    Instruction i5 = new OperateInstruction("Fred", 1, 10,
            new ShapeState(new Position2D(50, 5), 5, 10, 255, 0, 0),
            new ShapeState(new Position2D(50, 5), 50, 10, 255, 0, 0), Operation.SCALE);
    Instruction i6 = new OperateInstruction("John", 1, 10,
            new ShapeState(new Position2D(20, 100), 5, 10, 255, 0, 0),
            new ShapeState(new Position2D(20, 100), 5, 10, 0, 255, 0), Operation.COLOR);
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(i1);
    instructions.add(i2);
    instructions.add(i3);
    instructions.add(i4);
    instructions.add(i5);
    instructions.add(i6);
    AnimationModel model = new AnimationModelImpl(instructions);

    assertEquals("# t == tick\n" +
                    "# (x,y) == position\n" +
                    "# (w,h) == dimensions\n" +
                    "# (r,g,b) == color (with values between 0 and 255)\n" +
                    "#                       start                                   end\n" +
                    "#             -----------------------------         ------------------" +
                    "----------\n" +
                    "#             t   x   y   w   h   r   g   b         t   x   y   w   h" +
                    "   r   g  b\n" +
                    "shape Fred RECTANGLE\n" +
                    "motion Fred      1 50 5 5 10 255 0 0                10 50 5 50 10 255 0 0\n" +
                    "shape John ELLIPSE\n" +
                    "motion John      1 20 20 5 10 255 0 0                10 20 100 5 10 " +
                    "255 0 0\n" +
                    "shape Cat STAR\n" +
                    "motion John      1 20 100 5 10 255 0 0                10 20 100 5 10" +
                    " 0 255 0\n",
            model.getDirections());
  }

}
