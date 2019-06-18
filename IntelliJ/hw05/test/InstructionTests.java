import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.instruction.CreateInstruction;
import cs3500.animator.model.instruction.Instruction;
import cs3500.animator.model.instruction.OperateInstruction;
import cs3500.animator.model.instruction.Operation;
import cs3500.animator.model.instruction.ShapeState;
import cs3500.animator.model.shape.Position2D;
import cs3500.animator.model.shape.ShapeType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
<<<<<<< HEAD
 * Contain
=======
 * Contains tests for the Instruction constructors and methods.
>>>>>>> 9c525a44256b20baa9247c3750b4f6ede8e76b59
 */
public class InstructionTests {
  private Instruction createRect;
  private CreateInstruction createRect1;
  private Instruction moveRect;
  private OperateInstruction moveRect1;
  private Instruction scaleRect;
  private Instruction colorRect;
  private Instruction createEllipse;
  private CreateInstruction createEllipse1;
  private Instruction scaleEllipse;
  private Instruction moveEllipse;
  private OperateInstruction moveEllipse1;
  private Instruction colorEllipse;
  private ShapeState shapeStateEndMove;
  private ShapeState shapeStateEndScale;
  private ShapeState shapeStateEndColor;
  private List<String> jenList;
  List<String> bobList;

  private void init() {
    ShapeState shapeStateStart = new ShapeState(new Position2D(10, 10), 13, 10, 255, 250, 254);
    shapeStateEndColor = new ShapeState(new Position2D(10, 10), 13, 10, 260, 100, 0);
    shapeStateEndScale = new ShapeState(new Position2D(10, 10), 40, 13, 255, 250, 254);
    shapeStateEndMove = new ShapeState(new Position2D(20, 17), 13, 10, 255, 250, 254);
    createRect = new CreateInstruction("bob", ShapeType.RECTANGLE);
    createEllipse = new CreateInstruction( "jen", ShapeType.ELLIPSE);

    createRect1 = new CreateInstruction( "bob", ShapeType.RECTANGLE);
    createEllipse1 = new CreateInstruction("jen", ShapeType.ELLIPSE);
    moveRect = new OperateInstruction("bob", 1, 7, shapeStateStart,
            shapeStateEndMove, Operation.MOVE);
    scaleRect = new OperateInstruction("bob", 2, 8, shapeStateStart,
            shapeStateEndScale, Operation.SCALE);
    colorRect = new OperateInstruction( "bob", 3, 9, shapeStateStart,
            shapeStateEndColor, Operation.COLOR);
    moveEllipse = new OperateInstruction("jen", 4, 10, shapeStateStart,
            shapeStateEndMove, Operation.MOVE);
    scaleEllipse = new OperateInstruction( "jen", 5, 11, shapeStateStart,
            shapeStateEndScale, Operation.SCALE);
    colorEllipse = new OperateInstruction( "jen", 6, 12, shapeStateStart,
            shapeStateEndColor, Operation.COLOR);

    moveEllipse1 = new OperateInstruction( "jen", 1, 7, shapeStateStart,
            shapeStateEndMove, Operation.MOVE);
    moveRect1 = new OperateInstruction( "bob", 1, 7, shapeStateStart,
            shapeStateEndMove, Operation.MOVE);
    jenList = new ArrayList<>();
    jenList.add("jen");
    bobList = new ArrayList<>();
    bobList.add("bob");
  }

  @Test
  public void testPrintInstruction() {
    init();
    assertEquals("shape jen ELLIPSE", createEllipse.printInstruction());
    assertEquals("shape bob RECTANGLE", createRect.printInstruction());
    assertEquals("motion jen 6 10 10 13 10 255 250 254            12 10 10 13 10 260 100 0",
            colorEllipse.printInstruction());
    assertEquals("motion bob 2 10 10 13 10 255 250 254            8 10 10 40 13 255 250 254",
            scaleRect.printInstruction());
  }

  @Test
  public void testGetShapeName() {
    init();
    assertEquals("bob", createRect.getShapeName());
    assertEquals("bob", moveRect.getShapeName());
    assertEquals("bob", scaleRect.getShapeName());
    assertEquals("bob", colorRect.getShapeName());
    assertEquals("jen", createEllipse.getShapeName());
    assertEquals("jen", moveEllipse.getShapeName());
    assertEquals("jen", colorEllipse.getShapeName());
    assertEquals("jen", scaleEllipse.getShapeName());
  }

