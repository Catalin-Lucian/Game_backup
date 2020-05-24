package PaooGame.States;

import PaooGame.GUI.BType;
import PaooGame.GUI.MButton;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PauseState extends State {

    protected BufferedImage background;
    protected MButton x_button;
    protected MButton save_button;
    protected MButton exit_button;

    public PauseState(StateManager stateManager) {
        super(stateManager);
        x_button=new MButton(795,50, Assets.x_button.getTile(1),Assets.x_button.getTile(2), BType.CLOSE);
        save_button=new MButton(477,141,Assets.menu_button.getTile(11),Assets.menu_button.getTile(12), BType.SAVE);
        exit_button=new MButton(477,471,Assets.menu_button.getTile(7),Assets.menu_button.getTile(8),BType.Exit);
        background=Assets.pause_image;
    }

    @Override
    public void Update() {
        x_button.update();
        if (x_button.clicked() ) stateManager.Return();
        save_button.update();
        if (save_button.clicked()) System.out.println("Game saved");
        exit_button.update();
        if(exit_button.clicked()) stateManager.selectState(BType.MENU);

    }

    @Override
    public void Draw(Graphics g) {
        stateManager.lastState.Draw(g);
        g.drawImage(background,0,0,null);
        x_button.draw(g);
        save_button.draw(g);
        exit_button.draw(g);
    }
}
