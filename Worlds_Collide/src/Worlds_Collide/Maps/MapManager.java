package Worlds_Collide.Maps;

import Worlds_Collide.Graphics.Camera;
import Worlds_Collide.Items.EntityManager;
import Worlds_Collide.RefLinks;
import Worlds_Collide.__Utils.DataBase;

import java.awt.Graphics;


public class MapManager {

    private Map map;
    private final MapFactory mapFactory;
    private int level=7;
    private boolean changeLvL=false;
    private final DataBase dataBase;

    public MapManager(DataBase dataBase){
        RefLinks.setMapManager(this);
        this.dataBase=dataBase;
        mapFactory=new MapFactory();

        buildMap();
    }

    public MapManager(DataBase dataBase,boolean load){
        RefLinks.setMapManager(this);
        this.dataBase=dataBase;
        mapFactory=new MapFactory();
        loadMap();
        buildMap();
    }

    public void loadMap(){
        level= dataBase.getData("MAP","PLAYER");
    }

    public void saveMap(){
        dataBase.updateMap(level);
    }

    public void buildMap(){
        map =mapFactory.createMap(level);
        RefLinks.SetMap(map);
    }

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

    public void changeLVL(int index){
        level +=index;
        changeLvL=true;
    }


}
