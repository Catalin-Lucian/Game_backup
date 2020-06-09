package Worlds_Collide.Items;

import Worlds_Collide.Items.Characters.Enemy;
import Worlds_Collide.Items.Characters.Enemys.Black_ball;
import Worlds_Collide.Items.Characters.Enemys.Boss;
import Worlds_Collide.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EntityManager {
    private static ArrayList<Enemy> enemies = new ArrayList<>();
    private static final Random rd=new Random();


    public void Update(){
       for (int i=0;i<enemies.size();++i) {
           enemies.get(i).Update();
       }
    }
    public void Draw(Graphics g){

        for (int i=0;i<enemies.size();++i) {
            enemies.get(i).Draw(g);
        }
    }

    public static void addEnemy(Enemy e){
        enemies.add(e);
    }

    public static void removeEnemy(Enemy e){
        enemies.remove(e);
    }

    public static void attack(int damage,int direction){
        for (int i=0;i<enemies.size();++i) {
                if (RefLinks.getPlayerAttackBound().intersects(enemies.get(i).getBounds())) enemies.get(i).getHit(damage,direction);
        }
    }

    public static void spawnEnemy(int x,int y){
        addEnemy(getRandomEnemy(x,y));
    }
    public static void spawnBoss(int x,int y){
        addEnemy(new Boss(x,y,5));
    }

    protected static Enemy getRandomEnemy(int x,int y){
        int i= rd.nextInt(2);
        System.out.println(i);
        switch (i){
            case 0: return new Black_ball(x,y,2);
            case 1: return new Black_ball(x,y,2);

            default:return null;
        }
    }

    public static void reset(){
        enemies=new ArrayList<>();
    }


}


