package PaooGame.States;


import PaooGame.RefLinks;
import PaooGame.__Utils.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class StateManager {

    private ArrayList <State> states;
    public static Vector2D border;
    public static final int PLAY =0;
    public static final int MENU =1;
    public static final int SETTINGS=2;
    public static final int ABOUT=3;


    public StateManager (){
        border =new Vector2D(RefLinks.GetWidth(),RefLinks.GetHeight());
       // Vector2D.setWorldVar(border.x, border.y);


        states=new ArrayList<State>();
        states.add(new PlayState(this));

    }

    public void Pop(int state){
        states.remove(state);
    }
    public void Add(RefLinks refLinks,int state){
        if (state== PLAY){
            states.add(new PlayState( this));
        }
        if (state== MENU){
            states.add(new MenuState( this));
        }
        if (state== SETTINGS){
            states.add(new SettingsState( this));
        }
        if (state== ABOUT){
            states.add(new AboutState( this));
        }

    }
    public void addAndPop(int state){

    }

    public void update(){
     //   Vector2D.setWorldVar(border.x,border.y);
        for (int i =0;i<states.size();++i){
            states.get(i).Update();
        }
    }

    public void draw(Graphics g){
        for (int i =0;i<states.size();++i){
            states.get(i).Draw(g);
        }
    }



}
