package Worlds_Collide.Graphics;

import java.awt.image.BufferedImage;

public class Assets
{
    public static EntitySprite player;
    public static EntitySprite black_ball;
    public static EntitySprite mushroom;
    public static EntitySprite boss;


    public static BufferedImage menu_image;
    public static BufferedImage about_image;
    public static BufferedImage settings_image;
    public static TilesSprite check_button;
    public static TilesSprite life_bar;
    public static TilesSprite x_button;
    public static TilesSprite menu_button;
    public static BufferedImage pause_image;
    public static TilesSprite map_sprite;
    public static BufferedImage win_image;
    public static BufferedImage save;

    public static BufferedImage back_1;
    public static BufferedImage front_1;
    public static BufferedImage back_2;
    public static BufferedImage front_2;
    public static BufferedImage back_3;
    public static BufferedImage front_3;
    public static BufferedImage back_4;
    public static BufferedImage front_4;
    public static BufferedImage back_5;
    public static BufferedImage front_5;
    public static BufferedImage back_6;
    public static BufferedImage front_6;
    public static BufferedImage back_7;
    public static BufferedImage front_7;

    public static TilesSprite potion;





    public static void Init() {
        player=new EntitySprite(89,76,ImageLoader.LoadImage("/Textures/Entitys/player.png"));
        black_ball=new EntitySprite(64,64,ImageLoader.LoadImage("/Textures/Entitys/black_ball.png"));
        mushroom=new EntitySprite(150,150,ImageLoader.LoadImage("/Textures/Entitys/mushroom.png"));
        boss=new EntitySprite(78,49,ImageLoader.LoadImage("/Textures/Entitys/boss.png"));


        menu_image=ImageLoader.LoadImage("/Textures/GUI/menu.png");
        about_image=ImageLoader.LoadImage("/Textures/GUI/about.png");
        settings_image=ImageLoader.LoadImage("/Textures/GUI/settings.png");
        pause_image=ImageLoader.LoadImage("/Textures/GUI/pause.png");
        win_image=ImageLoader.LoadImage("/Textures/GUI/win.png");
        save=ImageLoader.LoadImage("/Textures/GUI/save.png");
        check_button=new TilesSprite(36,36,ImageLoader.LoadImage("/Textures/GUI/checker.png"));
        life_bar=new TilesSprite(340,68,ImageLoader.LoadImage("/Textures/GUI/lifebar.png"));
        x_button=new TilesSprite(43,60,ImageLoader.LoadImage("/Textures/GUI/x_button.png"));
        menu_button=new TilesSprite(336,96,ImageLoader.LoadImage("/Textures/GUI/menu_buttons.png"));
        

        potion=new TilesSprite(160,160,ImageLoader.LoadImage("/Textures/GUI/potion.png"));

        back_1 =ImageLoader.LoadImage("/Textures/Levels/level_1/lvl_1_back.png");
        front_1 =ImageLoader.LoadImage("/Textures/Levels/level_1/lvl_1_front.png");

        back_2 =ImageLoader.LoadImage("/Textures/Levels/level_2/back_2.png");
        front_2 =ImageLoader.LoadImage("/Textures/Levels/level_2/front_2.png");

        back_3 =ImageLoader.LoadImage("/Textures/Levels/level_3/back_3.png");
        front_3 =ImageLoader.LoadImage("/Textures/Levels/level_3/front_3.png");

        back_4 =ImageLoader.LoadImage("/Textures/Levels/level_4/back_4.png");
        front_4 =ImageLoader.LoadImage("/Textures/Levels/level_4/front_4.png");

        back_5 =ImageLoader.LoadImage("/Textures/Levels/level_5/back_5.png");
        front_5 =ImageLoader.LoadImage("/Textures/Levels/level_5/front_5.png");

        back_6 =ImageLoader.LoadImage("/Textures/Levels/level_6/back_6.png");
        front_6 =ImageLoader.LoadImage("/Textures/Levels/level_6/front_6.png");

        back_7 =ImageLoader.LoadImage("/Textures/Levels/level_7/back_7.png");
        front_7 =ImageLoader.LoadImage("/Textures/Levels/level_7/front_7.png");


        map_sprite=new TilesSprite(48,48,ImageLoader.LoadImage("/Textures/Levels/spritesheet.png"));

    }
}
