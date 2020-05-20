package PaooGame.Graphics;

import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.__Utils.Vector2D;

public  class Camera {

    private static final float x_boundMin=0;
    private static float x_boundMax;
    private static float x_edge_left;       // pozitia de unde se incepe draw
    private static float x_edge_right;      // pozitia unde se termina draw

    private static final float left_=(float)0.2*RefLinks.GetWidth();
    private static final float right_=(float)0.65*RefLinks.GetWidth();




    public Camera (){
        x_boundMax= Map.getmWidthSize();;
        x_edge_left=0;
        x_edge_right=RefLinks.GetWidth();
        RefLinks.SetCam(this);
    }

    public static boolean moveCamera(float x,float f){
        if (f>0 && x>right_ && x_edge_right+f<x_boundMax) {
            move(f);
            return true;
        }
        if (f<0 && x<left_&& x_edge_left+f>x_boundMin){
            move(f);
            return true;
        }
        return false;
    }

    private static void move (float f){
        x_edge_left+=f;
        x_edge_right+=f;
    }


    public static void setX_boundMax(float size){
        x_boundMax=size;
    }

    public static float getX_edge_left() {
        return x_edge_left;
    }

    public static float getX_edge_right() {
        return x_edge_right;
    }
}
