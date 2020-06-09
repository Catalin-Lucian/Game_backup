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
    private EntityManager entityManager;


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
        player.setPosition(stateManager.dataBase.getData("X","PLAYER"),stateManager.dataBase.getData("Y","PLAYER"));
        player.setDeaths(stateManager.dataBase.getData("DEATHS","PLAYER"));
        player.setPotions(stateManager.dataBase.getData("BOTTLES","PLAYER"));
        mapManager=new MapManager(stateManager.dataBase,true);
        new Camera();
    }

    @Override
    public void Update()
    {


        mapManager.update();
        player.Update();
        entityManager.Update();

        if (RefLinks.GetKeyHandler().K_escape.clicked){
            stateManager.selectState(BType.PAUSE);
        }

        if (mapManager.getLevel()==7 && entityManager.noEnemy()) stateManager.selectState(BType.END);

    }

    @Override
    public void Draw(Graphics g)
    {
        mapManager.drawMapBack(g);
        entityManager.Draw(g);
        player.Draw(g);
        mapManager.drawMapFront(g);
        player.drawBar(g);


    }

    public void reset(){
        player=new Player(60, 350,2.5f );
    }
    public void newMap(){
        entityManager=new EntityManager();

    }


}
