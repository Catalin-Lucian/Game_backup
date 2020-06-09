package Worlds_Collide.GUI;

import Worlds_Collide.Graphics.Assets;
import Worlds_Collide.Input.MouseHandler;
import Worlds_Collide.__Utils.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

///contains a list of check boxes
public class Checkers {

    protected ArrayList<Vector2D> pos= new ArrayList<>();///< position of a check box
    protected ArrayList<Integer> data =new ArrayList<>();///<data of check box
    protected int sel;///< witch is selected

    protected BufferedImage[] images =new BufferedImage[2];///< select and unselect image
    protected float width;
    protected float height;

    ///create an check list with first selection
    public Checkers(int defaultSelect){
        images[0]= Assets.check_button.getTile(2);
        images[1]=Assets.check_button.getTile(1);
        width=images[0].getWidth();
        height=images[0].getHeight();
        sel=defaultSelect;
    }
    /// add in the list of boxes
    public void add(Vector2D poz, int d){
        pos.add(poz);
        data.add(d);
    }

    ///update to see if selected
    public void update() {
        for (int i=0;i<pos.size();++i){
            if(pos.get(i).underMouse(width,height) && MouseHandler.mouseB==1) sel=i;
        }
    }

    ///draw all boxes
    public void draw(Graphics g) {
        for(Vector2D e: pos){
            g.drawImage(images[0],(int)e.getX(),(int)e.getY(),null);
        }
        g.drawImage(images[1],(int)pos.get(sel).getX(),(int)pos.get(sel).getY(),null);
    }

    ///return type of check (data)
    public int getData(){

        return data.get(sel);
    }
}
