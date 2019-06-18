package cs3500.animator.controller;

import cs3500.animator.model.model.AnimationModel;
import cs3500.animator.model.shape.IViewOnlyShape;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextAnimationView;
import cs3500.animator.view.VisualAnimationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

/**
 * Our implementation of the controller for the animator. When supplied a view and a model
 * it will supply the view with information about the model and request that information from
 * the model, not letting them interact directly.
 */
public class AnimatorController implements IController {

  private AnimationModel model;
  private IView view;
  private Timer timer;
  private int tick = 1;

  /**
   * This is the constructor for the animator controller. It takes in an AnimationModel and and
   * IView, which will run the given animation model depending on what type of view is given.
   *
   * @param model the given animation that is to be run
   * @param view  what the output should be
   */
  public AnimatorController(AnimationModel model, IView view, int speed) {
    this.model = model;
    this.view = view;

    if (!(view instanceof TextAnimationView)) {
      timer = new Timer(10000 / speed, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          List<IViewOnlyShape> shapesToRender = model.getShapesAtTick(tick++);
          view.render(shapesToRender);
        }
      });
    }
  }

  @Override
  public void beginAnimation(String outputFile) {
    if (view instanceof VisualAnimationView) {
      timer.start();
    } else if (view instanceof TextAnimationView) {
      this.view.render(this.model.getDirections());
    }
    else {
      this.view.render(new ArrayList<>(this.model.getInstructions()));
    }
  }
}
