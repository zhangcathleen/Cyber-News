package cs3500.animator.controller;

/**
 * The controller interface for the animator. The functions here have been designed to give
 * control to the controller, and the primary operation for the controller to function (process a
 * turtle command).
 */

public interface IController {

  /**
   * Start the program, i.e. give control to the controller
   */
  void beginAnimation(String outputFile);
}
