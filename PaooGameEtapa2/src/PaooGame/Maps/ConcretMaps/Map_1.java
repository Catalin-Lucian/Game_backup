package PaooGame.Maps.ConcretMaps;


import PaooGame.Graphics.Assets;
import PaooGame.Maps.Map;

import java.io.File;


public class Map_1 extends Map {

    public Map_1() {

        this.sprite= Assets.lvl_1_sheet;
        this.background=Assets.lvl_1_background_full;
        this.background_1=Assets.lvl_1_background_1;
        this.background_2=Assets.lvl_1_background_2;
        try{
            this.f=new File("res/Textures/Levels/level_1/lvl_1.txt");
        } catch (Exception e) {
            e.getStackTrace();
        }
        System.out.println("build map_1 ...");
    }

    @Override
    public void initMap() {
        System.out.println("init map ...");
        loadLayers();
    }



}
