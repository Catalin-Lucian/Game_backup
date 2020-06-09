package Worlds_Collide.__Utils;

import Worlds_Collide.Graphics.Camera;
import Worlds_Collide.Input.MouseHandler;
import Worlds_Collide.Maps.Map;
import Worlds_Collide.RefLinks;


///class used for positioning
public class Vector2D {

    private float x;
    private float y;

    /// constructor no parameters
    public Vector2D(){
        x=0;
        y=0;
    }

    /// "copy" constructor
    public Vector2D(Vector2D vect){
        new Vector2D(vect.x,vect.y);
    }

    /// constructor with parameters x and y
    public Vector2D(float x, float y){
        this.y=y;
        this.x=x;
    }

    public void addX(float f){ x+=f; }///< adds to x
    public void addY(float f){ y+=f;}///< adds to y
    public void setX(float f){ x=f;}///< sets x
    public void setY(float f){ y=f;}///< sets y

    public float getY() {
        return y;
    }///< returns y

    public float getX() {
        return x;
    }///< return x

    /// set x and y
    public void setVector(Vector2D poz){
            this.x=poz.x;
            this.y=poz.y;
    }

    /// set x and y
    public void setVector(float x,float y){
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString()
    {
       return x+","+y;
    }///< return string x and y

    public boolean isONCamera(){
        return (x> Camera.getX_edge_left() && x<Camera.getX_edge_right());
    }///< check if position is on camera

    public boolean isUnderMap(){
        return y>RefLinks.GetHeight();
    }///<check if position is under map

    public boolean underMouse(float width,float height){
        return (MouseHandler.mouseX>x && MouseHandler.mouseX<x+width &&
                MouseHandler.mouseY>y && MouseHandler.mouseY<y+height);
    }///< verifies if position + width and height is under mouse


    /// checks if has pass left edge of camera
    public boolean onLeftEdge(){
        return x<0;
    }
    /// checks if has pass right edge of camera
    public boolean onRightEdge(){
        return x> Map.getmWidthSize();
    }

}
