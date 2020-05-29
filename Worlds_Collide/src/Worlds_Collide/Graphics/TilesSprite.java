package Worlds_Collide.Graphics;

import java.awt.image.BufferedImage;

public class TilesSprite extends Sprite {

    private BufferedImage[] spriteArray;


    public TilesSprite (BufferedImage img){
       super(img);
    }

    public TilesSprite (int w,int h,BufferedImage img){
        super(w,h,img);
    }
    @Override
    public void loadSpriteArray() {
        spriteArray=new BufferedImage[(nr_hSprite * nr_wSprite)+1];
        int i=1;
        for (int hpoz = 0; hpoz< nr_hSprite; ++hpoz){
            for (int wpoz = 0; wpoz< nr_wSprite; ++wpoz){
                spriteArray[i++]=getSprite(wpoz,hpoz);
            }
        }
    }

    public BufferedImage[] getSpriteArray(){
        return spriteArray;
    }

    public BufferedImage getTile(int id){
        return spriteArray[id];
    }
}
