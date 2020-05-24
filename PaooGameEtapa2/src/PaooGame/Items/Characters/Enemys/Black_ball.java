package PaooGame.Items.Characters.Enemys;

import PaooGame.Animating.Animation;
import PaooGame.GUI.Health_bar;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Camera;
import PaooGame.Items.Characters.Enemy;
import PaooGame.__Utils.Vector2D;

import java.awt.*;

public class Black_ball extends Enemy {

    public Black_ball(float x,float y,float size){
        super(new Vector2D(x,y), (int)(Assets.black_ball.getW()*size),(int)(Assets.black_ball.getH()*size));


        bounds=new Rectangle((int)GetX()+20,(int)GetY()+30,width-40,height-60);
        attackBounds=new Rectangle((int)GetX()+50,(int)GetY()+50,width-100,height-100);
        pSprite=Assets.black_ball;


        animation=new Animation(pSprite.getSpriteArray(state));

        damage=2;
        range=350;
        speed=DEFAULT_SPEED-4;

        life=300;
        life_bar.setFull(300);

        state=idle;
    }


    @Override
    public void Update() {
        if(position.isONCamera()){
            getState();
            manageState();
            Move();
            animation.update();
        }
    }

    @Override
    public void Draw(Graphics g) {

        if(position.isONCamera())
        {
            if (animation.hasPlayedOnce()) inAction =false;

            if (state==attack || state==hit)
                life_bar.drawRed(g,life);

            g.drawImage(animation.getImage(),(int)(GetX()- Camera.getX_edge_left()),(int)GetY(),width,height,null);
            g.setColor(Color.red);
            g.drawRect((int)(bounds.x-Camera.getX_edge_left()),bounds.y,bounds.width,bounds.height);
            g.drawRect((int)(attackBounds.x-Camera.getX_edge_left()),attackBounds.y,attackBounds.width,attackBounds.height);

        }

    }


}


