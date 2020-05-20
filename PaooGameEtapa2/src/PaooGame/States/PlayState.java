package PaooGame.States;

import PaooGame.Graphics.Camera;
import PaooGame.Items.Characters.Enemys.Black_ball;
import PaooGame.Items.EntityManager;
import PaooGame.Items.Hero;
import PaooGame.Maps.MapManager;
import PaooGame.RefLinks;

import java.awt.*;


public class PlayState extends State
{
    private Hero hero;              /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private MapManager mapManager;
    private Camera cam;
    private EntityManager entityManager;
    public PlayState(StateManager stateManager)
    {
        super(stateManager);
        entityManager=new EntityManager();
        EntityManager.addEnemy(new Black_ball(1500,250,250,10,10,2f));
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
        entityManager.Update();

    }

    @Override
    public void Draw(Graphics g)
    {
        mapManager.drawMap(g);
        entityManager.Draw(g);
        hero.Draw(g);


    }



}
