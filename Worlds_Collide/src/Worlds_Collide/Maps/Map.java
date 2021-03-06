package Worlds_Collide.Maps;

import Worlds_Collide.Graphics.Assets;
import Worlds_Collide.Graphics.Camera;
import Worlds_Collide.Graphics.TilesSprite;
import Worlds_Collide.Items.EntityManager;
import Worlds_Collide.RefLinks;
import Worlds_Collide.__Utils.Settings;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

///base class for map
public abstract class  Map {
    public static final int TILE_SIZE=48;
    protected static int M_WIDTH_SIZE ;

    protected TilesSprite sprite;///< tile class with all images
    protected BufferedImage back;///< front image
    protected BufferedImage front;///< back image
    protected int [][][] layers;///< all data fro a map

    protected int nr_layers;///< number of layes
    protected int width_tiles;///< nr tile per width
    protected int height_tiles;///< nr tile per height
    protected int quality;///< map quality
    protected File f;


    public Map(){
        this.sprite= Assets.map_sprite;
        quality=RefLinks.getQuality();
    }

    /// read data from file
    public void loadLayers() {
        try{
            Scanner scn=new Scanner(f);
            nr_layers=scn.nextInt();
            height_tiles=scn.nextInt();
            width_tiles=scn.nextInt();

            layers=new int[nr_layers][height_tiles][width_tiles];
            for (int l=0;l<nr_layers;++l){
                for (int hpoz=0;hpoz<height_tiles;++hpoz){
                    for (int wpoz=0;wpoz<width_tiles;++wpoz){
                        layers[l][hpoz][wpoz]=scn.nextInt();;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        M_WIDTH_SIZE=width_tiles*TILE_SIZE;
    }

    /// adds enemies to entityemanager list
    public void spawnEntities(){
        for (int hpoz=0;hpoz<height_tiles;++hpoz){
            for (int wpoz=0;wpoz<width_tiles;++wpoz){
                if (layers[nr_layers-1][hpoz][wpoz]==2) EntityManager.spawnEnemy(wpoz*TILE_SIZE,hpoz*TILE_SIZE);
                if (layers[nr_layers-1][hpoz][wpoz]==3) EntityManager.spawnBoss(wpoz*TILE_SIZE,hpoz*TILE_SIZE);
            }
        }
    }


    /// draw back layer
    public void drawBack(Graphics g){


        g.drawImage(cropImage(back,0.5),0,0,null);


        for (int l=0;l<nr_layers-1;++l) {
            for (int hpoz =0; hpoz < height_tiles; ++hpoz) {
                for (int wpoz =(int) Camera.getX_edge_left()/TILE_SIZE; wpoz <(int) (Camera.getX_edge_right()/TILE_SIZE)+1; ++wpoz) {
                    if (layers[l][hpoz][wpoz] != 0)
                        g.drawImage(sprite.getTile(layers[l][hpoz][wpoz]), (int )(wpoz*TILE_SIZE-Camera.getX_edge_left()), hpoz*TILE_SIZE, null);
                }
            }
        }



        /*for (int hpoz =0; hpoz < height_tiles; ++hpoz) {
            for (int wpoz = (int) Camera.getX_edge_left() / TILE_SIZE; wpoz < (int) (Camera.getX_edge_right() / TILE_SIZE) + 1; ++wpoz) {
                if (layers[nr_layers - 1][hpoz][wpoz] != 0)
                    g.drawImage(sprite.getTile(layers[nr_layers - 1][hpoz][wpoz]), (int) (wpoz * TILE_SIZE - Camera.getX_edge_left()), hpoz * TILE_SIZE, null);
            }
        }*/

    }


    /// draw front layer
    public void drawFront(Graphics g){

        for (int hpoz =0; hpoz < height_tiles; ++hpoz) {
            for (int wpoz =(int) Camera.getX_edge_left()/TILE_SIZE; wpoz <(int) (Camera.getX_edge_right()/TILE_SIZE)+1; ++wpoz) {
                if (layers[2][hpoz][wpoz] != 0)
                    g.drawImage(sprite.getTile(layers[2][hpoz][wpoz]), (int )(wpoz*TILE_SIZE-Camera.getX_edge_left()), hpoz*TILE_SIZE, null);
            }
        }
        if (quality== Settings.HIGH) g.drawImage(cropImage(front,1.5),0,0,null);

    }

    public static int getmWidthSize(){
        return M_WIDTH_SIZE;
    }

    /// return true if at position on col layer is solid
    public boolean getSolid(float w, float h){
        try {
            return layers[nr_layers - 1][(int) (h / TILE_SIZE)][(int) (w/ TILE_SIZE)] == 1;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /// crop images for the dimension needed
    public BufferedImage cropImage(BufferedImage image, double delay){
        return image.getSubimage((int) (Camera.getX_edge_left()*delay),0, RefLinks.GetWidth(),image.getHeight());
    }

    /// resets map
    public void reset(){
        loadLayers();
        spawnEntities();
    }

}
