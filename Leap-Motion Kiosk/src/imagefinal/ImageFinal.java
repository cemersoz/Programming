/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagefinal;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author cem
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class ImageFinal {
    JLabel lbl;
    JFrame frame;
    public ImageFinal() throws IOException
    {
        frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(1200,900);
        lbl=new JLabel();       
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void doFile(String image) throws IOException{
        BufferedImage img=ImageIO.read(new File(image));
        Graphics graphics = img.createGraphics();
        Graphics2D g2 = (Graphics2D) graphics;
        for(int i=0;i<Main.getLength();i++){
            if(i!=Main.getHort()){
                g2.setStroke(new BasicStroke(10));
                g2.setColor(Color.GRAY);
                g2.drawOval(600-Main.getLength()*25+i*50,840,10,10);
            }
            else{
                g2.setColor(Color.LIGHT_GRAY);
                g2.setStroke(new BasicStroke(15));
                g2.drawOval(600-Main.getLength()*25+i*50,840,15,15);
            }
        }
        for(int i=0;i<3;i++){
            if(i!=Main.getVert()){
                g2.setStroke(new BasicStroke(10));
                g2.setColor(Color.GRAY);
                g2.drawOval(1160,375+i*50,10,10);
            }
            else{
                g2.setColor(Color.LIGHT_GRAY);
                g2.setStroke(new BasicStroke(15));
                g2.drawOval(1160,375+i*50,15,15);
            }
        }
        //g2.drawOval(300, 300, 200, 200);
        //g2.drawString("Temperature: 6C", 500, 500);
        ImageIcon icon=new ImageIcon(img);
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
    }
}