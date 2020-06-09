package Worlds_Collide.States;

import Worlds_Collide.GUI.BType;
import Worlds_Collide.GUI.MButton;
import Worlds_Collide.Graphics.Assets;
import Worlds_Collide.RefLinks;


import java.awt.*;
import java.awt.image.BufferedImage;

/*
 *  show player the final stats
 */
public class EndSatate extends State {

    protected BufferedImage background;
    protected MButton exit_button;
    protected Font myfont=new Font("Monospaced", Font.BOLD,25);

    public EndSatate(StateManager stateManager) {
        super(stateManager);
        background= Assets.win_image;
        exit_button=new MButton(495,461,Assets.menu_button.getTile(7),Assets.menu_button.getTile(8), BType.EXIT);

    }

    @Override
    public void Update() {
        exit_button.update();
        if(exit_button.clicked()) stateManager.selectState(BType.MENU);
    }

    @Override
    public void Draw(Graphics g) {
        stateManager.lastState.Draw(g);
        g.drawImage(background,0,0,null);
        g.setColor(Color.YELLOW);
        g.setFont(myfont);
        g.drawString(Integer.toString(RefLinks.getPlayer().getDeaths()),683,200);
        g.drawString(Integer.toString(RefLinks.getPlayer().GetLife()),689,251);
        int finalscore=RefLinks.getPlayer().GetLife()*RefLinks.getDifficulty()-RefLinks.getPlayer().getDeaths()*RefLinks.getDifficulty();
        g.drawString(Integer.toString(finalscore)+"   PCT",689,350);
        exit_button.draw(g);
    }
}
