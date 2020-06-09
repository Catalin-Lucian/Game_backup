package Worlds_Collide.Graphics;

import Worlds_Collide.Maps.Map;
import Worlds_Collide.RefLinks;


/// used track where to start and stop the drawing of a map
public  class Camera {

    private static final float x_boundMin=0;
    private static float x_boundMax;
    private static float x_edge_left;       /// pozitia de unde se incepe draw
    private static float x_edge_right;      /// pozitia unde se termina draw

    private static final float left_=(float)0.2*RefLinks.GetWidth();
    private static final float right_=(float)0.65*RefLinks.GetWidth();




    public Camera (){
        RefLinks.SetCam(this);
        init();
    }

    /// inits camera with data from map
    public static void init(){
        x_boundMax= Map.getmWidthSize();;
        x_edge_left=0;
        x_edge_right=RefLinks.GetWidth();
    }

    /// checks if it shooed move the camera based on players position and speed
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

    /// moves the edges
    private static void move (float f){
        x_edge_left+=f;
        x_edge_right+=f;
    }


    public static void setX_boundMax(float size){
        x_boundMax=size;
    }///< set x bounds
    public static float getX_edge_left() {
        return x_edge_left;
    }///< return left edge
    public static float getX_edge_right() {
        return x_edge_right;
    }///< return right edge


}
