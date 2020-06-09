package Worlds_Collide.Items.Characters.Enemys;

import Worlds_Collide.Animating.Animation;
import Worlds_Collide.Graphics.Assets;
import Worlds_Collide.Items.Characters.Enemy;
import Worlds_Collide.__Utils.Vector2D;

import java.awt.*;

public class Mushroom extends Enemy {

    public Mushroom(float x,float y,float size){
        super(new Vector2D(x,y), (int)(Assets.black_ball.getW()*size),(int)(Assets.black_ball.getH()*size));
        pSprite=Assets.mushroom;
        animation=new Animation(pSprite.getSpriteArray(state));

        bounds=new Rectangle((int)GetX()+20,(int)GetY()+30,width-40,height-60);
        attackBounds=new Rectangle((int)GetX()+50,(int)GetY()+50,width-100,height-100);


        damage=2;
        range=350;
        speed=DEFAULT_SPEED-4;


        state=idle;
    }


    @Override
    public void Update() {
        super.Update();
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
    }


}
