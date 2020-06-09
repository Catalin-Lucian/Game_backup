package Worlds_Collide.Items;

import java.awt.*;


import Worlds_Collide.Animating.Animation;
import Worlds_Collide.GUI.Health_bar;
import Worlds_Collide.Graphics.Assets;
import Worlds_Collide.Graphics.Camera;
import Worlds_Collide.Input.MouseHandler;
import Worlds_Collide.Items.Characters.Entity;
import Worlds_Collide.Maps.Map;
import Worlds_Collide.RefLinks;
import Worlds_Collide.__Utils.DataBase;
import Worlds_Collide.__Utils.Vector2D;


/// the player class
public class Player extends Entity
{
    /// all action states
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




    private short state = idle;///<start state/ current state
    private short attackType=0;

    private boolean continueAttacking=false;

    protected int potions=0;///< nr of potions

    protected Animation pAnimation;///< potion animation
    protected Vector2D startMapPosition;///< position for start of map


    protected int fulllife;///< max life
    protected int count=0;

    protected int deaths=0;///< number of deaths
    protected Font myfont=new Font("Monospaced", Font.BOLD,25);


    public Player(float x, float y)
    {
        super(new Vector2D(x,y),Assets.player.getW(),Assets.player.getH());
        startMapPosition=new Vector2D(position);

        pSprite=Assets.player;
        bounds=new Rectangle(0,0,0,0);
        setBounds();
        setAttackBounds();
        animation=new Animation(pSprite.getSpriteArray(R_idle));
        pAnimation=new Animation(Assets.potion.getSpriteArray());
        pAnimation.setDelay(100);

        damage=10;

        life=1000;
        fulllife=life;
        life_bar=new Health_bar(0,0,life);

        state=idle;
        lastAnimation=R_idle;
        RefLinks.setPlayer(this);
    }

    public Player(float x, float y, float size)
    {
        super(new Vector2D(x,y),(int)(Assets.player.getW()*size),(int)(Assets.player.getH()*size));

        startMapPosition=new Vector2D(position);
        pSprite=Assets.player;

        bounds=new Rectangle(0,0,0,0);
        setBounds();
        setAttackBounds();
        animation=new Animation(pSprite.getSpriteArray(R_idle));
        pAnimation=new Animation(Assets.potion.getSpriteArray());
        animation.setDelay(100);
        damage=10;

        life=1000;
        fulllife=life;
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
        pAnimation.update();
        pAnimation.setDelay(10);


        if(direction==RIGHT) attackBounds.x=bounds.x;
        else    attackBounds.x=bounds.x-width/3;

        onEdge();
    }

    ///move player or camera
    @Override
    public void Move() {
        MoveY();
        if (!Camera.moveCamera(GetX(), xMove))
            MoveX();
    }

    /// set collision bounds
    public void setBounds(){
        bounds.x=(int)(position.getX()+width/2-20);
        bounds.y=(int)(position.getY()+width/3);
        bounds.width=40;
        bounds.height=(int)(height/2.3);
    }

    ///set attack  bounds
    public void setAttackBounds(){
        attackBounds=new Rectangle(bounds.x,bounds.y,bounds.width+width/3,bounds.height);
    }


    ///get player movement
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

    ///get state
    protected void getState() {
        getMove();
        count++;
        if (RefLinks.GetKeyHandler().K_q.clicked && count>10) {
            count=0;
            if(potions>0 ){
                if (life+100>fulllife){
                    life=fulllife;
                }
                else {
                    life += 100;
                }
                potions--;
            }
        }

        if (!inAction && !inAir) {
            if (RefLinks.GetKeyHandler().K_shift.clicked) {
                inAction=true;
                setAnimation(roll);
                animation.setDelay(3);
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
                animation.setDelay(1);
                return;
            }
            if (MouseHandler.mouseB == 1) {
                inAction=true;
                nextAttack();
                setAnimation(attack);
                if (attackType != 3)  animation.setDelay(2);
                else animation.setDelay(1);
                return;
            }

            if (xMove!=0) setAnimation(run);
            else setAnimation(idle);
        }

    }

