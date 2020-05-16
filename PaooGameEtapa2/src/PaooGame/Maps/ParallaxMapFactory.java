package PaooGame.Maps;

import PaooGame.Maps.ConcretMaps.Map_1;
import PaooGame.RefLinks;

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
