package Worlds_Collide.States;

import Worlds_Collide.GUI.BType;
import Worlds_Collide.Graphics.Camera;
import Worlds_Collide.Items.EntityManager;
import Worlds_Collide.Items.Player;
import Worlds_Collide.Maps.MapManager;
import Worlds_Collide.RefLinks;

import java.awt.*;


public class PlayState extends State
{
    private Player player;              /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private final MapManager mapManager;
    private final EntityManager entityManager;


    public PlayState(StateManager stateManager)
    {
        super(stateManager);
        entityManager=new EntityManager();
        player = new Player(60, 150,2.5f );
        mapManager=new MapManager(stateManager.dataBase);
        new Camera();
    }

    public PlayState(StateManager stateManager,boolean load){
        super(stateManager);
        entityManager=new EntityManager();
        player = new Player(60, 150,2.5f );
        player.SetLife(stateManager.dataBase.getData("LIFE","PLAYER"));
        mapManager=new MapManager(stateManager.dataBase,true);
        new Camera();
    }

    @Override
    public void Update()
    {

//        mapManager.update();
        player.Update();
        entityManager.Update();

        if (RefLinks.GetKeyHandler().K_escape.clicked){
            stateManager.selectState(BType.PAUSE);
        }

    }

    @Override
    public void Draw(Graphics g)
    {
        mapManager.drawMapBack(g);
        entityManager.Draw(g);
        player.Draw(g);
        mapManager.drawMapFront(g);


    }

    public void reset(){
        player=new Player(60, 350,2.5f );
    }



}
