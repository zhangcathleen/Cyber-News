package cs3500.animator;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.model.AnimationModel;
import cs3500.animator.model.model.AnimationModelImpl;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.animator.view.TextAnimationView;
import cs3500.animator.view.VisualAnimationView;

/**
 * The main class for our program. This class accepts user input, and sets up a controller with a
 * view and model based on that input.
 */
public final class Excellence {
  /**
   * The main method which can be run by a user or from the command line.
   * @param args arguments representing the type of view to use, the input and output locations,
   *             and the speed of the animation.
   */
  public static void main(String[] args) {
    String sourceFile = null;
    IView view = null;
    String outputFile = "System.out";
    int speed = 1;
    AnimationModel model = new AnimationModelImpl();
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("in")) {
        sourceFile = args[i + 1];
      }
      if (args[i].equals("view")) {
        if (args[i + 1].equals("text")) {
          view = new TextAnimationView();
        }
        else if (args[i + 1].equals("visual")) {
          view = new VisualAnimationView();
        }
        else if (args[i + 1].equals("svg")) {
          view = new SVGAnimationView();
        }
        else {
          throw new IllegalArgumentException("not a valid view type");
        }
      }
      if (args[i].equals("out")) {
        outputFile = args[i + 1];
      }
      if (args[i].equals("speed")) {
        speed = Integer.parseInt(args[i + 1]);
      }
    }
    if (sourceFile == null) {
      throw new IllegalArgumentException("must have an input file");
    }
    if (view == null) {
      throw new IllegalArgumentException("must have a view");
    }
    IController controller = new AnimatorController(model, view, speed);
    controller.beginAnimation(outputFile);
  }
}


