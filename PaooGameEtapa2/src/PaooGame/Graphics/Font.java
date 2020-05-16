package PaooGame.Graphics;

import java.awt.image.BufferedImage;

public class Font {
    private final BufferedImage FONTSHEET;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE=48;
    public int w;
    public int h;
    private int wLetter;
    private int hLetter;

    public Font(String path){
        w=TILE_SIZE;
        h=TILE_SIZE;

        FONTSHEET = ImageLoader.LoadImage(path);
        wLetter = FONTSHEET.getWidth()/w;
        hLetter = FONTSHEET.getHeight()/h;
        laodSpriteArray();
    }

    public Font(String path,int w,int h){
        this.w=w;
        this.h=h;
        FONTSHEET =ImageLoader.LoadImage(path);

        wLetter = FONTSHEET.getWidth()/w;
        hLetter = FONTSHEET.getHeight()/h;
        laodSpriteArray();
    }

    public void setSize(int width,int height){
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int width){
        this.w=width;
        wLetter = FONTSHEET.getWidth()/w;
    }
    public  void setHeight(int height){
        this.h=height;
        hLetter = FONTSHEET.getHeight()/h;
    }

    public int getWidth(){
        return w;
    }
    public int retHeight(){
        return  h;
    }
    public void laodSpriteArray(){
        spriteArray =new BufferedImage[wLetter][hLetter];

        for (int x = 0; x< wLetter; x++){
            for (int y = 0; y< hLetter; ++y){
                spriteArray[x][y]=getLetter(x,y);
            }
        }
    }

    public BufferedImage getFONTSHEET(){
        return FONTSHEET;
    }

    public BufferedImage getLetter(int x,int y){
        return FONTSHEET.getSubimage(x*w,y*h,w,h);
    }

    public  BufferedImage getFont(char letter){
        int value= letter -65;

        int x= value % wLetter;
        int y= value / hLetter;
        System.out.println(x+", "+y);
        return getLetter(x,y);
    }


}
