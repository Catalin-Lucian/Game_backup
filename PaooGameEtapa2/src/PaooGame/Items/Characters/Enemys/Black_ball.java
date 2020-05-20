package PaooGame.Items.Characters.Enemys;

import PaooGame.Animating.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Camera;
import PaooGame.Items.Characters.Enemy;
import PaooGame.Items.EntityManager;
import PaooGame.RefLinks;
import PaooGame.__Utils.Vector2D;

import java.awt.*;

public class Black_ball extends Enemy {

    public Black_ball(float x,float y,float range,int damage,int attackSpeed,float size){
        super(new Vector2D(x,y), (int)(Assets.black_ball.getW()*size),(int)(Assets.black_ball.getH()*size),range,damage,attackSpeed);
        bounds=new Rectangle((int)GetX()+10,(int)GetY()+30,width-20,height-60);
        attackBounds=new Rectangle((int)GetX()+50,(int)GetY()+50,width-100,height-100);
        pSprite=Assets.black_ball;
        animation=new Animation(pSprite.getSpriteArray(state.ordinal()));
        speed=DEFAULT_SPEED-4;
    }


    @Override
    public void Update() {
        if(position.isONCamera()){
            if (!playingCAnim) {
                if (life<0) EntityManager.removeEnemy(this);
                performAction();
            }
            gravity();
            Move();
            animation.update();
            if (animation.hasPlayedOnce()) playingCAnim=false;
        }
    }

    @Override
    public void Draw(Graphics g) {

        if(position.isONCamera())
        {

            g.drawImage(animation.getImage(),(int)(GetX()- Camera.getX_edge_left()),(int)GetY(),width,height,null);
            g.setColor(Color.red);
            g.drawRect((int)(bounds.x-Camera.getX_edge_left()),bounds.y,bounds.width,bounds.height);
            g.drawRect((int)(attackBounds.x-Camera.getX_edge_left()),attackBounds.y,attackBounds.width,attackBounds.height);

        }

    }

    @Override
    protected void hitPlayer() {
        if (animation.getFrame()==3) RefLinks.getHero().getHit(damage);
    }
}


