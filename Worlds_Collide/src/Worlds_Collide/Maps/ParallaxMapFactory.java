package Worlds_Collide.Maps;

import Worlds_Collide.Maps.ConcretMaps.Map_1;

public class ParallaxMapFactory extends MapFactory{


    @Override
    public Map buildMap(Level lvl) {
        Map map;
        switch(lvl){
            case level_1:map= new Map_1();break;
            default: map=null;
        }
        return map;
    }

}
