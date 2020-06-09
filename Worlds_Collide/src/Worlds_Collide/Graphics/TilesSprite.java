package Worlds_Collide.Graphics;

import java.awt.image.BufferedImage;

/// used for tiles
public class TilesSprite extends Sprite {

    private BufferedImage[] spriteArray;///contains a vector of all tiles images

    /// constructor with image
    public TilesSprite (BufferedImage img){
       super(img);
    }

    /// constructor with image and size of one image
    public TilesSprite (int w,int h,BufferedImage img){
        super(w,h,img);
    }

    ///loads the vector by cutting the big sprite sheet
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

    ///return all tiles
    public BufferedImage[] getSpriteArray(){
        return spriteArray;
    }

    ///return one tile identified by id (nr in vector)
    public BufferedImage getTile(int id){
        return spriteArray[id];
    }
}
