package Worlds_Collide.GUI;

import Worlds_Collide.Input.MouseHandler;
import Worlds_Collide.__Utils.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MButton {
    protected Vector2D position;
    protected BufferedImage[] images =new BufferedImage[2];
    protected short sel=0; // 0 - unselected , 1 - selected
    protected float width;
    protected float height;
    protected BType BType;

    public MButton(float x, float y, BufferedImage normal, BufferedImage selected, BType BType){
        position=new Vector2D(x,y);
        images[0]=normal;
        images[1]=selected;
        width=normal.getWidth();
        height=normal.getHeight();
        this.BType = BType;
    }

    public void update(){
        if (position.underMouse(width,height)) sel=1;
        else    sel=0;
    }

    public void draw(Graphics g) {
        g.drawImage(images[sel],(int)position.getX(),(int)position.getY(),null);
    }

    public boolean clicked(){
        return sel == 1 && MouseHandler.mouseB == 1;
    }

    public BType getBType(){
        return BType;
    }


}
