package Worlds_Collide.__Utils;

import Worlds_Collide.Graphics.Camera;
import Worlds_Collide.Input.MouseHandler;
import Worlds_Collide.RefLinks;

public class Vector2D {

    private float x;
    private float y;


    public Vector2D(){
        x=0;
        y=0;
    }

    public Vector2D(Vector2D vect){
        new Vector2D(vect.x,vect.y);
    }

    public Vector2D(float x, float y){
        this.y=y;
        this.x=x;
    }

    public void addX(float f){ x+=f; }
    public void addY(float f){ y+=f;}
    public void setX(float f){ x=f;}
    public void setY(float f){ y=f;}

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public void setVector(Vector2D poz){
            this.x=poz.x;
            this.y=poz.y;
        }

    public void setVector(float x,float y){
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString()
    {
       return x+","+y;
    }

    public boolean isONCamera(){
        return (x> Camera.getX_edge_left() && x<Camera.getX_edge_right());
    }

    public boolean isUnderMap(){
        return y>RefLinks.GetHeight();
    }

    public boolean underMouse(float width,float height){
        return (MouseHandler.mouseX>x && MouseHandler.mouseX<x+width &&
                MouseHandler.mouseY>y && MouseHandler.mouseY<y+height);
    }

}
