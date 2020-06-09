package Worlds_Collide.Maps;

import Worlds_Collide.Graphics.Camera;
import Worlds_Collide.Items.EntityManager;
import Worlds_Collide.RefLinks;
import Worlds_Collide.__Utils.DataBase;

import java.awt.Graphics;

///manages maps
public class MapManager {

    private Map map;///< current map
    private final MapFactory mapFactory;
    private int level=1;///< current level
    private boolean changeLvL=false;///< bool to see if to change map
    private final DataBase dataBase;///< instance of class data base

    /// crates new map
    public MapManager(DataBase dataBase){
        RefLinks.setMapManager(this);
        this.dataBase=dataBase;
        mapFactory=new MapFactory();

        buildMap();
    }

    /// load last map played
    public MapManager(DataBase dataBase,boolean load){
        RefLinks.setMapManager(this);
        this.dataBase=dataBase;
        mapFactory=new MapFactory();
        loadMap();
        buildMap();
    }

    /// loads level from database
    public void loadMap(){
        level= dataBase.getData("MAP","PLAYER");
    }

    /// save lvl in database
    public void saveMap(){
        dataBase.updateMap(level);
    }

    /// builds the map
    public void buildMap(){
        map =mapFactory.createMap(level);
        RefLinks.SetMap(map);
    }

    /// return the lvl
    public int getLevel(){
        return level;
    }

    public void update(){
        if (changeLvL) {
            changeLvL=false;
            EntityManager.reset();
            buildMap();
            Camera.init();

        }
    }

    public void drawMapBack(Graphics g){
        map.drawBack(g);
    }
    public void drawMapFront(Graphics g){
        map.drawFront(g);
    }

    /// is called to announce the change of an map
    public void changeLVL(int index){
        level +=index;
        changeLvL=true;
    }


}
