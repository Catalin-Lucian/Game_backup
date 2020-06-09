package Worlds_Collide.Items.Characters;

import Worlds_Collide.GUI.Health_bar;
import Worlds_Collide.Graphics.Camera;
import Worlds_Collide.Items.EntityManager;
import Worlds_Collide.RefLinks;
import Worlds_Collide.__Utils.Vector2D;

import java.awt.*;


///base class for all enemies
public abstract class Enemy extends Entity {

    /// all types of actions states
    protected static final short R_attack=0, L_attack=1;
    protected static final short R_dead=2, L_dead=3;
    protected static final short R_hit=4, L_hit=5;
    protected static final short R_idle=6, L_idle=7;
    protected static final short R_move=8,L_move=9;
    protected static final short attack=R_attack;
    protected static final short dead=R_dead;
    protected static final short hit=R_hit;
    protected static final short idle=R_idle;
    protected static final short move=R_move;


    protected float range;///< range to see player
    protected float disEtoH;///< distance to lpayer
    protected int attackframe=0;///<  in witch frame to attack
    protected int gothitdirect;///< direction when get hit
    protected int animationspeed=7;///< animation speed


    public Enemy(Vector2D pos, int width, int height) {
        super(pos, width, height);
        life_bar= new Health_bar(940,0,life);
    }

    /// detect if player in range
    public boolean detectHero(){
        disEtoH = position.getX() - RefLinks.getPlayerX();
        if (range < Math.abs(disEtoH)){
            disEtoH =0;
            return false;
        }
        return true;
    }

    /// detect if player in attack bounds
    public boolean inAttackRange(){
        return RefLinks.getPlayerBound().intersects(attackBounds);
    }

    /// get hit from player
    public void getHit(int damage,int direction){
        life -= damage;
        gothitdirect=direction;
        if (life<0) state=dead;
        else   {
            jump_speed=-10;
            pushBack=10;
            state=hit;
        }
        setAnimation(state);
        animation.setDelay(1);
        inAction=true;
    }

    /// set the state
    public void getState(){
       if(!inAction) {
           if (detectHero()) {
               if (inAttackRange()){
                    state = attack;
               }
               else state = move;
           } else state = idle;
       }
    }

    ///manage current state
    public void manageState(){
        gravity();
        if(position.isUnderMap()) EntityManager.removeEnemy(this);
        switch (state){
            case dead: _dead();break;
            case hit: _hit();break;
            case attack: _attack();break;
            case idle:  _idle();break;
            case move: _run();break;
        }
    }

    /// what to do when in state dead
    protected void _dead(){
        animation.setDelay(7);
        if(animation.onLastFrame()) EntityManager.removeEnemy(this);
    }

    /// what to do when in state hit
    protected void _hit(){
        --pushBack;
        if(pushBack<0) pushBack=0;
        if (gothitdirect==RIGHT) xMove=canMoveX(pushBack);
        else xMove=canMoveX(-pushBack);
    }

    /// what to do when in state attack
    protected void _attack(){
       if (!inAction) {
           setAnimation(state);
           animation.setDelay(4);
           inAction=true;
       }
       if (animation.getFrame()==attackframe)
           hitPlayer();

    }

    /// what to do when in state idle
    protected void _idle(){
        setAnimation(state);
    }

    /// what to do when in state run
    protected void _run(){
        if(disEtoH >0){
            direction=LEFT;
            if (disEtoH<speed)   xMove=canMoveX(-disEtoH-1);
            else xMove=canMoveX(-speed);
        }
        if(disEtoH <0){
            direction=RIGHT;
            if (-disEtoH<speed) xMove=canMoveX(-disEtoH+1);
            else xMove=canMoveX(speed);
        }
        setAnimation(state);
    }

    ///set the animation conform state
    public void setAnimation(short state){
        currentAnimation= (short) (state+direction);
        if (currentAnimation!=lastAnimation){
            animation.setFrames(pSprite.getSpriteArray(currentAnimation));
            lastAnimation=currentAnimation;

        }
        animation.setDelay(animationspeed);
    }


    ///checks if can move on x axis return speed or 0
    @Override
    public float canMoveX(float d) {
        for (float h=bounds.y ; h<=(bounds.y+bounds.height); h+=bounds.height/2.) {
            if (d>0 && RefLinks.GetMap().getSolid(bounds.x + bounds.width + d, h) ||
                    d<0 && RefLinks.GetMap().getSolid(bounds.x + d, h))
                return 0;
        }
        return d;
    }

    ///checks if can move on y axis return speed or 0
    @Override
    public float canMoveY(float d) {
        for (float w=bounds.x ; w<=(bounds.x+bounds.width); w+=bounds.width/2.) {
            if (d> 0 && RefLinks.GetMap().getSolid(w, bounds.y + bounds.height + d)){
                inAir=false;
                return 0;
            }
            if (d< 0 && RefLinks.GetMap().getSolid(w, bounds.y + d) ) return 0;
        }
        return d;
    }

    ///hits the player if in attack bounds
    protected void hitPlayer(){
        if(inAttackRange())
            RefLinks.getPlayer().getHit(damage);
    }

    /// check if enemy on camera
    public boolean onCamera(){
        return bounds.x>Camera.getX_edge_left() &&  bounds.x+bounds.width<Camera.getX_edge_right()+100;
    }

    ///if on camera updates
    @Override
    public void Update(){
        if(onCamera()){
            getState();
            manageState();
            Move();
            animation.update();
        }
    }

    ///if on camera draws
    @Override
    public void Draw(Graphics g) {

        if(onCamera())
        {
            if (animation.hasPlayedOnce()) inAction =false;

            if (state==attack || state==hit)
                life_bar.drawRed(g,life);

            g.drawImage(animation.getImage(),(int)(GetX()- Camera.getX_edge_left()),(int)GetY(),width,height,null);


//            g.setColor(Color.red);
//            g.drawRect((int)(bounds.x-Camera.getX_edge_left()),bounds.y,bounds.width,bounds.height);
//            g.setColor(Color.green);
//            g.drawRect((int)(attackBounds.x-Camera.getX_edge_left()),attackBounds.y,attackBounds.width,attackBounds.height);

        }

    }
}

