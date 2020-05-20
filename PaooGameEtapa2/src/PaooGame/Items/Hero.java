package PaooGame.Items;

import java.awt.*;

import PaooGame.Animating.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Camera;
import PaooGame.Input.MouseHandler;
import PaooGame.Items.Characters.Entity;
import PaooGame.RefLinks;
import PaooGame.__Utils.Vector2D;


public class Hero extends Entity
{

    private S_anim state = S_anim.idle;
    private int attack=0;


    public Hero( float x, float y)
    {
        super(new Vector2D(x,y),Assets.player.getW(),Assets.player.getH());
        pSprite=Assets.player;
        bounds.x=(int)(x+Assets.player.getW());
        bounds.y=(int)(y+Assets.player.getH());
        bounds.width=Assets.player.getW();
        bounds.height=Assets.player.getH();
        animation=new Animation(pSprite.getSpriteArray(S_anim.R_idle.ordinal()));
        attackBounds=new Rectangle(bounds.x,bounds.y,bounds.width+width/3,bounds.height);
        RefLinks.setHero(this);
    }

    public Hero( float x, float y,float size)
    {
        super(new Vector2D(x,y),(int)(Assets.player.getW()*size),(int)(Assets.player.getH()*size));

        bounds.x=(int)(x+Assets.player.getW()*size/3);
        bounds.y=(int)(y+Assets.player.getH()*size/3);
        bounds.width=(int)(Assets.player.getW()*size)/3;
        bounds.height=(int)(Assets.player.getH()*size/2);

        attackBounds=new Rectangle(bounds.x,bounds.y,bounds.width+width/3,bounds.height);

        damage=10;
        pSprite=Assets.player;
        animation=new Animation(pSprite.getSpriteArray(S_anim.R_idle.ordinal()));
        RefLinks.setHero(this);
    }


    @Override
    public void Update()
    {
        actionSet();
        actionControl();

        Move();
        animation.update();

        if(rightDir) attackBounds.x=bounds.x;
        else    attackBounds.x=bounds.x-width/3;
    }

    @Override
    public void Move() {
        MoveY();
        if (!Camera.moveCamera(GetX(),xMove))
            MoveX();
    }



    protected void getMove(){
        xMove=0;
        if (RefLinks.GetKeyHandler().K_left.down) {
            xMove=canMoveX(-speed);
            rightDir = false;
        }

        if (RefLinks.GetKeyHandler().K_right.down) {
            xMove= canMoveX(speed);
            rightDir = true;
        }

    }

    protected void actionSet()
    {
        getMove();
        if (!inAction && !inAir) {
            if (life < 0) {
                setAnimation(S_anim.dead,true);
                return;
            }
            if (RefLinks.GetKeyHandler().K_shift.clicked)
            {
                setAnimation(S_anim.roll,true);
                return;
            }
            if (RefLinks.GetKeyHandler().K_space.clicked)
            {
                inAir=true;
                jump_speed=-20;
                setAnimation(S_anim.jump,true);
                animation.setDelay(5);
                return;
            }
            if (MouseHandler.mouseB == 3)
            {
                setAnimation(S_anim.parry,true);
                return;
            }
            if (MouseHandler.mouseB == 1) {
                nextAttack();
                setAnimation(S_anim.attack,true);
                if (attack != 3)  animation.setDelay(3);
                return;
            }

            if (xMove<0) setAnimation(S_anim.L_run);
            else if (xMove>0) setAnimation(S_anim.R_run);
            else setAnimation(S_anim.idle,false);
        }
    }

    protected void actionControl(){
        gravity();
        switch (state){
            case roll: setRoll();break;
            case jump: setJump();break;
            case parry: setParry();break;
            case attack: setAttack();break;

        }
    }


    protected void nextAttack(){
        attack++;
        if (attack==4) attack=1;
        xMove=0;
    }

    protected void setAttack(){
        if (animation.getFrame()==8) EntityManager.attack(damage);
        xMove=0;
    }

    protected void setAnimation(S_anim anim,boolean continu) {
        inAction =continu;
        if (anim!=state){
            animation.setFrames(pSprite.getSpriteArray(anim.get(rightDir,attack)));
            state=anim;
        }
    }
    protected void setAnimation(S_anim anim){
        if (anim!=state){
            animation.setFrames(pSprite.getSpriteArray(anim.ordinal()));
            state=anim;
        }
    }

    protected void resetAll(){
        //refLink.GetMap().reset();
        // --- reset life --- reset pozition ---
    }

    protected void setRoll(){

            if(rightDir) xMove= canMoveX(speed);
            else    xMove= canMoveX(-speed);

    }

    protected void setParry(){
        if (MouseHandler.mouseB!=3) inAction =false;
        if(animation.getFrame()==9) animation.setDelay(-1);
        xMove=0;
    }

    protected void setJump(){
        getMove();
        if (animation.getFrame()==7 && inAir) animation.setDelay(-1);
        else animation.setDelay(3);
    }

    public void getHit(int damage){
        if (state!=S_anim.parry && state!=S_anim.attack )
        {
          //  life -=damage;
            setAnimation(S_anim.hit,true);
            animation.setDelay(3);
        }

    }


    @Override
    public void Draw(Graphics g)
    {
        if(inAction && animation.hasPlayed(1)){
            inAction =false;
        }

        g.drawImage(animation.getImage(),(int)GetX(),(int)GetY(),width,height,null);

//        g.setColor(Color.black);
//        g.drawRect(bounds.x,bounds.y,bounds.width,bounds.height);
//        g.setColor(Color.blue);
//        g.drawRect(attackBounds.x,attackBounds.y,attackBounds.width,attackBounds.height);
//        g.setColor(Color.red);
//        g.drawRect((int)GetX(),(int)GetY(),width,height);

    }


}



enum S_anim {
    R_attack_1,     L_attack_1,
    R_attack_2,     L_attack_2,
    R_attack_3,     L_attack_3,
    R_dead,         L_dead,
    R_hit,          L_hit,
    R_idle,         L_idle,
    R_parry,        L_parry,
    R_roll,         L_roll,
    R_run,          L_run,
    R_jump,         L_jump,


    attack,
    dead,
    hit,
    idle,
    parry,
    roll,
    run,
    jump;



    public  int get(boolean right, int nr){
        switch (this){
            case jump:
                if (right) return R_jump.ordinal();
                else return L_jump.ordinal();
            case run:
                if (right) return R_run.ordinal();
                else return L_run.ordinal();
            case roll:
                if (right) return R_roll.ordinal();
                else return L_roll.ordinal();
            case parry:
                if (right) return R_parry.ordinal();
                else return L_parry.ordinal();
            case idle:
                if (right) return R_idle.ordinal();
                else return L_idle.ordinal();
            case hit:
                if (right) return R_hit.ordinal();
                else return L_hit.ordinal();
            case dead:
                if (right) return R_dead.ordinal();
                else return L_dead.ordinal();
            case attack:
                switch (nr){
                    case 1:
                        if (right) return R_attack_1.ordinal();
                        else       return L_attack_1.ordinal();
                    case 2:
                        if (right) return R_attack_2.ordinal();
                        else return L_attack_2.ordinal();
                    case 3:
                        if(right)return R_attack_3.ordinal();
                        else return L_attack_3.ordinal();
                }
            default: return R_idle.ordinal();
        }
    }
}
