package PaooGame.States;

import PaooGame.Graphics.Camera;
import PaooGame.Items.Hero;
import PaooGame.Maps.MapManager;
import PaooGame.RefLinks;

import java.awt.*;


public class PlayState extends State
{
    private Hero hero;              /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private MapManager mapManager;
    private Camera cam;
    public PlayState(StateManager stateManager)
    {
        super(stateManager);
        hero = new Hero(60, 350,2.5f );
        mapManager=new MapManager();
        mapManager.buildMap("static");
        cam=new Camera();
    }

    @Override
    public void Update()
    {
//        map.Update();
        hero.Update();
    }

    @Override
    public void Draw(Graphics g)
    {
        mapManager.drawMap(g);
        hero.Draw(g);


    }



}
