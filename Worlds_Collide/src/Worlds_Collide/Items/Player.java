package Worlds_Collide.Items;

import java.awt.*;

import Worlds_Collide.Animating.Animation;
import Worlds_Collide.GUI.Health_bar;
import Worlds_Collide.Graphics.Assets;
import Worlds_Collide.Graphics.Camera;
import Worlds_Collide.Input.MouseHandler;
import Worlds_Collide.Items.Characters.Entity;
import Worlds_Collide.RefLinks;
import Worlds_Collide.__Utils.DataBase;
import Worlds_Collide.__Utils.Vector2D;


public class Player extends Entity
{
    protected final short R_attack_1=0, L_attack_1=1;
    protected final short R_attack_2=2, L_attack_2=3;
    protected final short R_attack_3=4, L_attack_3=5;
    protected final short R_dead=6,     L_dead=7;
    protected final short R_hit=8,      L_hit=9;
    protected final short R_idle=10,    L_idle=11;
    protected final short R_parry=12,   L_parry=13;
    protected final short R_roll=14,    L_roll=15;
    protected final short R_run=16,     L_run=17;
    protected final short R_jump=18,    L_jump=19;
    protected final short attack=R_attack_1;
    protected final short dead=R_dead;
    protected final short hit=R_hit;
    protected final short idle=R_idle;
    protected final short parry=R_parry;
    protected final short roll=R_roll;
    protected final short run=R_run;
    protected final short jump=R_jump;




    private short state = idle;
    private short attackType=0;
    private boolean continueAttacking=false;


    public Player(float x, float y)
    {
        super(new Vector2D(x,y),Assets.player.getW(),Assets.player.getH());
        pSprite=Assets.player;
        bounds=new Rectangle((int)(x+Assets.player.getW()),(int)(y+Assets.player.getH()),Assets.player.getW(),Assets.player.getH());
        attackBounds=new Rectangle(bounds.x,bounds.y,bounds.width+width/3,bounds.height);
        animation=new Animation(pSprite.getSpriteArray(R_idle));

        damage=10;

        life=1000;
        life_bar=new Health_bar(0,0,life);

        state=idle;
        lastAnimation=R_idle;
        RefLinks.setPlayer(this);
    }

    public Player(float x, float y, float size)
    {
        super(new Vector2D(x,y),(int)(Assets.player.getW()*size),(int)(Assets.player.getH()*size));
        pSprite=Assets.player;
        bounds=new Rectangle((int)(x+pSprite.getW()*size/3.5),(int)(y+pSprite.getH()*size/3),(int)(pSprite.getW()*size)/4,(int)(pSprite.getH()*size/2));
        attackBounds=new Rectangle(bounds.x,bounds.y,bounds.width+width/5,bounds.height);
        animation=new Animation(pSprite.getSpriteArray(R_idle));

        damage=10;

        life=1000;
        life_bar=new Health_bar(0,0,life);

        state=idle;
        lastAnimation=R_idle;
        RefLinks.setPlayer(this);
    }


    @Override
    public void Update()
    {
        if (position.isUnderMap()) resetAll();
        getState();
        manageState();
        Move();
        animation.update();

        if(direction==RIGHT) attackBounds.x=bounds.x;
        else    attackBounds.x=bounds.x-width/5;
    }

    @Override
    public void Move() {
        MoveY();
        if (!Camera.moveCamera(GetX(), xMove))
            MoveX();

    }



    protected void getMove(){
        xMove=0;
        if (RefLinks.GetKeyHandler().K_left.down) {
            xMove=canMoveX(-speed);
            direction=LEFT;
        }
        if (RefLinks.GetKeyHandler().K_right.down) {
            xMove= canMoveX(speed);
            direction=RIGHT;
        }
    }

    protected void getState() {
        getMove();
        if (!inAction && !inAir) {
            if (RefLinks.GetKeyHandler().K_shift.clicked) {
                inAction=true;
                setAnimation(roll);
                return;
            }
            if (RefLinks.GetKeyHandler().K_space.clicked) {
                inAir=true;
                inAction=true;
                jump_speed=-18;
                setAnimation(jump);
                animation.setDelay(5);
                return;
            }
            if (MouseHandler.mouseB == 3) {
                inAction=true;
                setAnimation(parry);
                return;
            }
            if (MouseHandler.mouseB == 1) {
                inAction=true;
                nextAttack();
                setAnimation(attack);
                if (attackType != 3)  animation.setDelay(3);
                return;
            }

            if (xMove!=0) setAnimation(run);
            else setAnimation(idle);
        }
    }

    protected void manageState(){
        switch (state){
            case roll: setRoll();break;
            case jump: setJump();break;
            case parry: setParry();break;
            case attack: setAttack();break;
            case dead: setDead();break;
            case hit :
                if (RefLinks.GetKeyHandler().K_shift.clicked) setAnimation(roll);
        }
        gravity();
    }

    public void getHit(int damage){
        if (state!=parry && state!=roll)
        {
            life -= damage;
            if (life<0) state=dead;
            else state=hit;
            setAnimation(state);
            animation.setDelay(3);
            inAction=true;
        } else{
            life -= damage/5;
        }

    }

    protected void setDead(){
        if (animation.onLastFrame()) resetAll();
        xMove=0;
    }

    protected void setRoll(){
        if(direction==RIGHT) xMove= canMoveX(speed);
        else    xMove= canMoveX(-speed);
    }

    protected void setJump(){
        if (animation.getFrame()==7 && inAir) animation.setDelay(-1);
        else animation.setDelay(3);
    }

    protected void setParry(){
        if (MouseHandler.mouseB!=3) inAction =false;
        if (animation.getFrame()==9) animation.setDelay(-1);
        xMove=0;
    }

    protected void setAttack(){
        if (animation.getFrame()==4) EntityManager.attack(damage);
        if (animation.getFrame()==9 && MouseHandler.mouseB==1) {
            continueAttacking=true;
            nextAttack();
        }
        if(animation.onLastFrame()) {
            setAnimation(attack);
            if (attackType != 3)  animation.setDelay(3);
        }
        xMove =0;
    }

    protected void nextAttack(){
        attackType+=2;
        if (attackType>4) attackType=0;
        xMove=0;
    }

    protected void setAnimation(short newState){
        state=newState;
        if (newState==attack) currentAnimation= (short) (newState+direction+attackType);
        else currentAnimation= (short) (newState+direction);
        if (currentAnimation!=lastAnimation){
            animation.setFrames(pSprite.getSpriteArray(currentAnimation));
            lastAnimation=currentAnimation;
        }
    }

    protected void resetAll(){

    }

    @Override
    public int GetLife() {
        return super.GetLife();
    }

    @Override
    public void SetLife(int life) {
        super.SetLife(life);
    }

    public void saveLife(DataBase d){
        d.updateLife(life);
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

    public void drawBar(Graphics g){
        life_bar.drawGreen(g,life);
    }


}
