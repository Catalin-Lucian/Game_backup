package Worlds_Collide.States;


import Worlds_Collide.GUI.Checkers;
import Worlds_Collide.GUI.MButton;
import Worlds_Collide.GUI.BType;
import Worlds_Collide.Graphics.Assets;

import Worlds_Collide.__Utils.Settings;
import Worlds_Collide.__Utils.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;



public class SettingsState extends State
{

    protected MButton x;
    protected Checkers qlt;
    protected Checkers dif;
    protected BufferedImage background;

    public SettingsState(StateManager stateManager) {
        super(stateManager);

        background = Assets.settings_image;
        qlt = new Checkers(stateManager.getQuality());
        qlt.add(new Vector2D(602, 229), Settings.LOW);
        qlt.add(new Vector2D(602, 278), Settings.HIGH);
        dif = new Checkers(stateManager.getDifficulty());
        dif.add(new Vector2D(602, 419), Settings.EASY);
        dif.add(new Vector2D(602, 466), Settings.NORMAL);
        dif.add(new Vector2D(602, 513), Settings.HARD);
        x = new MButton(830, 46, Assets.x_button.getTile(1), Assets.x_button.getTile(2), BType.EXIT);
    }

    @Override
    public void Update()
    {
        x.update();
        if(x.clicked()){
            stateManager.dataBase.updateSettings(stateManager.quality,stateManager.difficulty);
            stateManager.Return();
        }
        qlt.update();
        dif.update();

        stateManager.setQuality(qlt.getData());
        stateManager.setDifficulty(dif.getData());
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(background,0,0,null);
        qlt.draw(g);
        dif.draw(g);
        x.draw(g);
    }
}
