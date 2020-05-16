package PaooGame.__Utils;

import PaooGame.RefLinks;

public class Vector2D {

    public float x;
    public float y;

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

    public void addX(float f){ x+=f;}
    public void addY(float f){ y+=f;}
    public void setX(float f){ x=f;}
    public void setY(float f){ y=f;}

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
//
//    public boolean isONCamera(){
//
//
//    }
//
//    public boolean isOFMap()
//    {
//        if ()
//    }

    public boolean isUnderMap(){
        return y>RefLinks.GetHeight();
    }

}