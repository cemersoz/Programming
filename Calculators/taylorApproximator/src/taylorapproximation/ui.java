/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taylorapproximation;

/**
 *
 * @author cem
 */
import java.awt.*;
import javax.swing.*;
public class ui extends JFrame{
   // Constants
   public static final int CANVAS_WIDTH  = 1024;
   public static final int CANVAS_HEIGHT = 768;
 
   // Declare an instance the drawing canvas (extends JPanel)
   private DrawCanvas canvas;
 
   /** Constructor to set up the GUI components */
   public ui() {
      canvas = new DrawCanvas();    // Construct the drawing canvas
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
      // Set the Drawing JPanel as the JFrame's content-pane
      Container cp = getContentPane();
      cp.add(canvas);
      // or
      // setContentPane(canvas);
 
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
      this.pack();              // Either pack() the components; or setSize()
      this.setTitle("......");  // this JFrame sets the title
      this.setVisible(true);    // this JFrame show
   }
 
   /**
    * Define a inner class called DrawCanvas which is a JPanel used for custom drawing
    */
   private class DrawCanvas extends JPanel {
      // Override paintComponent to perform your own painting
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);     // paint parent's background
         setBackground(Color.BLACK);  // set background color for this JPanel
 
         // Your custom painting codes. For example,
         // Drawing primitive shapes
         g.setColor(Color.YELLOW);    // set the drawing color
         g.drawLine(30, 40, 100, 200);
         g.drawOval(150, 180, 10, 10);
         g.drawRect(200, 210, 20, 30);
         g.setColor(Color.RED);       // change the drawing color
         g.fillOval(300, 310, 30, 50);
         g.fillRect(400, 350, 60, 50);
         // Printing texts
         g.setColor(Color.WHITE);
         g.setFont(new Font("Monospaced", Font.PLAIN, 12));
         g.drawString("Testing custom drawing ...", 10, 20);
      }
   }
 
   /** Entry main method */
   public static void main(String[] args) {
      // Run the GUI codes on the Event-Dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new ui(); // Let the constructor do the job
         }
      });
   }
}