  @Test
  public void testTimeOverlapsWith() {
    init();
    assertFalse(createRect.timeOverlapsWith(moveEllipse1));
    assertFalse(createRect.timeOverlapsWith(moveRect1));
    assertFalse(createEllipse.timeOverlapsWith(moveEllipse1));
    assertFalse(createEllipse.timeOverlapsWith(moveRect1));

    assertTrue(moveRect.timeOverlapsWith(moveRect1));
    assertFalse(moveRect.timeOverlapsWith(moveEllipse1));
    assertFalse(moveRect.timeOverlapsWith(createRect));
    assertFalse(createEllipse.timeOverlapsWith(createEllipse));
  }

  @Test
  public void testIsAddable() {
    init();
    assertTrue(moveEllipse.isAddable(jenList));
    assertFalse(moveEllipse.isAddable(bobList));
    assertFalse(colorRect.isAddable(jenList));
    assertTrue(colorRect.isAddable(bobList));

    assertTrue(createEllipse.isAddable(bobList));
    assertFalse(createEllipse.isAddable(jenList));
    assertFalse(createRect.isAddable(bobList));
    assertTrue(createRect.isAddable(jenList));
  }

  @Test
  public void testTimeOverlapsWithOperationInstruction() {
    init();
    assertFalse(createRect.timeOverlapsWithOperationInstruction(moveEllipse1));
    assertFalse(createRect.timeOverlapsWithOperationInstruction(moveRect1));
    assertFalse(createEllipse.timeOverlapsWithOperationInstruction(moveEllipse1));
    assertFalse(createEllipse.timeOverlapsWithOperationInstruction(moveRect1));

    assertTrue(moveRect.timeOverlapsWithOperationInstruction(moveRect1));
    assertFalse(moveRect.timeOverlapsWithOperationInstruction(moveEllipse1));
  }

  @Test
  public void testAddCreateShapeName() {
    init();
    assertEquals(1, jenList.size());
    moveRect.addCreateShapeName(jenList);
    assertEquals(1, jenList.size());
    moveRect.addCreateShapeName(bobList);
    assertEquals(1, bobList.size());
    createRect.addCreateShapeName(jenList);
    assertEquals(2, jenList.size());
    scaleRect.addCreateShapeName(jenList);
    assertEquals(2, jenList.size());
    createEllipse.addCreateShapeName(jenList);
    assertEquals(2, jenList.size());

    assertEquals(1, bobList.size());
    createRect.addCreateShapeName(bobList);
    assertEquals(1, bobList.size());
    createEllipse.addCreateShapeName(bobList);
    assertEquals(2, bobList.size());
  }

  @Test
  public void testGetStartTick() {
    init();
    assertEquals(createEllipse.getStartTick(), -1);
    assertEquals(createRect.getStartTick(), -1);
    assertEquals(moveRect.getStartTick(), 1);
    assertEquals(scaleRect.getStartTick(), 2);
    assertEquals(colorRect.getStartTick(), 3);
    assertEquals(moveEllipse.getStartTick(), 4);
    assertEquals(scaleEllipse.getStartTick(), 5);
    assertEquals(colorEllipse.getStartTick(), 6);
  }

  @Test
  public void testGetEndTick() {
    init();
    assertEquals(createEllipse.getEndTick(), -1);
    assertEquals(createRect.getEndTick(), -1);
    assertEquals(moveRect.getEndTick(), 7);
    assertEquals(scaleRect.getEndTick(), 8);
    assertEquals(colorRect.getEndTick(), 9);
    assertEquals(moveEllipse.getEndTick(), 10);
    assertEquals(scaleEllipse.getEndTick(), 11);
    assertEquals(colorEllipse.getEndTick(), 12);
  }

  @Test
  public void testGetOperation() {
    init();
    assertEquals(Operation.MOVE, moveRect1.getOperation());
    assertEquals(Operation.MOVE, moveEllipse1.getOperation());
  }

  @Test
  public void testGetShapeType() {
    init();
    assertEquals(ShapeType.RECTANGLE, createRect1.getType());
    assertEquals(ShapeType.ELLIPSE, createEllipse1.getType());
  }

  @Test
  public void testShapeState() {
    init();
    assertEquals(shapeStateEndColor.printState(), "10 10 13 10 260 100 0");
    assertEquals(shapeStateEndScale.printState(), "10 10 40 13 255 250 254");
    assertEquals(shapeStateEndMove.printState(), "20 17 13 10 255 250 254");
  }
}
