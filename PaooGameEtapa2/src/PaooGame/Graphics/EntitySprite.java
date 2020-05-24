package PaooGame.Graphics;


import java.awt.image.BufferedImage;


public class EntitySprite extends Sprite
{
    private BufferedImage[][] spriteArray;


    public EntitySprite(BufferedImage img){
        super(img);
    }

    public EntitySprite(int w, int h, BufferedImage img)  {
        super(w,h,img);
    }

    @Override
    public void loadSpriteArray(){
        spriteArray=new BufferedImage[nr_hSprite][nr_wSprite];
            for (int x = 0; x< nr_hSprite; ++x){
                for (int y = 0; y< nr_wSprite; y++){
                    spriteArray[x][y]=getSprite(y,x);
                }
            }
    }
    

    public BufferedImage[] getSpriteArray(short i){
        return spriteArray[i];
    }

    public BufferedImage[][] getSpriteArray2(){
        return spriteArray;
    }

    public BufferedImage getLastSprite(int i){
        return spriteArray[i][nr_wSprite];
    }


}
