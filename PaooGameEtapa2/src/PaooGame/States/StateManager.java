package PaooGame.States;


import PaooGame.GUI.BType;
import PaooGame.RefLinks;

import java.awt.*;

public class StateManager {


    protected  State currentState;
    protected  State lastState;


    public  BType quality= BType.HIGH;
    public  BType difficulty= BType.NORMAL;


    public StateManager (){
        currentState=new MenuState(this);

    }

    public void update(){
       currentState.Update();
    }

    public void draw(Graphics g){
        currentState.Draw(g);
    }

    public void Return(){
        State aux=lastState;
        lastState=currentState;
        currentState=aux;
    }

    public void selectState(BType type){
        lastState=currentState;
        switch (type){
            case MENU:
                currentState=new MenuState(this);break;
            case PLAY:
                currentState=new PlayState(this);break;
            case LOAD:

            case SETTINGS:
                currentState=new SettingsState(this);break;
            case ABOUT:
                currentState=new AboutState(this);break;
            case PAUSE:
                currentState=new PauseState(this);break;
            case Exit:
                RefLinks.GetGame().StopGame();


        }
    }

}
