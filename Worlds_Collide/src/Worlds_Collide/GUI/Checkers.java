package Worlds_Collide.GUI;

import Worlds_Collide.Graphics.Assets;
import Worlds_Collide.Input.MouseHandler;
import Worlds_Collide.__Utils.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Checkers {

    protected ArrayList<Vector2D> pos= new ArrayList<>();
    protected ArrayList<Integer> data =new ArrayList<>();
    protected int sel;

    protected BufferedImage[] images =new BufferedImage[2];
    protected float width;
    protected float height;


    public Checkers(int defaultSelect){
        images[0]= Assets.check_button.getTile(2);
        images[1]=Assets.check_button.getTile(1);
        width=images[0].getWidth();
        height=images[0].getHeight();
        sel=defaultSelect;
    }

    public void add(Vector2D poz, int d){
        pos.add(poz);
        data.add(d);
    }

    public void update() {
        for (int i=0;i<pos.size();++i){
            if(pos.get(i).underMouse(width,height) && MouseHandler.mouseB==1) sel=i;
        }
    }

    public void draw(Graphics g) {
        for(Vector2D e: pos){
            g.drawImage(images[0],(int)e.getX(),(int)e.getY(),null);
        }
        g.drawImage(images[1],(int)pos.get(sel).getX(),(int)pos.get(sel).getY(),null);
    }


    public int getData(){

        return data.get(sel);
    }
}
