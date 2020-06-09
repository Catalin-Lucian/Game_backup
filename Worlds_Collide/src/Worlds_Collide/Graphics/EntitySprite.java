package Worlds_Collide.Graphics;


import java.awt.image.BufferedImage;

/// used for entities , contains all images
public class EntitySprite extends Sprite
{
    private BufferedImage[][] spriteArray;///< all images organized by rows

    /// constructor with image
    public EntitySprite(BufferedImage img){
        super(img);
    }

    /// constructor with image and size
    public EntitySprite(int w, int h, BufferedImage img)  {
        super(w,h,img);
    }

    /// cuts image in smaller images
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
    }///< return a row(contains all img for an animation)

    public BufferedImage[][] getSpriteArray2(){
        return spriteArray;
    }///< return all images

    public BufferedImage getLastSprite(int i){
        return spriteArray[i][nr_wSprite];
    }///< return last images in a row


}
