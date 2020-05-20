package PaooGame.Items;

import PaooGame.Items.Characters.Enemy;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;

public class EntityManager {
    private static final ArrayList<Enemy> enemies = new ArrayList<>();

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

    public static void attack(int damage){
        for (int i=0;i<enemies.size();++i) {
                if (RefLinks.getHeroAttackBound().intersects(enemies.get(i).getBounds())) enemies.get(i).getHit(damage);
        }
    }

}
