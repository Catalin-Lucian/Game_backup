package PaooGame.Items.Characters;


import PaooGame.Items.Characters.Enemys.E_anim;
import PaooGame.RefLinks;
import PaooGame.__Utils.Vector2D;

public abstract class Enemy extends Entity {

    protected int count=0;

    protected float range;
    protected E_anim state= E_anim.R_attack;

    protected float d;
    protected int attackSpeed;

    public Enemy(Vector2D pos, int width, int height,float range,int damage,int attackSpeed) {
        super(pos, width, height);
        this.damage=damage;
        this.range=range;
        this.attackSpeed=attackSpeed;
    }

    protected float detectHeroDistance(){

        float d = position.getX() - RefLinks.getHeroX();
        if (range > Math.abs(d)) return d;
        return 0;
    }

    protected boolean inAttackRange(){
        return RefLinks.getHeroBound().intersects(attackBounds);
    }

    public void getHit(int damage){
        life -= damage;
        if (life<0){
            if (rightDir) setAnimation(E_anim.R_dead);
            else setAnimation(E_anim.L_dead);
            animation.setDelay(3);
            playingCAnim=true;
            return;
        }
        if (!rightDir) {
            setAnimation(E_anim.L_hit);
            xMove=canMoveX(100);
        } else {
            setAnimation(E_anim.R_hit);
            xMove=canMoveX(-100);
        }
        animation.setDelay(7);
        playingCAnim=true;
    }

    protected  void performAction(){
        d=detectHeroDistance();
        gravity();
        if (d==0){
            _idle_();
        }
        else {
            if(inAttackRange()){
                _attack_();
                animation.setDelay(6);
            }
            else _movement_();
        }
    }


    protected void _idle_(){
        if(rightDir) setAnimation(E_anim.R_idle);
        else setAnimation(E_anim.L_idle);
    }

    protected void _attack_(){
        if (rightDir) setAnimation(E_anim.R_attack);
        else setAnimation(E_anim.L_attack);
      //  animation.setDelay(5);
        hitPlayer();
    }
    protected void _movement_(){
        if(d>0){
            rightDir=false;
            xMove=canMoveX(-speed);
            setAnimation(E_anim.L_move);
        }
        if(d<0){
            rightDir=true;
            xMove=canMoveX(speed);
            setAnimation(E_anim.R_move);
        }
    }

    protected void setAnimation(E_anim anim){
        if (anim!=state){
            animation.setFrames(pSprite.getSpriteArray(anim.ordinal()));
            state=anim;
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



    protected abstract void hitPlayer();
}

