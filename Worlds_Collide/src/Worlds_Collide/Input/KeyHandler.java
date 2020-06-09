package Worlds_Collide.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyHandler implements KeyListener {

    public static ArrayList<Key> keys=new ArrayList<>();

    public  class Key {
        public int presses ;
        public boolean down, clicked;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean pressed){
            if (pressed != down){
                down=pressed;
            }
            if (pressed) presses++;
            else presses=0;

            if(down && presses==1) clicked=true;
            else clicked=false;


        }
    }

    public Key K_up=new Key();
    public  Key K_down=new Key();
    public  Key K_right=new Key();
    public  Key K_left=new Key();
    public  Key K_space =new Key();
    public  Key K_shift =new Key();
    public  Key K_enter=new Key();
    public  Key K_escape=new Key();
    public  Key K_q=new Key();

    public KeyHandler(){
    }

    public void toggle(KeyEvent e,boolean pressed){
        if (e.getKeyCode()==KeyEvent.VK_A) K_left.toggle(pressed);
        if (e.getKeyCode()==KeyEvent.VK_D) K_right.toggle(pressed);
        if (e.getKeyCode()==KeyEvent.VK_S) K_down.toggle(pressed);
        if (e.getKeyCode()==KeyEvent.VK_W) K_up.toggle(pressed);
        if (e.getKeyCode()==KeyEvent.VK_ENTER) K_enter.toggle(pressed);
        if (e.getKeyCode()==KeyEvent.VK_ESCAPE) K_escape.toggle(pressed);
        if (e.getKeyCode()==KeyEvent.VK_SPACE) K_space.toggle(pressed);
        if (e.getKeyCode()==KeyEvent.VK_SHIFT) K_shift.toggle(pressed);
        if (e.getKeyCode()==KeyEvent.VK_Q) K_q.toggle(pressed);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        toggle(keyEvent,true);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        toggle(keyEvent,false);
    }
}
