package Worlds_Collide.Graphics;

import java.awt.image.BufferedImage;

public class Assets
{
    public static EntitySprite player;
    public static EntitySprite black_ball;
    public static EntitySprite mushroom;
    public static BufferedImage menu_image;
    public static BufferedImage about_image;
    public static BufferedImage settings_image;
    public static TilesSprite check_button;
    public static TilesSprite life_bar;
    public static TilesSprite x_button;
    public static TilesSprite menu_button;
    public static BufferedImage pause_image;
    public static TilesSprite map_sprite;

    public static BufferedImage lvl_1_back;
    public static BufferedImage lvl_1_front;






    public static void Init() {
        player=new EntitySprite(89,76,ImageLoader.LoadImage("/Textures/Entitys/player.png"));
        black_ball=new EntitySprite(64,64,ImageLoader.LoadImage("/Textures/Entitys/black_ball.png"));
        mushroom=new EntitySprite(150,150,ImageLoader.LoadImage("/Textures/Entitys/mushroom.png"));


        menu_image=ImageLoader.LoadImage("/Textures/GUI/menu.png");
        about_image=ImageLoader.LoadImage("/Textures/GUI/about.png");
        settings_image=ImageLoader.LoadImage("/Textures/GUI/settings.png");
        pause_image=ImageLoader.LoadImage("/Textures/GUI/pause.png");

        check_button=new TilesSprite(36,36,ImageLoader.LoadImage("/Textures/GUI/checker.png"));
        life_bar=new TilesSprite(340,68,ImageLoader.LoadImage("/Textures/GUI/lifebar.png"));
        x_button=new TilesSprite(43,60,ImageLoader.LoadImage("/Textures/GUI/x_button.png"));
        menu_button=new TilesSprite(336,96,ImageLoader.LoadImage("/Textures/GUI/menu_buttons.png"));

        lvl_1_back=ImageLoader.LoadImage("/Textures/Levels/level_1/lvl_1_back.png");
        lvl_1_front=ImageLoader.LoadImage("/Textures/Levels/level_1/lvl_1_front.png");




        map_sprite=new TilesSprite(48,48,ImageLoader.LoadImage("/Textures/Levels/spritesheet.png"));




    }
}
