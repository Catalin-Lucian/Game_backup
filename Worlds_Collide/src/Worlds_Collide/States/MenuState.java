package Worlds_Collide.States;

import Worlds_Collide.GUI.BType;
import Worlds_Collide.GUI.MButton;
import Worlds_Collide.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
 *  the main menu of the game
 */
public class MenuState extends State
{
    protected BufferedImage background;
    protected ArrayList<MButton> buttons=new ArrayList<>();

    public MenuState(StateManager stateManager)
    {
        super(stateManager);
        background=Assets.menu_image;
        buttons.add(new MButton(43,145,Assets.menu_button.getTile(1),Assets.menu_button.getTile(2), BType.PLAY));
        buttons.add(new MButton(43,241,Assets.menu_button.getTile(9),Assets.menu_button.getTile(10), BType.LOAD));
        buttons.add(new MButton(43,337,Assets.menu_button.getTile(3),Assets.menu_button.getTile(4), BType.SETTINGS));
        buttons.add(new MButton(43,433,Assets.menu_button.getTile(5),Assets.menu_button.getTile(6), BType.ABOUT));
        buttons.add(new MButton(43,560,Assets.menu_button.getTile(7),Assets.menu_button.getTile(8), BType.EXIT));

    }

    @Override
    public void Update()
    {
        for (MButton b:buttons){
            b.update();
            if(b.clicked()){
                stateManager.selectState(b.getBType());
                return;
            }
        }
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(background,0,0,null);
        for (MButton b:buttons){
            b.draw(g);
        }
    }

}
