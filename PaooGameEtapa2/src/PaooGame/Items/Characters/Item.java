package PaooGame.Items.Characters;

import PaooGame.__Utils.Vector2D;
import javafx.scene.shape.Circle;

import java.awt.*;



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
