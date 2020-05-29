package Worlds_Collide.Items.Characters;


import Worlds_Collide.GUI.Health_bar;
import Worlds_Collide.Items.EntityManager;
import Worlds_Collide.RefLinks;
import Worlds_Collide.__Utils.Vector2D;

public abstract class Enemy extends Entity {

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


    protected float range;
    protected float disEtoH;


    public Enemy(Vector2D pos, int width, int height) {
        super(pos, width, height);
        life_bar= new Health_bar(940,0,life);
    }

    public boolean detectHero(){
        disEtoH = position.getX() - RefLinks.getPlayerX();
        if (range < Math.abs(disEtoH)){
            disEtoH =0;
            return false;
        }
        return true;
    }

    public boolean inAttackRange(){
        return RefLinks.getPlayerBound().intersects(attackBounds);
    }

    public void getHit(int damage){
        life -= damage;
        if (life<0) state=dead;
        else   {
            jump_speed=-10;
            pushBack=10;
            state=hit;
        }
        setAnimation(state);
        animation.setDelay(5);
        inAction=true;
    }

    public void getState(){
       if(!inAction) {
           if (detectHero()) {
               if (inAttackRange()) state = attack;
               else state = move;
           } else state = idle;
       }
    }

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

    protected void _dead(){
        if (!inAction){
            setAnimation(state);
            inAction=true;
        }
        if(animation.onLastFrame()) EntityManager.removeEnemy(this);
    }

    protected void _hit(){
        --pushBack;
        if(pushBack<0) pushBack=0;
        if (direction==LEFT) xMove=canMoveX(pushBack);
        else xMove=canMoveX(-pushBack);
    }

    protected void _attack(){
       if (!inAction) {
           setAnimation(state);
           animation.setDelay(6);
           hitPlayer();
           inAction=true;
       }
    }

    protected void _idle(){
        setAnimation(state);
    }

    protected void _run(){
        if(disEtoH >0){
            direction=LEFT;
            xMove=canMoveX(-speed);
        }
        if(disEtoH <0){
            direction=RIGHT;
            xMove=canMoveX(speed);
        }
        setAnimation(state);
    }

    public void setAnimation(short state){
        currentAnimation= (short) (state+direction);
        if (currentAnimation!=lastAnimation){
            animation.setFrames(pSprite.getSpriteArray(currentAnimation));
            lastAnimation=currentAnimation;
        }
    }



    @Override
    public float canMoveX(float d) {
        for (float h=bounds.y ; h<=(bounds.y+bounds.height); h+=bounds.height/2.) {
            if (d>0 && RefLinks.GetMap().getSolid(bounds.x + bounds.width + d, h) ||
                    d<0 && RefLinks.GetMap().getSolid(bounds.x + d, h))
                return 0;
        }
        return d;
    }

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



    protected void hitPlayer(){
        RefLinks.getPlayer().getHit(damage);
    }
}

