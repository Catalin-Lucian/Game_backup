package Worlds_Collide.GUI;

import Worlds_Collide.Graphics.Assets;
import Worlds_Collide.__Utils.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

///entity healt bar
public class Health_bar{
    protected Vector2D position;
    protected BufferedImage[] images =new BufferedImage[4];
    protected float width;
    protected float height;
    protected float full;


    ///init data
    public Health_bar(float x,float y,float life){
        position=new Vector2D(x,y);
        images[0]= Assets.life_bar.getTile(1);//empty bar
        images[1]=Assets.life_bar.getTile(2);// green bar
        images[2]=Assets.life_bar.getTile(3);//red bar
        images[3]=Assets.life_bar.getTile(4);//skull
        width=images[0].getWidth();
        height=images[0].getHeight();
        full=life;
    }
    ///set full life
    public void setFull(float full) {
        this.full = full;
    }

    ///draw green bar
    public void drawGreen(Graphics g, float life){
        g.drawImage(images[1],(int)(position.getX()),(int)position.getY(),(int)(width*life/full),(int)height,null);
        g.drawImage(images[0],(int)position.getX(),(int)position.getX(),null);
        g.drawImage(images[3],(int)position.getX(),(int)position.getY(),null);
    }

    ///draw red bar
    public void drawRed(Graphics g, float life){
        g.drawImage(images[2],(int)(position.getX()),(int)position.getY(),(int)((width*life)/full),(int)height,null);
        g.drawImage(images[0],(int)position.getX(),(int)position.getY(),null);
        g.drawImage(Assets.life_bar.getTile(5),(int)position.getX(),(int)position.getY(),null);
    }

}
