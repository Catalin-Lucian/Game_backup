package Worlds_Collide.Input;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class MouseHandler implements MouseInputListener {


    public static int mouseX=-1;
    public static int mouseY=-1;
    public static int mouseB=-1;

    public MouseHandler(){

    }

   /* public int getMouseX(){
        return mouseX;
    }
    public int getMouseY(){
        return mouseY;
    }

    public int getMouseB(){
        return mouseB;
    }*/

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        mouseB=mouseEvent.getButton();  ///  1--stanga /// 2 --migloc // 3-- dreapta
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        mouseB=-1;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseX=mouseEvent.getX();
        mouseY=mouseEvent.getY();
       // System.out.println(mouseX+" "+mouseY);
    }
}
