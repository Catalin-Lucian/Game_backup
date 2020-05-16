package PaooGame.Maps;



import PaooGame.RefLinks;

import java.awt.Graphics;


public class MapManager {
    private Map map;
    private MapFactory mapFactory;
    private Level level=Level.level_1;

    public MapManager(){
    }

    public void buildMap(String type){
        if (type.equals("static")){
            System.out.println("static map .....");
            mapFactory=new ParallaxMapFactory();
            map=mapFactory.createMap(Level.level_1);
            RefLinks.SetMap(map);

            System.out.println("!!! Map created !!!");
        }
    }

    public void drawMap(Graphics g){

        map.draw(g);
    }

}
