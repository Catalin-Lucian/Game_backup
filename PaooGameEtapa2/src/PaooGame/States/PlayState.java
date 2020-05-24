package PaooGame.States;

import PaooGame.GUI.BType;
import PaooGame.Graphics.Camera;
import PaooGame.Items.Characters.Enemys.Black_ball;
import PaooGame.Items.EntityManager;
import PaooGame.Items.Player;
import PaooGame.Maps.MapManager;
import PaooGame.RefLinks;

import java.awt.*;


public class PlayState extends State
{
    private Player player;              /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private MapManager mapManager;
    private Camera cam;
    private EntityManager entityManager;
    public PlayState(StateManager stateManager)
    {
        super(stateManager);
        entityManager=new EntityManager();
        player = new Player(60, 350,2.5f );
        mapManager=new MapManager();
        mapManager.buildMap("static");
        cam=new Camera();
    }

    @Override
    public void Update()
    {

        player.Update();
        entityManager.Update();

        if (RefLinks.GetKeyHandler().K_escape.clicked){
            stateManager.selectState(BType.PAUSE);
        }

    }

    @Override
    public void Draw(Graphics g)
    {
        mapManager.drawMap(g);
        entityManager.Draw(g);
        player.Draw(g);


    }

    public void reset(){
        player=new Player(60, 350,2.5f );
    }



}
