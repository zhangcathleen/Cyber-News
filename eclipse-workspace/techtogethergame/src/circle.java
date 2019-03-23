
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;



public class circle extends JComponent
{
  private static int myX;               //instance variables to define circle object
  private static int myY;
  private static int myWidth;
  private static int myHeight;
  private static Color myColor;
  
  public circle()
  {
    myX = 100;                    //set values to variables
    myY = 100;
    myWidth = 10;
    myHeight =10;
    myColor = Color.BLACK;
    
  }

  public circle(int inx, int iny, int indiameter,Color color)
  {
    myX = inx;                    //bring in parameters for circle from main
    myY = iny;
    myWidth = indiameter;
    myHeight = indiameter;
    myColor = color;
  
  }

  public void paintComponent(Graphics g)        //draw circle with paint component
  {
  
    Graphics2D g2 = (Graphics2D) g;
    Ellipse2D.Double circle = new Ellipse2D.Double(myX,myY,myWidth,myHeight);
    g2.draw(circle);                //will have black outline for circle
    g2.setColor(myColor);             //changes fill color
    g2.fill(circle);
    
  }
  
  public void setFinal(int indiameter)        //set height and width of circle 
  {
    myHeight = indiameter;
    myWidth = indiameter;
    repaint();                    //repaint with new parameters
  }
  
  public void setNewColor(Color color)        //sets new color to object with color passed over from main
  {
    myColor = color;
    repaint();                    //repaint with new parameters
  }
  

}


