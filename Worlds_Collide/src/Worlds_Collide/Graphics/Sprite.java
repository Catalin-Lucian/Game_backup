package Worlds_Collide.Graphics;

import Worlds_Collide.__Utils.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Sprite {
    protected final BufferedImage SPRITESHEET;
    protected final int TILE_SIZE=48;
    protected int w;
    protected int h;
    protected int nr_wSprite;
    protected int nr_hSprite;



    public Sprite(BufferedImage img){
        w=TILE_SIZE;
        h=TILE_SIZE;

        SPRITESHEET=img;
        nr_wSprite =SPRITESHEET.getWidth()/w;
        nr_hSprite =SPRITESHEET.getHeight()/h;
        loadSpriteArray();
    }

    public Sprite(int w, int h, BufferedImage img)  {
        this.w=w;
        this.h=h;
        this.SPRITESHEET=img;
        nr_wSprite =SPRITESHEET.getWidth()/w;
        nr_hSprite =SPRITESHEET.getHeight()/h;
        loadSpriteArray();
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public void setSize(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int width){
        this.w=width;
        nr_wSprite =SPRITESHEET.getWidth()/w;
    }
    public  void setHeight(int height){
        this.h=height;
        nr_hSprite =SPRITESHEET.getHeight()/h;
    }

    public int getWidth(){
        return w;
    }
    public int getHeight(){
        return  h;
    }

    public  abstract void loadSpriteArray();

    public BufferedImage getSPRITESHEET(){
        return SPRITESHEET;
    }

    public BufferedImage getSprite(int x,int y){
        return SPRITESHEET.getSubimage(x*w,y*h,w,h);
    }

    public static void drawArray(Graphics g, BufferedImage[] img, Vector2D pos, int width, int height, int xOffset, int yOffset)
    {
        float x=pos.getX();
        float y=pos.getY();

        for (BufferedImage image: img){
            if (image!=null){
                g.drawImage(image,(int )x,(int )y,width,height,null);

            }
            x +=xOffset;
            y +=yOffset;
        }
    }


    public static void drawArray(Graphics g, Font f, String word, Vector2D pos, int width, int height, int xOffset, int yOffset){
        float x=pos.getX();
        float y=pos.getY();

        for (int i=0;i<word.length();++i){
            if(word.charAt(i) != ' '){
                g.drawImage(f.getFont(word.charAt(i)),(int )x,(int)y,width,height,null);
            }
            x +=xOffset;
            y +=yOffset;

        }

    }
}