    ///check what state is in and calles state method
    protected void manageState(){
        switch (state){
            case roll: setRoll();break;
            case jump: setJump();break;
            case parry: setParry();break;
            case attack: setAttack();break;
            case dead: setDead();break;
            case hit : setHit();

        }
        gravity();
    }

    ///manage hit state
    protected void setHit(){
        if (RefLinks.GetKeyHandler().K_shift.clicked) setAnimation(roll);
        if (RefLinks.GetKeyHandler().K_space.clicked) {
            inAir=true;
            inAction=true;
            jump_speed=-18;
            setAnimation(jump);
            animation.setDelay(5);
        }
     }

    ///method called by enemies
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

    ///manage dead state
    protected void setDead(){
        if (animation.onLastFrame()) resetAll();
        xMove=0;
    }

    ///manage roll state
    protected void setRoll(){
        if(direction==RIGHT) xMove= canMoveX(speed+3);
        else    xMove= canMoveX(-speed-3);
    }

    ///manage jump state
    protected void setJump(){
        if (animation.getFrame()==7 && inAir) animation.setDelay(-1);
        else animation.setDelay(3);
    }

    ///manage parry state
    protected void setParry(){
        if (MouseHandler.mouseB!=3) inAction =false;
        if (animation.getFrame()==9) animation.setDelay(-1);
        xMove=0;
    }

    ///manage attack state
    protected void setAttack(){
        int frame=animation.getFrame();
        if (frame==4){
            EntityManager.attack(damage,direction);
        }
        if (frame==9 ) {
            animation.setDelay(5);
            if (MouseHandler.mouseB==1) {
                continueAttacking = true;
                nextAttack();
            }
        }
        if(frame==6){
            animation.setDelay(2);
        }
        if(animation.onLastFrame()) {
            setAnimation(attack);
            if (attackType != 3)  animation.setDelay(2);
        }
        if (xMove!=0) {
            setAnimation(run);
            inAction=false;
        }
    }

    ///manage attack state
    protected void nextAttack(){
        attackType+=2;
        if (attackType>4) attackType=0;
        xMove=0;
    }

    ///sets animation if state is changed
    protected void setAnimation(short newState){
        state=newState;
        if (newState==attack) currentAnimation= (short) (newState+direction+attackType);
        else currentAnimation= (short) (newState+direction);
        if (currentAnimation!=lastAnimation){
            animation.setFrames(pSprite.getSpriteArray(currentAnimation));
            lastAnimation=currentAnimation;
        }
    }

    ///resets all stats an position
    protected void resetAll(){
        deaths++;
        potions=0;
        life=fulllife;
        position.setVector(startMapPosition);
        setBounds();
        setAttackBounds();
        Camera.init();
    }

    /// check if out of map and cals to change map
    public void onEdge(){
       if(bounds.x+Camera.getX_edge_left()> Map.getmWidthSize()){
           RefLinks.getMapManager().changeLVL(1);
           position.setX(position.getX()-bounds.x);
           setBounds();
           setAttackBounds();
           startMapPosition.setVector(position);
       }
    }

    public void setDeaths(int deaths){
        this.deaths=deaths;
    }///< stes nr deaths

    public int getDeaths() {
        return deaths;
    }

    public int getPotions() {
        return potions;
    }

    public void setPotions(int potions){
        this.potions=potions;
    }
    public void addPotion(){
        potions++;
    }

    @Override
    public int GetLife() {
        return super.GetLife();
    }

    @Override
    public void SetLife(int life) {
        super.SetLife(life);
    }

    ///save stats in database
    public void saveStats(DataBase d){
        d.updateLife(life);
        d.updateDeaths(deaths);
        d.updatePotions(potions);
        d.updatePosition((int)startMapPosition.getX(),(int)startMapPosition.getY());
    }

    ///set the position
    public void setPosition(int x ,int y){
        position.setVector(x,y);
        setBounds();
        setAttackBounds();
    }

    ///draw the animation
    @Override
    public void Draw(Graphics g)
    {
        if(inAction && animation.onLastFrame()){
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

    ///draw the UI
    public void drawBar(Graphics g){
        life_bar.drawGreen(g,life);
        g.setColor(Color.red);
        g.drawImage(pAnimation.getImage(),-30,550,160,160,null);
        g.setFont(myfont);
        g.drawString(Integer.toString(potions),100,700);
    }


}
