package Worlds_Collide.Maps;


import Worlds_Collide.Maps.ConcretMaps.Map_1;
import Worlds_Collide.RefLinks;
import Worlds_Collide.__Utils.Settings;

public class MapFactory  {

    public Map createMap(Level lvl){
        Map map;
        switch (lvl) {
            case level_1:
                map = new Map_1();
                break;
            default:
                map = null;
        }

        return map;
    }

}
