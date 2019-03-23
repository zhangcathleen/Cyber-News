import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class Game extends JPanel{

  public static void main(String args[]) {

    JFrame f = new JFrame("Student Debt Game");

    f.setSize(600, 300);

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    f.setTitle("Student Debt Game");

    circle hasan = new circle(50, 170, 30, Color.GRAY);

    f.add(hasan);

    f.setVisible(true);
  }

}