package Worlds_Collide.Items.Characters.Enemys;

import Worlds_Collide.Animating.Animation;
import Worlds_Collide.Graphics.Assets;
import Worlds_Collide.Items.Characters.Enemy;
import Worlds_Collide.RefLinks;
import Worlds_Collide.__Utils.Vector2D;

import java.awt.*;

public class Boss extends Enemy {



    public Boss(float x,float y,int size) {
        super(new Vector2D(x,y), Assets.boss.getWidth()*size, Assets.boss.getHeight()*size);
        pSprite=Assets.boss;
        animation=new Animation(pSprite.getSpriteArray(state));

        bounds.x=(int)(position.getX()+width/3);
        bounds.y=(int)(position.getY()+width/3);
        bounds.width=width/3;
        bounds.height=(int)(height/2.3);
        attackBounds=new Rectangle(bounds);
        attackBounds.width=bounds.width+bounds.width;

        range=1000;
        life=200*RefLinks.getDifficulty();
        life_bar.setFull(life);

        attackframe=13;
        animationspeed=4;

        damage=40* RefLinks.getDifficulty();
        speed=4f;
    }

    @Override
    public void Update() {
        super.Update();
        if(direction==RIGHT){
            attackBounds.x=bounds.x;
        }
        else{
            attackBounds.x=bounds.x-bounds.width;
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }



}
