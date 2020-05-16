package PaooGame.Maps;

import PaooGame.Graphics.Camera;
import PaooGame.Graphics.TilesSprite;
import PaooGame.RefLinks;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Map {
    public static final int TILE_SIZE=48;
    protected static int M_WIDTH_SIZE ;

    protected TilesSprite sprite;
    protected BufferedImage background;
    protected BufferedImage background_1;
    protected BufferedImage background_2;
    protected int [][][] layers;

    protected int nr_layers;
    protected int width_tiles;
    protected int height_tiles;
    protected File f;


    public Map(){

    }

    public abstract void initMap();


    public BufferedImage cropBackground(BufferedImage image,double delay){
        return image.getSubimage((int) (Camera.getX_edge_left()*delay),0, RefLinks.GetWidth(),image.getHeight());
    }


    public void loadLayers() {
        try{
            int i;
            Scanner scn=new Scanner(f);
            nr_layers=scn.nextInt();
            height_tiles=scn.nextInt();
            width_tiles=scn.nextInt();
            System.out.println("map size: "+nr_layers+" "+height_tiles+" "+width_tiles);
            layers=new int[nr_layers][height_tiles][width_tiles];
            System.out.println("loading layers ...");
            for (int l=0;l<nr_layers;++l){
                for (int hpoz=0;hpoz<height_tiles;++hpoz){
                    for (int wpoz=0;wpoz<width_tiles;++wpoz){
                        i=scn.nextInt();
                        layers[l][hpoz][wpoz]=i;

                    }
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        M_WIDTH_SIZE=width_tiles*TILE_SIZE;
    }



    public void draw(Graphics g){
        g.drawImage(cropBackground(background_1,0.25),0,0,null);
        g.drawImage(cropBackground(background_2,0.65),0,0,null);
        //g.drawImage(background,0,0,null);

        for (int l=0;l<nr_layers-1;++l) {
            for (int hpoz =0; hpoz < height_tiles; ++hpoz) {
                for (int wpoz =(int) Camera.getX_edge_left()/TILE_SIZE; wpoz <(int) (Camera.getX_edge_right()/TILE_SIZE)+1; ++wpoz) {
                    if (layers[l][hpoz][wpoz] != 0)
                        g.drawImage(sprite.getTile(layers[l][hpoz][wpoz]), (int )(wpoz*TILE_SIZE-Camera.getX_edge_left()), hpoz*TILE_SIZE, null);
                }
            }
        }



       /* for (int hpoz =0; hpoz < height_tiles; ++hpoz) {
            for (int wpoz = (int) Camera.getX_edge_left() / TILE_SIZE; wpoz < (int) (Camera.getX_edge_right() / TILE_SIZE) + 1; ++wpoz) {
                if (layers[nr_layers - 1][hpoz][wpoz] != 0)
                    g.drawImage(sprite.getTile(layers[nr_layers - 1][hpoz][wpoz]), (int) (wpoz * TILE_SIZE - Camera.getX_edge_left()), hpoz * TILE_SIZE, null);
            }
        }
*/

    }

    public static int getmWidthSize(){
        return M_WIDTH_SIZE;
    }


    public boolean getSolid(float w, float h){
        try {
            return layers[nr_layers - 1][(int) (h / TILE_SIZE)][(int) ((w+Camera.getX_edge_left())/ TILE_SIZE)] != 0;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

}
