package Worlds_Collide.Items;

import Worlds_Collide.Items.Characters.Enemy;
import Worlds_Collide.Items.Characters.Enemys.Black_ball;
import Worlds_Collide.Items.Characters.Enemys.Boss;
import Worlds_Collide.Items.Characters.Enemys.Mushroom;
import Worlds_Collide.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

///class that manages all enemies on amp
public class EntityManager {
    private static ArrayList<Enemy> enemies = new ArrayList<>();///< list of enemies
    private static final Random rd=new Random();

    ///class update for every enemy
    public void Update(){
       for (int i=0;i<enemies.size();++i) {
           enemies.get(i).Update();
       }
    }

    ///class draw for every enemy
    public void Draw(Graphics g){

        for (int i=0;i<enemies.size();++i) {
            enemies.get(i).Draw(g);
        }
    }

    ///add an enemy
    public static void addEnemy(Enemy e){
        enemies.add(e);
    }

    ///removes an enemy
    public static void removeEnemy(Enemy e){
        enemies.remove(e);
        if(rd.nextInt(10)<4) RefLinks.getPlayer().addPotion();
    }

    ///calls hit method for every enemy in list
    public static void attack(int damage,int direction){
        for (int i=0;i<enemies.size();++i) {
                if (RefLinks.getPlayerAttackBound().intersects(enemies.get(i).getBounds())) enemies.get(i).getHit(damage,direction);
        }
    }

    ///add random enemy in list
    public static void spawnEnemy(int x,int y){
        addEnemy(getRandomEnemy(x,y));
    }

    /// add boss in list
    public static void spawnBoss(int x,int y){
        addEnemy(new Boss(x,y,5));
    }

    /// return random enemy
    protected static Enemy getRandomEnemy(int x,int y){
        int i= rd.nextInt(2);
        System.out.println(i);
        switch (i){
            case 0: return new Black_ball(x,y,2);
            case 1: return new Mushroom(x,y,5);

            default:return null;
        }
    }

    /// reset the list
    public static void reset(){
        enemies=new ArrayList<>();
    }

    /// check if no enemy in list
    public boolean noEnemy(){
        return enemies.isEmpty();
    }

}


