/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagefinal;

import com.leapmotion.leap.Controller;
import java.io.IOException;

/**
 *
 * @author cem
 */
public class Main {
    static int direction=0;
    static int hort=0;
    static int vert=0;
    static boolean checked=false;
    static String[] A1={"C:\\Users\\cem\\Pictures\\leap2.jpg","C:\\Users\\cem\\Pictures\\Kiosk.png","C:\\Users\\cem\\Pictures\\Arcade.png","C:\\Users\\cem\\Pictures\\Image.png","C:\\Users\\cem\\Pictures\\kar.jpg","C:\\Users\\cem\\Pictures\\Automate.jpg"};
    static String[] A2={"C:\\Users\\cem\\Pictures\\AppInventor.jpg","C:\\Users\\cem\\Pictures\\Sunu2.png"};
    static String[] A3={"C:\\Users\\cem\\Pictures\\DHT.png"};
    public static int getLength(){
        if(vert==0)
            return A1.length;
        else if(vert==1)
            return A2.length;
        else if(vert==2)
            return A3.length;
        else
            return 0;
    }
    public static int getHort(){
        return hort;
    }
    public static int getVert(){
        return vert;
    }
    public static void main(String avg[]) throws IOException
    {
    Controller controller = new Controller(); //initializes leap
        ImageFinal abc=new ImageFinal();
        SampleListener listener=new SampleListener();
        controller.addListener(listener); //starts leap
        while(true){
            direction=listener.doSwipe(controller);
            if(direction!=0 && !checked){
                System.out.println(direction);
                if(direction==1&&vert>0){
                   vert--; 
                   hort=0;
                }         
                else if(direction==4&&vert<2){
                    vert++;
                    hort=0;
                }
                else if(direction==3&&hort>0)
                    hort--;
                else if(direction==2&&((vert==0&&hort<5)||(vert==1&&hort<1)))
                    hort++;
                System.out.println("hort: "+hort+"vert: "+vert);
                checked=true;
            }
            else if(direction==0){
                checked=false;
            }            
        String image=null;
        if(vert==0){
            image=A1[hort];
        }
        else if(vert==1){
            image=A2[hort];
        }
        else if(vert==2){
            image=A3[hort];
        }
            abc.doFile(image);
        }
    }
}

