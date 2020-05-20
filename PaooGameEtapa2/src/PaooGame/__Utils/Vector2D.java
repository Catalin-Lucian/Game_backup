package PaooGame.__Utils;

import PaooGame.Graphics.Camera;
import PaooGame.RefLinks;
import javafx.scene.shape.Line;

import java.awt.geom.Line2D;
import java.sql.Ref;

public class Vector2D {

    private float x;
    private float y;


    private static final int RANGE=20;
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

}
