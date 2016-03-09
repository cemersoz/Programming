/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagefinal;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import static com.leapmotion.leap.Gesture.Type.TYPE_SWIPE;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.SwipeGesture;
import com.leapmotion.leap.Vector;

/**
 *
 * @author cem
 */
public class SampleListener extends Listener{
    public int appY_2, appY_1;
    int direct=0;
    public int vert=0;
    public int hort=0;
    public void onInit(Controller controller) {
        System.out.println("Initialized");
    }
    public void onConnect(Controller controller) {
        System.out.println("Connected"); //to indicate whether the leap motion connection is successful
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE); //to accept circle gesture
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
    }
    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }
    public void onExit(Controller controller) {
        System.out.println("Exited");
    }
    public int doSwipe(Controller controller) { //to get the most recent frame and information
        Frame frame = controller.frame(); 
        GestureList gestures = frame.gestures();
        direct=0;
        for(Gesture gesture : frame.gestures()){
            if (gesture.type()==TYPE_SWIPE) {
                SwipeGesture swipe = new SwipeGesture(gesture);
                Vector swipeDirection = swipe.direction();
                float dirX = swipeDirection.get(0)*10;
                float dirY = swipeDirection.get(1)*10;
                if(dirY<0 && Math.abs(dirY)>Math.abs(dirX)){
                    direct=1; //down
                    //System.out.println("down");
                }
                else if(dirX<0 && Math.abs(dirX)>Math.abs(dirY)){
                    direct=2; //left
                    //System.out.println("left");
                }
                else if(dirX>0 && Math.abs(dirX)>Math.abs(dirY)){
                    direct=3; //right
                    //System.out.println("right");
                }
                else if(dirY>0 && Math.abs(dirY)>Math.abs(dirX)){
                    direct=4; //up
                    //System.out.println("up");
                }
                else{
                    direct=0;
                }
            }
        }
        return direct;
    }
}
