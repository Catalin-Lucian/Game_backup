package PaooGame.Items;

import java.awt.*;

import PaooGame.Animating.Animation;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Camera;
import PaooGame.Graphics.EntitySprite;
import PaooGame.Input.MouseHandler;
import PaooGame.RefLinks;
import PaooGame.__Utils.Vector2D;


public class Hero extends Entity
{

    private Animation animation;
    private EntitySprite pSprite;

    private boolean rightDir=true;   // true = right   false = left
    private boolean playingCAnim=false; // if true hero can't move till the end of anim

    private S_anim state = S_anim.idle;
    private int attack=0;
    private float jump_speed=0;




    public Hero( float x, float y)
    {
        super(new Vector2D(x,y),Assets.player.getW(),Assets.player.getH());
        pSprite=Assets.player;
        animation=new Animation(pSprite.getSpriteArray(S_anim.R_idle.ordinal()));
    }

    public Hero( float x, float y,float size)
    {
        super(new Vector2D(x,y),(int)(Assets.player.getW()*size),(int)(Assets.player.getH()*size));

        bounds.x=(int)(x+Assets.player.getW()*size/3);
        bounds.y=(int)(y+Assets.player.getH()*size/3);
        bounds.width=(int)(Assets.player.getW()*size)/3;
        bounds.height=(int)(Assets.player.getH()*size/2);

        pSprite=Assets.player;
        animation=new Animation(pSprite.getSpriteArray(S_anim.R_idle.ordinal()));
    }


    @Override
    public void Update()
    {
        actionSet();
        actionControl();

        Move();
        animation.update();
    }

    @Override
    public void Move() {
        MoveY();
        if (Camera.moveCamera(position.x,xMove)) return;
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
        if (!playingCAnim && !inAir) {
            if (life == 0) {
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
                if (attack==3) animation.setDelay(5);
                return;
            }


            getMove();
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
        }
    }


    protected void nextAttack(){
        attack++;
        if (attack==4) attack=1;
    }

    protected void setAnimation(S_anim anim,boolean continu) {
        playingCAnim=continu;
        if (anim!=state){
            animation.setFrames(pSprite.getSpriteArray(anim.get(anim,rightDir,attack)));
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
        if(animation.getFrame()>3 && animation.getFrame()<10) {
            if (rightDir) xMove= canMoveX(speed+2);
            else    xMove= canMoveX(-speed-2);
        }
        else{
            if(rightDir) xMove= canMoveX(2.f);
            else    xMove= canMoveX(-2.f);
        }
    }

    protected void setParry(){
        if (MouseHandler.mouseB!=3) playingCAnim=false;
        if(animation.getFrame()==9) animation.setDelay(-1);
    }

    protected void setJump(){
        getMove();
        if (animation.getFrame()==7 && inAir) animation.setDelay(-1);
        else animation.setDelay(3);
    }

    protected void gravity() {
        jump_speed=canMoveY(++jump_speed);
        inAir= jump_speed != 0;
        yMove=jump_speed;
    }

    @Override
    public void Draw(Graphics g)
    {
        if(playingCAnim && animation.hasPlayed(1)){
            playingCAnim=false;
        }

        g.drawImage(animation.getImage(),(int)GetX(),(int)GetY(),width,height,null);

        g.setColor(Color.black);
        g.drawRect(bounds.x,bounds.y,bounds.width,bounds.height);
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



    public  int get(S_anim state, boolean right, int nr){
        switch (state){
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
