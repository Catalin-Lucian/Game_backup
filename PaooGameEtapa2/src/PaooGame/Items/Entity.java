package PaooGame.Items;

import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.__Utils.Vector2D;

import java.awt.*;

public abstract class Entity extends Item
{
    public static final int DEFAULT_LIFE            = 10;
    public static final float DEFAULT_SPEED         = 7.0f;
    public static final float DEFAULT_Jump         = 2.0f;
    public static final int DEFAULT_CREATURE_WIDTH  = 48;
    public static final int DEFAULT_CREATURE_HEIGHT = 48;

    protected int life;
    protected float speed;
    protected float xMove;
    protected float yMove;
    protected boolean inAir=true;


   // protected Animation animation;

    public Entity( Vector2D pos, int width, int height)
    {
        super( pos, width, height);

        life    = DEFAULT_LIFE;
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;

     //   this.size=size;
     //   animation=new Animation();


    }

    public void Move()
    {
        MoveX();
        MoveY();
    }

    public void MoveX()
    {
      position.addX(xMove);
      bounds.x +=(int)xMove;
    }

    public void MoveY()
    {
        position.addY(yMove);
        bounds.y +=yMove;
    }
    public int GetLife()
    {
        return life;
    }
    public float GetSpeed()
    {
        return speed;
    }
    public void SetLife(int life)
    {
        this.life = life;
    }
    public void SetSpeed(float speed)
    {
        this.speed = speed;
    }
    public float GetXMove()
    {
        return xMove;
    }
    public float GetYMove()
    {
        return yMove;
    }
    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }
    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }



    public float canMoveX(float d){
        for (float h=bounds.y ; h<=(bounds.y+bounds.height); h+=bounds.height/2.) {
            if (d>0 && RefLinks.GetMap().getSolid(bounds.x + bounds.width + d, h) ||
                    d<0 && RefLinks.GetMap().getSolid(bounds.x + d, h))
                return 0;
        }
        return d;
    }

    public float canMoveY(float d){
        for (float w=bounds.x ; w<=(bounds.x+bounds.width); w+=bounds.width/2.) {
            if (d> 0 && RefLinks.GetMap().getSolid(w, bounds.y + bounds.height + d)){
                inAir=false;
                return 0;
            }
            if (d< 0 && RefLinks.GetMap().getSolid(w, bounds.y + d) ) return 0;
        }
        return d;
    }

}

