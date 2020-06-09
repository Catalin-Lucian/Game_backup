package Worlds_Collide.Maps.ConcretMaps;

import Worlds_Collide.Maps.Map;

import java.awt.image.BufferedImage;
import java.io.File;

///a nconcred map
public class ConcreteMap extends Map {

    ///need a front ,back image and path to mpa data
    public ConcreteMap(BufferedImage back, BufferedImage front, String pathLvl) {

        this.back=back;
        this.front=front;
        try{
            this.f=new File(pathLvl);
        } catch (Exception e) {
            e.getStackTrace();
        }
        loadLayers();
        spawnEntities();
    }

}
