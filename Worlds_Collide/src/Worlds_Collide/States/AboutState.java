package Worlds_Collide.States;



import Worlds_Collide.GUI.MButton;
import Worlds_Collide.GUI.BType;
import Worlds_Collide.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;


/*
 *   tels player the keys to play
 */
public class AboutState extends State
{
    protected MButton x_button;
    protected BufferedImage background;

    public AboutState(StateManager stateManager)
    {
        super(stateManager);
        background= Assets.about_image;
        x_button =new MButton(830,46,Assets.x_button.getTile(1),Assets.x_button.getTile(2), BType.EXIT);
    }

    @Override
    public void Update()
    {
        x_button.update();
        if(x_button.clicked()) stateManager.Return();
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(background,0,0,null);
        x_button.draw(g);

    }


}
