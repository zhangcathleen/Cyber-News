import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Random;

public class Game extends JPanel{

  public static void main(String args[]) {

    JFrame f = new JFrame("Student Debt Game");

    f.setSize(600, 300);

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    f.setTitle("Student Debt Game");

    circle hasan = new circle(50, 170, 30, Color.GRAY);

    f.add(hasan);
    
    f.add(new circle(110, 70, 15, Color.GREEN));
//    addRandomDebt(f, 600, 300, 20);

    f.setVisible(true);
  }
  
//  private static void addRandomDebt(JFrame f, int x, int y, int k) {
//    
//    for (int a = 0 ; a > k ; a++) {
//      f.add(new circle(new Random().nextInt(x), new Random().nextInt(y), 15, Color.GREEN));
//    }
//    
//  }


}