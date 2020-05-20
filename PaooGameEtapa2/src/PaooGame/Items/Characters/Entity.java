package PaooGame.Items.Characters;

import PaooGame.Animating.Animation;
import PaooGame.Graphics.Camera;
import PaooGame.Graphics.EntitySprite;
import PaooGame.RefLinks;
import PaooGame.__Utils.Vector2D;

public abstract class Entity extends Item
{
    public static final int DEFAULT_LIFE            = 100;
    public static final float DEFAULT_SPEED         = 7.0f;

    protected Animation animation;
    protected EntitySprite pSprite;

    protected boolean playingCAnim=false; // if true hero can't move till the end of anim

    protected boolean rightDir=true;
    protected boolean inAir=true;
    protected float jump_speed=0;

    protected int life;
    protected int damage;
    protected float speed;
    protected float xMove;
    protected float yMove;


    public Entity( Vector2D pos, int width, int height)
    {
        super( pos, width, height);

        life    = DEFAULT_LIFE;
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;

    }

    public void Move()
    {
        MoveX();
        MoveY();
    }

    public void MoveX()
    {
      position.addX(xMove);
      bounds.x +=xMove;
      attackBounds.x +=xMove;
      xMove=0;
    }

    public void MoveY()
    {
        position.addY(yMove);
        bounds.y +=yMove;
        attackBounds.y +=yMove;
        yMove=0;
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
            if (d>0 && RefLinks.GetMap().getSolid(bounds.x + bounds.width + d+Camera.getX_edge_left(), h) ||
                    d<0 && RefLinks.GetMap().getSolid(bounds.x + d+ Camera.getX_edge_left(), h))
                return 0;
        }
        return d;
    }

    public float canMoveY(float d){
        for (float w=bounds.x ; w<=(bounds.x+bounds.width); w+=bounds.width/2.) {
            if (d> 0 && RefLinks.GetMap().getSolid(w+Camera.getX_edge_left(), bounds.y + bounds.height + d)){
                inAir=false;
                return 0;
            }
            if (d< 0 && RefLinks.GetMap().getSolid(w+Camera.getX_edge_left(), bounds.y + d) ) return 0;
        }
        return d;
    }

    protected void gravity() {
        jump_speed=canMoveY(++jump_speed);
        inAir= jump_speed != 0;
        yMove=jump_speed;
    }

    public Animation getAnimation(){
        return animation;
    }

}

