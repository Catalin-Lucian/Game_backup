package PaooGame.Graphics;




import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets
{
    public static EntitySprite player;
    public static TilesSprite lvl_1_sheet;
    public static BufferedImage lvl_1_background_full;
    public static BufferedImage lvl_1_background_1;
    public static BufferedImage lvl_1_background_2;


    public static void Init() {
        player=new EntitySprite(89,76,ImageLoader.LoadImage("/Textures/player.png"));

        lvl_1_sheet=new TilesSprite(48,48,ImageLoader.LoadImage("/Textures/Levels/level_1/lvl_1_sheet.png"));
        lvl_1_background_full=ImageLoader.LoadImage("/Textures/Levels/level_1/background_full.png");
        lvl_1_background_1=ImageLoader.LoadImage("/Textures/Levels/level_1/background_1.png");
        lvl_1_background_2=ImageLoader.LoadImage("/Textures/Levels/level_1/background_2.png");




    }
}
