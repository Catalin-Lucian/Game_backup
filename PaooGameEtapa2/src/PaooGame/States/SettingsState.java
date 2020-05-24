package PaooGame.States;


import PaooGame.GUI.Checkers;
import PaooGame.GUI.MButton;
import PaooGame.GUI.BType;
import PaooGame.Graphics.Assets;

import PaooGame.__Utils.Vector2D;

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
        qlt = new Checkers(stateManager.quality.ordinal());
        qlt.add(new Vector2D(602, 229), BType.LOW);
        qlt.add(new Vector2D(602, 278), BType.HIGH);
        dif = new Checkers(stateManager.difficulty.ordinal()-2);
        dif.add(new Vector2D(602, 419), BType.EASY);
        dif.add(new Vector2D(602, 466), BType.NORMAL);
        dif.add(new Vector2D(602, 513), BType.HARD);
        x = new MButton(830, 46, Assets.x_button.getTile(1), Assets.x_button.getTile(2), BType.Exit);
    }

    @Override
    public void Update()
    {
        x.update();
        if(x.clicked()) stateManager.Return();
        qlt.update();
        dif.update();

        stateManager.quality=qlt.getType();
        stateManager.difficulty=dif.getType();

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
