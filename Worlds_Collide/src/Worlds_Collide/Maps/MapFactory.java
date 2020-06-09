package Worlds_Collide.Maps;


import Worlds_Collide.Graphics.Assets;
import Worlds_Collide.Maps.ConcretMaps.ConcreteMap;


public class MapFactory  {

    /// return map conform level
    public Map createMap(int lvl){
        Map map;
        switch (lvl) {
            case 1:
                map = new ConcreteMap(Assets.back_1,Assets.front_1,"res/Textures/Levels/level_1/lvl_1.txt");
                break;
            case 2:
                map=new ConcreteMap(Assets.back_2,Assets.front_2,"res/Textures/Levels/level_2/lvl_2.txt");
                break;
            case 3:
                map=new ConcreteMap(Assets.back_3,Assets.front_3,"res/Textures/Levels/level_3/lvl_3.txt");
                break;
            case 4:
                map=new ConcreteMap(Assets.back_4,Assets.front_4,"res/Textures/Levels/level_4/lvl_4.txt");
                break;
            case 5:
                map=new ConcreteMap(Assets.back_5,Assets.front_5,"res/Textures/Levels/level_5/lvl_5.txt");
                break;
            case 6:
                map=new ConcreteMap(Assets.back_6,Assets.front_6,"res/Textures/Levels/level_6/lvl_6.txt");
                break;
            case 7:
                map=new ConcreteMap(Assets.back_7,Assets.front_7,"res/Textures/Levels/level_7/lvl_7.txt");
                break;
            default:
                map = null;
        }

        return map;
    }

}
