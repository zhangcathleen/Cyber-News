import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.instruction.CreateInstruction;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.model.instruction.OperateInstruction;
import cs3500.animator.model.instruction.Operation;
import cs3500.animator.model.instruction.ShapeState;
import cs3500.animator.model.model.AnimationModel;
import cs3500.animator.model.model.AnimationModelImpl;
import cs3500.animator.model.shape.Position2D;
import cs3500.animator.model.shape.ShapeType;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextAnimationView;

/**
 * Contains tests for the TextAnimationView.
 */
public class TextViewTests {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void testAddingInstruction() {
    Instruction i1 = new CreateInstruction("1", "Fred", ShapeType.RECTANGLE);
    Instruction i2 = new OperateInstruction("2", "Fred", 1, 10,
            new ShapeState(new Position2D(20, 20), 5, 10, 255, 0, 0),
            new ShapeState(new Position2D(20, 100), 5, 10, 255, 0, 0), Operation.MOVE
            );
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(i1);
    instructions.add(i2);
    AnimationModel model = new AnimationModelImpl(instructions);
    IView view = new TextAnimationView();
    IController controller = new AnimatorController(model, view, 10);
    controller.beginAnimation("file");
    assertEquals("# t == tick\n" +
            "# (x,y) == position\n" +
            "# (w,h) == dimensions\n" +
            "# (r,g,b) == color (with values between 0 and 255)\n" +
            "#                       start                                   end\n" +
            "#             -----------------------------         ----------------------------\n" +
            "#             t   x   y   w   h   r   g   b         t   x   y   w   h   r   g  b\n" +
            "shape Fred RECTANGLE\n" +
            "motion Fred      1 20 20 5 10 255 0 0                10 20 100 5 10 255 0 0\n",
            outContent.toString());
  }
}
