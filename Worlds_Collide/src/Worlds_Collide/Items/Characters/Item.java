package Worlds_Collide.Items.Characters;

import Worlds_Collide.__Utils.Vector2D;

import java.awt.*;


///base class for every item on map
public abstract class Item
{
    protected Vector2D position;
    protected int width;
    protected int height;

    protected Rectangle bounds;
    protected Rectangle attackBounds;



    public Item( Vector2D position, int width, int height)
    {

        this.position=position;
        this.width = width;
        this.height = height;

        bounds=new Rectangle(0,0,0,0);
        attackBounds=new Rectangle(0,0,0,0);
    }

    public abstract void Update();
    public abstract void Draw(Graphics g);


    public float GetX()
    {
        return position.getX();
    }

    public float GetY()
    {
        return position.getY();
    }

    public int GetWidth()
    {
        return width;
    }

    public int GetHeight()
    {
        return height;
    }

    public void SetX(float x)
    {
        position.setX(x);
    }

    public void SetY(float y)
    {
        position.setY(y);
    }

    public void SetWidth(int width)
    {
        this.width = width;
    }


    public void SetHeight(int height)
    {
        this.height = height;
    }

    public void SetAttackMode()
    {
        bounds = attackBounds;
    }

    public Vector2D getPos(){
        return position;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public Rectangle getAttackBounds(){
        return attackBounds;
    }
}
