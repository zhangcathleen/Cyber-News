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
import cs3500.animator.view.SVGAnimationView;

/**
 * Contains tests for the TextAnimationView.
 */
public class SVGViewTests {

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
    Instruction i3 = new OperateInstruction("3", "Fred", 10, 30,
            new ShapeState(new Position2D(20, 100), 5, 10, 255, 0, 0),
            new ShapeState(new Position2D(20, 100), 5, 10, 0, 255, 0), Operation.COLOR
    );
    List<Instruction> instructions = new ArrayList<>();
    instructions.add(i1);
    instructions.add(i2);
    instructions.add(i3);
    AnimationModel model = new AnimationModelImpl(instructions);
    IView view = new SVGAnimationView();
    IController controller = new AnimatorController(model, view, 10);
    controller.beginAnimation("file");
    assertEquals("<svg>\n" +
                    "   <rectx=20 y=20 width=5 height=10>\n" +
                    "   <rectx=20 y=100 width=5 height=10>\n" +
                    "</svg>",
            outContent.toString());
  }
}
