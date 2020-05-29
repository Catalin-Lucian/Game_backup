package Worlds_Collide.Maps.ConcretMaps;


import Worlds_Collide.Graphics.Assets;
import Worlds_Collide.Maps.Map;

import java.io.File;


public class Map_1 extends Map {

    public Map_1() {

        this.back=Assets.lvl_1_back;
        this.front=Assets.lvl_1_front;
        this.light=Assets.lvl_1_light;
        try{
            this.f=new File("res/Textures/Levels/level_1/lvl_1.txt");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void initMap() {
        loadLayers();
        spawnEntities();
    }



}
