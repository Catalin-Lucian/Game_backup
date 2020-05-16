package PaooGame.Maps;


public abstract class MapFactory {

    public Map createMap(Level lvl){
        System.out.println("init map ...");
        Map map=buildMap(lvl);
        map.initMap();
        return map;
    }

    public abstract Map buildMap(Level lvl);

}
